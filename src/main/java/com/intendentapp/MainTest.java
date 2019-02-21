package com.intendentapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.intendentapp.dbservices.DayReportService;
import com.intendentapp.dtomodel.CardEntity;
import com.intendentapp.dtomodel.CardPrzychodEntity;
import com.intendentapp.dtomodel.CardRozchodEntity;
import com.intendentapp.dtomodel.DayReportEntity;
import com.intendentapp.dtomodel.ProductEntity;
import com.intendentapp.generator.GenerateDayReport;
import com.intendentapp.model.DayReport;
import com.intendentapp.repository.DayReportRespository;

public class MainTest {

	public DayReport metodka() {
		DayReport dr = new DayReport();
    	dr.setDinner1("Obiad 1");
    	dr.setDinner2("Obiad 2");
    	dr.setDinner3("Obiad 3");
    	dr.setDinner4("Obiad 4");
    	dr.setDate("2000-01-01");
    	dr.setReportNumber("1");
    	dr.setPodstawowa("10");
    	dr.setPrzedszkole("6");
    	dr.setNauczyciele("5");
    	dr.setZerowka("");
    	dr.setPodzialPodstawowa("5+5");
    	dr.setPodzialPrzedszkole("3+3");
    	dr.setPodzialZerowka("");
    	dr.setProduct("Cebula");
    	dr.setAmount("2");
    	dr.setUnitprice("4.34");
    	dr.setPositionValue("8.62");
    	dr.setNumber("1s");
    
    	return dr;
    	
    	/*GenerateDayReport generateDayReport = new GenerateDayReport(dr);
    	
    	ProductEntity pe = new ProductEntity();
    	pe.setName("Arbuz");
    	pe.setNumber("1");
    	pe.setUnit("kg");
    	pe.setUnitprice(3.0);
    	List<ProductEntity> productEntityList = new ArrayList<ProductEntity>();
    	productEntityList.add(pe);
    	generateDayReport.generate(productEntityList);*/
    	

	}
	
	public CardEntity returnCardEntity() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		CardEntity ce = new CardEntity();
		ce.setName("Parówka cienka");
		ce.setNumber("1");
		ce.setShopName("Cekcyn");
		ce.setUnit("szt.");
		
		CardPrzychodEntity cpe = new CardPrzychodEntity();
		CardRozchodEntity cre1 = new CardRozchodEntity();
		CardRozchodEntity cre2 = new CardRozchodEntity();
		CardPrzychodEntity cpe2 = new CardPrzychodEntity();
		CardRozchodEntity cre3 = new CardRozchodEntity();
		
		try {
			
			cpe.setPurchaseDate(formatter.parse("2019-01-15"));
			cpe.setUnitprice(3.00);
			cpe.setAmount(6.00);
			cpe.setValue(18.00);
			
			
			cre1.setDateOut(formatter.parse("2019-01-16"));
			cre1.setReportNumber(1);
			cre1.setUnitprice(3.00);
			cre1.setAmount(2.00);
			cre1.setValue(6.00);
			cre1.setState(4.00);
			cre1.setRemainedValue(12.00);
			
			
			cre2.setDateOut(formatter.parse("2019-01-17"));
			cre2.setReportNumber(2);
			cre2.setUnitprice(3.00);
			cre2.setAmount(4.00);
			cre2.setValue(12.00);
			cre2.setState(0.00);
			cre2.setRemainedValue(0.00);
			
			
			cpe2.setPurchaseDate(formatter.parse("2019-01-20"));
			cpe2.setUnitprice(3.00);
			cpe2.setAmount(2.00);
			cpe2.setValue(6.00);
			
			cre3.setDateOut(formatter.parse("2019-01-21"));
			cre3.setReportNumber(3);
			cre3.setUnitprice(3.00);
			cre3.setAmount(2.00);
			cre3.setValue(6.00);
			cre3.setState(0.00);
			cre3.setRemainedValue(0.00);
		}catch (Exception e) {
			
		}
		
		List<CardPrzychodEntity> cardPrzychodList = new ArrayList<>();
		List<CardRozchodEntity> cardRozchodList = new ArrayList<>();
		
		cardPrzychodList.add(cpe);
		cardPrzychodList.add(cpe2);
		
		cardRozchodList.add(cre2);
		cardRozchodList.add(cre1);
		cardRozchodList.add(cre3);
		
		ce.setCardPrzychodList(cardPrzychodList);
		ce.setCardRozchodList(cardRozchodList);
		
		return ce;
		
		
	}

}
