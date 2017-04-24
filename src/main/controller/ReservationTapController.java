package main.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.ReservationSub.command.ReservationSubSystemOperations;
import main.Shared.UrlLoader;
import main.dbsub.DBFacade;
import main.dbsub.DBService;
import main.model.*;
import main.model.Guest;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by gebre on 4/22/2017.
 */
public class ReservationTapController implements Initializable {
    @FXML
    private TableView tblVWReservation;

    @FXML
    private TableColumn<Reservation, String> code;
    @FXML
    private TableColumn<Reservation, Room> room;
    @FXML
    private TableColumn<Reservation, Guest> guest;
    @FXML
    private TableColumn<Reservation, Date> checkInDate;
    @FXML
    private TableColumn<Reservation, Date> bookedDate;
    @FXML
    private TableColumn<Reservation, Date> checkOutDate;
    @FXML
    private TableColumn<Reservation, String> status;
    @FXML
    private TextField txtReservationSearch;

    List<Reservation> regList = null;
    private DBService dbService;

    public ReservationTapController() {
        this.dbService = new DBFacade();
    }

    public void onTxtReservationSearch() {

    }

    //
    public void onBtnReservationSearch() {

    }

    //
    public void onBtnReservationAddClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resource/view/addReservation.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            ReservationController reserveCtrl = fxmlLoader.<ReservationController>getController();
            stage.setScene(new Scene(root1));
            reserveCtrl.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public void onBtnReservationRefreshClicked() {
        refreshHomeTableView();
    }

    //
    public void onBtnReservationCheckOutClicked() {
        checkOut();
    }

    //
    public void onBtnReservationCancelClicked() {
        cancelReservation();
    }

    //
    public void onBtnReservationDeleteClicked() {
        deleteReservation();
    }
    public void onBtnReservationEditClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resource/view/addReservation.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            ReservationController reserveCtrl = fxmlLoader.<ReservationController>getController();
            reserveCtrl.setEditedReservation((Reservation) tblVWReservation.getSelectionModel().getSelectedItem());
            reserveCtrl.setEditWindow(true);
            stage.setScene(new Scene(root1));
            reserveCtrl.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        code = new TableColumn("Id");
        room = new TableColumn("Room");
        guest = new TableColumn("Guest");
        checkInDate = new TableColumn("CheckIn Date");
        bookedDate = new TableColumn("Booked Date");
        checkOutDate = new TableColumn("Check Out Date");
        status = new TableColumn("Status");

        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        guest.setCellValueFactory(new PropertyValueFactory<>("guest"));
        checkInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        bookedDate.setCellValueFactory(new PropertyValueFactory<>("bookedDate"));
        checkOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        status.setCellValueFactory(new PropertyValueFactory<>("registrationStatus"));

        TableColumn<Guest, Number> indexColumn = new TableColumn<Guest, Number>("No.");
        indexColumn.setSortable(false);
        indexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(tblVWReservation.getItems().indexOf(column.getValue())));

        refreshHomeTableView();
        tblVWReservation.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        tblVWReservation.getColumns().addAll(indexColumn,code, room, guest, checkInDate, bookedDate, checkOutDate, status);

    }

    public void refreshHomeTableView() {
        //tblVWReservation.refresh();
        //  tblVWReservation.setItems(null);
        regList = null;
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        ObservableList<Reservation> masterData = (ObservableList<Reservation>) parseReservationList();
        FilteredList<Reservation> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txtReservationSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getGuest().getfName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getGuest().getlName().toLowerCase().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Reservation> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblVWReservation.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        tblVWReservation.setItems(sortedData);
    }

    private List<Reservation> parseReservationList() {

        regList = this.dbService.getAllReservation();
        List<Reservation> modifiedList = new ArrayList<>();

        for (Reservation reg : regList) {

            int code = reg.getCode();
            Room room = reg.getRoom();
            Guest guest = reg.getGuest();
            Date checkIn = reg.getCheckInDate();
            Date booked = reg.getBookedDate();
            Date checkOut = reg.getCheckOut();
            String status = reg.getRegistrationStatus();
            Reservation acc = new Reservation(code, checkIn, booked, checkOut, guest, room, status);
            modifiedList.add(acc);
        }
        final ObservableList<Reservation> list = FXCollections.observableArrayList();
        list.addAll(modifiedList);
        return list;
    }

    public void checkOut() {
        Reservation reservation = (Reservation) tblVWReservation.getSelectionModel().getSelectedItem();
        if (reservation != null) {
            if (reservation.getRegistrationStatus().equals("CHECKIN")) {

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resource/view/Payment.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    Payment payment = fxmlLoader.<Payment>getController();
                    payment.setReservation(reservation, stage);
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                JOptionPane.showMessageDialog(null, "You can not check out un checked In reservation!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please first select the row to check out!");
        }

    }

    public void cancelReservation() {
        Reservation person = (Reservation) tblVWReservation.getSelectionModel().getSelectedItem();
        if (person != null) {
            if (person.getRegistrationStatus() != "CANCELLED") {
                // Guest gu = person.getGuest();
                // Room rm = person.getRoom();
                // person.setGuest((Guest) allGuests.stream().filter(r -> r.getfName().equals(gu.getfName())));
                //  person.setRoom((Room) allRooms.stream().filter(r -> r.getRoomNumber() == rm.getRoomNumber()));
                person.setRegistrationStatus("CANCELLED");
                //update the registration here
                try {
                    Boolean isUpdated = new ReservationSubSystemOperations().editReservation(person);

                    if (isUpdated) {
                        JOptionPane.showMessageDialog(null, "Reservation cancelled successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "There is an error in cancelling this reservation!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "This reservation is already cancelled!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please first select the row to cancel!");
        }
    }

    public void deleteReservation() {
        Reservation reservation = (Reservation) tblVWReservation.getSelectionModel().getSelectedItem();
        if (reservation != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Confirmation Delete");
            alert.setContentText("Are you sure you want to delete?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                //delete the selected reservation here.


                boolean isDeleted = new ReservationSubSystemOperations().deleteReservationById(reservation);
//                if (isDeleted) {
//                boolean isDeleted = new ReservationImpl().deleteReservationById(reservation.getCode().toString());
                //   int isDeleted = this.dbService.deleteReservationById(reservation.getCode());
                if (isDeleted) {
                    JOptionPane.showMessageDialog(null, "Successfully Deleted!");

                } else {
                    JOptionPane.showMessageDialog(null, "There is an error in deleting the reservation!");
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }


        } else {
            JOptionPane.showMessageDialog(null, "Please first select the row to delete!");
        }
    }

}
