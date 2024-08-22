package com.janu.wallet_bill_app.servicesImplementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.janu.wallet_bill_app.exceptions.*;
import com.janu.wallet_bill_app.model.Customer;
import com.janu.wallet_bill_app.model.Transaction;
import com.janu.wallet_bill_app.model.User;
import com.janu.wallet_bill_app.model.Wallet;
import com.janu.wallet_bill_app.repository.CustomerRepo;
import com.janu.wallet_bill_app.repository.WalletRepo;
import com.janu.wallet_bill_app.services.LoginLogoutAdminService;
import com.janu.wallet_bill_app.services.LoginLogoutCustomerService;
import com.janu.wallet_bill_app.services.TransactionService;
import com.janu.wallet_bill_app.services.WalletService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImplementation implements WalletService {

	@Autowired
	private LoginLogoutCustomerService loginLogoutCustomerService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private WalletRepo walletRepo;


	@Override
	public Double showWalletBalance(String key) throws LoginException, CustomerException {
		Customer customer = loginLogoutCustomerService.validateCustomer(key);

		if (customer != null) {

			Wallet wallet = customer.getWallet();

			Double balance = wallet.getBalance();

			return balance;

		} else {
			throw new CustomerException("Invalid Customer Key, Please Login In !");
		}
	}

	@Override
	public Transaction addMoneyToWallet(User user, String key, Double amount) throws UserException, LoginException, CustomerException, WalletException {
		Customer customer = loginLogoutCustomerService.validateCustomer(key);

		if (customer != null) {
			Wallet wallet = customer.getWallet();

			if (amount <= 0) {
				throw new WalletException("Amount must be positive!");
			}

			// Create a new transaction for adding money
			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setType("Credit");
			transaction.setDescription("Money added to wallet");
			transaction.setReceiver(customer.getFirstName() + customer.getLastName());
			transaction.setDate(LocalDate.now());
			transaction.setTime(LocalTime.now());
			transaction.setWalletId(wallet.getWalletId());

			// Update wallet balance
			Double newBalance = wallet.getBalance() + amount;
			wallet.setBalance(newBalance);

			// Save the wallet and transaction
			walletRepo.save(wallet);
			transactionService.addTransaction(key, customer.getFirstName() + customer.getLastName(), "Money added to wallet", "Credit", amount);

			return transaction;

		} else {
			throw new CustomerException("Invalid Customer Key! Please Log In!");
		}
	}

	@Override
	public List<Transaction> viewWalletTransactions(String key) throws AdminException, CustomerException, WalletException, LoginException {
		Customer customer = loginLogoutCustomerService.validateCustomer(key);

		if (customer != null) {
			Wallet wallet = customer.getWallet();
			List<Transaction> transactions = wallet.getListofTransactions();

			if (transactions == null || transactions.isEmpty()) {
				throw new WalletException("No transactions found for this wallet!");
			}

			return transactions;
		} else {
			throw new CustomerException("Invalid Customer Key! Please Log In!");
		}
	}
}
