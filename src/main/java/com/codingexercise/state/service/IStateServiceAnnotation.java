package com.codingexercise.state.service;

import java.util.List;
import com.codingexercise.state.model.State;

public interface IStateServiceAnnotation {
  public List<State> getAllStatesAnnotation();

  public List<State> getAlabamaAndGeorgiaAnnotation();

  public State getStatesByNameAnnotation(String state);
}
