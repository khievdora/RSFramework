package main.dbsub;
import main.model.Reservation;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gebre on 4/21/2017.
 */
public class ReservationImpl implements IReservation {
    private IDatabase iDatabase = null;

    public ReservationImpl() {
        iDatabase = Database.getInstance();
    }
    @Override
    public int saveReservation(Reservation reservation) {
        int reservationId = 0;
        try{

            String query = "INSERT INTO reservation(checkInDate, CheckOutDate, bookedDate, idGuest, idRoom,reservationStatus) " +
                    "VALUES(" +
                    "'"+reservation.getCheckInDate()+"'," +
                    "'"+reservation.getCheckOut()+"'," +
                    "'"+reservation.getBookedDate()+"'," +
                    ""+reservation.getGuest().getCode()+"," +
                    ""+reservation.getRoom().getCode()+"," +
                    "'"+reservation.getRegistrationStatus()+"')";
            reservationId = iDatabase.executeUpdate(query);
            reservation.setCode(reservationId);
            if (reservationId != 0) {
                reservation.setCode(reservationId);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return reservationId;
    }

    @Override
    public int updateReservation(Reservation reservation) {
        int result = 0;
        try{

            String query ="UPDATE reservation SET " +
                    "checkInDate='"+reservation.getCheckInDate()+"', " +
                    "CheckOutDate='"+reservation.getCheckOut()+"'," +
                    "bookedDate='"+reservation.getBookedDate()+"', " +
                    "idGuest="+reservation.getGuest().getCode()+"," +
                    "idRoom="+reservation.getRoom().getCode()+"," +
                    "reservationStatus= '"+reservation.getRegistrationStatus()+"'" +
                    " WHERE idReservation="+reservation.getCode()+"";
            System.out.println(query);
            result = iDatabase.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }

    @Override
    public int deleteReservationById(int idReservation) {
        int result = 0;
        try{
            String query = "DELETE FROM reservation WHERE idReservation="+idReservation;
            result = iDatabase.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }

    @Override
    public int deleteAllReservation() {
        int result = 0;
        try{
            String query = "DELETE FROM reservation";
            result = iDatabase.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }
    @Override
    public List<Reservation> getAllReservation() {
        String query = "SELECT * FROM reservation";
        List<Reservation> reservations = new ArrayList<Reservation>();
        try{
            ResultSet rs = iDatabase.executeQuery(query);
            while (rs.next()){
                Reservation reservation=new Reservation();
                reservation.setCode(rs.getInt("idReservation"));
                reservation.setCheckInDate(rs.getDate(2));
                reservation.setCheckOut(rs.getDate(3));
                reservation.setBookedDate(rs.getDate(4));
//                reservation.setCheckInDate(rs.getDate(2));
//                reservation.setCheckOut(rs.getDate(3));
//                reservation.setBookedDate(rs.getDate(4));
                reservation.setGuest(new GuestImpl().getGuestById(rs.getInt(5)));
                reservation.setRoom(new RoomImpl().getRoomById(rs.getInt(6)));
                reservation.setRegistrationStatus(rs.getString(7));

                reservations.add(reservation);
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return reservations;
    }

    @Override
    public Reservation getReservatinById(int idReservation) {
        String query = "SELECT * FROM reservation WHERE idReservation="+idReservation;
        Reservation reservation=new Reservation();
        try{
            ResultSet rs = iDatabase.executeQuery(query);
            while (rs.next()){
                reservation.setCode(rs.getInt(1));
                reservation.setCheckInDate(rs.getDate(2));
                reservation.setCheckOut(rs.getDate(3));
                reservation.setBookedDate(rs.getDate(4));
                reservation.setGuest(new GuestImpl().getGuestById(rs.getInt(5)));
                reservation.setRoom(new RoomImpl().getRoomById(rs.getInt(6)));
                reservation.setRegistrationStatus(rs.getString(7));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return reservation;
    }

}
