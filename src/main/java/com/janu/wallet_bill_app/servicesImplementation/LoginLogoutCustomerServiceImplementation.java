
package com.janu.wallet_bill_app.servicesImplementation;

import java.time.LocalDateTime;
import java.util.Optional;

import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.exceptions.LogoutException;
import com.janu.wallet_bill_app.exceptions.UserException;
import com.janu.wallet_bill_app.model.CurrentCustomerSession;
import com.janu.wallet_bill_app.model.Customer;
import com.janu.wallet_bill_app.model.User;
import com.janu.wallet_bill_app.repository.CurrentCustomerSessionRepo;
import com.janu.wallet_bill_app.repository.CustomerRepo;
import com.janu.wallet_bill_app.services.LoginLogoutCustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import net.bytebuddy.utility.RandomString;

@Service
public class LoginLogoutCustomerServiceImplementation implements LoginLogoutCustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CurrentCustomerSessionRepo currentCustomerSessionRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public CurrentCustomerSession loginCustomer(User user) throws LoginException, CustomerException {
		if ("Customer".equals(user.getRole())) {

			Optional<Customer> optional_customer = customerRepo.findById(user.getMobileNumber());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				Optional<CurrentCustomerSession> optional_CurrentUserSession = Optional.ofNullable(currentCustomerSessionRepo
						.findByCustomerMobileNumber(customer.getMobileNumber()));

				if (optional_CurrentUserSession.isPresent()) {

					throw new LoginException(
							"User Already Logged In With This Customer Id : " + customer.getMobileNumber());
				} else {

					if (user.getMobileNumber().equals(customer.getMobileNumber())
							&& passwordEncoder.matches(user.getPassword(), customer.getPassword())) {

						CurrentCustomerSession currentCustomerSession = new CurrentCustomerSession();

						String key = RandomString.make(6);

						currentCustomerSession.setCustomerMobileNumber(customer.getMobileNumber());
						currentCustomerSession.setKey(key);
						currentCustomerSession.setLocalDateTime(LocalDateTime.now());

						return currentCustomerSessionRepo.save(currentCustomerSession);

					} else {
						throw new LoginException("Invalid User_Id or Password");
					}
				}

			} else {
				throw new CustomerException("No Registered Customer Found With This User_Id : " + user.getMobileNumber());
			}

		} else {

			throw new LoginException("Please, Select Customer as Role to Login !");
		}
	}

	@Override
	public String logoutCustomer(String key) throws LogoutException {
		Optional<CurrentCustomerSession> currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (currentCustomerSession.isPresent()) {

			currentCustomerSessionRepo.delete(currentCustomerSession.get());

			return "Logged Out Successfully !";

		} else {
			throw new LogoutException("Invalid Key, No User Logged In !");
		}
	}

	@Override
	public User authenticateCustomer(User user, String key) throws UserException, LoginException, CustomerException {
		Optional<CurrentCustomerSession> optional_currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_currentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_currentCustomerSession.get();

			Optional<Customer> optional_customer = customerRepo
					.findById(currentCustomerSession.getCustomerMobileNumber());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				if (customer.getMobileNumber().equals(user.getMobileNumber())
						&& customer.getPassword().equals(user.getPassword())) {

					return user;
				} else {
					throw new UserException("Invalid UserId or Password");
				}

			} else {
				throw new CustomerException("No Customer Found with this Customer Id : "
						+ currentCustomerSession.getCustomerMobileNumber());
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}
	}

	@Override
	public Customer validateCustomer(String key) throws LoginException, CustomerException {
		Optional<CurrentCustomerSession> optional_currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_currentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_currentCustomerSession.get();

			Optional<Customer> optional_customer = customerRepo
					.findById(currentCustomerSession.getCustomerMobileNumber());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				return customer;

			} else {
				throw new CustomerException("No Customer Found with this Customer Id : "
						+ currentCustomerSession.getCustomerMobileNumber());
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}
	}
}
