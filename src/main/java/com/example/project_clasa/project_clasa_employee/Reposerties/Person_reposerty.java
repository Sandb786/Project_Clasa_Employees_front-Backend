package com.example.project_clasa.project_clasa_employee.Reposerties;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;
import java.util.List;




public interface Person_reposerty extends MongoRepository<Person,String>
{
   public List<Person> findByEmail(String email);

   public List<Person> findByStatus(String status);
}
