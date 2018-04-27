package com.github.piergiuseppe82.smarttimesheet.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.piergiuseppe82.smarttimesheet.data.model.Activity;
import com.github.piergiuseppe82.smarttimesheet.data.model.ActivityType;
import com.github.piergiuseppe82.smarttimesheet.data.model.Customer;
import com.github.piergiuseppe82.smarttimesheet.data.model.Day;
import com.github.piergiuseppe82.smarttimesheet.data.model.Employee;
import com.github.piergiuseppe82.smarttimesheet.document.model.Document;
import com.github.piergiuseppe82.smarttimesheet.document.model.DocumentData;
import com.github.piergiuseppe82.smarttimesheet.document.services.DocumentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDocumentService {

		@Autowired
		private DocumentService documentService;
		
		@Test
		public void testGenerateDocument() {
			DocumentData docuemntData = makeSampleObject();
			Document generateDocument = documentService.generateDocument("WSMOD1800Generator",docuemntData);
			assertNotNull(generateDocument);
			assertNotNull(generateDocument.getExtension());
			assertNotNull(generateDocument.getName());
			File file = generateDocument.getFile();
			assertNotNull(file);
			assertTrue(file.exists());
		}

		
		@Test
		public void testGeneratePdf() {
			DocumentData docuemntData = makeSampleObject();
			Document generateDocument = documentService.generatePdf("WSMOD1800Generator",docuemntData);
			assertNotNull(generateDocument);
			assertNotNull(generateDocument.getExtension());
			assertEquals("pdf", generateDocument.getExtension());
			assertNotNull(generateDocument.getName());
			File file = generateDocument.getFile();
			assertNotNull(file);
			assertTrue(file.exists());
		}
		

		private DocumentData makeSampleObject() {
			DocumentData docuemntData = new DocumentData();
			Customer customer = new Customer();
			customer.setName("Company 1");
			docuemntData.setCustomer(customer);
			Employee employee = new Employee();
			employee.setLastName("Do");
			employee.setName("Jhon");
			docuemntData.setEmployee(employee);			
			List<Day> days = makeDays(customer, employee);		
			docuemntData.setDays(days);
			return docuemntData;
		}


		private List<Day> makeDays(Customer customer, Employee employee) {
			int year = 2018;
			int month = 3;
			Calendar calendar = new GregorianCalendar();
			calendar.set(year, month, 1);
			int daysOnMonth = getDaysOnMonth(year,month+1);
			List<Day> days = new ArrayList<>();
			for (int i = 0; i < daysOnMonth; i++) {
				calendar.add(Calendar.DATE, 1);
				Day day = new Day();
				day.setDate( calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				Activity activity = new Activity();
				activity.setCustomer(customer);
				activity.setEmployee(employee);
				ActivityType activityType = new ActivityType();
				activityType.setCode("0"+i);
				activityType.setDescription("Desc 0"+i);
				activity.setActivityType(activityType);
				day.setHours(8D);
				day.setActivity(activity);
				day.setNote("NOTA "+i);
				days.add(day);
			}
			return days;
		}
		
		
		private int getDaysOnMonth(int year, int month) {			
			YearMonth yearMonthObject = YearMonth.of(year,month);
			return yearMonthObject.lengthOfMonth();   
		}
}
