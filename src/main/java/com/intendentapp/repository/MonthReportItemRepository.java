package com.intendentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intendentapp.dtomodel.MonthReportItemEntity;

public interface MonthReportItemRepository extends JpaRepository<MonthReportItemEntity, Integer>, MonthReportItemRepositoryCustom {
}
