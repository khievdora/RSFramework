package main.dbconnection;

import java.sql.ResultSet;

/**
 * Created by gebre on 4/18/2017.
 */
public class DataAccessFacade {
    private IDatabase database;

    public DataAccessFacade(){
        database = DatabaseSingleton.getDatabase();
    }

    public void openConnection(){
        database.openConnection();
    }
    public ResultSet executeQuery(String querySql, String queryValue){
        return database.executeQuery(querySql, queryValue);
    }
    public void runProcedure(String sql, int value){
        database.runStoreProcedure(sql, value);
    }
    public void closeConnection(){
        database.closeConnection();
    }
    @Override
    public String toString(){
        return "helll" + database;
    }
}
