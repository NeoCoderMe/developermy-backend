package com.developermy.security.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.developermy.security.jwt.suppliers.JWTSupplier;
import com.developermy.security.jwt.suppliers.SecurityTokenDTOSupplier;
import com.developermy.security.jwt.suppliers.UserAuthenticationRequestSupplier;
import com.developermy.security.models.SecurityTokenDTO;
import com.developermy.security.models.UserAuthenticationRequest;
import com.developermy.security.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UsersController.class)
class UsersControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		Mockito.reset(userService);
	}

	@Test
	void login_expected_ok() throws Exception {
		UserAuthenticationRequest userAuthenticationRequest = UserAuthenticationRequestSupplier.getJWTRequestDTOAdmin();
		SecurityTokenDTO securityTokenDTO = SecurityTokenDTOSupplier.getSecurityTokenDTO();

		when(userService.loginUser(userAuthenticationRequest)).thenReturn(securityTokenDTO);

		// POST
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(userAuthenticationRequest));

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

	@Test
	void decode_jwt_expected_ok() throws Exception {
		String jwt = JWTSupplier.getJWT();

		when(userService.decode(jwt)).thenReturn("admin");
		// POST
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/decode-jwt")
			.contentType(MediaType.APPLICATION_JSON)
			.header("Authorization", jwt);

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

}
