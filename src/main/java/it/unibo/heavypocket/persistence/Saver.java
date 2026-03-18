package it.unibo.heavypocket.persistence;

import java.io.IOException;

import it.unibo.heavypocket.mvc.model.Account;

/**
 * Service responsible for persisting account state.
 */
public interface Saver {

    /**
     * Saves the provided account state.
     * 
     * @param account the account to persist
     * @throws IOException if an I/O error occurs while saving
     */
    void saveAccount(Account account) throws IOException;
}
