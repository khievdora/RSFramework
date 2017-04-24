package test;

import main.dbsub.IRoomType;
import main.dbsub.RoomTypeImpl;
import main.model.RoomType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dora on 4/22/2017.
 */
public class RoomTypeMainTest {

    public static void main(String[] args) {

        RoomTypeMainTest mainTest = new RoomTypeMainTest();

        IRoomType iRoomType = new RoomTypeImpl();

        // Add new RoomTypeTapController
        //RoomTypeTapController roomType = new RoomTypeTapController(0, "QUEEN ROOM", 5);
        RoomType roomType = iRoomType.getRoomTypeById(4);
        //iRoomType.saveRoomType(roomType);
        System.out.println("RoomTypeTapController = " + roomType.toString());

        // Delete RoomController type
        //iRoomType.deleteRoomTypeById(8);

        // Update RoomTypeTapController
        roomType.setMaxCapacity(5);
        iRoomType.updateRoomType(roomType);

        //Load List of RoomTypeTapController
        List<RoomType> roomTypeList = new ArrayList<>();
        roomTypeList = iRoomType.getAllRoomType();
        mainTest.displayAllRoomType(roomTypeList);

    }

    public void displayAllRoomType(List<RoomType> roomTypeList) {
        System.out.println("-------------------------------");
        System.out.println("RoomController Type List");
        System.out.println("-------------------------------");
        for (int i = 0; i < roomTypeList.size(); i++) {
            System.out.println(roomTypeList.get(i).toString());
        }
    }

}
