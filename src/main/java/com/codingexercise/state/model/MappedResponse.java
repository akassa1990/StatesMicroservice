package com.codingexercise.state.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

  @Override
  public String toString() {
    return "MappedResponse [restResponse=" + restResponse + "]";
  }

}
