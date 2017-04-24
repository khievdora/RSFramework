package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

/**
 * Created by Gize on 4/19/2017.
 */
public class ReservationHistory {
    private StringProperty code;
    private StringProperty reservation;
    private StringProperty account;
    private StringProperty status;
    private Date modifiedDate;

    public ReservationHistory(String code, String reservation, String account, String status, Date modifiedDate) {
        this.code = new SimpleStringProperty(code);
        this.reservation = new SimpleStringProperty(reservation);
        this.account = new SimpleStringProperty(account);
        this.status = new SimpleStringProperty(status);
        this.modifiedDate = modifiedDate;
    }

    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getReservation() {
        return reservation.get();
    }

    public StringProperty reservationProperty() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation.set(reservation);
    }

    public String getAccount() {
        return account.get();
    }

    public StringProperty accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
