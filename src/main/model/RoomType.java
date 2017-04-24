package main.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Gize on 4/19/2017.
 */
public class RoomType {
    private  IntegerProperty code;
    private  StringProperty description;
    private  IntegerProperty maxCapacity;

    public RoomType(int code, String description, Integer maxCapacity) {
        this.code = new SimpleIntegerProperty(code);
        this.description = new SimpleStringProperty(description);
        this.maxCapacity = new SimpleIntegerProperty(maxCapacity);
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

    public void setCode(String code) {
        this.code.set(Integer.parseInt(code));
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getMaxCapacity() {
        return maxCapacity.get();
    }

    public IntegerProperty maxCapacityProperty() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity.set(maxCapacity);
    }

    @Override
    public String toString() {
        return description.getValue() ;
    }
}
