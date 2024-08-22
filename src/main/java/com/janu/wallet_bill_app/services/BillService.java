/**
 * 
 */
package com.janu.wallet_bill_app.services;

import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.exceptions.WalletException;
import com.janu.wallet_bill_app.model.Bill;
import com.janu.wallet_bill_app.model.Transaction;

import java.util.List;


public interface BillService {

	public Transaction BillPayment(String key, Bill bill) throws CustomerException, LoginException, WalletException;

	public List<Bill> viewBillPayments(String key) throws CustomerException, LoginException;

	// Admin functionalities
	public List<Bill> adminViewAllBills(String key) throws AdminException, LoginException;

}
