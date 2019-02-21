package com.intendentapp.repository;

import java.util.List;

import com.intendentapp.dtomodel.ProductEntity;

public interface ProductRepositoryCustom  {
    List<ProductEntity> findByName(String name);
}
