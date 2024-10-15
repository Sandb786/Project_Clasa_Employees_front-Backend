package com.example.project_clasa.project_clasa_employee.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;
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

   /*  'multipart/form-data:'' This encoding type allows you to send files as well as text data in
    a single request. It divides the data into different parts, each with its own content type,
    and sends it in a way that the server can parse correctly. 

     File destination=new File(path+"\\"+mulfile.getOriginalFilename());
    
            mulfile.transferTo(destination);

       <form th:action="@{/upload}" method="post" enctype="multipart/form-data">         */

    @PostMapping("/submit")
    public String postMethodName(@ModelAttribute("obj") Person person,@RequestParam("img") MultipartFile file) 
    {
       
        System.out.println("\n\nData: "+person); 

        try {
            
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getSize());
            System.out.println(file.getName());
        } catch (Exception e) 
        {
           System.out.println("\n\nFile error");
        }
       
        return "index";
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
