package com.example.project_clasa.project_clasa_employee.Mail_Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.project_clasa.project_clasa_employee.Other_Service.File_fatcher;
import com.example.project_clasa.project_clasa_employee.Modal_classes.Person;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class Mailsender 
{
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private File_fatcher fatcher;

    public String sendOtpMail(String to,int otp,String name) throws MessagingException, IOException
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

    public String sendInterviewCallMail(Person person,String date,String time) throws MessagingException, IOException
    {
        // 1.Initilize Mimeassage useng 'MailSender' Class. 
           MimeMessage mimeMessage=mailSender.createMimeMessage();

        // 2.Initilize 'MimeMassageHalper' using 'MimeMassage' 
           MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);

        // 3. Set Mail Subject
           helper.setSubject("Invitation of Interview"); 

        // 4.Set Body of Massage (String type interviewFile using 'fatcher' class)
           helper.setText(fatcher.getInterviewMailFromate(person, date, time),true);

        // 5.Set Rechiver Mail address
           helper.setTo("sandeepmahawat85@gmail.com");

        // 6.Sending Mail Using 'MailSender' Class and parse 'MimeMeassage' in it.
           mailSender.send(mimeMessage);   

        return "mail sended";
    }


    public String sendOfferLatterMail(Person person,String date,String salary) throws MessagingException, IOException
    {
        // 1.Initilize Mimeassage useng 'MailSender' Class. 
           MimeMessage mimeMessage=mailSender.createMimeMessage();

        // 2.Initilize 'MimeMassageHalper' using 'MimeMassage' 
           MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);

        // 3. Set Mail Subject
           helper.setSubject("Offer Latter from Lanksoft Ltd."); 

        // 4.Set Body of Massage (String type interviewFile using 'fatcher' class)
           helper.setText(fatcher.getOfferLatterFromate(person, date, salary),true);

        // 5.Set Rechiver Mail address
          helper.setTo("sandeepmahawat85@gmail.com");

        // 6.Sending Mail Using 'MailSender' Class and parse 'MimeMeassage' in it.
           mailSender.send(mimeMessage);   

        return "mail sended";
    }
    
}
