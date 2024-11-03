package com.example.project_clasa.project_clasa_employee.Modal_classes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Admin_login 
{
    @Size(min = 3,message = "*Admin ID Should have atlest 3 characters.")
    private String adminId;

    @Size(min = 3,max = 8,message = "*Password should be between 3 and 8 characters.")
    private String password;
}
