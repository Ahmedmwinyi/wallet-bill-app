package com.janu.wallet_bill_app.controller;

import java.util.List;

import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.exceptions.WalletException;
import com.janu.wallet_bill_app.model.Bill;
import com.janu.wallet_bill_app.model.Transaction;
import com.janu.wallet_bill_app.services.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
@CrossOrigin(origins = "*")
public class BillPaymentController {

	@Autowired
	private BillService billService;

	@PostMapping("/payment")
	public ResponseEntity<Transaction> BillPaymentHandler(@RequestParam String key, @Valid @RequestBody Bill bill)
			throws CustomerException, LoginException, WalletException {

		Transaction transaction = billService.BillPayment(key, bill);

		return new ResponseEntity<>(transaction, HttpStatus.ACCEPTED);

	}

	@GetMapping("/view/allpayments")
	public ResponseEntity<List<Bill>> ViewAllBillPaymentsHandler(@RequestParam String key)
			throws CustomerException, LoginException {

		List<Bill> listofbills = billService.viewBillPayments(key);
		return new ResponseEntity<>(listofbills, HttpStatus.OK);

	}

	@GetMapping("/admin/view/all")
	public ResponseEntity<List<Bill>> AdminViewAllBillsHandler(@RequestParam String key) throws AdminException, LoginException {
		List<Bill> allBills = billService.adminViewAllBills(key);
		return new ResponseEntity<>(allBills, HttpStatus.OK);
	}

}
