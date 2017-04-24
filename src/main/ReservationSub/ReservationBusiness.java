package main.ReservationSub;

import main.controller.ReservationController;
import main.model.Reservation;

/**
 * Created by Gize on 4/19/2017.
 */
public class ReservationBusiness {
    private ReservationService checkIn;
    private ReservationService waiting;
    private ReservationService confirmed;
    private ReservationService currentStatus;
    private ReservationController reservationTapController;

    public ReservationBusiness(String status, ReservationController tabControl) {
        if (status.equals("CHECKIN")) {
            currentStatus = new CheckInReservation(this);

        } else if (status.equals("CONFIRMED")) {
            currentStatus = new ConfirmedReservation(this);

        } else {
            currentStatus = new WaitingReservation(this);

        }
        this.reservationTapController = tabControl;
    }

    public void reserve(Reservation obj) {
        currentStatus.reserve(obj);

    }

    public void validate(Reservation obj) {
        //TODO: call controller method that validates
        this.reservationTapController.makeValidation(obj);

    }

    public void provideQuantee() {
        //TODO: call controller method that provides quarantee
        this.reservationTapController.provideQuarantee();

    }

    public void save(Reservation obj) {
        //TODO: call controller method that saves to the database
        this.reservationTapController.save(obj);

    }

    public ReservationService getCurrentStatus() {
        return currentStatus;
    }

    public ReservationService getCheckIn() {
        return checkIn;
    }

    public ReservationService getWaiting() {
        return waiting;
    }

    public ReservationService getConfirmed() {
        return confirmed;
    }
}
