package com.soa.personservice.Service;

import com.soa.personservice.pojo.Person;
import com.soa.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public interface PersonService {

    public Person findById(String id);

    public List<Person> findByName(String name);

    public List<Person> findAll();

    public int modifyAgeById(int age, String id);

    public void delete(String id);

    public void save(Person user);
}
