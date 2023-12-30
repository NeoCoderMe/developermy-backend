package com.developermy.feature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.developermy.crosscutting.controllers.BaseController;
import com.developermy.crosscutting.models.GenericResponseDTO;
import com.developermy.feature.models.FeatureRequestDTO;
import com.developermy.feature.models.FeatureResponseDTO;
import com.developermy.feature.services.FeatureService;

@Validated
@RestController
@RequestMapping("/feature")
public class FeatureController extends BaseController {

	private FeatureService featureService;

	public FeatureController(FeatureService featureService) {
		this.featureService = featureService;
	}

	// All named saveFeature
	// saveFeature over create because we use the save method also for update
	@PostMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponseDTO<FeatureResponseDTO>> saveFeature(
			@RequestBody FeatureRequestDTO featureRequestDTO) {
		return buildOkResponse(featureService.saveFeature(featureRequestDTO));
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponseDTO<FeatureResponseDTO>> getFeature(@PathVariable Long id) {
		return buildOkResponse(featureService.getFeature(id));
	}

	// All named createFeature
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponseDTO<FeatureResponseDTO>> updateFeature(
			@RequestBody FeatureRequestDTO featureRequestDTO, @PathVariable("id") Long id) {
		return buildOkResponse(featureService.updateFeature(featureRequestDTO, id));
	}

}
