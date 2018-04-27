package com.github.piergiuseppe82.smarttimesheet.document.model;

import java.io.File;

public class Document {
	private String extension;
	private String name;
	private File file;
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
