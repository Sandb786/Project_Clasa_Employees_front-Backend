package com.example.project_clasa.project_clasa_employee.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
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



@Controller
public class Person_Controler 
{

    @Autowired
    private OtpGenrator genrator;

    @Autowired
    private Mailsender mailsender;

    // Some temporary Variable for Oparetins....
    private Person temPerson;
    private int tempOtp;
   
    //File Temp Veriable...
    private InputStream inputStream;
    private Path filePath;
    


    @GetMapping("/form")
    public String getMethodName(Model model) 
    {

     // Enable Registretion form and give it to Person Object.....
        model.addAttribute("form", true);
        model.addAttribute("otp", false);
        model.addAttribute("obj",new Person());

        return "/Regis_form/form";
    }



    @PostMapping("/submit")
    public String postMethodName(@Valid @ModelAttribute("obj") Person person,BindingResult result,@RequestParam("img") MultipartFile file,Model model) throws IOException, MessagingException
    {
        
       /* 1.
           * Dynamic Path Using '  Resource resource = new ClassPathResource("static/User_images")'.
           * Then Convert Resource object into a File object 'resource.getFile()', allowing you to work with the file’s methods, like getPath(), exists()...
           * Then 'getPath()' then returns the absolute path of File as a String.  
        */
            String path="F:\\Spring\\Spring-Boot-Security\\project_clasa_employee\\src\\main\\resources\\static\\User_images";
            // String path=new ClassPathResource("static/User_images").getFile().getPath(); 

            /*NOTE :- It Store file into 'Target' folder. So when application is recomplied the files is deleted .
                      so for devolopment we need to store file path manually. We use it At the time of deployment. */

        
       // 2. Set Person Photo Name to Person object....
        person.setPhoto(file.getOriginalFilename());


       // 3. Set Persona Current Status 'Applicant'
        person.setStatus("Applicant");


        //System.out.println("\n\nData: "+person);


        //4. Photograph Validation ------------------
        if (file.isEmpty()) 
        {
            System.out.println("\n\nPhoto Velidaton File Empty: "+file.isEmpty());   
            model.addAttribute("img_error", "*Upload Your Profile Photo");
            model.addAttribute("form", true);
            return "/Regis_form/form";
        }
       
        //5. Fields Validation ----------------------
        if (result.hasFieldErrors()) 
        {
            System.out.println("\n\nFeilds Have Error: "+result.hasErrors());
            System.out.println("\n\nError: "+result);
            model.addAttribute("form", true);
            return "/Regis_form/form";
        }

       
         // 6. Set the file(photo) to temprory File veriables(InputStream inputStream ,Path filePath).................
              inputStream=file.getInputStream();
              filePath=Paths.get(path,file.getOriginalFilename());


        // 7. Set 'Person' to 'Temporary_Person' Object so after email verificaton it save to databese.
              temPerson=person;
        

        // 8. Send Otp to user Enterd Email using Email Class....
               tempOtp=genrator.getOtp();
               System.out.println("\n\n Genrated OTP: "+tempOtp);

               try { mailsender.sendMail(person.getEmail(), tempOtp, person.getName()); } // throw MessagingException.
              
               catch (Exception e)
               { 
                  model.addAttribute("otp_error","Somthing is Wrong, 'Please Try again later' OR chack Your 'Internate Connection'");
                  model.addAttribute("has_error",true);
                  model.addAttribute("Mail_exeption",true);
                  model.addAttribute("otp", true);
                  model.addAttribute("form", false);              
                  return "/Regis_form/form"; 
                }
               
        

        // 9. Enable OTP Field For verification.............
        model.addAttribute("otp", true);
        model.addAttribute("form", false);
        model.addAttribute("has_error",false); //It tell that Otp feields Dose not have any error

        return "/Regis_form/form";
       
    }   

    @PostMapping("/validate-otp")
    public String otpValidator(@RequestParam("otp") String otp,Model model) throws IOException
    { 
        /* 1.

            * Compare an 'int' with a 'String' using the 'equals()' method. 
            * The equals() method in Java is intended for comparing objects of the same type, not primitive types like int.
            * Solution: Convert the 'int' to a 'String'.
            * USE: "String.valueOf(Int_Value)".
         */

         System.out.println("\n\n TempOtp OTP And UserOtp");
         System.out.println(tempOtp+" = "+otp);

      // 2. OTP Validation .... 
        if (!otp.equals(String.valueOf(tempOtp))) 
        {
            // Correct Validation...
            model.addAttribute("otp_error","enter Right otp....");
            model.addAttribute("has_error",true);

            //Length Validation.....
            if (!(otp.length()==6)) 
             {  model.addAttribute("otp_error","Otp Length should be 6."); model.addAttribute("has_error",true); }

            model.addAttribute("otp", true);
            model.addAttribute("form", false);

            return "/Regis_form/form";

        }


        // 3. Save the 'Temporery_person' object to the Database 
           System.out.println("\n Temp person: "+temPerson);



         // 4. Save The Photo to the Destination.................
               Files.copy(inputStream,filePath,StandardCopyOption.REPLACE_EXISTING);
           

        return "index";
    }

    
    

    /***************************** Testing Code *********************************/

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
