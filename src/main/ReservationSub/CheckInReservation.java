package main.ReservationSub;

import main.model.Reservation;

/**
 * Created by Gize on 4/19/2017.
 */
public class CheckInReservation implements ReservationService {
    ReservationBusiness reservationBusiness;

    public CheckInReservation(ReservationBusiness reservationBusiness) {
        this.reservationBusiness = reservationBusiness;
    }

    @Override
    public void reserve(Reservation resObj) {
        reservationBusiness.validate(resObj);
        reservationBusiness.save(resObj);
    }
}
