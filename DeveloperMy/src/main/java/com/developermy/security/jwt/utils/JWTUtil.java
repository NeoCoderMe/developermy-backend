package com.developermy.security.jwt.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.developermy.security.jwt.models.UserAuthenticationRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

public class JWTUtil {

	private static final String SECRET_JWT = "9e71d4296c4f0e33a266c09cc2168698fa9b449016708ec19c10ded4b88c645390ce72971645b9ca5c80616b45bf0a4cbcdd8b8a430f2f64c72d505fcf8de8db8";

	private static final String USER = "User";

	private static final String AUTHORITIES = "Authorities";

	private static final String SUBJECT = "sub";

	private static final int MAX_SIZE = 700;

	private static final String SHA512 = "HmacSHA512";

	private static final long EXPIRATION = 915000; // 15 Seconds

	public static String generateJWT(UserAuthenticationRequest userDetails) throws InvalidKeyException, Exception {
		Map<String, Object> claims = new HashMap<>();
		claims.put(USER, userDetails.getFullName());
		claims.put(AUTHORITIES, userDetails.getAuthorities());
		claims.put("Real g for life", "Only Gay Hackers Attack Me.");
		claims.put(SUBJECT, userDetails.getEmail());

		SecretKeySpec secret_key = new SecretKeySpec(SECRET_JWT.getBytes("UTF-8"), SHA512);

		return Jwts.builder()
			.subject(userDetails.getFullName())
			.claims(claims)
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + EXPIRATION))
			.signWith(secret_key)
			.compact();

	}

	public static boolean isValidToken(String token) {
		try {
			if (token == null || token.isBlank() || token.length() > MAX_SIZE) {
				return false;
			}

			Claims claims = decodeJwtToken(token);
			Date tokenExpiration = claims.getExpiration();

			return !isDateExpired(tokenExpiration);
		}
		catch (Exception e) {
			return false;
		}

	}

	// public static boolean isValidTokenForEmail(String token, String email) {
	// try {
	// if (token == null || token.isBlank() || token.length() > MAX_SIZE || email == null
	// || email.isBlank()) {
	// return false;
	// }
	//
	// Claims claims = decodeJwtToken(token);
	// String email2 = claims.getSubject();
	// Date expires = claims.getExpiration();
	// boolean isValidEmail = email2.trim().equalsIgnoreCase(email.trim());
	//
	// return isValidEmail && !isDateExpired(expires);
	// }
	// catch (Exception e) {
	// return false;
	// }
	//
	// }

	private static Boolean isDateExpired(Date tokenExpiration) {
		return tokenExpiration.before(new Date(System.currentTimeMillis()));
	}

	private static Claims decodeJwtToken(String jwtToken) throws WeakKeyException, UnsupportedEncodingException {
		// byte[] keyBytes = Base64.getUrlDecoder().decode(jwtToken);
		SecretKey secret = Keys.hmacShaKeyFor(SECRET_JWT.getBytes("UTF-8"));

		return Jwts.parser().verifyWith(secret).build().parseSignedClaims(jwtToken).getPayload();
	}

}
