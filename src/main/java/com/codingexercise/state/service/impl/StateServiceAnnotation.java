package com.codingexercise.state.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.codingexercise.state.model.MappedResponse;
import com.codingexercise.state.model.State;
import com.codingexercise.state.service.IStateServiceAnnotation;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("stateServiceAnnotation")
public class StateServiceAnnotation implements IStateServiceAnnotation {
	@Autowired
	RestTemplate restTemplate;

	private static final String STATES_URL = "http://services.groupkt.com/state/get/USA/all";

	@Override
	public List<State> getAlabamaAndGeorgiaAnnotation() {

		ObjectMapper mapper = new ObjectMapper();
		MappedResponse res = new MappedResponse();

		ResponseEntity<String> eventsEntity = this.restTemplate.getForEntity(STATES_URL, String.class);
		String stateJson = eventsEntity.getBody();

		try {
			res = mapper.readValue(stateJson, MappedResponse.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res.getRestResponse().getResult().stream()
												.filter(s -> s.getName().equalsIgnoreCase("Alabama") || s.getName().equalsIgnoreCase("Georgia"))
												.collect(Collectors.toList());
	}

	@Override
	public List<State> getAllStatesAnnotation() {

		ObjectMapper mapper = new ObjectMapper();
		MappedResponse res = new MappedResponse();

		ResponseEntity<String> eventsEntity = this.restTemplate.getForEntity(STATES_URL, String.class);
		String stateJson = eventsEntity.getBody();

		try {
			res = mapper.readValue(stateJson, MappedResponse.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res.getRestResponse().getResult();
	}

	@Override
	public State getStatesByNameAnnotation(String state) {

		ObjectMapper mapper = new ObjectMapper();
		MappedResponse res = new MappedResponse();

		ResponseEntity<String> eventsEntity = this.restTemplate.getForEntity(STATES_URL, String.class);
		String stateJson = eventsEntity.getBody();

		try {
			res = mapper.readValue(stateJson, MappedResponse.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res.getRestResponse().getResult().stream().filter(s -> s.getName().equalsIgnoreCase(state)).findFirst().get();
	}
}
