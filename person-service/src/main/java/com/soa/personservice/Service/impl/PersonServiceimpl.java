package com.soa.personservice.Service.impl;

import com.soa.personservice.Service.PersonService;
import com.soa.personservice.pojo.Person;
import com.soa.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceimpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findById(String id)
    {
        return personRepository.findById(id).get();
    }

    @Override
    public List<Person> findByName(String name){
        return personRepository.findByName(name);
    }
    @Override
    public List<Person> findAll()  {
        return personRepository.findAll();
    }

    @Override
    public int modifyAgeById(int age, String id)  {
        return personRepository.modifyAgeById(age,id);
    }


    @Override
    public void delete(String id)  {
        personRepository.deleteById(id);
    }


    @Override
    public void save(Person user)  {
        personRepository.save(user);
    }
}
