package com.example.project_clasa.project_clasa_employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.project_clasa.project_clasa_employee.Service_Classes.Person_Service;

@SpringBootApplication
public class ProjectClasaEmployeeApplication 
{

	
	public static void main(String[] args) 
	{
		int x=1;
		SpringApplication.run(ProjectClasaEmployeeApplication.class, args);
		System.out.println("\n\n****************************");
		System.out.println("\n Application Start At: 8083");
		System.out.println("\n****************************");
		
	}

}
