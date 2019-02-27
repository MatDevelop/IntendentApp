package com.intendentapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intendentapp.dtomodel.MonthReportEntity;

@Repository
@Transactional(readOnly = true)
public class MonthReportRepositoryImpl implements MonthReportRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<MonthReportEntity> findByForMonth(String forMonth) {
		Query query = entityManager.createNativeQuery("SELECT * FROM intendentdb.month_report " +
				"WHERE for_month = ?", MonthReportEntity.class);
		query.setParameter(1, forMonth);
		return query.getResultList();
	}
}
