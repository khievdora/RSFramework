package main.model;

import javafx.beans.property.*;

import java.sql.Date;

/**
 * Created by Gize on 4/19/2017.
 */
public interface FRReservationModel {

    public int getCode();

    public void setCode(int code);

    public Date getCheckInDate();

    public void setCheckInDate(Date checkInDate);

    public Date getCheckOut();

    public void setCheckOut(Date checkOut);

    public Date getBookedDate();

    public void setBookedDate(Date bookedDate);

    public FRCustomerModel getGuest();

    public void setGuest(FRCustomerModel FRCustomerModel);

    public FRProductModel getRoom();

    public void setRoom(FRProductModel FRProductModel);

    public String getRegistrationStatus();

    public void setRegistrationStatus(String registrationStatus);
}
