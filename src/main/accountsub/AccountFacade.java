package main.accountsub;

import main.dbsub.DBFacade;
import main.dbsub.DBService;
import main.model.Account;

import java.util.List;

/**
 * Created by Dora on 4/19/2017.
 */
public class AccountFacade implements AccountService {

    private DBService dbService;

    public AccountFacade() {
        this.dbService = new DBFacade();
    }

    @Override
    public Account getAccount(String userName, String password) {
        return this.dbService.getAccountByUserNameAndPassword(userName, password);
    }

    @Override
    public Account getAccountByUserName(String userName) {
        return this.dbService.getAccountByUserName(userName);
    }

    @Override
    public Account getAccountByUserId(String userId) {
        return this.dbService.getAccountById(userId);
    }

    @Override
    public List<Account> getAllAccount() {
        return this.dbService.getAllAccount();
    }

    @Override
    public int saveAccount(Account account) {
        return this.dbService.saveAccount(account);
    }

    @Override
    public int updateAccount(Account account) {
        return this.dbService.updateAccount(account);
    }

    @Override
    public int deleteAccount(Account account) {
        return this.dbService.deleteAccount(account);
    }

    @Override
    public int deleteAccountById(String accountId) {
        return this.dbService.deleteAccountById(accountId);
    }
}
