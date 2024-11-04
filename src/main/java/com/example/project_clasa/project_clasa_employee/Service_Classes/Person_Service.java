package com.example.project_clasa.project_clasa_employee.Service_Classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;
import com.example.project_clasa.project_clasa_employee.Reposerties.Person_reposerty;

@Service
public class Person_Service 
{
    @Autowired
    private Person_reposerty reposerty;

    public List<Person> getAllPersons()
    {
        return reposerty.findAll();
    }

    public Person getPersonByid(String id)
    {
       //repository.findById(id).orElse(null): This returns the Person if present; otherwise, it returns null. 
         return reposerty.findById(id).orElse(null);
    }

    public boolean existByEmail(String eamil)
    {
        List<Person> list=reposerty.findByEmail(eamil);
        return !list.isEmpty();
    }

    public void savePerson(Person person)
    {
        reposerty.save(person);
    }

    public int countPerson()
    {
        return (int)reposerty.count();
    }
}
