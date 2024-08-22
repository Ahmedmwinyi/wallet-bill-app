package com.janu.wallet_bill_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Wallet {

	@Id
	@NotBlank(message = "{Wallet.id.invalid}")
	private String walletId;

	@NotNull(message = "{Wallet.Balance.invalid}")
	private Double balance;

	@ElementCollection
	private List<Bill> listofBills = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> listofTransactions = new ArrayList<>();
}
