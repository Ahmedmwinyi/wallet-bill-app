/**
 * 
 */
package com.janu.wallet_bill_app.services;

import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.model.Customer;

import java.util.List;


public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException;

	public String removeCustomer(String key, String customerMobileNumber) throws CustomerException, LoginException;

	public Customer viewCustomer(String key, String customer_Id) throws CustomerException, LoginException;

	// Check for Admin Role
	public List<Customer> viewAllCustomers(String key) throws AdminException, LoginException, CustomerException;

	public Customer viewCustomerByMobileNumber(String mobileNumber) throws CustomerException;

	public String adminRemoveCustomer(String key, String customer_Id) throws AdminException, LoginException, CustomerException;



}
