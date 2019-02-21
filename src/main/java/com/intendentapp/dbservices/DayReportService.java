package com.intendentapp.dbservices;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.intendentapp.dtomodel.DayReportEntity;
import com.intendentapp.repository.DayReportRespository;

@Repository
@Transactional
public class DayReportService {
	
	private final DayReportRespository dayReportRespository;

	public DayReportService(DayReportRespository dayReportRespository) {
		this.dayReportRespository = dayReportRespository;
	}
	
	public List<DayReportEntity> findAll(){
		return dayReportRespository.findAll();
	}
	
	public DayReportEntity findDayReport(Integer id) {
		return dayReportRespository.findOne(id);
	}
	
	public void save(DayReportEntity dayReportEntity) {
		dayReportRespository.save(dayReportEntity);
	}
	
	public List<DayReportEntity> findByDate(Date date){
		return dayReportRespository.findByDate(date);
	}
	
	
	
}
