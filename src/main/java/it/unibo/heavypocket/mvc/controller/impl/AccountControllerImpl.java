package it.unibo.heavypocket.mvc.controller.impl;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Comparator;
import java.util.List;

import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.view.AccountView;

// @TODO ordinamento transazioni
public final class AccountControllerImpl implements AccountController {

    private static final String ERROR_CRUD = "Transaction not found";
    private static final String ERROR_AMOUNT = "Amount must be greater than zero";
    private static final String ERROR_FIELDS = "Please fill in all fields";
    private static final String ERROR_AMOUNT_FORMAT = "Invalid amount format";

    private final Account model;
    private final AccountView view;

    public AccountControllerImpl(final Account model, final AccountView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        showTransactions();
        showTags();
    }

    // @Override
    // public List<Transaction> getTransactions() {
    // return model.getTransactions();
    // }

    @Override
    public void showTransactions() {
        final List<Transaction> transactions = model.getTransactions().stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .toList();
        view.showTransactionList(transactions);
    }

    @Override
    public void showTags() {
        final List<Tag> tags = model.getTags().stream().toList();
        view.showTagList(tags);
    }

    @Override
    public BigDecimal getTotalBalance() {
        return model.getTotalBalance();
    }

    // @TODO il balance può andare in negativo?
    // @TODO c'è una lunghezza massima nella descrizione?
    // @TODO c'è un valore massimo per l'importo?
    @Override
    public void addTransaction(final TransactionDTO transactionDTO) {
        try {
            validateTransactionDTO(transactionDTO);
            final Transaction transaction = Transaction.builder()
                    .withId(UUID.randomUUID())
                    .withAmount(validateAmount(transactionDTO.amount()))
                    .withDate(transactionDTO.date())
                    .withDescription(transactionDTO.description())
                    .withType(transactionDTO.type())
                    .withTag(transactionDTO.tag())
                    .build();
            model.addTransaction(transaction);
            showTransactions();
        } catch (final IllegalArgumentException | NullPointerException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void callToEditTransaction(final UUID id) {
        model.getTransactionById(id).ifPresentOrElse(
                transaction -> {
                    final TransactionDTO transactionDTO = new TransactionDTO(
                            String.valueOf(transaction.getAmount()),
                            transaction.getDate(),
                            transaction.getDescription(),
                            transaction.getType(),
                            transaction.getTag());
                    view.showEditTransaction(id, transactionDTO);
                },
                () -> view.showError(ERROR_CRUD));
    }

    // @TODO
    @Override
    public void editTransaction(final UUID id, final TransactionDTO transactionDTO) {
        try {
            validateTransactionDTO(transactionDTO);
            final Transaction transaction = Transaction.builder()
                    .withId(id)
                    .withAmount(validateAmount(transactionDTO.amount()))
                    .withDate(transactionDTO.date())
                    .withDescription(transactionDTO.description())
                    .withType(transactionDTO.type())
                    .withTag(transactionDTO.tag())
                    .build();
            model.editTransaction(id, transaction);
            showTransactions();
        } catch (final IllegalArgumentException | NullPointerException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void deleteTransaction(final UUID id) {
        model.getTransactionById(id).ifPresentOrElse(
                model::deleteTransaction,
                () -> view.showError(ERROR_CRUD));
        showTransactions();
    }

    @Override
    public void search(final FiltersDTO filters) {
        if (filters == null) {
            showTransactions();
            return;
        }
        final List<Transaction> filteredTransactions = model.getTransactions().stream()
                .filter(t -> filters.type() == null || model.searchByType(filters.type()).contains(t))
                .filter(t -> filters.date() == null || model.searchByDate(filters.date()).contains(t))
                .filter(t -> filters.tag() == null || model.searchByTag(filters.tag()).contains(t))
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .toList();
        view.showTransactionList(filteredTransactions);
    }

    // @TODO capire se spostare la validazione in una classe apposita
    private void validateTransactionDTO(final TransactionDTO transactionDTO) {
        if (transactionDTO == null
                || transactionDTO.type() == null
                || transactionDTO.amount() == null
                || transactionDTO.amount().isBlank()
                || transactionDTO.date() == null
                || transactionDTO.description() == null
                || transactionDTO.description().isBlank()
                || transactionDTO.tag() == null) {
            throw new IllegalArgumentException(ERROR_FIELDS);
        }
    }

    // @TODO capire se spostare la validazione in una classe apposita
    private BigDecimal validateAmount(final String amountString) {
        final String amount = amountString.trim().replace(',', '.');
        final BigDecimal finalAmount;
        try {
            finalAmount = new BigDecimal(amount);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_AMOUNT_FORMAT);
        }
        if (finalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ERROR_AMOUNT);
        }
        return finalAmount;
    }
}
