package com.example.project_clasa.project_clasa_employee.Other_Service;

import java.io.IOException;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;

// This Class Ftach the Data of file And return it into the string formate....
@Service
public class File_fatcher 
{
   


// 'OTP' Formate html file feching mathod.
    public String getOtpformate(int otp,String name) throws IOException
    {

      /* 
         * Dynamic Path Using '  Resource resource = new ClassPathResource("templates/Formates/mailOtp.html")'.
         * Then Convert Resource object into a File object 'resource.getFile()', allowing you to work with the file’s methods, like getPath(), exists()...
         * Then 'getPath()' then returns the absolute path of File as a String .  
      */
       String filePath= new ClassPathResource("templates/Formates/mailOtp.html").getFile().getPath(); // 'F:\gem\fnn\....'
       System.out.println("\n\n ClassPathResource getPath(): "+filePath);


      //Now Convert 'String filePath' into 'Path object'....
        Path path=Paths.get(filePath); 
        System.out.println("\n\n Path: "+path);

      //Read the content of the .txt , .html , .xml file into a String using 'Files.readString(path)'...
        String otpFile=Files.readString(path);  
        
       // This method replace the value of veriable with '%s'. 
        String otpHtmlFile=String.format(otpFile,name,otp);
        
        System.out.println("\n\n \tHTML: \n"+otpHtmlFile);

        return otpHtmlFile;
    }

// Get Img Dynamic Path..   
    public String getImgUrl()
    {
       // 1.Local Storage Images is Not supported So we use Image cdn 
        return "https://www.tvh.com/sites/tvh/files/styles/max_650x650/public/2023-07/baldwin_filters_0_3.png?itok=SRyWoos7";
    }

// 'Call For Interviwe' Mail Formate..   
    public String getInterviewMailFromate(Person person,String date,String time) throws IOException
    {
       /* 1.
         * Dynamic Path Using '  Resource resource = new ClassPathResource("templates/Formates/mailOtp.html")'.
         * Then Convert Resource object into a File object 'resource.getFile()', allowing you to work with the file’s methods, like getPath(), exists()...
         * Then 'getPath()' then returns the absolute path of File as a String .  
      */
      String filePath= new ClassPathResource("templates/Formates/InterviewMail.html").getFile().getPath(); // '(F:/gem/fnn/....)'
      
      // 2. Now Convert 'String filePath' into 'Path object'....
       Path path=Paths.get(filePath); 

      // 3. Read the content of the .txt , .html , .xml file into a String using 'Files.readString(path)'...
        String Inter_Mail=Files.readString(path);  

      // 4. This method replace the value of veriable with '%s'. 
        String Inter_Mail_HtmlFile=String.format(Inter_Mail,getImgUrl(),person.getName(),person.getTech_type(),date,time,person.getId());
        
        System.out.println("\n\n \tInteview HTML: \n"+Inter_Mail_HtmlFile);
       
      return Inter_Mail_HtmlFile;
    }


// 'Offer Latter Formate' Mail Formate..   
    public String getOfferLatterFromate(Person person,String date,String salary) throws IOException
    {
       /* 1.
         * Dynamic Path Using '  Resource resource = new ClassPathResource("templates/Formates/mailOtp.html")'.
         * Then Convert Resource object into a File object 'resource.getFile()', allowing you to work with the file’s methods, like getPath(), exists()...
         * Then 'getPath()' then returns the absolute path of File as a String .  
      */

      String filePath= new ClassPathResource("templates/Formates/OfferLatter.html").getFile().getPath(); // '(F:/gem/fnn/....)'
      
      // 2. Now Convert 'String filePath' into 'Path object'....
       Path path=Paths.get(filePath); 

      // 3. Read the content of the .txt , .html , .xml file into a String using 'Files.readString(path)'...
        String Inter_Mail=Files.readString(path);  

      // 4. This method replace the value of veriable with '%s'. 
        String Inter_Mail_HtmlFile=String.format(Inter_Mail,person.getName(),person.getTech_type(),person.getTech_type(),
                                                            date,salary,person.getCity());
        
        // System.out.println("\n\n \tInteview HTML: \n"+Inter_Mail_HtmlFile);
       
      return Inter_Mail_HtmlFile;
    }



    

    
}
