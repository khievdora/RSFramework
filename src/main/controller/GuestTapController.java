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
import main.ReservationSub.command.ReservationSubSystemOperations;
import main.Shared.WindowNavigation;
import main.dbsub.DBFacade;
import main.dbsub.DBService;
import main.model.*;
import main.model.Guest;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by gebre on 4/23/2017.
 */
public class GuestTapController implements Initializable {
    @FXML
    private TableView tblVWGuest;

    @FXML
    private TableColumn<Guest, String> code;
    @FXML
    private TableColumn<Guest, String> firstName;
    @FXML
    private TableColumn<Guest, String> middleName;
    @FXML
    private TableColumn<Guest, String> lastName;
    @FXML
    private TableColumn<Guest, String> idCard;
    @FXML
    private TableColumn<Guest, String> pasport;
    @FXML
    private TableColumn<Guest, String> phone;
    @FXML
    private TextField txtGuestSearch;

    List<Guest> guestList = null;
    private DBService dbService;

    public GuestTapController() {
        this.dbService = new DBFacade();
    }

    public void onTxtGuestSearch() {

    }

    public void onBtnGuestSearchClicked() {

    }

    public void onBtnGuestAddClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resource/view/Guest.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            main.controller.Guest guCtrl = fxmlLoader.<main.controller.Guest>getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            guCtrl.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBtnGuestRefreshClicked() {
        refreshHomeTableView();
    }

    public void onBtnGuestEditClicked() {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../resource/view/Guest.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            main.controller.Guest guCtrl = fxmlLoader.<main.controller.Guest>getController();
            guCtrl.setEditedGuest((Guest) tblVWGuest.getSelectionModel().getSelectedItem());
            guCtrl.setEditWindow(true);
            stage.setScene(new Scene(root1));
            guCtrl.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
      //  roomTypeController.setRoomTypeControllerListener(this);
    }

    public void onBtnGuestDeleteClicked() {

        deleteGuest();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        code = new TableColumn("Id");
        firstName = new TableColumn("First Name");
        middleName = new TableColumn("Middle Name");
        lastName = new TableColumn("Last Name");
        idCard = new TableColumn("ID Card");
        pasport = new TableColumn("Passport");
        phone = new TableColumn("Phone");

        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        middleName.setCellValueFactory(new PropertyValueFactory<>("mName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        idCard.setCellValueFactory(new PropertyValueFactory<>("idCard"));
        pasport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Guest, Number> indexColumn = new TableColumn<Guest, Number>("No.");
        indexColumn.setSortable(false);
        indexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(tblVWGuest.getItems().indexOf(column.getValue())));

        refreshHomeTableView();
        tblVWGuest.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tblVWGuest.getColumns().addAll(indexColumn,code, firstName, middleName, lastName, idCard, pasport, phone);

    }

    public void refreshHomeTableView() {

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        ObservableList<Guest> masterData = (ObservableList<Guest>) parseReservationList();
        FilteredList<Guest> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txtGuestSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getfName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getlName().toLowerCase().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                else if (person.getmName().toLowerCase().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches middle name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Guest> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblVWGuest.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        tblVWGuest.setItems(sortedData);
    }

    private List<Guest> parseReservationList() {

        guestList = this.dbService.getAllGuest();
        List<Guest> modifiedList = new ArrayList<>();

        for (Guest gu : guestList) {

            int code = gu.getCode();
            String fName = gu.getfName();
            String mName = gu.getmName();
            String lName = gu.getlName();
            String iDcard = gu.getIdCard();
            String pass = gu.getPassport();
            String phone = gu.getPhone();
            Guest guest = new Guest(code, fName, mName, lName, iDcard, pass, gu.getAddress(), phone);
            modifiedList.add(guest);
        }
        final ObservableList<Guest> list = FXCollections.observableArrayList();
        list.addAll(modifiedList);
        return list;
    }

    public void deleteGuest() {
        Guest guest = (Guest) tblVWGuest.getSelectionModel().getSelectedItem();
        if (guest != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Confirmation Delete");
            alert.setContentText("Are you sure you want to delete?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                //delete the selected reservation here.
                int isDeleted = this.dbService.deleteGuestById(guest.getCode());
                if (isDeleted != 0) {
                    JOptionPane.showMessageDialog(null, "Successfully Deleted!");

                } else {
                    JOptionPane.showMessageDialog(null, "There is an error in deleting the reservation!");
                }
            } else {

            }


        } else {
            JOptionPane.showMessageDialog(null, "Please first select the row to delete!");
        }
    }
}
