package com.codingexercise.state.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codingexercise.state.model.State;

@Repository
public interface IStateRepository extends JpaRepository<State, Integer> {

}
