package main.authenticationsub;

/**
 * Created by Dora on 4/19/2017.
 */
public interface AuthenticationService {

    public void registerAuthenticationSubscriber(AuthenticationSubcriber authenticationSubcriber);
    public void login(String userName, String password);
    public void forgotPassword();
    public void resetPassword();

}
