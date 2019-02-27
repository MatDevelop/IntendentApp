package com.intendentapp.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intendentapp.dtomodel.MonthReportItemEntity;

@Repository
@Transactional(readOnly = true)
public class MonthReportItemRepositoryImpl implements MonthReportItemRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<MonthReportItemEntity> findByReportDate(Date date) {
		Query query = entityManager.createNativeQuery("SELECT * FROM intendentdb.month_report_item " +
				"WHERE date = ?", MonthReportItemEntity.class);
		query.setParameter(1, date);
		return query.getResultList();
	}

}
