package test;

import main.dbsub.DBFacade;

/**
 * Created by gebre on 4/21/2017.
 */
public class AccountTest {
    public static void main(String[] args) {
        DBFacade df = new DBFacade();
        System.out.println(df.getAccountByUserNameAndPassword("gk","123"));

    }

}
