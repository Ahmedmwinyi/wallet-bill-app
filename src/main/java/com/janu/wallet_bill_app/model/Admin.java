package com.janu.wallet_bill_app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Admin {
	@Id
	@NotNull(message = "{Admin.id.invalid")
	@Min(value = 1000)
	@Max(value = 1010)
	private Integer adminId;

	@NotBlank(message = "{Admin.contact.invalid}")
	@Size(min = 10, max = 13, message = "{Admin.contact.invalid}")
	private String mobileNumber;

	@NotBlank(message = "{Admin.name.invalid}")
	private String firstName;

	@NotBlank(message = "{Admin.name.invalid}")
	private String lastName;

	@NotBlank(message = "{Admin.password.invalid}")
	private String password;

	@NotBlank(message = "{Admin.email.invalid}")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "{Admin.email.invalid}")
	private String email;
}
