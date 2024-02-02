package com.developermy.security.provider;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.developermy.security.adapter.UserAdapter;
import com.developermy.security.jwt.suppliers.UserAuthenticationRequestSupplier;
import com.developermy.security.jwt.suppliers.UserEntitySupplier;
import com.developermy.security.models.UserAuthenticationRequest;
import com.developermy.security.models.UserEntity;
import com.developermy.security.models.UserResponse;
import com.developermy.security.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest {

	private UserAdapter userProvider = null;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	void setUp() throws Exception {
		userProvider = new UserAdapter(userRepository);
		Mockito.reset(userRepository);
	}

	@Test
	void find_user_expected_ok() {
		UserAuthenticationRequest userAuthenticationRequest = UserAuthenticationRequestSupplier.getJWTRequestDTOAdmin();
		UserEntity userEntity = UserEntitySupplier.getUserEntitySupplier();
		when(userRepository.findByUserNameAndPassword(anyString(), anyString())).thenReturn(Optional.of(userEntity));

		UserResponse userResponse = userProvider.findUser(userAuthenticationRequest);
		assertNotNull(userResponse);
		assertNotNull(userResponse.getId());
		assertNotNull(userResponse.getAuthorities());
		assertNotNull(userResponse.getUserName());
	}

}
