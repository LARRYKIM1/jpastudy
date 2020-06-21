package com.larrykim.jpastudy.dao;

import com.larrykim.jpastudy.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
