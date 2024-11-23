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

    public Person findByid(String id)
    {
        //Return Type: Optional<Person>
       //repository.findById(id).orElse(null): This returns the Person if present; otherwise, it returns null. 
         return reposerty.findById(id).orElse(null);
    }

    public boolean existByEmail(String eamil)
    {
        // List<Person> list=reposerty.findByEmail(eamil);
        // !list.isEmpy(): iF present: true | if Not: flase
        return !reposerty.findByEmail(eamil).isEmpty();
    }

    public void savePerson(Person person)
    {
        reposerty.save(person);
    }

    public void deletePerson(Person person)
    {
        System.out.println("\n\n Person delted: "+person);
        reposerty.delete(person);
    }

    public int countPerson()
    {
        return (int)reposerty.count();
    }

    public void setStatusById(String id,String status)
    {
        Person pr=findByid(id);
        pr.setStatus(status);
        savePerson(pr);
    }

    /************************DEMO SErvices................... ******************/

    public void setStatus(String status)
    {
      
        List<Person> list=reposerty.findAll();

        for (Person person : list) 
        {
            person.setStatus(status);
            reposerty.save(person);
        }
    }


}

