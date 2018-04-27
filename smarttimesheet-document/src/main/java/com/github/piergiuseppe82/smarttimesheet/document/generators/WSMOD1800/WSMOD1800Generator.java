package com.github.piergiuseppe82.smarttimesheet.document.generators.WSMOD1800;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.github.piergiuseppe82.smarttimesheet.data.model.Day;
import com.github.piergiuseppe82.smarttimesheet.document.generators.DocumentGenerator;
import com.github.piergiuseppe82.smarttimesheet.document.model.Document;
import com.github.piergiuseppe82.smarttimesheet.document.model.DocumentData;

@Component
public class WSMOD1800Generator implements DocumentGenerator{
	private File template;
	private DocumentData documentData;
	@Override
	public Document toDocument() {
		Document ret = null;
		try {
			FileInputStream fIPS = new FileInputStream(template);
			HSSFWorkbook wb = new HSSFWorkbook(fIPS);
			fIPS.close();
			HSSFSheet worksheet = wb.getSheetAt(0);

			// SET CUSTOMER
			HSSFRow clienteRow = worksheet.getRow(3);
			clienteRow.getCell(3).setCellValue(documentData.getCustomer().getName());

			// SET EMPLOYEE
			HSSFRow nominativoRow = worksheet.getRow(5);
			nominativoRow.getCell(3).setCellValue(documentData.getEmployee().getName()+" "+documentData.getEmployee().getLastName());
			
			// SET MONTH
			HSSFRow annomeseRow = worksheet.getRow(7);
			List<Day> days = documentData.getDays();
			annomeseRow.getCell(2).setCellValue(days.get(0).getDate().getMonthValue());
			annomeseRow.getCell(4).setCellValue(days.get(0).getDate().getYear());
			
			// SET DAYS
			int indexDayRow = 11;
			for (Day day : days) {
				 HSSFRow dayRow = worksheet.getRow(indexDayRow);
				 dayRow.getCell(2).setCellValue(day.getActivity().getActivityType().getCode());
				 dayRow.getCell(3).setCellValue(day.getActivity().getActivityType().getDescription());
				 dayRow.getCell(4).setCellValue(day.getHours());
				 dayRow.getCell(5).setCellValue(day.getNote());
				 
				 indexDayRow++;
			}			
			fIPS.close(); 
			FileOutputStream output_file = new FileOutputStream("target/test.xls");// Open FileOutputStream to write updates
			wb.write(output_file);
			wb.close();
			output_file.close(); 
			ret = new Document();
			ret.setExtension("xls");
			ret.setName("test");
			ret.setFile(new File("target/test.xls"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public Document toPdf() {
		Document ret = null;
		Process process = null;
		try {
			Document document = toDocument();
			//pdf:calc_pdf_Export
			Thread.sleep(1000);
			process = Runtime.getRuntime().exec("libreoffice --headless --convert-to pdf --outdir  target "+(document.getFile()).getAbsolutePath());
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			process.waitFor();
			ret = new Document();
			ret.setExtension("pdf");
			ret.setName("test");
			ret.setFile(new File(document.getFile().getAbsolutePath().replaceAll("\\."+document.getExtension(), ".pdf")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void setDocumentData(DocumentData documentData) {
		this.documentData = documentData;
		
	}
	
	@Override
	public void setDocumentTemplate(File template) {
		this.template = template;
		
	}

}
