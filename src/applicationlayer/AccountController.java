package applicationlayer;

import datalayer.DAOAccount;
import domain.Account;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * This class is responsible for the logic of accounts behind the user interface.
 */
public class AccountController {

    /**
     * Singleton pattern.
     */
    private static AccountController instance;

    /**
     * Saves the given account to the datasource.
     * @param account
     */
    public void save(Account account) {
        throw new NotImplementedException();
    }

    /**
     * Gets an account with the given name from the datasource.
     * @param name
     * @return account
     */
    public Account read(String name) {
        return DAOAccount.getInstance().read(name);
    }

    /**
     * Gets all accounts from the datasource
     * @return all accounts
     */
    public Set<Account> readAllAccounts() {
        return DAOAccount.getInstance().readAll();
    }

    /**
     * Gets all accounts from the datasource with a single profile
     * @return all accounts with single profile
     */
    public Set<Account> readAllSingleProfileAccounts() {
        return DAOAccount.getInstance().readAllSingleProfileAccounts();
    }

    /**
     * Removes the account from the datasource.
     * @param account
     */
    public void deleteAccount(Account account) {
        throw new NotImplementedException();
    }

    public static AccountController getInstance() {
        if(instance == null) {
            instance = new AccountController();
        }

        return instance;
    }
}
