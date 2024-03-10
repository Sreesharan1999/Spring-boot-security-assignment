package com.springsecurity.springBootSecurity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springsecurity.springBootSecurity.Model.Employee;
import com.springsecurity.springBootSecurity.Model.EmployeeService;
import com.springsecurity.springBootSecurity.pojo.AppUser;
import com.springsecurity.springBootSecurity.pojo.AppUserService;


@SpringBootApplication
public class SpringBootSecurityApplication implements CommandLineRunner {
	
	@Autowired
	EmployeeService service;
	
	@Autowired
	AppUserService appUserService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//filter
		List<Employee> allEmployeesFilter = service.filterByName("");
		for( Employee temp : allEmployeesFilter ) {
			System.out.println( "Name : " + temp.getName() ); 
		}
		
		//sorting
		List<Employee> allEmployeesSort = service.getBySort( "name" , Direction.ASC );
		for( Employee temp : allEmployeesSort) {
			System.out.println( "Name : " + temp.getName() );
		}
		
		Set<String> authAdmin = new HashSet<>();
		authAdmin.add("Admin");
		
		Set<String> authUser = new HashSet<>();
		authUser.add("User");
		
        PasswordEncoder en = new BCryptPasswordEncoder();
		
		AppUser appAdmin = new AppUser(1 , "admin" , "admin" , en.encode("adminPassword"),authAdmin);
		appUserService.add(appAdmin);
		
		AppUser appUser = new AppUser(2 , "user" , "user" , en.encode("userPassword"),authUser);
		appUserService.add(appUser); 
			
	}
		
}

