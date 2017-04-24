package main.dbconnection;

import java.sql.*;

/**
 * Created by gebre on 4/18/2017.
 */
public class Database implements IDatabase {
    private Connection connection = null;
    private PreparedStatement prep = null;
    private CallableStatement call = null;
    @Override
    public void openConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelReservation?useSSL=false","root","root");
        }catch(SQLException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet executeQuery(String sql, String value) {
        ResultSet rset = null;
        try{

            prep = connection.prepareStatement(sql);
            prep.setString(1, value);
            rset = prep.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return rset;
    }

    @Override
    public void runStoreProcedure(String sql, int value) {
        CallableStatement call = null;
        try{
            call = connection.prepareCall(sql);
            call.setInt(1, value);
            call.registerOutParameter(2, Types.INTEGER);
            call.execute();
            System.out.println(call.getInt(2));
        } catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(call != null){
                try{
                    call.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void closeConnection() {
        if(prep != null){
            try {
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
