package com.example.project_clasa.project_clasa_employee.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller
public class Controler 
{
    @GetMapping("/")
    public String indexPage()
    {
        return "index";
    }

    @GetMapping("/form")
    public String getMethodName(Model model) 
    {
        model.addAttribute("obj",new Person());

        return "/Regis_form/form";
    }



    @PostMapping("/submit")
    public String postMethodName(@Valid @ModelAttribute("obj") Person person,BindingResult result,@RequestParam("img") MultipartFile file) 
    {
       
        System.out.println("\n\n"+result);
        System.out.println("\n\nData: "+person); 

    
       
        return "/Regis_form/form";

    }   
    
    /**************************************************************/
    @GetMapping("/go-file")
    public String gotoform() 
    {
        return "/Regis_form/demoFile";
    }
    

    @PostMapping("/upload")
    public String getfile(@RequestParam("file")MultipartFile file,Model model) 
    {
       System.out.println("\nMethod Called...\n");

       System.out.println(file.getOriginalFilename());
       System.out.println(file.getContentType());
       System.out.println(file.getSize());
       System.out.println(file.getName());

        model.addAttribute("feed", "Method is called....");

        return "/Regis_form/demoFile";
    }
    
    
    
}
