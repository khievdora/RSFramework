package main.dbsub;

import main.model.Guest;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dora on 4/21/2017.
 */
public class GuestImpl implements IGuest {

    private IDatabase database = Database.getInstance();

    @Override
    public int saveGuest(Guest guest) {
        int guestId = 0;
        try {
            String sql = "INSERT INTO guest (firstName, middleName, lastName, idCard, passport, idAddress, " +
                    "phone) VALUES ('"+ guest.getfName() + "','" + guest.getmName() + "','" +
                    guest.getlName() + "','" + guest.getIdCard() + "','" + guest.getPassport() + "'," +
                    guest.getAddress().getCode() + ",'" + guest.getPhone() + "')";
            guestId = this.database.executeUpdate(sql);
            if (guestId != 0) {
                guest.setCode(guestId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.database.closeConnection();
        }
        return guestId;
    }

    @Override
    public int updateGuest(Guest guest) {
        int result = 0;
        try {
            String sql = "UPDATE guest SET firstName = '" + guest.getfName() + "'," +
                    "middleName = '" + guest.getmName() + "'," +
                    "lastName = '" + guest.getlName() + "'," +
                    "idCard = '" + guest.getIdCard() + "'," +
                    "passport = '" + guest.getPassport() + "'," +
                    "idAddress = " + guest.getAddress().getCode() + "," +
                    "phone = '" + guest.getPhone() + "' WHERE idGuest = " + guest.getCode();
            result = this.database.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.database.closeConnection();
        }
        return result;
    }

    @Override
    public int deleteGuestById(int guestId) {
        int result = 0;
        try {
            String sql = "DELETE FROM guest WHERE idGuest = '" + guestId + "'";
            result = this.database.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.database.closeConnection();
        }
        return result;
    }

    @Override
    public int deleteAllGuest() {
        int result = 0;
        try {
            String sql = "DELETE * FROM guest";
            result = this.database.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.database.closeConnection();
        }
        return result;
    }

    @Override
    public Guest getGuestById(int guestId) {
        Guest guest = null;
        try {
            String sql = "SELECT * FROM guest WHERE idGuest = '" + guestId + "' LIMIT 1";
            ResultSet rs = this.database.executeQuery(sql);
            if (rs.next()) {
                guest = new Guest(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        new AddressImpl().getAddressById(rs.getInt(7)),
                        rs.getString(8));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.database.closeConnection();
        }
        return guest;
    }

    @Override
    public List<Guest> getAllGuest() {
        List<Guest> guestList = new ArrayList<>();
        Guest guest = null;
        try {
            String sql = "SELECT * FROM guest";
            ResultSet rs = this.database.executeQuery(sql);
            while (rs.next()) {

                guest = new Guest(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(3),
                        rs.getString(5),
                        rs.getString(6),
                        new AddressImpl().getAddressById(rs.getInt(7)),
                        rs.getString(8));
                guestList.add(guest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.database.closeConnection();
        }
        return guestList;
    }
}
