package com.janu.wallet_bill_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;

	@NotBlank(message = "{Wallet.id.invalid}")
	@Size(min = 10, max = 12, message = "{Wallet.id.invalid}")
	private String walletId;

	@NotBlank(message = "{Transaction.type.invalid}")
	private String type;

	@NotNull(message = "{Transaction.date.invalid}")
	private LocalDate date;

	@NotNull(message = "{Transaction.time.invalid}")
	private LocalTime time;

	@NotNull(message = "{Transaction.amount.invalid}")
	@Min(value = 0, message = "{Transaction.amount.invalid}")
	private Double amount;

	@NotBlank(message = "{Transaction.description.invalid}")
	private String description;

	@NotBlank(message = "{Transaction.receiver.invalid}")
	private String receiver;
}
