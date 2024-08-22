
package com.janu.wallet_bill_app.services;


import com.janu.wallet_bill_app.exceptions.*;
import com.janu.wallet_bill_app.model.Transaction;
import com.janu.wallet_bill_app.model.User;

import java.util.List;

public interface WalletService {

	public Double showWalletBalance(String key) throws LoginException, CustomerException;

	public Transaction addMoneyToWallet(User user, String key, Double amount) throws UserException, LoginException, CustomerException, WalletException;

	// View all transactions related to the wallet
	public List<Transaction> viewWalletTransactions(String key) throws AdminException, CustomerException, WalletException, LoginException;

}
