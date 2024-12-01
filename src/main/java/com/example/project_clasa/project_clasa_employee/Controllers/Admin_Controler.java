package com.example.project_clasa.project_clasa_employee.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project_clasa.project_clasa_employee.Mail_Service.Mailsender;
import com.example.project_clasa.project_clasa_employee.Modal_classes.Admin_login;
import com.example.project_clasa.project_clasa_employee.Modal_classes.Employee;
import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;
import com.example.project_clasa.project_clasa_employee.Other_Service.FormateDateTime;
import com.example.project_clasa.project_clasa_employee.Service_Classes.Employee_Service;
import com.example.project_clasa.project_clasa_employee.Service_Classes.Person_Service;

import jakarta.validation.Valid;





@Controller
public class Admin_Controler 
{
 
    // 1. Person Service Class for Opretion...
    @Autowired
    private Person_Service person_Service;

     // 2. Employee Service Class for Opretion...
     @Autowired
     private Employee_Service employee_Service;

      // 3.Other Service Class for Opretion...
      @Autowired
      private FormateDateTime formateDateTime;

       // 5.MailSender Service Class for Opretion...
       @Autowired
       private Mailsender mailsender;


    
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

/*Admin Services Controller*/


  // 1. Redirect to Admin Pannel
    @GetMapping("/Admin_index")
    public String redirecToAdminIndex(Model model) 
    {
        person_Service.setStatus("Interview_Scheduled"); // Set Stetus for demo persus..

        employee_Service.deleteAllEmployee(); // Test code ....

        model.addAttribute("total_person", person_Service.countPerson());
        model.addAttribute("total_employee",employee_Service.countEmployee());
        return "/Admin_Templates/Ad_index";
    }

  // 2. Manage Admin Applications...
    @GetMapping("/Manage_Applications")
    public String person_Appli_Managment(Model model) 
    {
        model.addAttribute("all_applicant",person_Service.getAllPersons());
        return "/Admin_Templates/Manage_Application";
    }

  // 3. Redirect to Employee Profile
    @GetMapping("/Employee_index")
    public String getMethodName(Model model) 
    {
          
     model.addAttribute("Persons",person_Service.getPersonsByStatus("Employee"));
     model.addAttribute("Employees",employee_Service.getAllEmployee());
     

    
     return "/Admin_Templates/Employee";
    }
  
/******************************** Employee Applications Controller*****************************************/

  // Accept The Persons Applicantion...
   @GetMapping("/accpeted_application/{id}")
   public String acceptApplication(@PathVariable String id,Model model) 
    {

    // 1. Accepts The Person Applicaton And set Status 'Application_Accepted'...
       Person pr=person_Service.findByid(id);
       pr.setStatus("Application_Accepted");
       person_Service.savePerson(pr);
      
    // 2. Redirect To Application Managment Page with All Persons...
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

  // Show Person's Application From
  @GetMapping("/Application_form/{id}")
  public String applicationForm(@PathVariable String id,Model model) 
  {
 
     model.addAttribute("applicant", person_Service.findByid(id)); 
      return "/Formates/Person_application";
  }

  /******************************* Interview Handller*********************************/


  String cid=""; // This is for not Resend mail if it is once Send.

  // Scadule Person's Interview...
  @GetMapping("/Schedule_interview/{id}")
   public String demo(@PathVariable String id,Model model) 
   {   
       cid=id;
       //Status: Application_Accepted
       
       model.addAttribute("person",person_Service.findByid(id));
       return "/Admin_Templates/Scedule_Events"; // Redirect To new page
   }

 
   // Call Person For Interview...
  @PostMapping("/call-for-interview/{id}")
  public String callForInterview(@PathVariable String id,@RequestParam("interviewDate") String date,@RequestParam("interviewTime") String time,Model model)
   {
     
    try // if Applicantion not connected to Internate
    {

      if(cid.equals(id)) // It is Compare the candidate id to rechivded id.
      System.out.println("\n Interview mail Sended....");//mailsender.sendInterviewCallMail(person_Service.findByid(id),formateDateTime.formateDate(date),formateDateTime.formatTime(time));
      else
      System.out.println("\n Mail not send..."); 
      

      // Set status of person
      person_Service.setStatusById(id,"Interview_Scheduled"); 

    }
    catch (Exception e) 
    {
      System.out.println("\n Mail Exeption(Interview call)...");
    }
    
     
     cid=""; // After sending Mail put empty string in cid. 
     

     // 1. Redirect To Application Managment Page with All Persons...
        model.addAttribute("all_applicant",person_Service.getAllPersons());
        return "/Admin_Templates/Manage_Application";

   }


   // Interview Status chack...
  @PostMapping("/Interview_status/{id}")
  public String postMethodName(@PathVariable String id,@RequestParam("status") String status,Model model) 
    {

       // 1.Set the Status of person that it clear interview or not
       person_Service.setStatusById(id, status);

      
       // 2. Redirect To Application Managment Page with All Persons...
       model.addAttribute("all_applicant",person_Service.getAllPersons());
       return "/Admin_Templates/Manage_Application";
   }
   

/**************************** Offer Latter Handller******************************/


   //Scadule Person's Joining Date...
   @GetMapping("/Schedule_offer_Latter/{id}")
   public String scheduleJoinDate(@PathVariable String id,Model model) 
   {   
       cid=id;
       model.addAttribute("person",person_Service.findByid(id));
       return "/Admin_Templates/Scedule_Events"; // Redirect To new page
   }

  // Creating A local veriable to strore Joining date for use...
     String joinDate;

  // Send Person's Offer Latter... 
   @PostMapping("/send-offer-latter/{id}")
   public String sendOfferLatter(@PathVariable String id,@RequestParam("JoiningDate") String joindate,@RequestParam("Salary") String salary,Model model) 
   {

     try 
    {

      if(cid.equals(id)) // It is Compare the candidate id to rechivded id.
      System.out.println("\n Offer Latter Sended....");//mailsender.sendOfferLatterMail(person_Service.findByid(id),formateDateTime.formateDate(joindate),salary);
      else
      System.out.println("\n Mail not send..."); 

      //Set status of person
      person_Service.setStatusById(id, "Offer_Latter_Sended");

    }
    catch (Exception e) 
    {
      System.out.println("\n Mail Exeption(Offer Latter)...");
    }


    // Initilize Local joinDate veriable 
       joinDate=formateDateTime.formateDate(joindate);
       
       cid=""; // After sending Mail put empty string in cid. 
       
   
    // 1. Redirect To Application Managment Page with All Persons...
      model.addAttribute("all_applicant",person_Service.getAllPersons());
      return "/Admin_Templates/Manage_Application";
   }


  // Create Person As Employee. 
   @GetMapping("/make-it-employee/{id}")
   public String saveAsEmployee(@PathVariable String id,Model model)
   {

    // 1.Now Person Become Employee so set Person Status "Employee"
        person_Service.setStatusById(id, "Employee");

  
     // 2.Fatching The Current Date When Employee is Join
        String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    
     // 3.Save Emp detailes in Employee class..
       Employee employee= employee_Service.setEmpData(date, id);

       employee_Service.saveEmp(employee);

     //4. Redirect To Application Managment Page with All Persons...
     model.addAttribute("all_applicant",person_Service.getAllPersons());
     return "/Admin_Templates/Manage_Application";
     
   }
  
   
/***************************************** Employee Handler ****************************************/

   @GetMapping("/Employee_detail")
   public String employeeDtailPage(Model model) 
   {
    return "/Admin_Templates/Employee_detail";
   }
   

   

}