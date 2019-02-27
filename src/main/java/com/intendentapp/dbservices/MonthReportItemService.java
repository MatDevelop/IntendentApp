package com.intendentapp.dbservices;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.intendentapp.dtomodel.MonthReportEntity;
import com.intendentapp.dtomodel.MonthReportItemEntity;
import com.intendentapp.repository.MonthReportItemRepository;

@Repository
@Transactional
public class MonthReportItemService {
	
	private final MonthReportItemRepository monthReportItemRepository;

	public MonthReportItemService(MonthReportItemRepository monthReportItemRepository) {
		this.monthReportItemRepository = monthReportItemRepository;
	}
	
	public List<MonthReportItemEntity> findAll(){
		return monthReportItemRepository.findAll();
	}
	
	public MonthReportItemEntity findMonthReportItem(Integer id) {
		return monthReportItemRepository.findOne(id);
	}
	
	public void save(MonthReportItemEntity monthReportItemEntity) {
		monthReportItemRepository.save(monthReportItemEntity);
	}
	
	public List<MonthReportItemEntity> findByReportDate(Date date) {
		return monthReportItemRepository.findByReportDate(date);
	}
	
	public void delete(Integer id) {
		monthReportItemRepository.delete(id);
	}
}
