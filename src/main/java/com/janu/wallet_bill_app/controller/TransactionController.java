package com.janu.wallet_bill_app.controller;

import java.util.List;

import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.model.Transaction;
import com.janu.wallet_bill_app.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/addtransaction")
	public ResponseEntity<Transaction> addTransactionHandler(@RequestParam String key,
															 @Valid @RequestParam String receiver, @RequestParam String description,
															 @Valid @RequestParam String transactionType, @Valid @RequestParam Double amount)
			throws CustomerException, LoginException {

		Transaction transaction = transactionService.addTransaction(key, receiver, description, transactionType,
				amount);

		return new ResponseEntity<>(transaction, HttpStatus.ACCEPTED);

	}

	@GetMapping("/view")
	public ResponseEntity<Transaction> viewTransactionHandler(@RequestParam String key,
			@Valid @RequestParam Integer transactionId) throws CustomerException, LoginException {

		Transaction transaction = transactionService.viewTransaction(key, transactionId);

		return new ResponseEntity<>(transaction, HttpStatus.OK);

	}

	@GetMapping("/viewall")
	public ResponseEntity<List<Transaction>> viewAllTransactionsHandler(@RequestParam String key)
			throws CustomerException, LoginException {

		List<Transaction> listoftransactions = transactionService.viewAllTransactions(key);

		return new ResponseEntity<>(listoftransactions, HttpStatus.OK);

	}

	// Admin
	@GetMapping("/view/customer")
	public ResponseEntity<List<Transaction>> viewAllTransactionsByCustomerHandler(@RequestParam String key,
			@Valid @RequestParam String mobileNumber) throws AdminException, LoginException, CustomerException {

		List<Transaction> listoftransactions = transactionService.viewAllTransactionsByCustomer(key, mobileNumber);

		return new ResponseEntity<>(listoftransactions, HttpStatus.OK);
	}

	// Admin
	@GetMapping("/view/customer/date")
	public ResponseEntity<List<Transaction>> viewAllTransactionsByCustomerByDateHandler(@RequestParam String key,
			@Valid @RequestParam String date, @Valid @RequestParam String mobileNumber)
			throws AdminException, LoginException, CustomerException {

		List<Transaction> listoftransactions = transactionService.viewAllTransactionsByCustomerByDate(key, date,
				mobileNumber);

		return new ResponseEntity<>(listoftransactions, HttpStatus.OK);

	}

	@GetMapping("/viewall/date")
	public ResponseEntity<List<Transaction>> viewTransactionByDateHandler(@RequestParam String key,
			@Valid @RequestParam String date) throws LoginException, CustomerException {

		List<Transaction> listoftransactions = transactionService.viewTransactionByDate(key, date);

		return new ResponseEntity<>(listoftransactions, HttpStatus.OK);
	}

	// Admin
	@GetMapping("/viewall/admin")
	public ResponseEntity<List<Transaction>> viewAllTransactionsAdminHandler(@RequestParam String key)
			throws AdminException, LoginException {

		List<Transaction> transactions = transactionService.viewAllTransactionsAdmin(key);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteTransactionHandler(@RequestParam String key,
														   @Valid @RequestParam Integer transactionId)
			throws AdminException, LoginException, CustomerException {

		String result = transactionService.deleteTransaction(key, transactionId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
