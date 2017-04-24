package main.ReservationSub.command;

import main.dbsub.DBFacade;
import main.dbsub.DBService;
import main.model.Reservation;

/**
 * Created by Gize on 4/22/2017.
 */
public class DeleteReservationCommand implements ReservationCommands {
    DBService dbService = new DBFacade();
    Reservation obj;

    public DeleteReservationCommand(Reservation obj) {
        this.obj = obj;
    }

    @Override
    public boolean executeReservationCommands() {

        return dbService.deleteReservationById(obj.getCode()) != 0 ? true : false;

    }
}
