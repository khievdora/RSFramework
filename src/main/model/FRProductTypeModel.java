package main.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Gize on 4/19/2017.
 */
public interface FRProductTypeModel {

    public int getCode();

    public void setCode(int code);

    public void setCode(String code);

    public String getDescription();

    public void setDescription(String description);

    public int getMaxCapacity();

    public void setMaxCapacity(int maxCapacity);
}
