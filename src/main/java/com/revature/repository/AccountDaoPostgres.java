package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.model.Account;

public class AccountDaoPostgres implements AccountDao{

  private static Logger log = Logger.getLogger(AccountDaoPostgres.class);
  private static Connection conn;

  // this guy will run when the class loads, after static fields are initialized.
  static {
    try {

      // System.getenv();
      // Properties props = new Properties();
      // props.load();
      conn = DriverManager.getConnection(System.getenv("connstring"), System.getenv("username"),
          System.getenv("password"));
      log.info("Connected to Database");
    } catch (SQLException e) {
      log.error("Failed to connect to Database", e);
    }
  }
  
  @Override
  public Account get(int id) {
    Account out = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    log.trace("Setting prepared statement id to: " + id);
    try {
      stmt = conn.prepareStatement("SELECT * FROM account WHERE id = ?"); // ? is index 1
      stmt.setInt(1, id);
      // now our statement is read to go. lets run it
      if (stmt.execute()) {
        // Now we probably have some results. lets get them.
        rs = stmt.getResultSet();
        log.trace("Sucessfully got Result Set");
      }
      rs.next();
      //(int accountId, int userId, String accountType, double accountBalance)
      out = new Account(rs.getInt("id"), rs.getInt("user_id"), rs.getString("type"),
          rs.getDouble("balance"));
      log.trace("Adding new Accounts");
    } catch (SQLException e) {
      log.error("Failed to retrieve Account with id: " + id, e);
    }
    log.debug("Returning Account with id: " + id + ", or null if not found");
    return out;
  }

  @Override
  public List<Account> getAll() {
    List<Account> allAccounts = new ArrayList<Account>();

    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = conn.prepareStatement("Select * FROM account");
      if (stmt.execute()) {
        rs = stmt.getResultSet();
        log.trace("Sucessfully got Result Set");
      }
      while (rs.next()) {
        allAccounts.add(new Account(rs.getInt("id"), rs.getInt("user_id"), rs.getString("type"),
            rs.getDouble("balance")));
 
      }
      log.trace("Adding Accounts to List");
    } catch (SQLException e) {
      log.error("Failed to retrieve List of Accounts", e);
    }
    log.debug("Returning All Accounts in a List");
    return allAccounts;
  }

  @Override
  public List<Account> getAccountsByUserId(int userId) {
    List<Account> allAccountsWithUserId = new ArrayList<Account>();

    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = conn.prepareStatement("Select * FROM account where user_id = ?");
      stmt.setInt(1, userId);
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allAccountsWithUserId.add(new Account(rs.getInt("id"), rs.getInt("user_id"), rs.getString("type"),
            rs.getDouble("balance")));
     
      }
      log.trace("Adding Account to List with UserId: " + userId);
    } catch (SQLException e) {
      log.error("Failed to retrieve List of Accounts", e);
    }
    log.debug("Returning All Accounts in a List with UserId: " + userId);
    return allAccountsWithUserId;
  }

  @Override
  public void save(Account account) {
    PreparedStatement stmt = null;
    try {
      stmt = conn.prepareStatement(
          "INSERT INTO account(user_id, type, balance) VALUES ( ?, ?, ?)");
      stmt.setInt(1, account.getUserId());
      stmt.setString(2, account.getType());
      stmt.setDouble(3, account.getBalance());

      stmt.execute();
    } catch (SQLException e) {
      log.error("Failed to save Account", e);
    }
    log.debug("Successfully Saved Account");
  }

  @Override
  public void update(Account account) {
    PreparedStatement stmt = null;
    try {
      stmt = conn.prepareStatement(
          "UPDATE account SET user_id = ?, type = ?, balance = ? WHERE id = ?");
      stmt.setInt(1, account.getUserId());
      stmt.setString(2, account.getType());
      stmt.setDouble(3, account.getBalance());
      stmt.setInt(4, account.getId());

      stmt.execute();
    } catch (SQLException e) {
      log.error("Failed to update Account", e);
    }
    log.debug("Successfully updated Account");
  }

}
