package com.github.piergiuseppe82.smarttimesheet.data.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.github.piergiuseppe82.smarttimesheet.data.model.Day;

public interface DayRepository extends PagingAndSortingRepository<Day, Long> {

	Page<Day> findByDateGreaterThanEqualAndDateLessThan(
			@DateTimeFormat(pattern = "yyyyMMdd") @Param("from") LocalDate from,
			@DateTimeFormat(pattern = "yyyyMMdd") @Param("to") LocalDate to,Pageable pageable);
	
	Page<Day> findByDate(
			@DateTimeFormat(pattern = "yyyyMMdd") @Param("date") LocalDate date,Pageable pageable);
	
	Page<Day> findByDateAndActivityId(
			@DateTimeFormat(pattern = "yyyyMMdd") @Param("date") LocalDate date,
			@Param("activity") Long id,
			Pageable pageable);
}
