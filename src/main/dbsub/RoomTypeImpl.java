package main.dbsub;

import main.model.RoomType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dora on 4/22/2017.
 */
public class RoomTypeImpl implements IRoomType {

    private IDatabase iDatabase;

    public RoomTypeImpl() {
        this.iDatabase = Database.getInstance();
    }

    @Override
    public int saveRoomType(RoomType roomType) {
        int result = 0;
        try {
            String sql = "INSERT INTO roomtype (description, maxCapacity, status) VALUES (" +
                    "'"+ roomType.getDescription() +"'," +
                    ""+ roomType.getMaxCapacity() +"," +
                    "'ENABLE'" +
                    ")";
            result = this.iDatabase.executeUpdate(sql);
            if (result != 0) {
                roomType.setCode(result);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateRoomType(RoomType roomType) {
        int result = 0;

        try {
            String sql = "UPDATE roomtype SET " +
                    "description = '"+ roomType.getDescription() +"'," +
                    "maxCapacity = "+ roomType.getMaxCapacity() + " WHERE idRoomType = " + roomType.getCode();
            result = this.iDatabase.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteRoomType(RoomType roomType) {
        int result = 0;
        try {
            String sql = "DELETE FROM roomtype WHERE idRoomType = " + roomType.getCode();
            result = this.iDatabase.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteRoomTypeById(int roomTypeId) {
        int result = 0;
        try {
            String sql = "DELETE FROM roomtype WHERE idRoomType = " + roomTypeId;
            result = this.iDatabase.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public RoomType getRoomTypeById(int roomTypeId) {
        RoomType roomType = null;
        try {
            String sql = "SELECT * FROM roomtype WHERE idRoomType = " + roomTypeId + " LIMIT 1";
            ResultSet rs = this.iDatabase.executeQuery(sql);
            if (rs.next()) {
                roomType = new RoomType(
                        rs.getInt("idRoomType"),
                        rs.getString("description"),
                        rs.getInt("maxCapacity")
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return roomType;
    }

    @Override
    public List<RoomType> getAllRoomType() {
        List<RoomType> roomTypeList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM roomtype";
            ResultSet rs = this.iDatabase.executeQuery(sql);
            while (rs.next()) {
                RoomType roomType = new RoomType(
                        rs.getInt("idRoomType"),
                        rs.getString("description"),
                        rs.getInt("maxCapacity")
                );
                roomTypeList.add(roomType);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return roomTypeList;
    }
}
