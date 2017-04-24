package main.dbsub;

import main.model.Room;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dora on 4/22/2017.
 */
public class RoomImpl implements IRoom {

    private IDatabase iDatabase;

    public RoomImpl() {
        iDatabase = Database.getInstance();
    }

    @Override
    public int saveRoom(Room room) {
        int roomId = 0;
        try {
            String sql = "INSERT INTO room (roomName, roomNumber, roomStatus, floor, description, idRoomType, maxGuest, " +
                    "status, price) VALUES (" +
                    "'"+ room.getRoomName() +"'," +
                    "'"+ room.getRoomNumber() +"'," +
                    "'"+ room.getRoomStatus() +"'," +
                    "'"+ room.getFloor() +"'," +
                    "'"+ room.getDescription() +"'," +
                    ""+ room.getRoomType().getCode() +"," +
                    "'"+ room.getMaxQuest() +"'," +
                    "'"+ room.getStatus() +"'," +
                    room.getPrice() +
                    ")";
            roomId = this.iDatabase.executeUpdate(sql);
            if (roomId != 0) {
                room.setCode(roomId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return roomId;
    }

    @Override
    public int updateRoom(Room room) {
        int result = 0;
        try {
            String sql = "UPDATE room SET " +
                    "roomName = '"+ room.getRoomName() +"'," +
                    "roomNumber = '"+ room.getRoomNumber() +"'," +
                    "roomStatus = '"+ room.getRoomStatus() +"'," +
                    "floor = "+ room.getFloor() +"," +
                    "description = '"+ room.getDescription() +"'," +
                    "idRoomType = "+ room.getRoomType().getCode() +"," +
                    "maxGuest = "+ room.getMaxQuest() +"," +
                    "status = '"+ room.getStatus() +"'," +
                    "price = "+ room.getPrice() +" " +
                    "WHERE idRoom = " + room.getCode();
            result = this.iDatabase.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }

    @Override
    public int deleteRoom(Room room) {
        int result = 0;
        try {
            String sql = "DELETE FROM room WHERE idRoom = " + room.getCode();
            result = this.iDatabase.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }

    @Override
    public int deleteRoomById(int roomId) {
        int result = 0;
        try {
            String sql = "DELETE FROM room WHERE idRoom = " + roomId;
            result = this.iDatabase.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }

    @Override
    public Room getRoomById(int roomId) {
        Room room = null;
        try {
            String sql = "SELECT * FROM room WHERE idRoom = " + roomId + " LIMIT 1";
            ResultSet rs = this.iDatabase.executeQuery(sql);
            if (rs.next()) {
                room = new Room(
                    rs.getInt("idRoom"),
                    rs.getString("roomName"),
                    rs.getInt("roomNumber"),
                    rs.getString("roomStatus"),
                    rs.getInt("floor"),
                    rs.getString("description"),
                    new RoomTypeImpl().getRoomTypeById(rs.getInt("idRoomType")),
                    rs.getInt("maxGuest"),
                    rs.getString("status"),
                    rs.getFloat("price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return room;
    }

    @Override
    public List<Room> getAllRoom() {
        List<Room> roomList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM room";
            ResultSet rs = this.iDatabase.executeQuery(sql);
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("idRoom"),
                        rs.getString("roomName"),
                        rs.getInt("roomNumber"),
                        rs.getString("roomStatus"),
                        rs.getInt("floor"),
                        rs.getString("description"),
                        new RoomTypeImpl().getRoomTypeById(rs.getInt("idRoomType")),
                        rs.getInt("maxGuest"),
                        rs.getString("status"),
                        rs.getFloat("price")
                );
                roomList.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return roomList;
    }
}
