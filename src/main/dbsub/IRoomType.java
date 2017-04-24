package main.dbsub;

import main.model.RoomType;

import java.util.List;

/**
 * Created by Dora on 4/22/2017.
 */
public interface IRoomType {

    public int saveRoomType(RoomType roomType);
    public int updateRoomType(RoomType roomType);
    public int deleteRoomType(RoomType roomType);
    public int deleteRoomTypeById(int roomTypeId);
    public RoomType getRoomTypeById(int roomTypeId);
    public List<RoomType> getAllRoomType();


}
