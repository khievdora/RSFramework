package main.ReservationSub;

import main.model.Reservation;

/**
 * Created by Gize on 4/19/2017.
 */
public class WaitingReservation implements ReservationService {
    ReservationBusiness reservationBusiness;

    public WaitingReservation(ReservationBusiness reservationBusiness) {
        this.reservationBusiness = reservationBusiness;
    }

    @Override
    public void reserve(Reservation resObj) {
        reservationBusiness.save(resObj);
    }
}
