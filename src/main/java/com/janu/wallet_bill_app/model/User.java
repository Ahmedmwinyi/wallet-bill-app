package com.janu.wallet_bill_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

	@Id

	@Size(min = 10, max = 10, message = "{User.contact.invalid}")
	private String mobileNumber;


	private String password;

	private String role;
}
