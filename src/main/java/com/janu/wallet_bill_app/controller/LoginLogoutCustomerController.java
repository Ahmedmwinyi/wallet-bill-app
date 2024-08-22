package com.janu.wallet_bill_app.controller;

import com.janu.wallet_bill_app.exceptions.*;
import com.janu.wallet_bill_app.model.CurrentCustomerSession;
import com.janu.wallet_bill_app.model.Customer;
import com.janu.wallet_bill_app.model.User;
import com.janu.wallet_bill_app.services.LoginLogoutCustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class LoginLogoutCustomerController {

	@Autowired
	private LoginLogoutCustomerService loginLogoutCustomerService;

	@PostMapping("/login")
	public ResponseEntity<CurrentCustomerSession> loginCustomerHandler(@Valid @RequestBody User user)
			throws LoginException, CustomerException {

		CurrentCustomerSession currentCustomerSession = loginLogoutCustomerService.loginCustomer(user);

		return new ResponseEntity<CurrentCustomerSession>(currentCustomerSession, HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logoutCustomerHandler(@RequestParam String key)
			throws LogoutException, CurrentCustomerSessionException {

		String result = loginLogoutCustomerService.logoutCustomer(key);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@PostMapping("/authenticate")
	public ResponseEntity<User> authenticateCustomerHandler(@Valid @RequestBody User user, @RequestParam String key)
			throws UserException, LoginException, CustomerException {

		User validated_user = loginLogoutCustomerService.authenticateCustomer(user, key);

		return new ResponseEntity<User>(validated_user, HttpStatus.OK);
	}

	@GetMapping("/validate")
	public ResponseEntity<Customer> validateCustomerHandler(@RequestParam String key)
			throws LoginException, CustomerException {

		Customer validated_customer = loginLogoutCustomerService.validateCustomer(key);

		return new ResponseEntity<Customer>(validated_customer, HttpStatus.OK);
	}

}
