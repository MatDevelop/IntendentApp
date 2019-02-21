package com.intendentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intendentapp.dtomodel.StatsEntity;

public interface StatsRepository extends JpaRepository<StatsEntity, Integer>, StatsRepositoryCustom {
}
