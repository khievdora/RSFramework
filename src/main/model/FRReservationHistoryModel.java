package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

/**
 * Created by Gize on 4/19/2017.
 */
public interface FRReservationHistoryModel {

    public String getCode();

    public void setCode(String code);

    public String getReservation();

    public void setReservation(String reservation);

    public String getAccount();

    public void setAccount(String account);

    public String getStatus();

    public void setStatus(String status);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);
}
