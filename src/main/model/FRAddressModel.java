package main.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Gize on 4/19/2017.
 */
public interface FRAddressModel {

    public int getCode();

    public void setCode(int code);

    public String getZip();

    public void setZip(String zip);

    public String getStreet();

    public void setStreet(String street);

    public String getCity();

    public void setCity(String city);

    public String getState();

    public void setState(String state);

    public String getCountry();

    public void setCountry(String country);

}
