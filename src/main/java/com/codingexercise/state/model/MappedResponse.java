package com.codingexercise.state.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MappedResponse {

	@JsonProperty("RestResponse")
	private RestResponse restResponse;

	public MappedResponse() {
		super();
	}

	public MappedResponse(RestResponse restResponse) {
		super();
		this.restResponse = restResponse;
	}

	public RestResponse getRestResponse() {
		return restResponse;
	}

	public void setRestResponse(RestResponse restResponse) {
		this.restResponse = restResponse;
	}

	@Override
	public String toString() {
		return "MappedResponse [restResponse=" + restResponse + "]";
	}
	
}
