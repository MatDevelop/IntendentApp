package com.intendentapp.repository;

import java.util.List;

import com.intendentapp.dtomodel.MonthReportEntity;

public interface MonthReportRepositoryCustom {
	List<MonthReportEntity> findByForMonth(String forMonth);
}
