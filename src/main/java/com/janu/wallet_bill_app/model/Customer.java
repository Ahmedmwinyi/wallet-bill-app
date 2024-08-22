package com.janu.wallet_bill_app.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

	@Id
	@NotBlank(message = "{Customer.contact.invalid}")
	@Size(min = 10, max = 10, message = "{Customer.contact.invalid}")
	private String mobileNumber;

	@NotBlank(message = "{Customer.name.invalid}")
	private String firstName;

	@NotBlank(message = "{Customer.name.invalid}")
	private String lastName;

	@NotBlank(message = "{Customer.password.invalid}")
	private String password;

	private String role = "Customer";

	@NotBlank(message = "{Customer.email.invalid}")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "{Customer.email.invalid}")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;
}
