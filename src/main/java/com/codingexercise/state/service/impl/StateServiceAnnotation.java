package com.codingexercise.state.service.impl;

import static org.hamcrest.CoreMatchers.any;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.codingexercise.state.model.MappedResponse;
import com.codingexercise.state.model.State;
import com.codingexercise.state.repository.IStateRepository;
import com.codingexercise.state.service.IStateServiceAnnotation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Service("stateServiceAnnotation")
public class StateServiceAnnotation implements IStateServiceAnnotation {
  @Autowired
  RestTemplate restTemplate;
  
  @Autowired
  EntityManagerFactory emf;
  
  @Autowired
  IStateRepository stateRepositry;

  private static final String STATES_URL = "http://services.groupkt.com/state/get/USA/all";
  
  static int count = 0;

  @Override
  public List<State> getAlabamaAndGeorgiaAnnotation() {

    ObjectMapper mapper = new ObjectMapper();
    MappedResponse res = new MappedResponse();

    
    ResponseEntity<String> statesEntity = this.restTemplate.getForEntity(STATES_URL, String.class);
    String stateJson = statesEntity.getBody();

    try {
      res = mapper.readValue(stateJson, MappedResponse.class);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return res.getRestResponse().getResult().stream()
        .filter(
            s -> s.getName().equalsIgnoreCase("Alabama") || s.getName().equalsIgnoreCase("Georgia"))
        .collect(Collectors.toList());
  }

  @Override
  public List<State> getAllStatesAnnotation() {

    ObjectMapper mapper = new ObjectMapper();
    MappedResponse res = new MappedResponse();

    ResponseEntity<String> statesEntity = this.restTemplate.getForEntity(STATES_URL, String.class);
    String stateJson = statesEntity.getBody();

    try {
      res = mapper.readValue(stateJson, MappedResponse.class);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    removeMe(res.getRestResponse().getResult());

    return res.getRestResponse().getResult();
  }

  @Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class, RollbackException.class})
  private void removeMe(List<State> states) {

    
    // do stuff
    
    SessionFactory sf = emf.unwrap(SessionFactory.class);
    Session session = sf.openSession();
    Transaction tx = session.getTransaction();
   /* EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();*/
    
    System.out.println("\nStarting save using merge() ....");
    Instant before = Instant.now();
  /*  count++;
    for (State state : states) {
      state.setAbbr(state.getAbbr() + count);
    }

    stateRepositry.saveAll(states);*/
    states.get(0).setCountry("ETH");
    states.get(1).setCountry("ETH");
    states.get(2).setCountry("ETH");
    states.get(3).setCountry("ETH");
    states.get(4).setCountry(null);
    states.get(5).setCountry("ETH");
    states.get(6).setCountry("ETH");
    states.get(7).setCountry("ETH");
    states.get(8).setCountry("ETH");
    states.get(9).setCountry("ETH");
    
    
    count++;
    tx.begin();
    try {
      for (State state : states) {
        state.setAbbr(state.getAbbr()+ count);
        session.saveOrUpdate(state);
//        em.merge(state);
      }
      tx.commit();
      System.out.println("\nTransaction commited successfully.");
      System.out.println("Number of records " + states.size());
      
    } catch (Exception ex) {
      System.out.println("Exception occured: "+ex.getMessage());
      System.out.println("Exception: "+ex);
      tx.rollback();
      System.out.println("Rollback completed.");
    }
    session.close();
//    em.close();
//    emf.close();
    Instant after = Instant.now();
    
    long responseTime = Duration.between(before, after).toMillis(); // .toWhatsoever()
    System.out.println("Transaction completed in " + responseTime + " Millisecond.\n\n");
    
    /*long start_time = System.currentTimeMillis();
    resp = GeoLocationService.getLocationIp(ipAddress);
    long end_time = System.currentTimeMillis();
    long difference = end_time-start_time;*/
  }

  @Override
  public State getStatesByNameAnnotation(String state) {

    ObjectMapper mapper = new ObjectMapper();
    MappedResponse res = new MappedResponse();

    ResponseEntity<String> statesEntity = this.restTemplate.getForEntity(STATES_URL, String.class);
    String stateJson = statesEntity.getBody();

    try {
      res = mapper.readValue(stateJson, MappedResponse.class);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return res.getRestResponse().getResult().stream()
        .filter(s -> s.getName().equalsIgnoreCase(state)).findFirst().get();
  }
}
