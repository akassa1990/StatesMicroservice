package com.codingexercise.state.model;

import java.util.ArrayList;
import java.util.List;

public class RestResponse {

	private List<String> messages = new ArrayList<>();

	private List<State> result = new ArrayList<>();

	public RestResponse() {
	}

	public RestResponse(List<String> messages, List<State> result) {
		super();
		this.messages = messages;
		this.result = result;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<State> getResult() {
		return result;
	}

	public void setResult(List<State> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RestResponse [messages=" + messages + ", result=" + result + "]";
	}

}
