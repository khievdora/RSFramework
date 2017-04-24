package test;

import main.dbsub.AddressImpl;
import main.dbsub.IAddress;
import main.model.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dora on 4/22/2017.
 */
public class AddressMainTest {

    public static void main(String[] args) {

        AddressMainTest main = new AddressMainTest();

        IAddress iAddress = new AddressImpl();

        // List all address
        List<Address> addressList = new ArrayList<>();
        addressList = iAddress.getAllAddress();
        main.displayAllAddresses(addressList);

        // Add address
//        Address address = new Address(0, "52558", "12 K Kerkwood St", "Fairfield", "IOWA",
//                "USA");
//        iAddress.saveAddress(address);

        // Update address
//        Address updateAddress = iAddress.getAddressById(3);
//        updateAddress.setState("TEXAS");
//        updateAddress.setZip("53443");
//        iAddress.updateAddress(updateAddress);
//        System.out.println(updateAddress.toString());

        // Delete Address
        iAddress.deleteAddressById(3);
        addressList = iAddress.getAllAddress();
        main.displayAllAddresses(addressList);

    }

    public void displayAllAddresses(List<Address> addressList) {
        System.out.println("-------------------------------");
        System.out.println("Address List");
        System.out.println("-------------------------------");
        addressList.forEach(System.out::println);
    }
}
