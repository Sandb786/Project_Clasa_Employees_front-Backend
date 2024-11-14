package com.example.project_clasa.project_clasa_employee.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Admin_login;
import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;
import com.example.project_clasa.project_clasa_employee.Service_Classes.Employee_Service;
import com.example.project_clasa.project_clasa_employee.Service_Classes.Person_Service;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class Admin_Controler 
{
 
    // 1. Person Service Class for Opretion...
    @Autowired
    private Person_Service person_Service;

     // 1. Employee Service Class for Opretion...
     @Autowired
     private Employee_Service employee_Service;
 


    
    @GetMapping("/Admin_Login")
    public String loginPageAdmin(Model model) 
    {   
        model.addAttribute("obj",new Admin_login());
        return "/Admin_Templates/Login_page.html";
    }


    @PostMapping("/Validate_login")
    public String adminHomepage(@Valid @ModelAttribute("obj")Admin_login login,BindingResult result,Model model) 
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
        {
            model.addAttribute("total_person", person_Service.countPerson());
            model.addAttribute("total_employee",employee_Service.countEmployee());
            return "/Admin_Templates/Ad_index";
        }   
        
        
        return "/Admin_Templates/Login_page.html";
    }

/**********************************Admin Services Controller*******************************/


  // 1. Redirect to 
    @GetMapping("/Admin_index")
    public String redirecToAdminIndex(Model model) 
    {
        person_Service.setStatus("Application_Accepted"); // Set Stetus for demo persus..

        model.addAttribute("total_person", person_Service.countPerson());
        model.addAttribute("total_employee",employee_Service.countEmployee());
        return "/Admin_Templates/Ad_index";
    }

    @GetMapping("/Manage_Applications")
    public String person_Appli_Managment(Model model) 
    {
        model.addAttribute("all_applicant",person_Service.getAllPersons());
        return "/Admin_Templates/Manage_Application";
    }

/******************************** Employee Applications Controller****************************/

  // Accept The Persons Applicantion...
   @GetMapping("/accpeted_application/{id}")
   public String acceptApplication(@PathVariable String id,Model model) 
    {

       Person pr=person_Service.findByid(id);
       pr.setStatus("Application_Accepted");
       person_Service.savePerson(pr);
       System.out.println("\n  Person Application Is Accepted: "+id);


    // 1. Redirect To Application Managment Page with All Persons...
    model.addAttribute("all_applicant",person_Service.getAllPersons());
    return "/Admin_Templates/Manage_Application";

    }

  // Reject The Persons Applicantion...
    @GetMapping("/reject_application/{id}")
    public String rejectApplication(@PathVariable String id,Model model)  
    {
      // 1.Delete Person From Database Permanently..
        person_Service.deletePerson(person_Service.findByid(id));
       

      // 2. Redirect To Application Managment Page with All Persons...
        model.addAttribute("all_applicant",person_Service.getAllPersons());
        return "/Admin_Templates/Manage_Application";
    }


  /******************************************************************************************/


  // Call Person For Interview...
  @GetMapping("/call-for-interview/{id}")
  public String callForInterview(@PathVariable String id,Model model) 
   {

      Person pr=person_Service.findByid(id);
      pr.setStatus("Interview_Cleared");
      person_Service.savePerson(pr);
      System.out.println("\n  Person Interview Called: "+id);


   // 1. Redirect To Application Managment Page with All Persons...
   model.addAttribute("all_applicant",person_Service.getAllPersons());
   return "/Admin_Templates/Manage_Application";

   }

  // Send Person's Offer Latter... 
   @GetMapping("/send-offer-latter/{id}")
   public String sendOfferLatter(@PathVariable String id,Model model) 
   {

    Person pr=person_Service.findByid(id);
    pr.setStatus("Offer_Latter_Sended");
    person_Service.savePerson(pr);
    System.out.println("\n  Send Offer Latter: "+pr.getName());

    // 1. Redirect To Application Managment Page with All Persons...
      model.addAttribute("all_applicant",person_Service.getAllPersons());
      return "/Admin_Templates/Manage_Application";
   }
   
  // Create Person As Employee. 
   @GetMapping("/make-it-employee/{id}")
   public String saveAsEmployee(@PathVariable String id,Model model)
   {

    Person pr=person_Service.findByid(id);
    pr.setStatus("Employee");
    person_Service.savePerson(pr);
    System.out.println("\n  Employee: "+pr.getName());

    // 1. Redirect To Application Managment Page with All Persons...
    model.addAttribute("all_applicant",person_Service.getAllPersons());
    return "/Admin_Templates/Manage_Application";
   }
   


}
