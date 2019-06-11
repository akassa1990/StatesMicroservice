package com.codingexercise.state.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class States {

  private List<State> states = new ArrayList<>();

  public void addState(State state) {
    this.states.add(state);
  }

  @Override
  public String toString() {
    return "States [states=" + states + "]";
  }

}
