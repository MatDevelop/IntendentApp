package com.intendentapp.repository;

import org.springframework.stereotype.Repository;

import com.intendentapp.dtomodel.StatsEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<StatsEntity> findByMonth_year(String month_year) {
        Query query = entityManager.createNativeQuery("SELECT * FROM intendentdb.stats " +
                "WHERE month_year LIKE ?", StatsEntity.class);
        query.setParameter(1, month_year);
        return query.getResultList();
    }
}
