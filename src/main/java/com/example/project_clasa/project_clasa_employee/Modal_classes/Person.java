package com.example.project_clasa.project_clasa_employee.Modal_classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Document
public class Person {

    @Id
    private String id;

// Personal Informetion ************************************************************************

    @Size(min = 3,max = 20, message = "*Name is Empty OR should have at least 3-20 characters")
    private String name;

  
    @Size(min = 3,max = 20, message = "*Surname is Empty OR should have at least 3-20 characters")
    private String surname;

    @NotBlank(message = "*Mobile Number is Empty")
    @Size(min = 10,max = 10,message = "*invalid Mobile number")
    private String mobile;

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "*Email is invalid..")
    private String email;

// Technology Informetion ************************************************************************

    @NotBlank(message = "*Please Enter Technology type.")
    private String tech_type;

    @NotBlank(message = "*Please Enter Technology.")
    private String technology;

// Address Informetion ************************************************************************

    @NotBlank(message = "*Please provide Landmark info..")
    private String landmark;

    @NotBlank(message = "*Please Enter City")
    private String city;

    @NotBlank(message = "*Please Enter State")
    private String state;

    @Size(min = 6,max = 6,message = "Pincode shoud be 6 Numbers")
    private String pincode;

// Photograph info ************************************************************************

    private String photo;

// Others ************************************************************************

    private String status;

}
