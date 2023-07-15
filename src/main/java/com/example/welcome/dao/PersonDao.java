package com.example.welcome.dao;
import com.example.welcome.model.Person;

import java.util.List;
import java.util.UUID;


public interface PersonDao {

    void insertPerson(UUID id, Person person);

    default void insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        insertPerson(id, person);
    }

    List<Person> getAll();

}
