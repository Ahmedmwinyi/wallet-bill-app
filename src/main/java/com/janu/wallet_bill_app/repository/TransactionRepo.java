package com.janu.wallet_bill_app.repository;

import java.time.LocalDate;
import java.util.List;

import com.janu.wallet_bill_app.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	public List<Transaction> findByDate(LocalDate date);

}
