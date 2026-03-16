package it.unibo.heavypocket.persistence;

import java.io.IOException;

import it.unibo.heavypocket.mvc.model.Account;

public interface Saver {
    
    void saveAccount(final Account account) throws IOException;
}
