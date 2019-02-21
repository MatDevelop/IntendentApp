package com.intendentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intendentapp.dtomodel.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String>, ProductRepositoryCustom {

}
