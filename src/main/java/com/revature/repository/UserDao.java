package com.revature.repository;

import java.util.List;
import com.revature.model.User;

public interface UserDao {

  /**
   * Get a User by its id
   * @param id
   * @return
   */
    User get(int id);
    
    
    /**
     * Get a User by its Username
     * @param username
     * @return
     */
    List<User> getUserByUsername(String username);
    
    /**
     * Gets all users
     * @return
     */
    List<User> getAll();
    
    /**
     * Save a new user.
     * @param A user not persisted yet
     */
    void save(User user);
    
    /**
     * Update an existing user using its id.
     * @param account
     */
    void update(User user);
  
}
