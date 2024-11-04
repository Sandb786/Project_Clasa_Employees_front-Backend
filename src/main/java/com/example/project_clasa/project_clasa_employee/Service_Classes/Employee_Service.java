package com.example.project_clasa.project_clasa_employee.Service_Classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_clasa.project_clasa_employee.Reposerties.Employe_reposerty;

@Service
public class Employee_Service 
{
    @Autowired
    private Employe_reposerty reposerty;


    public int countEmployee()
    {
        return (int)reposerty.count();
    }
}
