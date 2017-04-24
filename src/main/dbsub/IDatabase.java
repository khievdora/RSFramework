package main.dbsub;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dora on 4/19/2017.
 */
public interface IDatabase {

    public void openConnectionForGenerateDB() throws ClassNotFoundException, SQLException;
    public void openConnection() throws ClassNotFoundException, SQLException;
    public void closeConnection();
    public ResultSet executeQuery(String sql);
    public int executeUpdate(String sql);
    public int executeUpdateWithConnectionOn(String sql);
    public ResultSet executeQueryWithConnectionOn(String sql);
}
