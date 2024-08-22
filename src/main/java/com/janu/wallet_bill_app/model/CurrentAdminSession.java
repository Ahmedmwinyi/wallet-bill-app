package com.janu.wallet_bill_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class CurrentAdminSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer currentSessionId;

	@NotNull(message = "{CurrentAdmin.id.invalid}")
	@Min(value=1000)
	@Max(value=1010)
	private Integer adminId;

	@NotBlank(message = "{CurrentAdmin.key.invalid}")
	@Size(min = 6, max = 6, message = "{CurrentAdmin.key.invalid}")
	private String key;

	@NotNull(message = "{CurrentAdmin.date.invalid}")
	private LocalDateTime localDateTime;
}
