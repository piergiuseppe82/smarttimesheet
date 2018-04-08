package com.github.piergiuseppe82.smarttimesheet.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.github.piergiuseppe82.smarttimesheet.data.model.ActivityType;

public interface ActivityRepository  extends CrudRepository<ActivityType, Long> { }
