package com.intendentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intendentapp.dtomodel.MonthReportEntity;

public interface MonthReportRepository extends JpaRepository<MonthReportEntity, Integer>, MonthReportRepositoryCustom{

}
