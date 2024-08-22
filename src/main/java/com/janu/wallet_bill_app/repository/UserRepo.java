package com.janu.wallet_bill_app.repository;

import com.janu.wallet_bill_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
