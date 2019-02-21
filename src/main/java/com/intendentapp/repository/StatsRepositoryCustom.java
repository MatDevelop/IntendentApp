package com.intendentapp.repository;

import java.util.List;

import com.intendentapp.dtomodel.StatsEntity;

public interface StatsRepositoryCustom {
    List<StatsEntity> findByMonth_year(String month_year);
}
