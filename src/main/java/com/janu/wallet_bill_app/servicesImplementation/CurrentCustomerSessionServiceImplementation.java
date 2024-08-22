package com.janu.wallet_bill_app.servicesImplementation;

import java.util.Optional;

import com.janu.wallet_bill_app.exceptions.CurrentCustomerSessionException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.model.CurrentCustomerSession;
import com.janu.wallet_bill_app.model.Customer;
import com.janu.wallet_bill_app.repository.CurrentCustomerSessionRepo;
import com.janu.wallet_bill_app.repository.CustomerRepo;
import com.janu.wallet_bill_app.services.CurrentCustomerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CurrentCustomerSessionServiceImplementation implements CurrentCustomerSessionService {

	@Autowired
	private CurrentCustomerSessionRepo currentCustomerSessionRepo;

	@Autowired
	private CustomerRepo customerRepo;


	@Override
	public CurrentCustomerSession getCurrentCustomerSession(String key) throws CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_CurrentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_CurrentCustomerSession.get();

			return currentCustomerSession;
		} else {
			throw new CurrentCustomerSessionException("Invalid key, Please Login In !");
		}
	}

	@Override
	public Customer getCustomerDetails(String key) throws CurrentCustomerSessionException, CustomerException {
		Optional<CurrentCustomerSession> optional_CurrentUserSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_CurrentUserSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_CurrentUserSession.get();

			String current_customerId = currentCustomerSession.getCustomerMobileNumber();

			Optional<Customer> current_Customer = customerRepo.findById(current_customerId);

			if (current_Customer.isPresent()) {

				Customer customer = current_Customer.get();

				return customer;

			} else {
				throw new CustomerException("No Registered Customer Found !");
			}

		} else {
			throw new CurrentCustomerSessionException("Invalid key, Please Login In !");
		}
	}

	@Override
	public String getCurrentCustomerId(String key) throws CurrentCustomerSessionException {
		Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_CurrentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_CurrentCustomerSession.get();

			String current_customerId = currentCustomerSession.getCustomerMobileNumber();

			return current_customerId;

		} else {

			throw new CurrentCustomerSessionException("Invalid key, Please Login In !");
		}
	}

	@Override
	public String getSessionKeyByMobileNumber(String customerMobileNumber) throws Exception {
		CurrentCustomerSession session = currentCustomerSessionRepo.findByCustomerMobileNumber(customerMobileNumber);
		if (session != null) {
			return session.getKey();
		} else {
			throw new Exception("Session not found for the provided mobile number");
		}
	}
}
