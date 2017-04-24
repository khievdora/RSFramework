package main.dbsub;

import main.model.Address;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dora on 4/22/2017.
 */
public class AddressImpl implements IAddress {

    private IDatabase iDatabase;

    public AddressImpl() {
        this.iDatabase = Database.getInstance();
    }

    @Override
    public int saveAddress(Address address) {
        int addressId = 0;
        try {
            String sql = "INSERT INTO address (zip, street, city, state, country) VALUES (" +
                    "'"+ address.getZip() +"'," +
                    "'"+ address.getStreet() +"'," +
                    "'"+ address.getCity() +"'," +
                    "'"+ address.getState() +"'," +
                    "'"+ address.getCountry() +"'" +
                    ")";
            addressId = this.iDatabase.executeUpdate(sql);
            if (addressId != 0) {
                address.setCode(addressId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return addressId;
    }

    @Override
    public int updateAddress(Address address) {
        int result = 0;
        try {
            String sql = "UPDATE address SET " +
                    "zip = '"+ address.getZip() +"'," +
                    "street = '"+ address.getStreet() +"'," +
                    "city = '"+ address.getCity() +"'," +
                    "state = '"+ address.getState() +"'," +
                    "country = '"+ address.getCountry() +"' " +
                    "WHERE idAddress = " + address.getCode();
            result = this.iDatabase.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteAddress(Address address) {
        int result = 0;
        try {
            String sql = "DELETE FROM address WHERE idAddress = " + address.getCode();
            result = this.iDatabase.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteAddressById(int addressId) {
        int result = 0;
        try {
            String sql = "DELETE FROM address WHERE idAddress = " + addressId;
            result = this.iDatabase.executeUpdate(sql);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }

    @Override
    public Address getAddressById(int addressId) {
        Address address = null;
        try {
            String sql = "SELECT * FROM address WHERE idAddress = " + addressId + " LIMIT 1";
            ResultSet rs = this.iDatabase.executeQuery(sql);
            if (rs.next()) {
                address = new Address(
                        rs.getInt("idAddress"),
                        rs.getString("zip"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return address;
    }

    @Override
    public List<Address> getAllAddress() {
        List<Address> addressList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM address";
            ResultSet rs = this.iDatabase.executeQuery(sql);
            while (rs.next()) {
                Address address = new Address(
                        rs.getInt("idAddress"),
                        rs.getString("zip"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country")
                );
                addressList.add(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addressList;
    }
}
