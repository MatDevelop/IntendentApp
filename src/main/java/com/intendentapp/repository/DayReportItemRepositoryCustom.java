package com.intendentapp.repository;

import java.util.List;

import com.intendentapp.dtomodel.DayReportItemEntity;

public interface DayReportItemRepositoryCustom {
	List<DayReportItemEntity> findByIdDayReport(Integer idDayReport);

}
