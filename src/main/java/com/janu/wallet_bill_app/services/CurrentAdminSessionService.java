package com.janu.wallet_bill_app.services;


import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.CurrentAdminSessionException;
import com.janu.wallet_bill_app.model.Admin;
import com.janu.wallet_bill_app.model.CurrentAdminSession;

public interface CurrentAdminSessionService {

	public CurrentAdminSession getCurrentAdminSession(String key) throws CurrentAdminSessionException;

	public Admin getAdminDetails(String key) throws AdminException, CurrentAdminSessionException;

	public Integer getAdminId(String key) throws CurrentAdminSessionException;

}
