package main.dbconnection;

/**
 * Created by gebre on 4/18/2017.
 */
public class DatabaseSingleton {
    private static Database database = null;
    public static Database getDatabase(){
        if(database == null){
            database = new Database();
        }

        return database;
    }

}
