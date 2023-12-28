package com.developermy.feature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.developermy.crosscutting.controllers.BaseController;
import com.developermy.crosscutting.models.GenericResponseDTO;
import com.developermy.feature.models.FeatureResponseDTO;
import com.developermy.feature.services.FeatureService;

@Controller
@RestController
@RequestMapping("/feature")
public class FeatureController extends BaseController{

	private FeatureService featureService;
	
	public FeatureController(FeatureService featureService) {
		this.featureService = featureService;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponseDTO<FeatureResponseDTO>> getFeature(@PathVariable Long id) {
		return buildOkResponse(featureService.getFeature(id));
	}
}
