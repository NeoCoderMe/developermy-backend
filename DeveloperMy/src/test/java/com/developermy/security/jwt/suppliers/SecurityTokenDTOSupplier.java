package com.developermy.security.jwt.suppliers;

import com.developermy.security.models.SecurityTokenDTO;

public final class SecurityTokenDTOSupplier {

	private SecurityTokenDTOSupplier() {

	}

	public static SecurityTokenDTO getSecurityTokenDTO() {
		return SecurityTokenDTO.builder()
			.jwt("eyJhbVCJ9.eyJzdWIiOiIxMjM0E2MjM5MDIyfQ.SflKxwRJ")
			.userName("John")
			.build();
	}

}
