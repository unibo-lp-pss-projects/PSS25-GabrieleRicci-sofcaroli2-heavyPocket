package it.unibo.heavypocket.persistence;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import java.util.ArrayList;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.impl.TransactionImpl;
import it.unibo.heavypocket.mvc.model.impl.TagEnumImpl;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.impl.AccountImpl;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Budget;
import it.unibo.heavypocket.mvc.model.impl.BudgetImpl;
import it.unibo.heavypocket.persistence.impl.SaverImpl;
import it.unibo.heavypocket.persistence.Saver;

public final class HeavyPocketLoader {

    private static final String DATA_PATH = "/persistence/data.json";

    private final InputStream inputStream;

    public HeavyPocketLoader(final InputStream inputStream) {
        this.inputStream = Objects.requireNonNull(inputStream, "Input stream cannot be null");
    }

    public static Account loadData() {
        final InputStream is = HeavyPocketLoader.class.getResourceAsStream(DATA_PATH);
        final Budget budget = new BudgetImpl(BigDecimal.ONE);
        if (is == null) {
            final Account account = new AccountImpl(
                    BigDecimal.ZERO,
                    new ArrayList<>(),
                    Set.of(TagEnumImpl.values()),
                    budget);
            final Saver saver = new SaverImpl();
            try {
                saver.saveAccount(account);
            } catch (final Exception e) {
                throw new RuntimeException("Failed to save initial account data");
            }
            return account;
        }
        return new HeavyPocketLoader(is).loadHeavyPocket();
    }

    public Account loadHeavyPocket() {

        final Gson gson = new Gson();

        final AccountJsonData data = gson.fromJson(new InputStreamReader(this.inputStream),
                AccountJsonData.class);

        final List<Transaction> transactions = data.transactions().stream()
                .map(t -> createTransaction(t))
                .collect(Collectors.toList());
        final Budget budget = new BudgetImpl(data.budget());
        return new AccountImpl(
                data.balance(),
                transactions,
                Set.of(TagEnumImpl.values()),
                budget);
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

    // @TODO: cavare da una delle due parti i record e dove posizionarli
    private record AccountJsonData(
            List<TransactionJsonData> transactions,
            BigDecimal balance,
            BigDecimal budget) {
    }

    private record TransactionJsonData(
            String id,
            BigDecimal amount,
            String date,
            String description,
            TransactionType type,
            String tag) {
    }
}
