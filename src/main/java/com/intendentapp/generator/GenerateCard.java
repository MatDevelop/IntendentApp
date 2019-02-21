package com.intendentapp.generator;

import com.intendentapp.insert.InsertToCardExcelReport;
import com.intendentapp.model.Card;

public class GenerateCard {
	private final String FILENAME="src/main/webapp/static/xlsx/cardrep.xlsx";
	
	private Card card;
	private Integer message;
	private InsertToCardExcelReport insertToCard;
	
	public GenerateCard() {}
	
	public GenerateCard(Card card) {
		insertToCard = new InsertToCardExcelReport(card, FILENAME);
		this.card = card;
	}
	
	public void generate() {
		insertToCard.insertAll();
		message = insertToCard.getOpenXlsx().save();
	}

	public Integer getMessage() {
		return message;
	}

	public InsertToCardExcelReport getInsertToCard() {
		return insertToCard;
	}
	
	
	
}
