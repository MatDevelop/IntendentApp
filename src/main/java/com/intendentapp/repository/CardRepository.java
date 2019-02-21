package com.intendentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intendentapp.dtomodel.CardEntity;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {

}
