package com.example.welcome.dao;

import com.example.welcome.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fake")
public class FakePersonDataAccessService implements PersonDao {

    List<Person> DB = new ArrayList<>();

    @Override
    public void insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getUserName(), person.getPassword()));
    }

    @Override
    public List<Person> getAll() {
        return DB;
    }
}
