package main.dbconnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gebre on 4/18/2017.
 */
public class Main {
    public static void main(String[] args) {
        DataAccessFacade facade = new DataAccessFacade();

        facade.openConnection();
        String querySql = "select *from tblperson where id=?";
        String value = "1";
        ResultSet result = facade.executeQuery(querySql, value);
        try{
            while(result.next()){
                System.out.println(result.getString("firstname"));
                System.out.println(result.getString("lastname"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            facade.closeConnection();
        }
    }
}
