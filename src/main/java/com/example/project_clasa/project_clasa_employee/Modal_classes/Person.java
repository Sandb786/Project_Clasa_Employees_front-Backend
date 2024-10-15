package com.example.project_clasa.project_clasa_employee.Modal_classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Person 
{

    @Id
    private String id;

    //Personal Informetion
    private String name;
    private String surname;
    private Long mobile;
    private String email;

    //Technology Informetion
    private String tech_type;
    private String technology;
    
    //Address Informetion
    private String landmark;
    private String city;
    private String state;
    private Integer pincode;

    //Photograph info
    private String photo;

    //Others
    private String status;


}
