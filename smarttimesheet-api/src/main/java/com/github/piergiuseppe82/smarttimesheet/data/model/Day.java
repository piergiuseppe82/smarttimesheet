package com.github.piergiuseppe82.smarttimesheet.data.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Day {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private LocalDate date;
	
	private Double hours;
	
	private String note;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}	
	
	
	
}
