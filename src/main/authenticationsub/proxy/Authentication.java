package main.authenticationsub.proxy;

import main.accountsub.AccountFacade;
import main.accountsub.AccountService;
import main.model.Account;

/**
 * Created by Dora on 4/23/2017.
 */
public class Authentication implements IAuthentication {

    @Override
    public Account requestLogin(String userName, String password) {
        AccountService accountService = new AccountFacade();
        return accountService.getAccount(userName, password);
    }
}
