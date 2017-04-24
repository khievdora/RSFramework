package main.dbsub;

import main.model.Account;

import java.util.List;

/**
 * Created by Dora on 4/21/2017.
 */
public interface IAccount {
    // CRUD User
    public int saveAccount(Account account);
    public int updateAccount(Account account);
    public int deleteAccount(Account account);
    public int deleteAccountById(String accountId);
    public Account getAccountById(String accountId);
    public Account getAccountByUserName(String userName);
    public Account getAccountByUserNameAndPassword(String userName, String password);
    public List<Account> getAccountByFirstName(String firstName);
    public List<Account> getAccountByLastName(String lastName);
    public List<Account> getAllAccount();

}
