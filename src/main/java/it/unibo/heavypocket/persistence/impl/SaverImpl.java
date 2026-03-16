package it.unibo.heavypocket.persistence.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;

import java.math.BigDecimal;

import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.persistence.AccountJsonData;
import it.unibo.heavypocket.persistence.Saver;
import it.unibo.heavypocket.persistence.TransactionJsonData;

public final class SaverImpl implements Saver {

    private static final String FILE_PATH = "src/main/resources/persistence/data.json";

    private final Gson gson;

    public SaverImpl() {
        this.gson = new Gson();
    }

    @Override
    public void saveAccount(final Account account) throws IOException {
        final AccountJsonData data = toJsonData(account);
        saveData(data);
    }

    public void saveData(final AccountJsonData data) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            this.gson.toJson(data, writer);
        }
    }

    private AccountJsonData toJsonData(final Account account) {
        final List<TransactionJsonData> transactions = account.getTransactions().stream()
                .map(this::toJsonData)
                .toList();
        return new AccountJsonData(
                transactions,
                account.getTotalBalance(),
                account.getBudget().getLimit(),
                BigDecimal.ZERO);
        // account.getSavingTarget()
    }

    private TransactionJsonData toJsonData(final Transaction transaction) {
        return new TransactionJsonData(
                transaction.getId().toString(),
                transaction.getAmount(),
                transaction.getDate().toString(),
                transaction.getDescription(),
                transaction.getType(),
                transaction.getTag().getName());
    }
}
