package main.model;

import javafx.beans.property.*;

/**
 * Created by Gize on 4/19/2017.
 */
public interface FRProductModel {

    public int getCode();

    public void setCode(int code);

    public void setCode(String code);

    public String getRoomName();

    public void setRoomName(String roomName);

    public int getRoomNumber();

    public void setRoomNumber(int roomNumber);

    public String getRoomStatus();

    public void setRoomStatus(String roomStatus);

    public int getFloor();

    public void setFloor(int floor);

    public String getDescription();

    public void setDescription(String description);

    public FRProductTypeModel getRoomType();

    public void setRoomType(FRProductTypeModel FRProductTypeModel);

    public int getMaxQuest();

    public void setMaxQuest(int maxQuest);

    public String getStatus();

    public void setStatus(String status);

    public float getPrice();

    public void setPrice(float price);

}
