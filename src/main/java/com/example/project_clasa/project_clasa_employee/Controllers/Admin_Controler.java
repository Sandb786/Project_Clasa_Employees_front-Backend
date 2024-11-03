package com.example.project_clasa.project_clasa_employee.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Admin_login;

import jakarta.validation.Valid;



@Controller
public class Admin_Controler 
{
    
    @GetMapping("/Admin_Login")
    public String loginPageAdmin(Model model) 
    {   
        model.addAttribute("obj",new Admin_login());
        return "/Admin_Templates/Login_page.html";
    }


    @PostMapping("/Validate_login")
    public String adminHomepage(@Valid @ModelAttribute("obj")Admin_login login,BindingResult result) 
    { 
       
        /* 1. "result.rejectValue()" is a method in Spring’s 'BindingResult interface' that allows you to programmatically add 
               validation errors to a specific field.This can be particularly useful when you need to apply custom business 
               logic beyond the basic annotations like( @Size, @NotBlank ) etc. */
      

       // 2. if 'AdminID' is wrong. WE Give 'Dyanmic Field Error' using "result.rejectValue()" Method.
          if (!login.getAdminId().equals("system")) 
             result.rejectValue("adminId", "error.adminId", "*Wrong Admin ID. Please enter a correct ID.");
          
            
       // 3. if 'Password' is wrong. WE Give 'Dyanmic Field Error' using "result.rejectValue()" Method.
          if (!login.getPassword().equals("1234")) 
              result.rejectValue("password", "error.password", "*Wrong Password. Please try again.");
          
            
        
       // 4. When the 'Admin_id' and 'Password' is correct.Redirect user to Admin_Dashbored.
        if (login.getAdminId().equals("system")&&login.getPassword().equals("1234"))     
            return "/Admin_Templates/Ad_index";
        
        
        return "/Admin_Templates/Login_page.html";
    }

    
    
}
