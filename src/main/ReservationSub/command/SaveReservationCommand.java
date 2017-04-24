package main.ReservationSub.command;

import main.dbsub.DBFacade;
import main.dbsub.DBService;
import main.model.Reservation;

/**
 * Created by Gize on 4/22/2017.
 */
public class SaveReservationCommand implements ReservationCommands {
    DBService dbService = new DBFacade();
    Reservation obj;

    public SaveReservationCommand(Reservation reservation) {
        this.obj=reservation;
    }

    @Override
    public boolean executeReservationCommands() {
      return  dbService.saveReservation(obj) != 0 ? true : false;
    }
}
