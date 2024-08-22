package com.janu.wallet_bill_app.services;

import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.model.Transaction;

import java.util.List;

public interface TransactionService {

	public Transaction addTransaction(String key, String receiver, String description, String transactionType,
									  Double amount) throws CustomerException, LoginException;

	public Transaction viewTransaction(String key, Integer transactionId) throws CustomerException, LoginException;

	public List<Transaction> viewAllTransactions(String key) throws CustomerException, LoginException;

	// Admin
	public List<Transaction> viewAllTransactionsByCustomer(String key, String mobileNumber)
			throws AdminException, LoginException, CustomerException;

	// Admin
	public List<Transaction> viewAllTransactionsByCustomerByDate(String key, String date, String mobileNumber)
			throws AdminException, LoginException, CustomerException;

	public List<Transaction> viewTransactionByDate(String key, String date) throws LoginException, CustomerException;

	// Admin - view all transactions
	public List<Transaction> viewAllTransactionsAdmin(String key) throws AdminException, LoginException;

	// Admin - delete a transaction
	public String deleteTransaction(String key, Integer transactionId) throws AdminException, LoginException, CustomerException;

}
