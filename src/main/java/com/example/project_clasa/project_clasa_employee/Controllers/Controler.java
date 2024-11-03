package com.example.project_clasa.project_clasa_employee.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controler 
{
    
     @GetMapping("/")
    public String indexPage()
    {
      //  System.out.println("\n\n"+genrator.getOtp());
      
        return "index";
    }

}
