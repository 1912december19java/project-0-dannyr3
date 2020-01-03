package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.model.Transaction;

public class TransactionDaoPostgres implements TransactionDao{

  private static Logger log = Logger.getLogger(TransactionDaoPostgres.class);
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
  public Transaction get(int id) {
    Transaction out = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT * FROM \"transaction\" WHERE id = ?"); // ? is index 1
      stmt.setInt(1, id);
      // now our statement is read to go. lets run it
      if (stmt.execute()) {
        // Now we probably have some results. lets get them.
        rs = stmt.getResultSet();
        log.trace("Sucessfully got Result Set");
      }
      rs.next();
      //(int id, int accountId, String transactionType, double amount)
      out = new Transaction(rs.getInt("id"), rs.getInt("account_id"), rs.getString("type"),
          rs.getDouble("amount"));
    } catch (SQLException e) {
      log.error("Failed to get Transaction with id: " + id, e);
    }
    log.debug("Returning Transaction with Id: " + id);
    return out;
  }

  @Override
  public List<Transaction> getAll() {
    List<Transaction> allTransactions = new ArrayList<Transaction>();
    log.trace("Trying to get a list of All Transactions");
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = conn.prepareStatement("Select * FROM \"transaction\"");
      if (stmt.execute()) {
        rs = stmt.getResultSet();
        log.trace("Sucessfully got Result Set");
      }
      while (rs.next()) {
        allTransactions.add(new Transaction(rs.getInt("id"), rs.getInt("account_id"), rs.getString("type"),
            rs.getDouble("amount")));
      }

    } catch (SQLException e) {
      log.error("Failed to get List of All Transactions", e);
    }
    log.debug("Returning List of Transactions");
    return allTransactions;
  }

  @Override
  public List<Transaction> getTransactionsByAccountId(int accountId) {
    List<Transaction> allTransactionsWithAccountId = new ArrayList<Transaction>();
    log.trace("Trying to get List of Transactions with Account Id: " + accountId);
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = conn.prepareStatement("Select * FROM \"transaction\" where account_id = ?");
      stmt.setInt(1, accountId);
      if (stmt.execute()) {
        rs = stmt.getResultSet();
        log.trace("Sucessfully got Result Set");
      }
      while (rs.next()) {
        allTransactionsWithAccountId.add(new Transaction(rs.getInt("id"), rs.getInt("account_id"), rs.getString("type"),
            rs.getDouble("amount")));
      }

    } catch (SQLException e) {
      log.error("Failed to get List of All Transactions by Account Id: " + accountId, e);
    }
    log.debug("Returning List of Transactions with Account Id: " + accountId);
    return allTransactionsWithAccountId;
  }

  @Override
  public void save(Transaction transaction) {
    PreparedStatement stmt = null;
    log.trace("Trying to Save transaction");
    try {
      stmt = conn.prepareStatement(
          "INSERT INTO \"transaction\"(account_id, type, amount) VALUES ( ?, ?, ?)");
      stmt.setInt(1, transaction.getAccountId());
      stmt.setString(2, transaction.getTransactionType());
      stmt.setDouble(3, transaction.getAmount());

      stmt.execute();
    } catch (SQLException e) {
      log.error("Failed to save transaction", e);
    }
    log.debug("Successfully Saved Transaction");
  }

  @Override
  public void update(Transaction transaction) {
    PreparedStatement stmt = null;
    log.trace("Trying to Update transaction");
    try {
      stmt = conn.prepareStatement(
          "UPDATE \"transaction\" SET account_id = ?, type = ?, amount = ? WHERE id = ?");
      stmt.setInt(1, transaction.getAccountId());
      stmt.setString(2, transaction.getTransactionType());
      stmt.setDouble(3, transaction.getAmount());
      stmt.setInt(4, transaction.getId());

      stmt.execute();
    } catch (SQLException e) {
      log.error("Failed to update transaction", e);
    }
    log.debug("Successfully Updated Transaction");
  }

}
