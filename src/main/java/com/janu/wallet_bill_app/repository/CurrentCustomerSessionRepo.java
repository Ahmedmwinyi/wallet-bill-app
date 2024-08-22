package com.janu.wallet_bill_app.repository;

import java.util.Optional;

import com.janu.wallet_bill_app.model.CurrentCustomerSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentCustomerSessionRepo extends JpaRepository<CurrentCustomerSession, Integer> {

	public Optional<CurrentCustomerSession> findByKey(String key);

	public CurrentCustomerSession findByCustomerMobileNumber(String customerMobileNumber);
}
