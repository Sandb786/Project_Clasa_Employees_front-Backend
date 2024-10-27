package com.example.project_clasa.project_clasa_employee.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project_clasa.project_clasa_employee.Mail_Service.Mailsender;
import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;
import com.example.project_clasa.project_clasa_employee.Other_Service.OtpGenrator;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class Controler 
{

    @Autowired
    private OtpGenrator genrator;

    @Autowired
    private Mailsender mailsender;

    private Person temPerson;

    @GetMapping("/")
    public String indexPage()
    {
        System.out.println("\n\n"+genrator.getOtp());
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
    public String postMethodName(@Valid @ModelAttribute("obj") Person person,BindingResult result,@RequestParam("img") MultipartFile file,@RequestParam("otp") String otp,Model model) throws IOException
    {
     /* 
         * Dynamic Path Using '  Resource resource = new ClassPathResource("static/User_images")'.
         * Then Convert Resource object into a File object 'resource.getFile()', allowing you to work with the file’s methods, like getPath(), exists()...
         * Then 'getPath()' then returns the absolute path of File as a String.  
     */
        String path=new ClassPathResource("static/User_images").getFile().getPath();


       if (otp.isEmpty()) 
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
       else 
       {
        
       }
        return "";
    }   

    @PostMapping("/validate-otp")
    public String otpValidator(@RequestBody String entity) 
    {
      
        return entity;
    }
    
    /**
     * @throws MalformedURLException ************************************************************/

    @GetMapping("/mailTest")
    public String gotoform(Model model) throws MalformedURLException 
    {

        return "/Regis_form/demoFile";
    }

    
    
    

    @PostMapping("/upload")
    public String getfile(@RequestParam("file")String mail,Model model) throws MessagingException, IOException 
    {
       System.out.println("\nMethod Called...\n");

       System.out.println(mail);

       String feedback;

       try {
        
        feedback=mailsender.sendMail(mail,genrator.getOtp(),temPerson.getName());
       } 
       catch (Exception e) 
       {
           model.addAttribute("feed", "Somthing went wrong...");
           System.out.println(e);
           return "/Regis_form/demoFile";
       }
     

        model.addAttribute("feed", feedback);

        return "/Regis_form/demoFile";
    }
    
    
    
}
