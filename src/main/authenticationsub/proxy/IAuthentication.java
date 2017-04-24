package main.authenticationsub.proxy;

import main.model.Account;

/**
 * Created by Dora on 4/23/2017.
 */
public interface IAuthentication {

    public Account requestLogin(String userName, String password);

}
