package main.dbsub;

import main.model.Account;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dora on 4/21/2017.
 */
public class AccountImpl implements IAccount {

    private IDatabase iDatabase = Database.getInstance();

    @Override
    public int saveAccount(Account account) {
        int accountId = 0;
        try{
            String query = "INSERT INTO account(username, password, status, userRole, accountStatus) VALUES(" +
                    "'"+account.getUserName()+"'," +
                    "'"+account.getPassword()+"'," +
                    "'"+account.getStatus()+"'," +
                    "'"+account.getUserRole()+"'," +
                    "'"+account.getAccountStatus()+"')";
            accountId = iDatabase.executeUpdate(query);
            if (accountId != 0) {
                account.setCode(accountId);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }

        return accountId;
    }

    @Override
    public int updateAccount(Account account) {
        int result = 0;
        try{
            String query ="UPDATE account SET " +
                    "username='"+account.getUserName()+"', " +
                    "password='"+account.getPassword()+"', " +
                    "status='"+account.getStatus()+"', " +
                    "userRole='"+account.getUserRole()+"', " +
                    "accountStatus= '"+account.getAccountStatus()+"' " +
                    "WHERE idAccount="+account.getCode();
            result = iDatabase.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }

    @Override
    public int deleteAccount(Account account) {
        int result = 0;
        try{
            String sql = "DELETE FROM account WHERE idAccount = " + account.getCode();
            result = iDatabase.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }


    @Override
    public int deleteAccountById(String accountId) {
        int result = 0;
        try{
            String query = "DELETE FROM account WHERE idAccount = "+accountId;
            result = iDatabase.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return result;
    }

    @Override
    public Account getAccountById(String accountId) {
        String query = "SELECT * FROM account WHERE idAccount = "+ accountId;
        Account account = new Account();
        try{
            ResultSet rs = iDatabase.executeQuery(query);
            while (rs.next()) {
                account.setCode(rs.getInt(1));
                account.setUserName(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setStatus(rs.getString(4));
                account.setUserRole(rs.getString(5));
                account.setAccountStatus(rs.getString(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        iDatabase.closeConnection();
        return account;
    }

    @Override
    public Account getAccountByUserName(String userName) {
        String query = "SELECT * FROM account WHERE username = '"+ userName +"'";
        Account account = new Account();
        try{
            ResultSet rs = iDatabase.executeQuery(query);
            while (rs.next()) {
                account.setCode(rs.getInt(1));
                account.setUserName(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setStatus(rs.getString(4));
                account.setUserRole(rs.getString(5));
                account.setAccountStatus(rs.getString(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        iDatabase.closeConnection();
        return account;
    }

    @Override
    public Account getAccountByUserNameAndPassword(String userName, String password) {
        String query = "SELECT * FROM account WHERE username = '"+ userName +"' AND password = '" +password+"'";
        Account account = new Account();
        try{
            ResultSet rs = iDatabase.executeQuery(query);
            while (rs.next()) {
                account.setCode(rs.getInt(1));
                account.setUserName(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setStatus(rs.getString(4));
                account.setUserRole(rs.getString(5));
                account.setAccountStatus(rs.getString(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        iDatabase.closeConnection();
        return account;
    }

    @Override
    public List<Account> getAccountByFirstName(String firstName) {
        String query = "SELECT * FROM account WHERE username = '"+ firstName +"'";
        List<Account> accounts = new ArrayList<Account>();
        Account account=new Account();
        try{
            ResultSet rs = iDatabase.executeQuery(query);
            while (rs.next()){
                account.setCode(rs.getInt(1));
                account.setUserName(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setStatus(rs.getString(4));
                account.setUserRole(rs.getString(5));
                account.setAccountStatus(rs.getString(6));
                accounts.add(account);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return accounts;
    }

    @Override
    public List<Account> getAccountByLastName(String lastName) {
        String query = "SELECT * FROM account WHERE username = '"+ lastName +"'";
        List<Account> accounts = new ArrayList<Account>();
        Account account=new Account();
        try{
            ResultSet rs = iDatabase.executeQuery(query);
            while (rs.next()){
                account.setCode(rs.getInt(1));
                account.setUserName(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setStatus(rs.getString(4));
                account.setUserRole(rs.getString(5));
                account.setAccountStatus(rs.getString(6));
                accounts.add(account);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return accounts;
    }

    @Override
    public List<Account> getAllAccount() {
        String query = "SELECT * FROM account";
        List<Account> accounts = new ArrayList<Account>();
        try{
            ResultSet rs = iDatabase.executeQuery(query);
            while (rs.next()){
                Account account=new Account();
                account.setCode(rs.getInt(1));
                account.setUserName(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setStatus(rs.getString(4));
                account.setUserRole(rs.getString(5));
                account.setAccountStatus(rs.getString(6));
                accounts.add(account);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            this.iDatabase.closeConnection();
        }
        return accounts;
    }
}
