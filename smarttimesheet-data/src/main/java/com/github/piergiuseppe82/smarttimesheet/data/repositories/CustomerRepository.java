package com.github.piergiuseppe82.smarttimesheet.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.github.piergiuseppe82.smarttimesheet.data.model.Customer;

public interface CustomerRepository  extends CrudRepository<Customer, Long> { }
