package com.codingexercise.state.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingexercise.annotation.StateApiResponse;
import com.codingexercise.state.model.State;
import com.codingexercise.state.model.States;
import com.codingexercise.state.service.IStateService;
import com.codingexercise.state.service.impl.StateServiceAnnotation;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class StateController {
	
	@Autowired
	IStateService stateService;
	
	@Autowired
	StateServiceAnnotation stateServiceAnnotation;
	
	@StateApiResponse
	@ApiOperation(value = "Get Details of Alabama and Georgia")
	@GetMapping(value = "/states/ag", produces = MediaType.APPLICATION_JSON_VALUE)
	public States getSpecificStates() {
		
		return this.stateService.getAlabamaAndGeorgia();
	}
	
	@StateApiResponse
	@ApiOperation(value = "Get List of US States")
	@GetMapping(value = "/states", produces = MediaType.APPLICATION_JSON_VALUE)
	public States getStates() {

		return this.stateService.getAllStates();
	}
	
	@StateApiResponse
	@ApiOperation(value = "Get details of a state passed as a path variable")
	@GetMapping(value = "/states/{stateName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public State getState( @PathVariable("stateName") String stateName) {
	
		return this.stateService.getStatesByName(stateName);
	}
	
	@StateApiResponse
	@ApiOperation(value = "Display list of states")
	@GetMapping(value = "/states/annotation", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<State> getStatesAnnotation() {
		System.out.println("Calling Annotation");
		
		return this.stateServiceAnnotation.getAllStatesAnnotation();
	}
	
	@StateApiResponse
	@ApiOperation(value = "Display list of states")
	@GetMapping(value = "/states/annotation/ag", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<State> getStateAnnotationAG() {
		System.out.println("Calling Annotation");
		
		return this.stateServiceAnnotation.getAlabamaAndGeorgiaAnnotation();
	}
	
	@StateApiResponse
	@ApiOperation(value = "Get details of a state passed as a path variable")
	@GetMapping(value = "/states/annotation/{stateName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public State getStateAnnotation( @PathVariable("stateName") String stateName) {
	
		return this.stateServiceAnnotation.getStatesByNameAnnotation(stateName);
	}
	
}
