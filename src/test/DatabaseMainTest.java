package test;

import main.dbsub.Database;
import main.dbsub.IDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dora on 4/19/2017.
 */
public class DatabaseMainTest {

    public static void main(String[] args) {
        IDatabase database = Database.getInstance();

        ResultSet resultSet = database.executeQuery("SELECT * FROM account");
        try {
            while(resultSet.next()) {
                System.out.println("username is " + resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }
}
