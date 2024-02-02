package com.developermy.security.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.developermy.security.adapter.UserAdapter;
import com.developermy.security.jwt.suppliers.UserAuthenticationRequestSupplier;
import com.developermy.security.jwt.suppliers.UserResponseSupplier;
import com.developermy.security.models.SecurityTokenDTO;
import com.developermy.security.models.UserAuthenticationRequest;
import com.developermy.security.models.UserResponse;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	private UserService userService = null;

	@Mock
	private UserAdapter loginProvider;

	private JwtService jwtService;

	@BeforeEach
	void setUp() throws Exception {
		jwtService = new JwtService();
		userService = new UserService(loginProvider, jwtService);
	}

	@Test
	void login_user_expected_ok() {
		UserAuthenticationRequest userAuthenticationRequest = UserAuthenticationRequestSupplier.getJWTRequestDTOAdmin();
		UserResponse userResponse = UserResponseSupplier.getUserResponseAdmin();

		when(loginProvider.findUser(userAuthenticationRequest)).thenReturn(userResponse);

		SecurityTokenDTO securityToken = userService.loginUser(userAuthenticationRequest);
		assertNotNull(securityToken);
		assertNotNull(securityToken.getUserName());
		assertNotNull(securityToken.getJwt());
	}

	@Test
	void encode_and_decode_valid_jwt_expected_ok() {

		// login
		UserResponse userResponse = UserResponseSupplier.getUserResponseAdmin();
		UserAuthenticationRequest userAuthenticationRequest = UserAuthenticationRequestSupplier.getJWTRequestDTOAdmin();
		when(loginProvider.findUser(userAuthenticationRequest)).thenReturn(userResponse);
		SecurityTokenDTO user = userService.loginUser(userAuthenticationRequest);
		String jwt = user.getJwt();

		// decode
		String securityToken = userService.decode(jwt);

		assertNotNull(securityToken);
		assertEquals("Admin", securityToken);
	}

}
