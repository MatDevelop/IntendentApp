package com.intendentapp.dtomodel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name = "card_przychod")
public class CardPrzychodEntity implements Serializable {

	@Id
	@Column(name = "id_card_przychod")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCardPrzychod;
	
	@Column(name = "purchase_date")
	private Date purchaseDate;
	
	@Column(name = "unitprice")
	private Double unitprice;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "`row_number`")
	private Integer rowNumber;
	
	@Column(name = "id_card_document")
	private Integer idCardDocument;
	
	public CardPrzychodEntity() {}

	public CardPrzychodEntity(Integer idCardPrzychod, Date purchaseDate, Double unitprice, Double amount, Double value,
			Integer rowNumber, Integer idCardDocument) {
		super();
		this.idCardPrzychod = idCardPrzychod;
		this.purchaseDate = purchaseDate;
		this.unitprice = unitprice;
		this.amount = amount;
		this.value = value;
		this.rowNumber = rowNumber;
		this.idCardDocument = idCardDocument;
	}

	public Integer getIdCardPrzychod() {
		return idCardPrzychod;
	}

	public void setIdCardPrzychod(Integer idCardPrzychod) {
		this.idCardPrzychod = idCardPrzychod;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getIdCardDocument() {
		return idCardDocument;
	}

	public void setIdCardDocument(Integer idCardDocument) {
		this.idCardDocument = idCardDocument;
	}
}
