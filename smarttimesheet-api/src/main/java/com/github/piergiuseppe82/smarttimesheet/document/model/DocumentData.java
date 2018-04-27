package com.github.piergiuseppe82.smarttimesheet.document.model;

import java.util.List;

import com.github.piergiuseppe82.smarttimesheet.data.model.Customer;
import com.github.piergiuseppe82.smarttimesheet.data.model.Day;
import com.github.piergiuseppe82.smarttimesheet.data.model.Employee;

public class DocumentData {
	private Customer customer;
	private Employee employee;
	private List<Day> days;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Day> getDays() {
		return days;
	}
	public void setDays(List<Day> days) {
		this.days = days;
	}
}
