package com.developermy.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.developermy.crosscutting.controllers.BaseController;
import com.developermy.crosscutting.models.GenericErrorMessageDTO;
import com.developermy.crosscutting.models.GenericResponse;
import com.developermy.security.models.SecurityTokenDTO;
import com.developermy.security.models.UserAuthenticationRequest;
import com.developermy.security.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users", description = "APIs for User Operations")
@Validated
@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Login User",
	           description = "Authenticates a user and returns a security token.")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "User successfully authenticated",
	                 content = { @Content(mediaType = "application/json",
	                                      schema = @Schema(implementation = SecurityTokenDTO.class)) }),
	    @ApiResponse(responseCode = "400", description = "Invalid request data",
	                 content = { @Content(mediaType = "application/json",
	                                      schema = @Schema(implementation = GenericErrorMessageDTO.class)) }),
	    @ApiResponse(responseCode = "401", description = "Unauthorized",
	                 content = { @Content(mediaType = "application/json",
	                                      schema = @Schema(implementation = GenericErrorMessageDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal server error",
	                 content = { @Content(mediaType = "application/json",
	                                      schema = @Schema(implementation = GenericErrorMessageDTO.class)) })
	})
	public ResponseEntity<GenericResponse<SecurityTokenDTO>> loginUser(
	        @Valid @RequestBody UserAuthenticationRequest jwtRequestDTO) {
	    return buildOkResponse(userService.loginUser(jwtRequestDTO));
	}

	@PostMapping("/decode-jwt")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponse<String>> decode(@RequestHeader("Authorization") String authorization) {
		return buildOkResponse(userService.decode(authorization));
	}

}
