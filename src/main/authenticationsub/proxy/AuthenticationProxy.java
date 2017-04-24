package main.authenticationsub.proxy;

import main.model.Account;

/**
 * Created by Dora on 4/23/2017.
 */
public class AuthenticationProxy implements IAuthentication {

    private IAuthentication authentication;

    public AuthenticationProxy() {
        this.authentication = new Authentication();
    }

    @Override
    public Account requestLogin(String userName, String password) {
        return this.authentication.requestLogin(userName, password);
    }
}
