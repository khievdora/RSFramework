package main.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import main.ReservationSub.ReservationBusiness;
import main.ReservationSub.command.ReservationSubSystemOperations;
import main.dbsub.DBFacade;
import main.dbsub.DBService;
import main.dbsub.GuestImpl;
import main.dbsub.RoomImpl;
import main.model.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by Gize on 4/23/2017.
 */
public class ReservationController implements Initializable {
    @FXML
    private ComboBox comboGuest;
    @FXML
    private ComboBox comboRoom;
    @FXML
    private ComboBox comboStatus;
    @FXML
    private DatePicker dpCheckIn;
    @FXML
    private DatePicker dpCheckOut;
    @FXML
    private DatePicker dpBooked;
    private DBService dbService;
    private Reservation editedReservation;
    private boolean isEditWindow = false;
    Stage reserveStage;

    public void setStage(Stage stage) {
        this.reserveStage = stage;
    }

    public ReservationController() {
        this.dbService = new DBFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboRoomlist();
        comboGuestlist();
        comboStatusList();
        dpBooked.setValue(LocalDate.now());
        dpCheckIn.setValue(LocalDate.now());
        dpCheckOut.setValue(LocalDate.now());
    }

    List<Room> allRooms = null;
    List<main.model.Guest> allGuests = null;

    //
    public void setEditedReservation(Reservation res) {
        this.editedReservation = res;
        dpCheckIn.setValue(LocalDate.parse(res.getCheckInDate().toString()));
        dpBooked.setValue(LocalDate.parse(res.getBookedDate().toString()));
        dpCheckOut.setValue(LocalDate.parse(res.getCheckOut().toString()));
        comboRoom.setValue(res.getRoom().getRoomNumber());
        comboGuest.setValue(res.getGuest().getfName());
        comboStatus.setValue(res.getRegistrationStatus());

    }

    public void setEditWindow(boolean value) {
        isEditWindow = value;

    }

    public void onBtnReservationAddClicked() {
        saveNew();
    }

    public void comboRoomlist() {

        List<Room> guests = new RoomImpl().getAllRoom();
        allRooms = new ArrayList<>();
        allRooms = guests;
        List<Integer> rooms = guests.stream().map(rm -> rm.getRoomNumber()).collect(Collectors.toList());
        ObservableList<Integer> data2 = FXCollections.observableArrayList();

        for (int id : rooms) {
            data2.add(id);

        }
        comboRoom.setItems(null);
        comboRoom.setItems(data2);


    }

    public void comboGuestlist() {


        List<main.model.Guest> guests = new GuestImpl().getAllGuest();
        allGuests = guests;
        List<String> roomNums = guests.stream().map(rm -> rm.getfName()).collect(Collectors.toList());

        ObservableList<String> data2 = FXCollections.observableArrayList();

        for (String id : roomNums) {
            data2.add(id);

        }
        comboGuest.setItems(null);
        comboGuest.setItems(data2);


    }

    public void comboStatusList() {
        ObservableList<String> options = FXCollections.observableArrayList(

                "CHECKIN", "CONFIRMED", "WAITING");

        comboStatus.setItems(options);
        comboStatus.getSelectionModel().selectFirst();
    }

    public void saveNew() {

        if (comboGuest.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Please select guest!");
            return;
        }
        if (comboRoom.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Please select room!");
            return;
        }
        main.model.Guest guest = this.dbService.getAllGuest().stream().filter(g -> g.getfName().equals(comboGuest.getValue())).findAny().get();
        Room room = this.dbService.getAllRoom().stream().filter(r -> r.getRoomNumber() == Integer.parseInt(comboRoom.getValue().toString())).findAny().get();
        Date checkIN = Date.valueOf(dpCheckIn.getValue());
        Date booked = Date.valueOf(dpBooked.getValue());
        Date checkOut = Date.valueOf(dpCheckOut.getValue());
        String status = (comboStatus.getValue().toString());
        Reservation resObj = new Reservation(1, checkIN, checkOut, booked, guest, room, status);

        ReservationBusiness reservationBusiness= new ReservationBusiness(status,this);
        if (!isEditWindow) {
            reservationBusiness.reserve(resObj);
           // save(resObj);
        } else {
            Edit(resObj);
        }

    }

    private void Edit(Reservation resObj) {
        try {
            ReservationSubSystemOperations impl = new ReservationSubSystemOperations();
            resObj.setCode(editedReservation.getCode());
            boolean isEdited = impl.editReservation(resObj);
            if (isEdited) {
                JOptionPane.showMessageDialog(null, "Edited Successfully.!");
            } else {
                JOptionPane.showMessageDialog(null, "There was an error in editing!");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "There was an error in Editing!");
            // e.printStackTrace();
        }
        this.reserveStage.close();
    }

    public void save(Reservation res) {
        try {
            ReservationSubSystemOperations impl = new ReservationSubSystemOperations();
            boolean isSaved = impl.addReservation(res);
            if (isSaved) {
                JOptionPane.showMessageDialog(null, "Reserved Successfully.!");
            } else {
                JOptionPane.showMessageDialog(null, "There was an error in saving!");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "There was an error in saving!");
            // e.printStackTrace();
        }
        this.reserveStage.close();
    }

    public void makeValidation(Reservation resObj) {
        if (!resObj.getCheckInDate().equals(Date.valueOf(LocalDate.now()))){
            JOptionPane.showMessageDialog(null, "To check In, Check Date should be the same as today's date!");
            return;
        }
        if (resObj.getRoom()==null){
            JOptionPane.showMessageDialog(null, "To check in a guest you must select a room!");
            return;
        }

    }

    public void provideQuarantee() {
        System.out.println("Please provide a quarantine before reserving.");
        return;
    }
}
