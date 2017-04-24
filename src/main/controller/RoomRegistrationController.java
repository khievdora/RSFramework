package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.dbsub.DBFacade;
import main.dbsub.DBService;
import main.dbsub.RoomImpl;
import main.dbsub.RoomTypeImpl;
import main.model.Room;
import main.model.RoomType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by gebre on 4/23/2017.
 */
public class RoomRegistrationController implements Initializable, IController {
    private RoomControllerListener listener;

    @FXML
    private Label lblRoomEditForm;
    @FXML
    private TextField txtRoomCode;
    @FXML
    private TextField txtRoomName;
    @FXML
    private TextField txtRoomNumber;
    @FXML
    private TextField txtRoomStatus;
    @FXML
    private Spinner<Integer> spnrRoomFloor;
    @FXML
    private TextField txtDescription;
    @FXML
    private Spinner spnrRoomMaxCapacity;
    @FXML
    private TextField txtStatus;
    @FXML
    private TextField txtRoomPrice;

    @FXML
    private Button btnRoomCancel;
    @FXML
    private Button btnRoomSave;

    @FXML
    private ComboBox cbRoomRegistration;
    private List<RoomType> lstRooms = null;

    private Stage roomStage;
    private DBService dbService;
    private Room editedRoom;
    private boolean isEditWindow = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dbService = new DBFacade();
        spnrRoomFloor = new Spinner(0, 120, 30);
        comboRoomTypelist();
    }

    @Override
    public void setStage(Stage stage) {
        this.roomStage = stage;

    }

    public void setEditedRoomType(Room room) {
        this.editedRoom = room;

        txtRoomCode.setText(String.valueOf(this.editedRoom.getCode()));
        txtRoomName.setText(this.editedRoom.getRoomName());
        txtRoomNumber.setText(String.valueOf(this.editedRoom.getRoomNumber()));
        txtRoomStatus.setText(this.editedRoom.getRoomStatus());
        spnrRoomFloor.getValueFactory().setValue(this.editedRoom.getFloor());
        cbRoomRegistration.setValue(room.getRoomType().getDescription());
        txtDescription.setText(this.editedRoom.getDescription());
        spnrRoomMaxCapacity.getValueFactory().setValue(this.editedRoom.getMaxQuest());
        txtRoomPrice.setText(String.valueOf(this.editedRoom.getPrice()));
        txtStatus.setText(this.editedRoom.getStatus());

    }

    public void setEditWindow(boolean value) {
        isEditWindow = value;
        lblRoomEditForm.setText("Room Edit Form");
    }

    public void onBtnRoomSaveClicked() {
        System.out.println("Button save clicked!!!");
//        final Spinner<Integer> spinner = new Spinner<Integer>();
//        final int initialValue = 3;
//        SpinnerValueFactory<Integer> valueFactory =
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);
//        SpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
//
//        spinner.setValueFactory(valueFactory);

        int code = Integer.parseInt(txtRoomCode.getText());
        String rName = txtRoomName.getText();
        int rNumber = Integer.parseInt(txtRoomNumber.getText());
        String rStatus = txtRoomStatus.getText();

        int rFloor = spnrRoomFloor.getValueFactory().getValue();
        String desc = txtDescription.getText();

        String selected_txt = (String) cbRoomRegistration.getSelectionModel().getSelectedItem();
        int maxCapacity = (int) spnrRoomMaxCapacity.getValueFactory().getValue();
        float rPrice = Float.parseFloat(txtRoomPrice.getText());
        String status = txtStatus.getText();

        RoomType roomType=this.dbService.getAllRoomType().stream().filter(r->r.getDescription().equals(selected_txt)).findAny().get();

        Room room = new Room(code, rName, rNumber, rStatus, rFloor, desc,roomType, maxCapacity, status, rPrice);
        int result = 0;
        if (!isEditWindow) {
            // Add new Room Type
            result = this.dbService.saveRoom(room);
            checkOperationResult(result, room);
        } else {
            // Update Room Type
            this.editedRoom.setCode(code);
            this.editedRoom.setRoomName(rName);
            this.editedRoom.setRoomNumber(rNumber);
            this.editedRoom.setFloor(rFloor);
            this.editedRoom.setRoomStatus(rStatus);
            this.editedRoom.setDescription(desc);
            this.editedRoom.setRoomType(null);
            this.editedRoom.setMaxQuest(maxCapacity);
            this.editedRoom.setStatus(status);
            this.editedRoom.setPrice(rPrice);
            result = this.dbService.updateRoom(editedRoom);
            checkOperationResult(result, room);
        }
    }

    private void checkOperationResult(int result, Room room) {
        if (result != 0) {
            // Save success
            this.roomStage.close();
            if (!isEditWindow) {
                this.listener.onSaveRoomSuccess(room);
            } else {
                this.listener.onUpdateRoomSuccess(room);
            }
        } else {
            // Save fail
            this.roomStage.close();
            this.listener.onSaveRoomFail("Save room type fail!!!");
        }
    }

    public void setRoomControllerListener( RoomControllerListener listener){
        this.listener=listener;

    }
    public void onBtnRoomCancelClicked(){
        System.out.println("Button Cancel clicked!!");
        this.roomStage.close();
    }



    public interface RoomControllerListener{
        public void onSaveRoomSuccess(Room room);
        public void onUpdateRoomSuccess(Room room);
        public void onSaveRoomFail(String errMessage);

    }
    public void comboRoomTypelist() {

        List<RoomType> roomTypes = new RoomTypeImpl().getAllRoomType();
        lstRooms = new ArrayList<>();
        lstRooms = roomTypes;
        List<String> rooms = roomTypes.stream().map(rm -> rm.getDescription()).collect(Collectors.toList());
        ObservableList<String> roomTypesData = FXCollections.observableArrayList();

        for (String id : rooms) {
            roomTypesData.add(id);
            System.out.println("Id = "+id);

        }
        cbRoomRegistration.setItems(null);
        cbRoomRegistration.setItems(roomTypesData);


    }

}
