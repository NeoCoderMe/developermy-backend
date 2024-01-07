package com.developermy.security.jwt.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.developermy.crosscutting.controllers.BaseController;
import com.developermy.crosscutting.models.GenericResponse;
import com.developermy.security.jwt.models.JwtVerificationResponse;
import com.developermy.security.jwt.models.SecurityTokenDTO;
import com.developermy.security.jwt.models.UserAuthenticationRequest;
import com.developermy.security.jwt.services.JwtService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "JWT", description = "APIs for JSON Web Token (JWT) Operations")
@Validated
@RestController
@RequestMapping("/jwt")
public class JWTController extends BaseController {

	private final JwtService jwtService;

    public JWTController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

	@PostMapping("/generate")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponse<SecurityTokenDTO>> generateJWT(
			@RequestBody UserAuthenticationRequest jwtRequestDTO) {
		return buildOkResponse(jwtService.generateJWT(jwtRequestDTO));
	}

	@PostMapping("/validate")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponse<JwtVerificationResponse>> isValidJWT(
			@RequestBody SecurityTokenDTO jwtRequestDTO) {
		return buildOkResponse(jwtService.isValid(jwtRequestDTO));
	}

}
