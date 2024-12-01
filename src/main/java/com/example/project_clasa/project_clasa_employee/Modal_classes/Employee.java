package com.example.project_clasa.project_clasa_employee.Modal_classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Employee 
{
    @Id
    String id;


    String eid;
    String doj;
    String role;
    String currentProject;

// Person who become Employee of Company
    String personId;
}
