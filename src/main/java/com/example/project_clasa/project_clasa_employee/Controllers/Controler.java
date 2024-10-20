package com.example.project_clasa.project_clasa_employee.Controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;

import jakarta.validation.Valid;


@Controller
public class Controler 
{
    String path="F:\\Spring\\Spring-Boot-Security\\project_clasa_employee\\src\\main\\resources\\static\\User_images";


    @GetMapping("/")
    public String indexPage()
    {
        return "index";
    }

    @GetMapping("/form")
    public String getMethodName(Model model) 
    {
        model.addAttribute("form", true);
        model.addAttribute("otp", false);
        model.addAttribute("obj",new Person());

        return "/Regis_form/form";
    }



    @PostMapping("/submit")
    public String postMethodName(@Valid @ModelAttribute("obj") Person person,BindingResult result,@RequestParam("img") MultipartFile file,Model model) throws IOException
    {
       
       
        person.setPhoto(file.getOriginalFilename());
        person.setStatus("Applicant");

        System.out.println("\n\nData: "+person); 

    
        // Photograph Validation ------------------
        if (file.isEmpty()) 
        {
          System.out.println("\n\n File Empty: "+file.isEmpty());   
           model.addAttribute("img_error", "*Upload Your Profile Photo");
        model.addAttribute("form", true);
            return "/Regis_form/form";
        }
       
        // Fields Validation ----------------------
        if (result.hasFieldErrors()) 
        {
            System.out.println("\n\nHave Error: "+result.hasErrors());
            System.out.println("\n\nError: "+result);
            model.addAttribute("form", true);
            return "/Regis_form/form";
        }

        
        // Save The Photo to the Destination.................
        File destination=new File(path+"\\"+file.getOriginalFilename());
        file.transferTo(destination);

        
        // Enable OTP Field For verification.............
        model.addAttribute("otp", true);
        model.addAttribute("form", false);
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
