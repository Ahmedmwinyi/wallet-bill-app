package com.janu.wallet_bill_app.servicesImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.exceptions.WalletException;
import com.janu.wallet_bill_app.model.*;
import com.janu.wallet_bill_app.repository.WalletRepo;
import com.janu.wallet_bill_app.services.BillService;
import com.janu.wallet_bill_app.services.TransactionService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImplementation implements BillService {

	@Autowired
	private LoginLogoutCustomerServiceImplementation loginLogoutCustomerServiceImplementation;

	@Autowired
	private LoginLogoutAdminServiceImplementation loginLogoutAdminServiceImplementation;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private WalletRepo walletRepo;

	@Override
	public Transaction BillPayment(String key, Bill bill) throws CustomerException, LoginException, WalletException {
		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {
			Wallet wallet = customer.getWallet();
			Double availablebalance = wallet.getBalance();
			List<Bill> listofbills = wallet.getListofBills();

			if (availablebalance >= bill.getAmount()) {

				Transaction transaction = transactionService.addTransaction(key, bill.getReceiver(), bill.getBillType(),
						"Bill Payment", bill.getAmount());

				if (transaction != null) {
					listofbills.add(bill);
					wallet.setBalance(availablebalance - bill.getAmount());
					wallet.setListofBills(listofbills);
					walletRepo.save(wallet);
					return transaction;
				}
				else {
					throw new TransactionException("Opps ! Transaction Failed !");
				}
			} else {
				throw new WalletException("Insufficient Funds ! Available Wallet Balance : " + availablebalance);
			}
		} else {
			throw new CustomerException("Invalid Customer Key, Please Login In ! ");
		}
	}

	@Override
	public List<Bill> viewBillPayments(String key) throws CustomerException, LoginException {
		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Wallet wallet = customer.getWallet();

			return wallet.getListofBills();

		} else {
			throw new CustomerException("Invalid Customer Key, Please Login In ! ");
		}
	}

	@Override
	public List<Bill> adminViewAllBills(String key) throws AdminException, LoginException {
		Admin validate_admin = loginLogoutAdminServiceImplementation.validateAdmin(key);

		if (validate_admin != null) {
			List<Bill> allBills = new ArrayList<>();
			List<Wallet> allWallets = walletRepo.findAll();

			for (Wallet wallet : allWallets) {
				allBills.addAll(wallet.getListofBills());
			}

			return allBills;
		} else {
			throw new AdminException("Invalid Admin Key, Please Login In as Admin!");
		}
	}

}
