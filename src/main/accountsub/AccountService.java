package main.accountsub;

import main.model.Account;

import java.util.List;

/**
 * Created by Dora on 4/19/2017.
 */
public interface AccountService {

    public Account getAccount(String userName, String password);
    public Account getAccountByUserName(String userName);
    public Account getAccountByUserId(String userId);
    public List<Account> getAllAccount();
    public int saveAccount(Account account);
    public int updateAccount(Account account);
    public int deleteAccount(Account account);
    public int deleteAccountById(String accountId);

}
