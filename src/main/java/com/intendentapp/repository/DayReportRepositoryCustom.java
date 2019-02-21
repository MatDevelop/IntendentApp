package com.intendentapp.repository;

import java.util.Date;
import java.util.List;

import com.intendentapp.dtomodel.DayReportEntity;

public interface DayReportRepositoryCustom {
	List<DayReportEntity> findByDate(Date date);
}
