package com.intendentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intendentapp.dtomodel.DayReportEntity;

public interface DayReportRespository extends JpaRepository<DayReportEntity, Integer>, DayReportRepositoryCustom{

}
