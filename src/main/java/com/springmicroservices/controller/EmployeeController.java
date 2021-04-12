package com.springmicroservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springmicroservices.model.Employee;
import com.springmicroservices.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public  ResponseEntity<?> getEmployees() {
		
		Iterable<Employee> employees = employeeRepository.findAll();
		if(ObjectUtils.isEmpty(employees)) {
			
			return new ResponseEntity<Object>("No records exists for Employee table", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<Object>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{empID}")
	public ResponseEntity<Object> getEmployee(@PathVariable("empID") Integer empID) {
		
		Employee dbEmployee = null;
		
		try {
			dbEmployee = employeeRepository.findById(empID).get();
			if(ObjectUtils.isEmpty(dbEmployee)) {
				
				return new ResponseEntity<Object>("The Employee object does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
			} 
		}
		catch(Exception e) {
			return new ResponseEntity<Object>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(dbEmployee, HttpStatus.OK);
	}

	@PutMapping("/employees")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		
		Employee dbEmployee = null;

		try {
			dbEmployee = employeeRepository.findById(employee.getEmpId()).get();
			
			if(ObjectUtils.isEmpty(dbEmployee)) {
				return new ResponseEntity<Object>("The Employee Object does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			dbEmployee.setEmpSalary(100000);
			employeeRepository.save(dbEmployee);
		}
		catch(Exception e) {
			return new ResponseEntity<Object>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(dbEmployee, HttpStatus.OK);
	}

	@PostMapping("/employees")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		
		employeeRepository.save(employee);
		return new ResponseEntity<String>("Employee Saved Successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/employees")
	public ResponseEntity<?> deleteEmployee(@RequestBody Employee employee) {
		
		Employee dbEmployee = employeeRepository.findById(employee.getEmpId()).get();
		
		if(ObjectUtils.isEmpty(dbEmployee)) {
			return new ResponseEntity<Object>("The Object does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			employeeRepository.delete(employee);
		}
		return new ResponseEntity<Object>("Employee Deleted Successfully", HttpStatus.OK);
	}
}
