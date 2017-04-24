package main.dbsub;

import main.model.Reservation;

import java.util.List;

/**
 * Created by gebre on 4/21/2017.
 */
public interface IReservation {
    public int saveReservation(Reservation reservation);
    public int updateReservation(Reservation reservation);
    public List<Reservation> getAllReservation();
    public Reservation getReservatinById(int idReservation);
    public int deleteAllReservation();
    public int deleteReservationById(int idReservation);
}
