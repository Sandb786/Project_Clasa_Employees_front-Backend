package com.example.project_clasa.project_clasa_employee.Other_Service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OtpGenrator 
{
    private int otp;

    public int getOtp()
    {
        //It Genrate Randome number.
        Random random=new Random();

        //We Spesify rang that we want randome no from 1,00,000-9,99,999
        otp=random.nextInt(100000,999999);
        
        return otp;
    }
}
