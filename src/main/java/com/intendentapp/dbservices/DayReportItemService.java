package com.intendentapp.dbservices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.intendentapp.dtomodel.DayReportItemEntity;
import com.intendentapp.repository.DayReportItemRepository;

@Repository
@Transactional
public class DayReportItemService {
	
	private final DayReportItemRepository dayReportItemRepository;

	public DayReportItemService(DayReportItemRepository dayReportItemRepository) {
		this.dayReportItemRepository = dayReportItemRepository;
	}
	
	public List<DayReportItemEntity> findAll(){
		return dayReportItemRepository.findAll();
	}
	
	public DayReportItemEntity findDayReportItemEntity(Integer id) {
		return dayReportItemRepository.findOne(id);
	}
	
	public void save(DayReportItemEntity dayReportItemEntity) {
		dayReportItemRepository.save(dayReportItemEntity);
	}
	
	public void delete(Integer id) {
		dayReportItemRepository.delete(id);
	}
	
	public List<DayReportItemEntity> findByIdDayReport(Integer idDayReport){
		return dayReportItemRepository.findByIdDayReport(idDayReport);
	}
	

}
