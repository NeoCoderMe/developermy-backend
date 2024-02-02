package com.developermy.security.jwt.suppliers;

public final class JWTSupplier {

	private JWTSupplier() {

	}

	public static String getJWT() {
		return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI"
				+ "6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36PO" + "k6yJV_adQssw5c";
	}

}
