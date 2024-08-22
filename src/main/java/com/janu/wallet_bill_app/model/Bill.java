package com.janu.wallet_bill_app.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

	@NotBlank(message = "{Bill.ConsumerNo.invalid}")
	private String consumerNo;

	@NotBlank(message = "{Bill.Type.invalid}")
	private String billType;

	@NotNull(message = "{Bill.Amount.invalid}")
	@Min(value = 0, message = "{Bill.Amount.invalid}")
	private Double amount;

	@NotBlank(message = "{Bill.Receiver.invalid}")
	private String receiver;

	private LocalDateTime paymentDateTime;

}
