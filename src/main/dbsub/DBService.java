package main.dbsub;

import main.model.*;

import java.util.List;

/**
 * Created by Dora on 4/19/2017.
 */
public interface DBService {
    // CRUD User
    public int saveAccount(Account account);
    public int updateAccount(Account account);
    public int deleteAccount(Account account);
    public int deleteAccountById(String accountId);
    public Account getAccountById(String accountId);
    public Account getAccountByUserName(String userName);
    public Account getAccountByUserNameAndPassword(String userName, String password);
    public List<Account> getAccountByFirstName(String firstName);
    public List<Account> getAccountByLastName(String lastName);
    public List<Account> getAllAccount();

    //CRUD Reservation
    public int saveReservation(Reservation reservation);
    public int updateReservation(Reservation reservation);
    public List<Reservation> getAllReservation();
    public Reservation getReservatinById(int idReservation);
    public int deleteAllReservation();
    public int deleteReservationById(int idReservation);

    //CRUD Guest
    public int saveGuest(Guest guest);
    public int updateGuest(Guest guest);
    public int deleteGuestById(int guestId);
    public int deleteAllGuest();
    public Guest getGuestById(int guestId);
    public List<Guest> getAllGuest();

    // CRUD Room Type
    public int saveRoomType(RoomType roomType);
    public int updateRoomType(RoomType roomType);
    public int deleteRoomType(RoomType roomType);
    public int deleteRoomTypeById(int roomTypeId);
    public RoomType getRoomTypeById(int roomTypeId);
    public List<RoomType> getAllRoomType();

    // CRUD Room
    public int saveRoom(Room room);
    public int updateRoom(Room room);
    public int deleteRoom(Room room);
    public int deleteRoomById(int roomId);
    public Room getRoomById(int roomId);
    public List<Room> getAllRoom();

    //CRUD Address
    public int saveAddress(Address address);
    public int updateAddress(Address address);
    public int deleteAddress(Address address);
    public int deleteAddressById(int addressId);
    public Address getAddressById(int addressId);
    public List<Address> getAllAddress();


}
