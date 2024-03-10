package com.springsecurity.springBootSecurity.Model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo repo;
	
	public void add(Employee employee) {
		repo.save(employee);
	}
	
	public List<Employee> getAll(){
		return repo.findAll();
	}
	
	public Employee getById(int id) {
		Optional<Employee> opt = repo.findById(id);
		if( opt.isEmpty()) {
			return null;
		}
		return opt.get();
	}
	
	public void update(Employee employee) {
		repo.save(employee);
	}
	
	public void delete(Employee employee) {
		repo.delete(employee);
	}
	
	//filter
	public List<Employee> filterByName( String searchKey ){
		
		Employee dummy = new Employee();
		dummy.setName(searchKey);
		
		ExampleMatcher em = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("id" ,"name" , "age" , "address" , "phoneNo");
		
		Example<Employee> example = Example.of(dummy, em);
		
		return repo.findAll(example);
	}
	
	//sorting
	
	public List<Employee> getBySort( String columns , Direction direction ){
		return repo.findAll( Sort.by(direction, columns) );
	}

}
