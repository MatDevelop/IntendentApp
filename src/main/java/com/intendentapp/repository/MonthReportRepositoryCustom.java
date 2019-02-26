package com.intendentapp.repository;

import com.intendentapp.dtomodel.MonthReportEntity;

public interface MonthReportRepositoryCustom {
	MonthReportEntity findByForMonth(String forMonth);
}
