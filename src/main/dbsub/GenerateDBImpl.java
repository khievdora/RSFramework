package main.dbsub;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dora on 4/21/2017.
 */
public class GenerateDBImpl implements IGenerateDB {

    private final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS " + Database.DATABASE_NAME;

    private final String[] generateTableScripts = new String[]{
            "CREATE TABLE IF NOT EXISTS account (" +
                    "idAccount INT NOT NULL AUTO_INCREMENT," +
                    "username VARCHAR(50) NOT NULL," +
                    "password VARCHAR(50) NOT NULL," +
                    "status VARCHAR(20) NOT NULL," +
                    "userRole VARCHAR(50) NOT NULL," +
                    "accountStatus VARCHAR(50) NOT NULL," +
                    "PRIMARY KEY (idAccount)" +
                    ")Engine=InnoDB",

            "CREATE TABLE IF NOT EXISTS address (" +
                    "idAddress INT NOT NULL AUTO_INCREMENT," +
                    "zip VARCHAR(10) NULL," +
                    "street VARCHAR(255) NULL," +
                    "city VARCHAR(100) NULL," +
                    "state VARCHAR(100) NULL," +
                    "country VARCHAR(100) NULL," +
                    "PRIMARY KEY (idAddress)" +
                    ")Engine=InnoDB",

            "CREATE TABLE IF NOT EXISTS employee (" +
                    "idEmployee INT NOT NULL AUTO_INCREMENT," +
                    "firstName VARCHAR(100) NULL," +
                    "middleName VARCHAR(100) NULL," +
                    "lastName VARCHAR(100) NULL," +
                    "email VARCHAR(100) NULL," +
                    "DOB DATE NULL," +
                    "SSN VARCHAR(50) NULL," +
                    "idAddress INT NULL," +
                    "phone1 VARCHAR(20) NULL," +
                    "phone2 VARCHAR(20) NULL," +
                    "status VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY (idEmployee)," +
                    "FOREIGN KEY (idAddress) REFERENCES address(idAddress)" +
                    "   ON DELETE SET NULL" +
                    "   ON UPDATE CASCADE" +
                    ")Engine=InnoDB",

            "CREATE TABLE IF NOT EXISTS roomtype (" +
                    "idRoomType INT NOT NULL AUTO_INCREMENT," +
                    "description VARCHAR(255) NULL," +
                    "maxCapacity INT NULL," +
                    "status VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY (idRoomType)" +
                    ")Engine=InnoDB",

            "CREATE TABLE IF NOT EXISTS room (" +
                    "idRoom INT NOT NULL AUTO_INCREMENT," +
                    "roomName VARCHAR(100) NULL," +
                    "roomNumber VARCHAR(20) NULL," +
                    "roomStatus VARCHAR(20) NULL," +
                    "floor INT NOT NULL," +
                    "description VARCHAR(255) NULL," +
                    "idRoomType INT NULL," +
                    "maxGuest INT NULL," +
                    "status VARCHAR(20) NOT NULL," +
                    "price FLOAT NULL," +
                    "PRIMARY KEY (idRoom)," +
                    "FOREIGN KEY (idRoomType) REFERENCES roomtype(idRoomType)" +
                    "   ON DELETE SET NULL" +
                    "   ON UPDATE CASCADE" +
                    ")Engine=InnoDB",

            "CREATE TABLE IF NOT EXISTS facility (" +
                    "idFacility INT NOT NULL AUTO_INCREMENT," +
                    "description VARCHAR(255) NULL," +
                    "quantity INT NULL," +
                    "status VARCHAR(20) NOT NULL," +
                    "idRoom INT NULL," +
                    "PRIMARY KEY (idFacility)," +
                    "FOREIGN KEY (idRoom) REFERENCES room(idRoom)" +
                    "   ON DELETE SET NULL" +
                    "   ON UPDATE CASCADE" +
                    ")Engine=InnoDB",

            "CREATE TABLE IF NOT EXISTS guest (" +
                    "idGuest INT NOT NULL AUTO_INCREMENT," +
                    "firstName VARCHAR(100) NULL," +
                    "middleName VARCHAR(100) NULL," +
                    "lastName VARCHAR(100) NULL," +
                    "idCard VARCHAR(50) NULL," +
                    "passport VARCHAR(50) NULL," +
                    "idAddress INT NULL," +
                    "phone VARCHAR(20) NULL," +
                    "PRIMARY KEY (idGuest)," +
                    "FOREIGN KEY (idAddress) REFERENCES address(idAddress)" +
                    "   ON DELETE SET NULL" +
                    "   ON UPDATE CASCADE" +
                    ")Engine=InnoDB",

            "CREATE TABLE IF NOT EXISTS reservation (" +
                    "idReservation INT NOT NULL AUTO_INCREMENT," +
                    "checkInDate DATE NOT NULL," +
                    "checkOutDate DATE NOT NULL," +
                    "bookedDate DATE NOT NULL," +
                    "idGuest INT NULL," +
                    "idRoom INT NULL," +
                    "reservationStatus VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY (idReservation)," +
                    "FOREIGN KEY (idGuest) REFERENCES guest(idGuest)" +
                    "   ON DELETE SET NULL" +
                    "   ON UPDATE CASCADE," +
                    "FOREIGN KEY (idRoom) REFERENCES room(idRoom)" +
                    "   ON DELETE SET NULL" +
                    "   ON UPDATE CASCADE"+
                    ")Engine=InnoDB",

            "CREATE TABLE IF NOT EXISTS reservationhistory (" +
                    "idReservationHistory INT NOT NULL AUTO_INCREMENT," +
                    "idReservation INT NULL," +
                    "idAccount INT NULL," +
                    "actionStatus VARCHAR(20) NOT NULL," +
                    "modifiedState VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY (idReservationHistory)," +
                    "FOREIGN KEY (idReservation) REFERENCES reservation(idReservation)" +
                    "   ON DELETE SET NULL" +
                    "   ON UPDATE CASCADE," +
                    "FOREIGN KEY (idAccount) REFERENCES account(idAccount)" +
                    "   ON DELETE SET NULL" +
                    "   ON UPDATE CASCADE" +
                    ")Engine=InnoDB",

            "CREATE TABLE IF NOT EXISTS package (" +
                    "idPackage INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(100) NULL," +
                    "price FLOAT NULL," +
                    "idReservation INT NULL," +
                    "PRIMARY KEY (idPackage)," +
                    "FOREIGN KEY (idReservation) REFERENCES reservation(idReservation)" +
                    "   ON DELETE SET NULL" +
                    "   ON UPDATE CASCADE" +
                    ")Engine=InnoDB",
    };

