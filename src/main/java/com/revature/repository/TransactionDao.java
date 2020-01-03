package com.revature.repository;

import java.util.List;
import com.revature.model.Transaction;

public interface TransactionDao {

  /**
   * Get a Transaction by its id
   * @param id
   * @return
   */
    Transaction get(int id);
    
    /**
     * Gets all transactions
     * @ return
     */
    List<Transaction> getAll();
    
    /**
     * Gets transactions by the account id
     * @return
     */
    List<Transaction> getTransactionsByAccountId(int accountId);
    
    /**
     * Save a new transaction
     * @param  A transaction not persisted yet
     */
    void save(Transaction transaction);
    
    /**
     * Update an existing transaction using its id.
     * @param transaction
     */
    void update(Transaction transaction);
  
}
