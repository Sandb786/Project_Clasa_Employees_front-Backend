package com.example.project_clasa.project_clasa_employee.Modal_classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Document
@Data
public class Person 
{

    @Id
    private String id;

    //Personal Informetion

    @NotBlank(message = "Name cannot be null")
    @Size(min = 3, message = "Name should have at least 2 characters")
    private String name;

    @NotBlank(message = "Surname cannot be null")
    @Size(min = 2, message = "Surname should have at least 2 characters")
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
