
package com.janu.wallet_bill_app.servicesImplementation;

import java.time.LocalDateTime;
import java.util.Optional;

import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.exceptions.LogoutException;
import com.janu.wallet_bill_app.exceptions.UserException;
import com.janu.wallet_bill_app.model.Admin;

import com.janu.wallet_bill_app.model.CurrentAdminSession;
import com.janu.wallet_bill_app.model.User;
import com.janu.wallet_bill_app.repository.AdminRepo;
import com.janu.wallet_bill_app.repository.CurrentAdminSessionRepo;
import com.janu.wallet_bill_app.services.LoginLogoutAdminService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogoutAdminServiceImplementation implements LoginLogoutAdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CurrentAdminSessionRepo currentAdminSessionRepo;

	@Override
	public CurrentAdminSession loginAdmin(User user) throws LoginException, AdminException {
		if ("Admin".equals(user.getRole())) {

			Optional<Admin> optionalAdmin = adminRepo.findByMobileNumber(user.getMobileNumber());

			if (optionalAdmin.isPresent()) {

				Admin admin = optionalAdmin.get();

				Optional<CurrentAdminSession> optional_currentAdminSession = currentAdminSessionRepo
						.findByAdminId(admin.getAdminId());

				if (optional_currentAdminSession.isEmpty()) {

					CurrentAdminSession currentAdminSession = new CurrentAdminSession();

					String key = RandomString.make(6);

					currentAdminSession.setAdminId(admin.getAdminId());
					currentAdminSession.setLocalDateTime(LocalDateTime.now());
					currentAdminSession.setKey(key);

					return currentAdminSessionRepo.save(currentAdminSession);
				} else {
					throw new LoginException("User Already Logged In With This Admin Id : " + admin.getAdminId());
				}

			} else {
				throw new AdminException("No Registered Admin Found With This User_Id : " + user.getMobileNumber());
			}

		} else {
			throw new LoginException("Please, Select Admin as Role to Login !");
		}
	}

	@Override
	public String logoutAdmin(String key) throws LogoutException {
		Optional<CurrentAdminSession> optional_currentAdminSession = currentAdminSessionRepo.findByKey(key);
		if (optional_currentAdminSession.isPresent()) {

			currentAdminSessionRepo.delete(optional_currentAdminSession.get());

			return "Logged Out Successfully !";

		} else {
			throw new LogoutException("No User Logged In !");

		}
	}

	@Override
	public User authenticateAdmin(User user, String key) throws UserException, AdminException, LoginException {
		Optional<CurrentAdminSession> optional_currentAdminSession = currentAdminSessionRepo.findByKey(key);

		if (optional_currentAdminSession.isPresent()) {

			CurrentAdminSession currentAdminSession = optional_currentAdminSession.get();

			Optional<Admin> optional_admin = adminRepo.findById(currentAdminSession.getAdminId());

			if (optional_admin.isPresent()) {

				Admin admin = optional_admin.get();

				if (admin.getMobileNumber().equals(user.getMobileNumber()) && admin.getPassword().equals(user.getPassword())) {

					return user;
				} else {
					throw new UserException("Invalid UserId or Password");
				}

			} else {
				throw new AdminException(
						"No Registered Admin Found with this Admin Id : " + currentAdminSession.getAdminId());
			}

		} else {
			throw new LoginException("Invalid Admin Key, Please Login In !");
		}
	}

	@Override
	public Admin validateAdmin(String key) throws AdminException, LoginException {
		Optional<CurrentAdminSession> optional_currentAdminSession = currentAdminSessionRepo.findByKey(key);

		if (optional_currentAdminSession.isPresent()) {

			CurrentAdminSession currentAdminSession = optional_currentAdminSession.get();

			Optional<Admin> optional_admin = adminRepo.findById(currentAdminSession.getAdminId());

			if (optional_admin.isPresent()) {

				Admin admin = optional_admin.get();

				return admin;

			} else {
				throw new AdminException(
						"No Registered Admin Found with this Admin Id : " + currentAdminSession.getAdminId());
			}

		} else {
			throw new LoginException("Invalid Admin Key, Please Login In !");
		}
	}
}