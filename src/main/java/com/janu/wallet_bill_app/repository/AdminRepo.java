package com.janu.wallet_bill_app.repository;

import java.util.Optional;

import com.janu.wallet_bill_app.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

	public Optional<Admin> findByMobileNumber(String mobileNumber);
	
}
