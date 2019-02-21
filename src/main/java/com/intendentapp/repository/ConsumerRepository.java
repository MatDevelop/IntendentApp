package com.intendentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intendentapp.dtomodel.ConsumerEntity;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Integer> {
}
