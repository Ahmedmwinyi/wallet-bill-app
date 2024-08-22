
package com.janu.wallet_bill_app.repository;


import com.janu.wallet_bill_app.model.CurrentAdminSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface CurrentAdminSessionRepo extends JpaRepository<CurrentAdminSession, Integer> {

	public Optional<CurrentAdminSession> findByKey(String key);

	public Optional<CurrentAdminSession> findByAdminId(Integer adminId);

}
