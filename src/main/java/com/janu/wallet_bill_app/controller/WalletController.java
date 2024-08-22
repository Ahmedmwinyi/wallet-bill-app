package com.janu.wallet_bill_app.controller;

import com.janu.wallet_bill_app.exceptions.*;
import com.janu.wallet_bill_app.model.Transaction;
import com.janu.wallet_bill_app.model.User;
import com.janu.wallet_bill_app.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
@CrossOrigin(origins = "*")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@GetMapping("/balance")
	public ResponseEntity<Double> showWalletBalanceHandler(@RequestParam String key)
			throws LoginException, CustomerException {

		Double balance = walletService.showWalletBalance(key);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);

	}

	@PostMapping("/transfer/towallet")
	public ResponseEntity<Transaction> addMoneyToWalletHandler(@Valid @RequestBody User user, @RequestParam String key,
			@Valid @RequestParam Double amount)
			throws UserException, LoginException, CustomerException, WalletException {

		Transaction transaction = walletService.addMoneyToWallet(user, key, amount);

		return new ResponseEntity<Transaction>(transaction, HttpStatus.ACCEPTED);
	}

	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> viewWalletTransactionsHandler(@RequestParam String key) throws AdminException, CustomerException, WalletException, LoginException {

		List<Transaction> transactions = walletService.viewWalletTransactions(key);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
}
