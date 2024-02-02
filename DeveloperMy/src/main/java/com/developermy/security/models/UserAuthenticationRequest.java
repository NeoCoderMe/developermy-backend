package com.developermy.security.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserAuthenticationRequest(
		@JsonProperty("user_name") @NotBlank(message = "User name is required") @Size(min = 3, max = 20,
				message = "User name" + " must be between 3 and 20 characters") String userName,

		@NotBlank(message = "Password is required") @Size(min = 6, max = 20,
				message = "Password must be between 6 and 20 characters") String password) {
}
