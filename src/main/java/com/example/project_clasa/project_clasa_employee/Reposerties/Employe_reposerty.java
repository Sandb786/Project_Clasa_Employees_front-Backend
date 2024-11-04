package com.example.project_clasa.project_clasa_employee.Reposerties;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Employee;

public interface Employe_reposerty extends MongoRepository<Employee,String>
{
    
}
