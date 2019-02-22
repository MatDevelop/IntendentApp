package com.intendentapp.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intendentapp.dtomodel.DayReportEntity;

import javax.persistence.Query;

@Repository
@Transactional(readOnly = true)
public class DayReportRepositoryImpl implements DayReportRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<DayReportEntity> findByDate(Date date) {
		Query query = entityManager.createNativeQuery("SELECT * FROM intendentdb.day_report_document "+
				"WHERE date LIKE ?", DayReportEntity.class);
		query.setParameter(1, date);
		return query.getResultList();
	}
}
