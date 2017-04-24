package main.dbconnection;

import java.sql.ResultSet;

/**
 * Created by gebre on 4/18/2017.
 */
public interface IDatabase {
    public void openConnection();
    public ResultSet executeQuery(String sql, String value);
    public void runStoreProcedure(String sql, int value);
    public void closeConnection();
}
