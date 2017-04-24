package main.model;

import javafx.beans.property.*;

/**
 * Created by Gize on 4/19/2017.
 */
public interface FRCustomerModel {

    public int getCode();

    public void setCode(int code);

    public String getfName();

    public void setfName(String fName);

    public String getmName();

    public void setmName(String mName);

    public String getlName();

    public void setlName(String lName);

    public String getIdCard();

    public void setIdCard(String idCard);

    public String getPassport();

    public void setPassport(String passport);

    public FRAddressModel getAddress();

    public void setAddress(FRAddressModel FRAddressModel);

    public String getPhone();

    public void setPhone(String phone);
}
