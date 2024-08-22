/**
 * 
 */
package com.janu.wallet_bill_app.services;


import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.exceptions.LogoutException;
import com.janu.wallet_bill_app.exceptions.UserException;
import com.janu.wallet_bill_app.model.CurrentCustomerSession;
import com.janu.wallet_bill_app.model.Customer;
import com.janu.wallet_bill_app.model.User;

public interface LoginLogoutCustomerService {

	public CurrentCustomerSession loginCustomer(User user) throws LoginException, CustomerException;

	public String logoutCustomer(String key) throws LogoutException;
	
	public User authenticateCustomer(User user, String key) throws UserException, LoginException, CustomerException;

	public Customer validateCustomer(String key) throws LoginException, CustomerException;
}
