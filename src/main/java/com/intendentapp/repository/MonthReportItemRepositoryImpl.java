package com.intendentapp.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.intendentapp.dtomodel.MonthReportEntity;
import com.intendentapp.dtomodel.MonthReportItemEntity;

public class MonthReportItemRepositoryImpl implements MonthReportItemRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<MonthReportItemEntity> findByReportDate(Date date) {
		Query query = entityManager.createNamedQuery("SELECT * FROM intendentdb.month_report_item " +
				"WHERE date = ?", MonthReportItemEntity.class);
		query.setParameter(1, date);
		return query.getResultList();
	}

}
