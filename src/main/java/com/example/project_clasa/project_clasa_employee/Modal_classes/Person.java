package com.example.project_clasa.project_clasa_employee.Modal_classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Document
@Data
public class Person {

    @Id
    private String id;

    // Personal Informetion

    @NotBlank(message = "*Name cannot be Empty")
    @Size(min = 3, message = "*Name should have at least 3 characters")
    private String name;

    @NotBlank(message = "*Surname cannot be Empty")
    @Size(min = 3, message = "*Surname should have at least 3 characters")
    private String surname;

    @NotBlank(message = "*Mobile number Can't be Empty")
    @Size(max = 10,message = "Mob No. should be 10")
    private String mobile;

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "*Email is invalid..")
    private String email;

    // Technology Informetion

    @NotBlank(message = "*Please Enter Technology type.")
    private String tech_type;

    @NotBlank(message = "*Please Enter Technology.")
    private String technology;

    // Address Informetion

    @NotBlank(message = "*Please provide Landmark info..")
    private String landmark;

    @NotBlank(message = "*Please Enter City")
    private String city;

    @NotBlank(message = "*Please Enter State")
    private String state;

    @NotNull(message = "*Please Enter Pin-Code")
    private Integer pincode;

    // Photograph info
    @NotBlank(message = "*Upload your Profile Photo")
    private String photo;

    // Others
    private String status;

}
