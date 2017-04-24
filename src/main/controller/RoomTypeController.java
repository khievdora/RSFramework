package main.controller;/**
 * Created by gebre on 4/22/2017.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.dbsub.DBFacade;
import main.dbsub.DBService;
import main.model.RoomType;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomTypeController implements Initializable, IController {

    @FXML
    private Label lblRoomTypeTitle;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtMaxGuest;

    private DBService dbService;

    private Stage roomTypeStage;
    private RoomTypeControllerListener listener;
    private RoomType editedRoomType;
    private boolean isEditWindow = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dbService = new DBFacade();
    }

    @Override
    public void setStage(Stage roomTypeStage) {
        this.roomTypeStage = roomTypeStage;
    }

    public void setEditedRoomType(RoomType roomType) {
        this.editedRoomType = roomType;
        isEditWindow = true;
        lblRoomTypeTitle.setText("RoomType Edit Form");
        txtDescription.setText(this.editedRoomType.getDescription());
        txtMaxGuest.setText(this.editedRoomType.getMaxCapacity() + "");
    }

//    public void setEditWindow(boolean value) {
//        isEditWindow = value;
//        lblRoomTypeTitle.setText("Room Type Edit Form");
//    }

    public void onButtonRoomTypeSaveClicked() throws NullPointerException {
        System.out.println("Button save clicked!!!");

        int code = 0;
        String desc = txtDescription.getText();
        int maxCapacity = Integer.parseInt(txtMaxGuest.getText());

        RoomType roomType = new RoomType(code, desc, maxCapacity);
        int result = 0;
        if (!isEditWindow) {
            // Add new Room Type
            result = this.dbService.saveRoomType(roomType);
            checkOperationResult(result, roomType);
        } else {
            // Update Room Type
            this.editedRoomType.setDescription(desc);
            this.editedRoomType.setMaxCapacity(maxCapacity);
            result = this.dbService.updateRoomType(editedRoomType);
            checkOperationResult(result, roomType);
        }
    }

    public void onButtonRoomTypeCancelClicked() {
        System.out.println("Button save clicked!!!");
        this.roomTypeStage.close();
    }

    public void setRoomTypeControllerListener(RoomTypeControllerListener listener) {
        this.listener = listener;
    }

    private void checkOperationResult(int result, RoomType roomType) {
        if (result != 0) {
            // Save success
            this.roomTypeStage.close();
            if (!isEditWindow) {
                this.listener.onSaveSuccess(roomType);
            } else {
                this.listener.onUpdateSuccess(roomType);
            }
        } else {
            // Save fail
            this.roomTypeStage.close();
            this.listener.onSaveFail("Save room type fail!!!");
        }
    }

    public interface RoomTypeControllerListener {
        public void onSaveSuccess(RoomType roomType);
        public void onUpdateSuccess(RoomType roomType);
        public void onSaveFail(String errMessage);
    }
}
