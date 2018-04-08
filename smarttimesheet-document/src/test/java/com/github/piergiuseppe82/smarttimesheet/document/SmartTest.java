package com.github.piergiuseppe82.smarttimesheet.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class SmartTest {

	@Test
	public void modifyTestPoi() throws IOException, InterruptedException {
		FileInputStream fIPS = new FileInputStream(this.getClass().getResource("/TemplateWideside.xls").getFile());
		HSSFWorkbook wb = new HSSFWorkbook(fIPS); // If there is already data in a workbook
		fIPS.close();
		HSSFSheet worksheet = wb.getSheetAt(0);
		
		// SET ANNO MESE
		HSSFRow annomeseRow = worksheet.getRow(7);
		annomeseRow.getCell(2).setCellValue(3);
		annomeseRow.getCell(4).setCellValue(2018);

		// SET Cliente
		HSSFRow clienteRow = worksheet.getRow(3);
		clienteRow.getCell(3).setCellValue("CLIENTE");

		// SET Nominativo
		HSSFRow nominativoRow = worksheet.getRow(5);
		nominativoRow.getCell(3).setCellValue("Pippo");

		fIPS.close(); // Close the InputStream
		FileOutputStream output_file = new FileOutputStream("target/test.xls");// Open FileOutputStream to write updates
		wb.write(output_file); // write changes
		wb.close();
		output_file.close(); // close the stream
	}

	@Test
	public void testPdf() throws Exception {

		Process process = null;
		try {
			process = Runtime.getRuntime().exec("libreoffice --headless --convert-to pdf:calc_pdf_Export --outdir  target "+(new File("target/test.xls")).getAbsolutePath());
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			return;
		}
	}

}
