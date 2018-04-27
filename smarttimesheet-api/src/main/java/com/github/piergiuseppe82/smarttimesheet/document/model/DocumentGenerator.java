package com.github.piergiuseppe82.smarttimesheet.document.model;

import java.io.File;

public interface DocumentGenerator {

	public Document toDocument();

	public Document toPdf();

	public void setDocumentData(DocumentData documentData);

	void setDocumentTemplate(File documentTemplate);

}
