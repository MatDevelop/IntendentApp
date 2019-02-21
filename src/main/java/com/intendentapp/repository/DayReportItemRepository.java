package com.intendentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intendentapp.dtomodel.DayReportItemEntity;

public interface DayReportItemRepository extends JpaRepository<DayReportItemEntity, Integer>, DayReportItemRepositoryCustom{

}
