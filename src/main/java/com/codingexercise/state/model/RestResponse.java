package com.codingexercise.state.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse {

  private List<String> messages = new ArrayList<>();

  private List<State> result = new ArrayList<>();

  public RestResponse() {}

  public RestResponse(List<String> messages, List<State> result) {
    super();
    this.messages = messages;
    this.result = result;
  }

  @Override
  public String toString() {
    return "RestResponse [messages=" + messages + ", result=" + result + "]";
  }

}
