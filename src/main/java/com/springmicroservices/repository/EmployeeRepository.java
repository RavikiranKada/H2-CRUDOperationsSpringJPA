package com.springmicroservices.repository;

import org.springframework.data.repository.CrudRepository;

import com.springmicroservices.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {


}
