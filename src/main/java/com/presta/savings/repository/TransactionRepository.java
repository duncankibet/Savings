package com.presta.savings.repository;

import com.presta.savings.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction,Long> {
}
