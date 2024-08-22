/**
 * 
 */
package com.janu.wallet_bill_app.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentCustomerSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer currentSessionId;

	@NotBlank(message = "{CurrentCustomer.id.invalid}")
	@Size(min = 10, max = 12, message = "{CurrentCustomer.id.invalid}")
	private String customerMobileNumber;

	@NotBlank(message = "{CurrentCustomer.key.invalid}")
	@Size(min = 6, max = 6, message = "{CurrentCustomer.key.invalid}")
	private String key;

	@NotNull(message = "{CurrentCustomer.date.invalid}")
	private LocalDateTime localDateTime;

}
