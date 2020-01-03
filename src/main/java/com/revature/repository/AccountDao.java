package com.revature.repository;

import java.util.List;
import com.revature.model.Account;

public interface AccountDao {
  
  /**
   * Get a Account by its id
   * @param id
   * @return
   */
    Account get(int id);
    
    /**
     * Gets all accounts
     * @return
     */
    List<Account> getAll();
    
    /**
     * Gets Accounts by the user id
     * @param userId
     * @return
     */
    List<Account> getAccountsByUserId(int userId);
    
    /**
     * Save a new account.
     * @param A account not persisted yet
     */
    void save(Account account);
    
    /**
     * Update an existing accounts using its id.
     * @param account
     */
    void update(Account account);
  
}
