package com.example.project_clasa.project_clasa_employee.Service_Classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_clasa.project_clasa_employee.Modal_classes.Employee;
import com.example.project_clasa.project_clasa_employee.Other_Service.OtpGenrator;
import com.example.project_clasa.project_clasa_employee.Reposerties.Employe_reposerty;

@Service
public class Employee_Service 
{
    @Autowired
    private Employe_reposerty reposerty;

    @Autowired
    private OtpGenrator genrator;


    public List<Employee> getAllEmployee()
    {
        return reposerty.findAll();
    }

    public Employee findByID(String id)
    {
      //repository.findById(id).orElse(null): This returns the Person if present; otherwise, it returns null. 
        return reposerty.findById(id).orElse(null);
    }

    public void saveEmp(Employee employee)
    {
        reposerty.save(employee);
    }

    public void deleteEmp(Employee employee)
    {
        reposerty.delete(employee);
    }

    public Employee setEmpData(String doj,String personId)
    {
        /********* Set Employee Data *********/

     // 1. Create A Employee Veriable And Set Data In It. 
        Employee employee=new Employee();

     // 2. Create Employee ID
        String eid="EMP"+genrator.getRendomeDigit();
        System.out.println("\n EID: "+eid);

     // 3. Set Data in Employee veriable

        employee.setEid(eid);
        employee.setDoj(doj);
        employee.setPersonId(personId);
        employee.setRole("Junior Devolaper");
        employee.setCurrentProject("CLASA");

     // 4. Return Employee Veriable
        System.out.println("\n EMP: "+employee);

        return employee;
    }

    public void deleteAllEmployee()
    {
        reposerty.deleteAll();
    }
   
    public int countEmployee()
    {
        return (int)reposerty.count();
    }
}