    private final String[] generateTestDataScripts = new String[] {
            "INSERT INTO account (username, password, status, userRole, accountStatus) VALUES (" +
                    "'admin'," +
                    "'admin'," +
                    "'ENABLE'," +
                    "'ADMIN'," +
                    "'ACTIVE'" +
                    ")",
            "INSERT INTO account (username, password, status, userRole, accountStatus) VALUES (" +
                    "'manager'," +
                    "'manager'," +
                    "'ENABLE'," +
                    "'MANAGER'," +
                    "'ACTIVE'" +
                    ")",
            "INSERT INTO account (username, password, status, userRole, accountStatus) VALUES (" +
                    "'user'," +
                    "'user'," +
                    "'ENABLE'," +
                    "'USER'," +
                    "'ACTIVE'" +
                    ")",

            "INSERT INTO address (zip, street, city, state, country) VALUES (" +
                    "'52556'," +
                    "'53 N Maple St'," +
                    "'Fairfield'," +
                    "'IOWA'," +
                    "'USA'" +
                    ")",
            "INSERT INTO address (zip, street, city, state, country) VALUES (" +
                    "'52557'," +
                    "'1000 N FOURTH ST'," +
                    "'Fairfield'," +
                    "'IOWA'," +
                    "'USA'" +
                    ")",

            "INSERT INTO employee (firstName, lastName, email, idAddress, status) VALUES (" +
                    "'Dora'," +
                    "'Khiev'," +
                    "'khievdora@gmail.com'," +
                    "1," +
                    "'ENABLE'" +
                    ")",

            "INSERT INTO roomtype (description, maxCapacity, status) VALUES (" +
                    "'FAMILY ROOM'," +
                    "4," +
                    "'ENABLE'" +
                    ")",
            "INSERT INTO roomtype (description, maxCapacity, status) VALUES (" +
                    "'SINGLE ROOM'," +
                    "1," +
                    "'ENABLE'" +
                    ")",
            "INSERT INTO roomtype (description, maxCapacity, status) VALUES (" +
                    "'DOUBLE ROOM'," +
                    "4," +
                    "'ENABLE'" +
                    ")",

            "INSERT INTO room (roomName, roomNumber, floor, idRoomType, status, price) VALUES (" +
                    "'A001'," +
                    "'001'," +
                    "1," +
                    "1," +
                    "'ENABLE'," +
                    "100" +
                    ")",
            "INSERT INTO room (roomName, roomNumber, floor, idRoomType, status, price) VALUES (" +
                    "'A002'," +
                    "'002'," +
                    "1," +
                    "2," +
                    "'ENABLE'," +
                    "50" +
                    ")",
            "INSERT INTO room (roomName, roomNumber, floor, idRoomType, status, price) VALUES (" +
                    "'A003'," +
                    "'003'," +
                    "2," +
                    "3," +
                    "'ENABLE'," +
                    "75" +
                    ")",

            "INSERT INTO facility (description, quantity, status, idRoom) VALUES (" +
                    "'TV'," +
                    "1," +
                    "'GOOD'," +
                    "1" +
                    ")",
            "INSERT INTO facility (description, quantity, status, idRoom) VALUES (" +
                    "'Fridge'," +
                    "1," +
                    "'GOOD'," +
                    "1" +
                    ")",
            "INSERT INTO facility (description, quantity, status, idRoom) VALUES (" +
                    "'TV'," +
                    "1," +
                    "'GOOD'," +
                    "2" +
                    ")",
            "INSERT INTO facility (description, quantity, status, idRoom) VALUES (" +
                    "'Fridge'," +
                    "1," +
                    "'GOOD'," +
                    "2" +
                    ")",

            "INSERT INTO guest (firstName, lastName, idAddress) VALUES (" +
                    "'David'," +
                    "'John'," +
                    "2" +
                    ")",

            "INSERT INTO reservation (checkInDate, checkOutDate, bookedDate, idGuest, idRoom, reservationStatus) VALUES (" +
                    "'2017-04-22'," +
                    "'2017-04-24'," +
                    "'2017-04-21'," +
                    "1," +
                    "1," +
                    "'CONFIRMED'" +
                    ")",
            "INSERT INTO reservation (checkInDate, checkOutDate, bookedDate, idGuest, idRoom, reservationStatus) VALUES (" +
                    "'2017-04-25'," +
                    "'2017-04-26'," +
                    "'2017-04-20'," +
                    "1," +
                    "2," +
                    "'WAITING'" +
                    ")",
            "INSERT INTO reservation (checkInDate, checkOutDate, bookedDate, idGuest, idRoom, reservationStatus) VALUES (" +
                    "'2017-04-29'," +
                    "'2017-04-30'," +
                    "'2017-04-21'," +
                    "1," +
                    "3," +
                    "'WAITING'" +
                    ")",
    };

    private IDatabase iDatabase = null;

    public GenerateDBImpl() {
        iDatabase = Database.getInstance();
    }

    @Override
    public void generateDB() {
        try {
            boolean firstGenerateDB = false;

            this.iDatabase.openConnectionForGenerateDB();
            // Check database exist or not
            String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '"+ Database.DATABASE_NAME +"'";
            ResultSet resultSet = this.iDatabase.executeQueryWithConnectionOn(sql);
            if (!resultSet.next()) {
                System.out.println("No database!!!");
                firstGenerateDB = true;


                int result = this.iDatabase.executeUpdateWithConnectionOn(CREATE_DATABASE);
                for (int i = 0; i < generateTableScripts.length; i++) {
                    this.iDatabase.executeUpdate(generateTableScripts[i]);
                }

                // Generate Test data
                if (firstGenerateDB) {
                    for (int i = 0; i < generateTestDataScripts.length; i++) {
                        this.iDatabase.executeUpdate(generateTestDataScripts[i]);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
    }
}
