package main.model;

import javafx.beans.property.*;

/**
 * Created by Gize on 4/19/2017.
 */
public class Guest {
    private IntegerProperty code;
    private  StringProperty fName;
    private  StringProperty mName;
    private  StringProperty lName;
    private  StringProperty idCard;
    private  StringProperty passport;
    private  ObjectProperty<Address> address;
    private  StringProperty phone;

    public Guest(int code, String fName, String mName, String lName, String idCard, String passport, Address address, String phone) {
        this.code = new SimpleIntegerProperty(code);
        this.fName = new SimpleStringProperty(fName);
        this.mName = new SimpleStringProperty(mName);
        this.lName = new SimpleStringProperty(lName);
        this.idCard =new SimpleStringProperty (idCard);
        this.passport =new SimpleStringProperty(passport);
        this.address = new SimpleObjectProperty<>(address);
        this.phone = new SimpleStringProperty(phone);
    }

    public int getCode() {
        return code.get();
    }

    public IntegerProperty codeProperty() {
        return code;
    }

    public void setCode(int code) {
        this.code.set(code);
    }

    public String getfName() {
        return fName.get();
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public String getmName() {
        return mName.get();
    }

    public StringProperty mNameProperty() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName.set(mName);
    }

    public String getlName() {
        return lName.get();
    }

    public StringProperty lNameProperty() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName.set(lName);
    }

    public String getIdCard() {
        return idCard.get();
    }

    public StringProperty idCardProperty() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard.set(idCard);
    }

    public String getPassport() {
        return passport.get();
    }

    public StringProperty passportProperty() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport.set(passport);
    }

    public Address getAddress() {
        return address.get();
    }

    public ObjectProperty<Address> addressProperty() {
        return address;
    }

    public void setAddress(Address address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    @Override
    public String toString() {
        return fName.getValue()+" "+lName.getValue();
    }
}
