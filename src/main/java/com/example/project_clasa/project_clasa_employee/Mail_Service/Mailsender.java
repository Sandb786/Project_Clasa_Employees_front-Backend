package com.example.project_clasa.project_clasa_employee.Mail_Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.project_clasa.project_clasa_employee.Other_Service.File_fatcher;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Mailsender 
{
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private File_fatcher fatcher;

    public String sendMail(String to,int otp,String name) throws MessagingException, IOException
    {

        System.out.println("\n\n Mail: TO: "+to+"\t OTP: "+otp);

        MimeMessage mailMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mailMessage);// true for multipart

        helper.setSubject("OTP"); //throws MessagingException

        helper.setTo(to); //throws MessagingException

       //helper.setFrom("sandeepmahawat09@gmail.com");


      // Set text content  //throws MessagingException, IOException 
      
        helper.setText(fatcher.getOtpformate(otp,name),true); // "Set to 'true' for HTML" "; 'false' for plain text";
        
        mailSender.send(mailMessage); 

        System.out.println("Mail Send Successfully...  OTP: "+otp);
        
        return "Mail Send Successfully..";
    }

    
}
