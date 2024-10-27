package com.example.project_clasa.project_clasa_employee.Other_Service;

import java.io.IOException;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

// This Class Ftach the Data of file And return it into the string formate....
@Service
public class File_fatcher 
{
   



    public String getOtpformate(int otp,String name) throws IOException
    {

      /* 
         * Dynamic Path Using '  Resource resource = new ClassPathResource("templates/Mail_formates/mailOtp.html")'.
         * Then Convert Resource object into a File object 'resource.getFile()', allowing you to work with the file’s methods, like getPath(), exists()...
         * Then 'getPath()' then returns the absolute path of File as a String .  
      */
       String filePath= new ClassPathResource("templates/Mail_formates/mailOtp.html").getFile().getPath(); // 'F:\gem\fnn\....'
       System.out.println("\n\n ClassPathResource getPath(): "+filePath);


      //Now Convert 'String filePath' into 'Path object'....
        Path path=Paths.get(filePath); 
        System.out.println("\n\n Path: "+path);

      //Read the content of the .txt , .html , .xml file into a String using 'Files.readString(path)'...
        String otpFile=Files.readString(path);  
        
        
        String otpHtmlFile=String.format(otpFile,name,otp);
        
        System.out.println("\n\n \tHTML: \n"+otpHtmlFile);

        return otpHtmlFile;
    }
}
