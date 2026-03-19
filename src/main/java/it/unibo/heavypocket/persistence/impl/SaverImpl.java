package it.unibo.heavypocket.persistence.impl;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;

import it.unibo.heavypocket.persistence.Saver;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.persistence.AccountJsonData;
import it.unibo.heavypocket.persistence.TransactionJsonData;

/**
 * Implementation of Saver.
 */
public final class SaverImpl implements Saver {

    private static final String FILE_PATH = "src/main/resources/persistence/data.json";

    private final Gson gson;

    /**
     * Creates a saver using a default Gson serializer.
     */
    public SaverImpl() {
        gson = new Gson();
    }

    /**
     * Saves the account by converting it into a serializable payload.
     * 
     * @param account the account to persist
     * @throws IOException if writing to persistence fails
     */
    @Override
    public void saveAccount(final Account account) throws IOException {
        final AccountJsonData data = toJsonData(account);
        saveData(data);
    }

    /**
     * Writes the provided payload to the persistence file.
     * 
     * @param data serialized account payload
     * @throws IOException if writing to persistence fails
     */
    public void saveData(final AccountJsonData data) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(data, writer);
        }
    }

    private AccountJsonData toJsonData(final Account account) {
        final List<TransactionJsonData> transactions = account.getTransactions().stream()
                .map(this::toJsonData)
                .toList();
        return new AccountJsonData(
                transactions,
                account.getBudget().getLimit());
    }

    private TransactionJsonData toJsonData(final Transaction transaction) {
        return new TransactionJsonData(
                transaction.getId().toString(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getDate().toString(),
                transaction.getDescription(),
                transaction.getTag().getName());
    }
}
