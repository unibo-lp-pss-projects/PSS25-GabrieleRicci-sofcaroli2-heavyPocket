package it.unibo.heavypocket.persistence.impl;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;

import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.impl.AccountImpl;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.impl.TagEnumImpl;
import it.unibo.heavypocket.mvc.model.Budget;
import it.unibo.heavypocket.mvc.model.impl.BudgetImpl;
import it.unibo.heavypocket.persistence.AccountJsonData;
import it.unibo.heavypocket.persistence.TransactionJsonData;
import it.unibo.heavypocket.persistence.Saver;
import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.impl.StatisticsImpl;

/**
 * Loads account state from JSON persistence.
 */
public final class Loader {

    private static final String DATA_PATH = "/persistence/data.json";
    private static final String INPUT_STREAM_ERROR = "Failed to load data from input stream";
    private static final String ACCOUNT_DATA_ERROR = "Failed to save initial account data";
    private static final String TRANSACTION_DATA_ERROR = "Failed to parse transaction data";
    private static final Set<Tag> TAGS = Set.of(TagEnumImpl.values());

    private final InputStream inputStream;

    /**
     * Creates a loader reading from the provided stream.
     * 
     * @param inputStream source stream containing account JSON data
     * @throws NullPointerException if inputStream is null
     */
    public Loader(final InputStream inputStream) {
        this.inputStream = Objects.requireNonNull(inputStream, INPUT_STREAM_ERROR);
    }

    /**
     * Loads account data from the default persistence path.
     * If no file is found, it creates and persists a default account.
     * 
     * @return the loaded account instance
     * @throws RuntimeException if default account initialization cannot be
     *                          persisted
     */
    public static Account loadData() {
        final InputStream is = Loader.class.getResourceAsStream(DATA_PATH);
        if (is == null) {
            final Budget budget = new BudgetImpl(BigDecimal.ONE);
            final Statistics statistics = new StatisticsImpl();
            final Account account = new AccountImpl(
                    new ArrayList<>(),
                    TAGS,
                    budget,
                    statistics);
            final Saver saver = new SaverImpl();
            try {
                saver.saveAccount(account);
            } catch (final IOException e) {
                throw new RuntimeException(ACCOUNT_DATA_ERROR, e);
            }
            return account;
        }
        return new Loader(is).loadHeavyPocket();
    }

    /**
     * Deserializes account data from this loader input stream.
     * 
     * @return the reconstructed account
     * @throws UncheckedIOException if the stream cannot be read
     */
    public Account loadHeavyPocket() {
        final Gson gson = new Gson();
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            final AccountJsonData data = gson.fromJson(reader, AccountJsonData.class);
            if (data == null) {
                throw new UncheckedIOException(ACCOUNT_DATA_ERROR, new IOException(ACCOUNT_DATA_ERROR));
            }
            final List<Transaction> transactions = data.transactions().stream()
                    .map(this::createTransaction)
                    .collect(Collectors.toList());
            final Budget budget = new BudgetImpl(data.budget());
            final Statistics statistics = new StatisticsImpl();
            return new AccountImpl(
                    transactions,
                    TAGS,
                    budget,
                    statistics);

        } catch (final IOException e) {
            throw new UncheckedIOException(TRANSACTION_DATA_ERROR, e);
        }
    }

    private Transaction createTransaction(final TransactionJsonData data) {
        return Transaction.builder()
                .withId(UUID.fromString(data.id()))
                .withAmount(data.amount())
                .withDate(LocalDate.parse(data.date()))
                .withDescription(data.description())
                .withType(data.type())
                .withTag(TagEnumImpl.valueOf(data.tag()))
                .build();
    }
}
