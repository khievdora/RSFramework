package main.dbsub;

import main.model.Guest;

import java.util.List;

/**
 * Created by Dora on 4/21/2017.
 */
public interface IGuest {
    public int saveGuest(Guest guest);
    public int updateGuest(Guest guest);
    public int deleteGuestById(int guestId);
    public int deleteAllGuest();
    public Guest getGuestById(int guestId);
    public List<Guest> getAllGuest();

}
