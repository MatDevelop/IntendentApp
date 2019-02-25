package com.intendentapp.testutils;

import com.intendentapp.model.Card;

public class CardTestUtils {
	
	private CardTestUtils() {}
	
	public static Card createTestCardModel() {
		Card card = new Card();
		card.setName("Schab b/k");
		card.setNumber("130");
		card.setShopName("CEKCYN");
		card.setUnit("kg");
		card.setRowNumbers("1,2,3,4");
		card.setPrzychodPurchaseDates("2019-02-25,,,");
		card.setPrzychodUnitprices("3.30,,,");
		card.setPrzychodAmounts("2,,,");
		card.setPrzychodValues("6.60,,,");
		card.setRozchodDatesOut("2019-02-25,2019-02-26,,");
		card.setRozchodUnitprices("3.30,3.30,,");
		card.setRozchodAmounts("1,1,,");
		card.setRozchodValues("3.30,3.30,,");
		card.setRozchodStates("3.30,0,,");
		card.setRozchodRemainedValues("1,0,,");
		card.setRozchodReportNumbers("1,2,,");

		return card;
	}
	
}
