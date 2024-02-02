package com.developermy.security.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.developermy.security.models.SecurityTokenDTO;
import com.developermy.security.models.UserResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;

@Service
public class JwtService {

	private static final String SECRET_JWT = "9e71d4296c4f0e33a266c09cc2168698fa9b449016708ec19c10ded4b88c645390ce72971645b9ca5c80616b45bf0a4cbcdd8b8a430f2f64c72d505fcf8de8db8";

	private static final String USER = "User";

	private static final String AUTHORITIES = "Authorities";

	private static final String SUBJECT = "sub";

	private static final int MAX_SIZE = 700;

	private static final String SHA512 = "HmacSHA512";

	private static final long EXPIRATION = 60 * 60 * 1000;

	public boolean isValidJWT(SecurityTokenDTO jwtRequestDTO) {

		return isValidToken(jwtRequestDTO.getJwt());
	}

	public String generateJWT(UserResponse userResponse) {
		if (userResponse == null) {
			throw new JwtException("Invalid userDetails ");
		}

		Map<String, Object> claims = new HashMap<>();
		claims.put(USER, userResponse.getUserName());
		claims.put(AUTHORITIES, userResponse.getAuthorities());
		claims.put(SUBJECT, userResponse.getUserName());
		try {
			SecretKeySpec secret_key = new SecretKeySpec(SECRET_JWT.getBytes("UTF-8"), SHA512);

			return Jwts.builder()
				.subject(userResponse.getUserName())
				.claims(claims)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(secret_key)
				.compact();
		}
		catch (Exception e) {
			throw new JwtException("Invalid JWT ");
		}

	}

	public Claims decodeJwtToken(String jwtToken) {
		try {
			SecretKey secret = Keys.hmacShaKeyFor(SECRET_JWT.getBytes("UTF-8"));

			return Jwts.parser().verifyWith(secret).build().parseSignedClaims(jwtToken).getPayload();

		}
		catch (WeakKeyException | UnsupportedEncodingException | SignatureException | ExpiredJwtException e) {
			throw new JwtException("Invalid JWT ");
		}

	}

	public boolean isValidToken(String token) {
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

	private Boolean isDateExpired(Date tokenExpiration) {
		return tokenExpiration.before(new Date(System.currentTimeMillis()));
	}

}