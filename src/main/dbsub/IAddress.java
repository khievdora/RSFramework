package main.dbsub;

import main.model.Address;

import java.util.List;

/**
 * Created by Dora on 4/22/2017.
 */
public interface IAddress {

    public int saveAddress(Address address);
    public int updateAddress(Address address);
    public int deleteAddress(Address address);
    public int deleteAddressById(int addressId);
    public Address getAddressById(int addressId);
    public List<Address> getAllAddress();


}
