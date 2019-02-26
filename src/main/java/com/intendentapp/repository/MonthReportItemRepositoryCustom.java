package com.intendentapp.repository;

import java.util.Date;
import java.util.List;

import com.intendentapp.dtomodel.MonthReportItemEntity;

public interface MonthReportItemRepositoryCustom {
	List<MonthReportItemEntity> findByReportDate(Date date);
}
