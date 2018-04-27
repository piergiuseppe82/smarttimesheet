package com.github.piergiuseppe82.smarttimesheet.document.generators;

import java.io.File;

import com.github.piergiuseppe82.smarttimesheet.document.model.Document;
import com.github.piergiuseppe82.smarttimesheet.document.model.DocumentData;

public interface DocumentGenerator {

	public Document toDocument();

	public Document toPdf();

	public void setDocumentData(DocumentData documentData);

	void setDocumentTemplate(File documentTemplate);

}
