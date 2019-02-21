package com.intendentapp.repository;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.intendentapp.dtomodel.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductEntity> findByName(String name) {
        Query query = entityManager.createNativeQuery("SELECT * FROM intendentdb.products " +
                "WHERE name LIKE ?", ProductEntity.class);
        query.setParameter(1, name);
        return query.getResultList();
    }
}
