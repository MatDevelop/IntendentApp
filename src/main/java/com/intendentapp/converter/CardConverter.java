package com.intendentapp.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.intendentapp.dtomodel.CardEntity;
import com.intendentapp.dtomodel.CardPrzychodEntity;
import com.intendentapp.dtomodel.CardRozchodEntity;
import com.intendentapp.generator.GenerateCard;
import com.intendentapp.insert.InsertToCardExcelReport;
import com.intendentapp.model.Card;
import com.intendentapp.utils.Utils;

public class CardConverter {

	private static final Logger log = LogManager.getLogger(CardConverter.class);
	
	private CardConverter() {}
	
	public static CardEntity convert(Card card, CardEntity cardEntity, GenerateCard generateCard) {
		InsertToCardExcelReport insert = generateCard.getInsertToCard();
		List<CardPrzychodEntity> cardPrzychodEntityList = new ArrayList<>();
		List<CardRozchodEntity> cardRozchodEntityList = new ArrayList<>();
		
		cardEntity.setName(card.getName());
		cardEntity.setNumber(card.getNumber());
		cardEntity.setShopName(card.getShopName());
		cardEntity.setUnit(card.getUnit());
		
		for(int i=0; i < insert.getRowNumbers().size(); i++) {
			//jeśli daty rozchodu i przychodu są puste to znaczy, ze nie ma więcej wierszy
			if(Utils.isEmptyRow(insert.getPrzychodPurchaseDates().get(i), insert.getRozchodDatesOut().get(i))) {	
				break;
			}
			CardPrzychodEntity cardPrzychodEntity = new CardPrzychodEntity();
			cardPrzychodEntity.setPurchaseDate(Utils.convertStringToDate(insert.getPrzychodPurchaseDates().get(i)));
			cardPrzychodEntity.setUnitprice(Utils.convertStringToDouble(insert.getPrzychodUnitprices().get(i)));
			cardPrzychodEntity.setAmount(Utils.convertStringToDouble(insert.getPrzychodAmounts().get(i)));
			cardPrzychodEntity.setValue(Utils.convertStringToDouble(insert.getPrzychodValues().get(i)));
			cardPrzychodEntity.setRowNumber(Utils.convertStringToInteger(insert.getRowNumbers().get(i)));
			cardPrzychodEntityList.add(cardPrzychodEntity);
			
			CardRozchodEntity cardRozchodEntity = new CardRozchodEntity();
			cardRozchodEntity.setDateOut(Utils.convertStringToDate(insert.getRozchodDatesOut().get(i)));
			cardRozchodEntity.setUnitprice(Utils.convertStringToDouble(insert.getRozchodUnitprices().get(i)));
			cardRozchodEntity.setAmount(Utils.convertStringToDouble(insert.getRozchodAmounts().get(i)));
			cardRozchodEntity.setValue(Utils.convertStringToDouble(insert.getRozchodValues().get(i)));
			cardRozchodEntity.setState(Utils.convertStringToDouble(insert.getRozchodStates().get(i)));
			cardRozchodEntity.setRemainedValue(Utils.convertStringToDouble(insert.getRozchodRemainedValues().get(i)));
			cardRozchodEntity.setReportNumber(Utils.convertStringToInteger(insert.getRozchodReportNumbers().get(i)));
			cardRozchodEntity.setRowNumber(Utils.convertStringToInteger(insert.getRowNumbers().get(i)));
			cardRozchodEntityList.add(cardRozchodEntity);
		}
		
		cardEntity.setCardPrzychodList(cardPrzychodEntityList);
		cardEntity.setCardRozchodList(cardRozchodEntityList);
		
		return cardEntity;
	}
}
