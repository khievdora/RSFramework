package main.dbsub;

import main.model.Room;

import java.util.List;

/**
 * Created by Dora on 4/22/2017.
 */
public interface IRoom {

    public int saveRoom(Room room);
    public int updateRoom(Room room);
    public int deleteRoom(Room room);
    public int deleteRoomById(int roomId);
    public Room getRoomById(int roomId);
    public List<Room> getAllRoom();

}
