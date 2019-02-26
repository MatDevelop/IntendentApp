package com.intendentapp.dbservices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.intendentapp.dtomodel.MonthReportEntity;
import com.intendentapp.repository.MonthReportRepository;

@Repository
@Transactional
public class MonthReportService {
	
	private final MonthReportRepository monthReportRepository;
	
	public MonthReportService(MonthReportRepository monthReportRepository) {
		this.monthReportRepository = monthReportRepository;
	}
	
	public List<MonthReportEntity> findAll(){
		return monthReportRepository.findAll();
	}
	
	public MonthReportEntity findMonthReport(Integer id) {
		return monthReportRepository.findOne(id);
	}
	
	public void save(MonthReportEntity monthReportEntity) {
		monthReportRepository.save(monthReportEntity);
	}
	
	public MonthReportEntity findByForMonth(String forMonth) {
		return monthReportRepository.findByForMonth(forMonth);
	}
}
