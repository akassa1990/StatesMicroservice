package com.codingexercise.state.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class State {
  
  @Id 
  private int id;
  private String country;
  private String name;
  private String abbr;
  private String area;
  private String largest_city;
  private String capital;

  public State() {
    super();
  }

  public State(int id, String country, String name, String abbr, String area, String largest_city,
      String capital) {
    super();
    this.id = id;
    this.country = country;
    this.name = name;
    this.abbr = abbr;
    this.area = area;
    this.largest_city = largest_city;
    this.capital = capital;
  }

  @Override
  public String toString() {
    return "State [id=" + id + ", country=" + country + ", name=" + name + ", abbr=" + abbr
        + ", area=" + area + ", largest_city=" + largest_city + ", capital=" + capital + "]";
  }

}
