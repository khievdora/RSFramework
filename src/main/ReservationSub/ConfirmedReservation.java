package main.ReservationSub;

import main.model.Reservation;

/**
 * Created by Gize on 4/19/2017.
 */
public class ConfirmedReservation implements ReservationService {
    ReservationBusiness reservationBusiness;

    public ConfirmedReservation(ReservationBusiness reservationBusiness) {
        this.reservationBusiness = reservationBusiness;
    }

    @Override
    public void reserve(Reservation resObj) {

        reservationBusiness.provideQuantee();
        reservationBusiness.save(resObj);
    }
}
