/**
 * 
 */
package com.janu.wallet_bill_app.services;


import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.exceptions.LogoutException;
import com.janu.wallet_bill_app.exceptions.UserException;
import com.janu.wallet_bill_app.model.Admin;
import com.janu.wallet_bill_app.model.CurrentAdminSession;
import com.janu.wallet_bill_app.model.Customer;
import com.janu.wallet_bill_app.model.User;

public interface LoginLogoutAdminService {

	public CurrentAdminSession loginAdmin(User user) throws LoginException, AdminException;

	public String logoutAdmin(String key) throws LogoutException;

	public User authenticateAdmin(User user, String key) throws UserException, AdminException, LoginException;

	public Admin validateAdmin(String key) throws AdminException, LoginException;

}
