package com.janu.wallet_bill_app.repository;

import com.janu.wallet_bill_app.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, String> {

}
