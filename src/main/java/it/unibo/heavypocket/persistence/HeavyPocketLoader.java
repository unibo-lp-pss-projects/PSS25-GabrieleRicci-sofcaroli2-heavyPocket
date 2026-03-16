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

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.impl.TransactionImpl;
import it.unibo.heavypocket.mvc.model.impl.TagEnumImpl;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.impl.AccountImpl;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Tag;

//@TODO: mettere il builder?

public final class HeavyPocketLoader {

        private static final String DATA_PATH = "/persistence/data.json";

        private final InputStream inputStream;

        public HeavyPocketLoader(final InputStream inputStream) {
                this.inputStream = Objects.requireNonNull(inputStream, "Input stream cannot be null");
        }

        public static Account loadData() {

                final InputStream is = HeavyPocketLoader.class.getResourceAsStream(DATA_PATH);

                if (is == null) {
                        System.err.println("Cannot find data.json");
                        return new AccountImpl(
                                        Collections.emptyList(),
                                        BigDecimal.ZERO,
                                        BigDecimal.ZERO,
                                        BigDecimal.ZERO,
                                        Collections.emptySet());
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

                return new AccountImpl(
                                transactions,
                                data.balance(),
                                data.budget(),
                                data.savingTarget(),
                                Set.of(TagEnumImpl.values()));
        }

        private Transaction createTransaction(final TransactionJsonData data) {
                return new TransactionImpl(
                                UUID.fromString(data.id()),
                                data.amount(),
                                LocalDate.parse(data.date()),
                                data.description(),
                                data.type(),
                                TagEnumImpl.valueOf(data.tag()));
        }

        //@TODO: cavare da una delle due parti i record e dove posizionareli
        private record AccountJsonData(
                        List<TransactionJsonData> transactions,
                        BigDecimal balance,
                        BigDecimal budget,
                        BigDecimal savingTarget) {
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
