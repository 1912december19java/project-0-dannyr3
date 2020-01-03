package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.model.User;

public class UserDaoPostgres implements UserDao{

  private static Logger log = Logger.getLogger(UserDaoPostgres.class);
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
  public List<User> getUserByUsername(String username) {
    List<User> allUsers = new ArrayList<User>();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    log.debug("Trying to get List of Usernames with username: " + username);
    try {
      stmt = conn.prepareStatement("SELECT * FROM \"user\" WHERE username = ?"); // ? is index 1
      stmt.setString(1, username);
      // now our statement is read to go. lets run it
      if (stmt.execute()) {
        // Now we probably have some results. lets get them.
        rs = stmt.getResultSet();
      }
      //(int id, String firstName, String lastName, String username, String password)
      while (rs.next()) {
        allUsers.add(new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
            rs.getString("username"), rs.getString("password")));
      }

    } catch (SQLException e) {
      log.error("Failed to get List of Users by Username: " + username, e);
    }
    log.debug("Successfully got list of Users with username: " + username);
    return allUsers;
  }
  
  @Override
  public User get(int id) {
    User out = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    log.debug("Trying to get User with id: " + id);
    
    try {
      stmt = conn.prepareStatement("SELECT * FROM \"user\" WHERE id = ?"); // ? is index 1
      stmt.setInt(1, id);
      // now our statement is read to go. lets run it
      if (stmt.execute()) {
        // Now we probably have some results. lets get them.
        rs = stmt.getResultSet();
      }
      rs.next();
      //(int id, String firstName, String lastName, String username, String password)
      out = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
          rs.getString("username"), rs.getString("password"));
    } catch (SQLException e) {
      log.error("Failed to get User with id: " + id, e);
    }
    log.debug("Successfully got User with Id: " + id);
    return out;
  }

  @Override
  public List<User> getAll() {
    List<User> allUsers = new ArrayList<User>();
    log.debug("Trying to get List of All Users");
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = conn.prepareStatement("SELECT * FROM \"user\"");
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allUsers.add(new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
            rs.getString("username"), rs.getString("password")));
      }

    } catch (SQLException e) {
      log.error("Failed to get List of All Users ", e);
    }
    log.debug("Successfully return list of all Users");
    return allUsers;
  }

  @Override
  public void save(User user) {
    PreparedStatement stmt = null;
    log.debug("Trying to save User");
    try {
      stmt = conn.prepareStatement(
          "INSERT INTO \"user\"(firstName, lastName, username, password) VALUES ( ?, ?, ?, ?)");
      stmt.setString(1, user.getFirstName());
      stmt.setString(2, user.getLastName());
      stmt.setString(3, user.getUsername());
      stmt.setString(4, user.getPassword());

      stmt.execute();
    } catch (SQLException e) {
      log.error("Failed to Save User ", e);
    }
    log.debug("Successfully Saved User");
  }

  @Override
  public void update(User user) {
    PreparedStatement stmt = null;
    log.debug("Trying to update User");
    try {
      stmt = conn.prepareStatement(
          "UPDATE \"user\" SET firstName = ?, lastName = ?, username = ?, password = ? WHERE id = ?");
      stmt.setString(1, user.getFirstName());
      stmt.setString(2, user.getLastName());
      stmt.setString(3, user.getUsername());
      stmt.setString(4, user.getPassword());
      stmt.setInt(5, user.getId());

      stmt.execute();
    } catch (SQLException e) {
      log.error("Failed to Update User ", e);
    }
    log.debug("Successfully Updated User");
    
  }

}
