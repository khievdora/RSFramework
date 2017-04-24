package main.ReservationSub.command;

import main.dbsub.ReservationImpl;
import main.model.Reservation;

import java.util.Stack;

/**
 * Created by Gize on 4/22/2017.
 */
public class ReservationSubSystemOperations {
    private ReservationCommands currentCommand = null;
    private Stack<ReservationCommands> executedCommands = new Stack<>();

    public boolean addReservation(Reservation res) {
        currentCommand = new SaveReservationCommand(res);
        if (currentCommand.executeReservationCommands()) {
            executedCommands.push(currentCommand);
            return true;
        }
        return false;
    }

    public boolean deleteReservationById(Reservation res) {
        currentCommand = new DeleteReservationCommand(res);
        if (currentCommand.executeReservationCommands()) {
            executedCommands.push(currentCommand);
            return true;
        }
        return false;
    }

    public boolean editReservation(Reservation res) {
        currentCommand = new EditReservationCommand(res);
        if (currentCommand.executeReservationCommands()) {
            executedCommands.push(currentCommand);
            return true;
        }
        return false;
    }
}
