package com.example.welcome.service;

import com.example.welcome.dao.PersonDao;
import com.example.welcome.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fake") PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> showAllPerson() {
        return personDao.getAll();
    }


    public void addPerson(Person person) {
        personDao.insertPerson(person);
    }


}
