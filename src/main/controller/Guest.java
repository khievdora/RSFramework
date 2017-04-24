package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.ReservationSub.command.ReservationSubSystemOperations;
import main.dbsub.DBFacade;
import main.dbsub.DBService;
import main.model.Address;
import main.model.Reservation;
import main.model.Room;
import main.model.RoomType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by Gize on 4/19/2017.
 */
public class Guest implements Initializable {
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtMiddleName;
    @FXML
    private TextField txtIdCard;
    @FXML
    private TextField txtPassport;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtZIP;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtState;

    private DBService dbService;
    private Stage roomTypeStage;
    private RoomTypeController.RoomTypeControllerListener listener;
    private main.model.Guest editedGuest;
    private boolean isEditWindow = false;
    Stage guestStage;

    public void setStage(Stage stage) {
        this.guestStage = stage;
    }

    public Guest() {
        this.dbService = new DBFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onBtnReservationAddClicked() {
        saveNew();
    }

    public void setEditedGuest(main.model.Guest gu) {
        this.editedGuest = gu;
        txtFirstName.setText(gu.getfName());
        txtMiddleName.setText(gu.getmName());
        txtLastName.setText(gu.getlName());
        txtIdCard.setText(gu.getIdCard());
        txtPassport.setText(gu.getPassport());
        txtPhone.setText(gu.getPhone());
        //
        txtZIP.setText(gu.getAddress().getZip());
        txtStreet.setText(gu.getAddress().getStreet());
        txtCity.setText(gu.getAddress().getCity());
        txtState.setText(gu.getAddress().getState());
        txtCountry.setText(gu.getAddress().getCountry());
    }

    public void setEditWindow(boolean value) {
        isEditWindow = value;

    }

    public void saveNew() {

        //Guest
        String fName = txtFirstName.getText();
        String mName = txtMiddleName.getText();
        String lName = txtLastName.getText();
        String iDcard = txtIdCard.getText();
        String pass = txtPassport.getText();
        String phone = txtPhone.getText();
        //Address
        String zip = txtZIP.getText();
        String city = txtCity.getText();
        String state = txtState.getText();
        String street = txtStreet.getText();
        String country = txtCountry.getText();
        if (lName.isEmpty() || fName.isEmpty() || zip.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Last name, First name and zip can not be empty.!");
            return;
        }

        Address address = new Address(1, zip, street, city, state, country);
        if (!isEditWindow) {
            int add = this.dbService.saveAddress(address);
            if (add != 0) {
                main.model.Guest guest = new main.model.Guest(1, fName, mName, lName, iDcard, pass, address, phone);
                try {

                    int isSaved = this.dbService.saveGuest(guest);
                    if (isSaved != 0) {
                        JOptionPane.showMessageDialog(null, "Saved Successfully.!");
                    } else {
                        JOptionPane.showMessageDialog(null, "There was an error in saving!");
                    }
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "There was an error in saving!");
                    // e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "There is error in saving address!");
            }
        } else {
            address.setCode(editedGuest.getAddress().getCode());
            int add = this.dbService.updateAddress(address);
            if (add != 0) {
                main.model.Guest guest = new main.model.Guest(editedGuest.getCode(), fName, mName, lName, iDcard, pass, address, phone);
                try {
                    int isSaved = this.dbService.updateGuest(guest);
                    if (isSaved != 0) {
                        JOptionPane.showMessageDialog(null, "Edited Successfully.!");
                    } else {
                        JOptionPane.showMessageDialog(null, "There was an error in editing!");
                    }
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "There was an error in Editing!");
                    // e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "There is error in editing address!");
            }

        }
        this.guestStage.close();
    }


}
