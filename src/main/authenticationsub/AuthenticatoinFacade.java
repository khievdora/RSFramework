package main.authenticationsub;

import main.authenticationsub.proxy.AuthenticationProxy;
import main.authenticationsub.proxy.IAuthentication;
import main.model.Account;
import main.accountsub.AccountFacade;
import main.accountsub.AccountService;

/**
 * Created by Dora on 4/19/2017.
 */
public class AuthenticatoinFacade implements AuthenticationService {

    private AuthenticationSubcriber authenticationSubcriber = null;
    private IAuthentication authenticationProxy = null;

    public AuthenticatoinFacade() {
        this.authenticationProxy = new AuthenticationProxy();
    }

    @Override
    public void registerAuthenticationSubscriber(AuthenticationSubcriber authenticationSubcriber) {
        this.authenticationSubcriber = authenticationSubcriber;
    }

    @Override
    public void login(String userName, String password) throws NullPointerException {
        Account account = this.authenticationProxy.requestLogin(userName, password);
        System.out.println("Account = " + account.toString());
        if (this.authenticationSubcriber == null) {
            throw new NullPointerException("There is no authentication subscriber in this form");
        } else {
            if (account == null || account.getCode() == 0) {
                this.authenticationSubcriber.onLoginFail("Username or password is incorrect!!!");
            } else {
                this.authenticationSubcriber.onLoginSuccess(account);
            }
        }
    }

    @Override
    public void forgotPassword() {

    }

    @Override
    public void resetPassword() {

    }
}
