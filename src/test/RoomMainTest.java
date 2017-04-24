package test;

import main.dbsub.IRoom;
import main.dbsub.RoomImpl;
import main.dbsub.RoomTypeImpl;
import main.model.Room;

import java.util.List;

/**
 * Created by Dora on 4/22/2017.
 */
public class RoomMainTest {

    public static void main(String[] args) {
        RoomMainTest main = new RoomMainTest();

        IRoom iRoom = new RoomImpl();

        // Display all Rooms
        List<Room> roomList = iRoom.getAllRoom();
        //main.displayAllRooms(roomList);

        // Add New RoomController
//        RoomController room = new RoomController(
//                0,
//                "B101",
//                101,
//                null,
//                1,
//                null,
//                new RoomTypeImpl().getRoomTypeById(2),
//                5,
//                "ENABLE",
//                75
//        );
//        iRoom.saveRoom(room);

        // Update RoomController
//        RoomController room = iRoom.getRoomById(4);
//        room.setFloor(2);
//        room.setPrice(100);
//        room.setRoomType(new RoomTypeImpl().getRoomTypeById(3));
//        iRoom.updateRoom(room);

        // Delete
        iRoom.deleteRoomById(4);

        // Display all rooms
        roomList = iRoom.getAllRoom();
        main.displayAllRooms(roomList);

    }

    public void displayAllRooms(List<Room> roomList) {
        roomList.forEach(System.out::println);
    }


}
