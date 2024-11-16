package com.example.project_clasa.project_clasa_employee.Other_Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class FormateDateTime {

    public String formateDate(String data) 
    {
        //1. Conver String type 'Date' into LocalDate object called 'date'
        LocalDate date = LocalDate.parse(data);

        //2. Create a formatter to convert "yyyy-mm-dd" to "dd-MM-yyyy" 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //3. Format the LocalDate to the desired format using 'formate()' Method.
        String formatedDate = date.format(formatter);

        return formatedDate;
    }

    public String formatTime(String time)
    {

     // 1.Conver String time to LocalTime object.
        LocalTime localTime = LocalTime.parse(time);

     // 2.'DateTimeFormatter.ofPattern("hh:mm a")' Formatter for 12-hour format with AM/PM  
        DateTimeFormatter formatter12Hour = DateTimeFormatter.ofPattern("hh:mm a");

    // Explaintion: Format to 12-hour: Use DateTimeFormatter.ofPattern("hh:mm a") to specify the desired 12-hour format.
    //               "hh" :  Hour in 12-hour format (01-12).
    //               "mm" :  Minutes.
    //               "a"  :  AM/PM marker.  


    // 3.Format the time to 12-hour format
       String time12 = localTime.format(formatter12Hour);

       return time12;
    }
}
