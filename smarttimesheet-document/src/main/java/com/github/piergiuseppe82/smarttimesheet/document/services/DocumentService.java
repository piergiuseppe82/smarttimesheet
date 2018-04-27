package com.github.piergiuseppe82.smarttimesheet.document.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.piergiuseppe82.smarttimesheet.document.generators.DocumentGenerator;
import com.github.piergiuseppe82.smarttimesheet.document.model.Document;
import com.github.piergiuseppe82.smarttimesheet.document.model.DocumentData;
import com.github.piergiuseppe82.smarttimesheet.document.providers.ContextProvider;

@Service
public class DocumentService {
	
	@Autowired
	private ContextProvider contextProvider;

	public Document generateDocument(DocumentGenerator documentGenerator, DocumentData documentData) {
		documentGenerator.setDocumentData(documentData);
		return documentGenerator.toDocument();	
	}
	
	public Document generatePdf(DocumentGenerator documentGenerator,  DocumentData documentData) {
		documentGenerator.setDocumentData(documentData);
		return documentGenerator.toPdf();		
	}
	
	public Document generateDocument(String  documentGeneratorBeanNAme, DocumentData documentData) {
		DocumentGenerator documentGenerator = (DocumentGenerator) contextProvider.getBean(documentGeneratorBeanNAme);
		documentGenerator.setDocumentData(documentData);
		documentGenerator.setDocumentTemplate(new File(this.getClass().getResource("/"+documentGeneratorBeanNAme+".xls").getPath()));
		return documentGenerator.toDocument();	
	}
	
	public Document generatePdf(String documentGeneratorBeanNAme,  DocumentData documentData) {
		DocumentGenerator documentGenerator = (DocumentGenerator) contextProvider.getBean(documentGeneratorBeanNAme);
		documentGenerator.setDocumentData(documentData);
		documentGenerator.setDocumentTemplate(new File(this.getClass().getResource("/"+documentGeneratorBeanNAme+".xls").getPath()));
		return documentGenerator.toPdf();		
	}
}
