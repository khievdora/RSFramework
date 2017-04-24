package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Gize on 4/19/2017.
 */
public class Package {
    private StringProperty code;
    private StringProperty name;
    private StringProperty price;
    private StringProperty reservation;

    public Package(String code, String name, String price, String reservation) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.reservation = new SimpleStringProperty(reservation);
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
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
}
