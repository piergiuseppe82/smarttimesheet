package com.github.piergiuseppe82.smarttimesheet.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.github.piergiuseppe82.smarttimesheet.data.model.Employee;

public interface EmployeeRepository  extends CrudRepository<Employee, Long> { }
