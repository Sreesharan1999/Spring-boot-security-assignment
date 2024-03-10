package com.springsecurity.springBootSecurity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.springBootSecurity.Model.Employee;
import com.springsecurity.springBootSecurity.Model.EmployeeService;
import com.springsecurity.springBootSecurity.pojo.AppUserService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@Autowired
	AppUserService appUserService;
	
	@PostMapping("/api/create")
	public String createEmp( @RequestParam int id , @RequestParam String name , @RequestParam int age , @RequestParam String address , @RequestParam String phoneNo ) {
	
		Employee e1 = new Employee( id , name , age , address , phoneNo );
		service.add(e1);
		return "Employee added successfully";
	}
	
	@GetMapping("/api/read")
	public List<Employee> getAllEmployees() {
		return service.getAll();
	}
	
	@PutMapping("/api/update")
	public String updateEmp( @RequestParam int id , @RequestParam String name , @RequestParam int age , @RequestParam String address , @RequestParam String phoneNo ) {
		
		Employee e1 = new Employee( id , name , age , address , phoneNo );
		service.update(e1);
		return "Employee updated successfully";
	}
	
	@DeleteMapping("/api/delete")
	public String deleteEmp( @RequestParam int id ) {
		
		Employee e1 = new Employee( id , null , 0 , null , null ) ;
		service.delete(e1);
		return "Employee deleted successfully";
	}

}
