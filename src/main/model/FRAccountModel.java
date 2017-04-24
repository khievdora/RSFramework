package main.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Gize on 4/19/2017.
 */
public interface FRAccountModel {

    public int getCode();

    public void setCode(int code);

    public String getUserName();

    public void setUserName(String userName);

    public String getPassword();

    public void setPassword(String password);

    public String getStatus();

    public void setStatus(String status);

    public String getUserRole();

    public void setUserRole(String userRole);

    public String getAccountStatus();

    public StringProperty accountStatusProperty();

    public void setAccountStatus(String accountStatus);
}