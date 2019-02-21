package com.intendentapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intendentapp.dtomodel.DayReportItemEntity;

@Repository
@Transactional(readOnly = true)
public class DayReportItemRepositoryImpl implements DayReportItemRepositoryCustom {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<DayReportItemEntity> findByIdDayReport(Integer idDayReport) {
		Query query = entityManager.createNativeQuery("SELECT * FROM intendentdb.day_report_item "+
				"WHERE id_day_report LIKE ?", DayReportItemEntity.class);
		query.setParameter(1, idDayReport);
		return query.getResultList();
	}
	
	

}
