package com.janu.wallet_bill_app.servicesImplementation;

import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.model.Admin;
import com.janu.wallet_bill_app.model.Customer;
import com.janu.wallet_bill_app.model.Wallet;
import com.janu.wallet_bill_app.repository.CustomerRepo;
import com.janu.wallet_bill_app.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LoginLogoutCustomerServiceImplementation loginLogoutCustomerServiceimplementation;

	@Autowired
	private LoginLogoutAdminServiceImplementation loginLogoutAdminServiceimplementation;


	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		Optional<Customer> find_customer = customerRepo.findById(customer.getMobileNumber());

		if (find_customer.isEmpty()) {
			Wallet wallet = new Wallet();
			System.out.println("49");

			wallet.setBalance(0.0);
			wallet.setWalletId(customer.getMobileNumber());

			// Encrypt the password
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));

			customer.setWallet(wallet);

			System.out.println(customer);
			System.out.println(wallet);

			Customer added_customer = customerRepo.save(customer);
			System.out.println("57");

			return added_customer;
		} else {
			throw new CustomerException("Customer Already Registered With This Mobile Number : " + customer.getMobileNumber());
		}
	}

	@Override
	public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException {
		Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

		if (validate_customer != null) {

			return customerRepo.save(customer);

		} else {
			throw new CustomerException("Invalid Key, Please Login In !");
		}
	}

	@Override
	public String removeCustomer(String key, String customerMobileNumber) throws CustomerException, LoginException {
		Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

		if (validate_customer != null) {

			customerRepo.deleteById(customerMobileNumber);

			return "Customer Deleted Successfully !";

		} else {
			throw new CustomerException("Invalid Key, Please Login In !");
		}
	}

	@Override
	public Customer viewCustomer(String key, String customerMobileNumber) throws CustomerException, LoginException {
		Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

		if (validate_customer != null) {

			Optional<Customer> optional_customer = customerRepo.findById(customerMobileNumber);

			if (optional_customer.isPresent()) {

				return optional_customer.get();
			} else {
				throw new CustomerException(
						"No Customer Found With The Customer Contact Number : " + customerMobileNumber);
			}

		} else {
			throw new CustomerException("Invalid Key, Please Login In !");
		}

	}

	@Override
	public List<Customer> viewAllCustomers(String key) throws AdminException, LoginException, CustomerException {
		Admin validate_admin = loginLogoutAdminServiceimplementation.validateAdmin(key);

		if (validate_admin != null) {

			List<Customer> listofcustomers = customerRepo.findAll();

			if (listofcustomers.isEmpty()) {
				throw new CustomerException("No Customers Available in the Database!");
			} else {
				return listofcustomers;
			}

		} else {
			throw new AdminException("Invalid Key, Please Login In as Admin!");
		}
	}

	@Override
	public Customer viewCustomerByMobileNumber(String mobileNumber) throws CustomerException {
		Optional<Customer> customerOpt = customerRepo.findById(mobileNumber);
		if (customerOpt.isPresent()) {
			return customerOpt.get();
		} else {
			throw new CustomerException("Customer not found");
		}
	}

	@Override
	public String adminRemoveCustomer(String key, String mobileNumber) throws AdminException, LoginException, CustomerException {
		Admin validate_admin = loginLogoutAdminServiceimplementation.validateAdmin(key);

		if (validate_admin != null) {
			Optional<Customer> customerOpt = customerRepo.findById(mobileNumber);
			if (customerOpt.isPresent()) {
				customerRepo.deleteById(mobileNumber);
				return "Customer Deleted Successfully by Admin!";
			} else {
				throw new CustomerException("Customer not found with mobile number: " + mobileNumber);
			}
		} else {
			throw new AdminException("Invalid Key, Please Login In as Admin!");
		}
	}
}
