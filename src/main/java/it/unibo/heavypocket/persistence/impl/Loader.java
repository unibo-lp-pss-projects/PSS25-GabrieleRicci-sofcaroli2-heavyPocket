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

public final class Loader {

    private static final String DATA_PATH = "/persistence/data.json";
    private static final String INPUT_STREAM_ERROR = "Failed to load data from input stream";
    private static final String ACCOUNT_DATA_ERROR = "Failed to save initial account data";
    private static final String TRANSACTION_DATA_ERROR = "Failed to parse transaction data";
    private static final Set<Tag> TAGS = Set.of(TagEnumImpl.values());

    private final InputStream inputStream;

    public Loader(final InputStream inputStream) {
        this.inputStream = Objects.requireNonNull(inputStream, INPUT_STREAM_ERROR);
    }

    public static Account loadData() {
        final InputStream is = Loader.class.getResourceAsStream(DATA_PATH);
        final Budget budget = new BudgetImpl(BigDecimal.ONE);
        if (is == null) {
            final Account account = new AccountImpl(
                    BigDecimal.ZERO,
                    new ArrayList<>(),
                    TAGS,
                    budget);
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

    public Account loadHeavyPocket() {
        final Gson gson = new Gson();
        try (InputStreamReader reader = new InputStreamReader(this.inputStream)) {
            final AccountJsonData data = gson.fromJson(reader, AccountJsonData.class);
            final List<Transaction> transactions = data.transactions().stream()
                    .map(this::createTransaction)
                    .collect(Collectors.toList());
            final Budget budget = new BudgetImpl(data.budget());
            return new AccountImpl(
                    data.balance(),
                    transactions,
                    TAGS,
                    budget);

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
