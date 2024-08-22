/**
 * 
 */
package com.janu.wallet_bill_app.services;


import com.janu.wallet_bill_app.exceptions.CurrentCustomerSessionException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.model.CurrentCustomerSession;
import com.janu.wallet_bill_app.model.Customer;

public interface CurrentCustomerSessionService {

	public CurrentCustomerSession getCurrentCustomerSession(String key) throws CurrentCustomerSessionException;

	public Customer getCustomerDetails(String key) throws CurrentCustomerSessionException, CustomerException;

	public String getCurrentCustomerId(String key) throws CurrentCustomerSessionException;

	public String getSessionKeyByMobileNumber(String customerMobileNumber) throws Exception;

}
