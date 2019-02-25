package com.intendentapp.dtomodel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name = "card_rozchod")
public class CardRozchodEntity implements Serializable {

	@Id
	@Column(name = "id_card_rozchod")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCardRozchod;
	
	@Column(name = "date_out")
	private Date dateOut;
	
	@Column(name = "report_number")
	private Integer reportNumber;
	
	@Column(name = "unitprice")
	private Double unitprice;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "state")
	private Double state;
	
	@Column(name = "remained_value")
	private Double remainedValue;
	
	@Column(name = "`row_number`")
	private Integer rowNumber;
	
	@Column(name = "id_card_document")
	private Integer idCardDocument;
	
	public CardRozchodEntity() {}

	public CardRozchodEntity(Integer idCardRozchod, Date dateOut, Integer reportNumber, Double unitprice, Double amount,
			Double value, Double state, Double remainedValue, Integer rowNumber, Integer idCardDocument) {
		super();
		this.idCardRozchod = idCardRozchod;
		this.dateOut = dateOut;
		this.reportNumber = reportNumber;
		this.unitprice = unitprice;
		this.amount = amount;
		this.value = value;
		this.state = state;
		this.remainedValue = remainedValue;
		this.rowNumber = rowNumber;
		this.idCardDocument = idCardDocument;
	}

	public Integer getIdCardRozchod() {
		return idCardRozchod;
	}

	public void setIdCardRozchod(Integer idCardRozchod) {
		this.idCardRozchod = idCardRozchod;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Integer getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(Integer reportNumber) {
		this.reportNumber = reportNumber;
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

	public Double getState() {
		return state;
	}

	public void setState(Double state) {
		this.state = state;
	}

	public Double getRemainedValue() {
		return remainedValue;
	}

	public void setRemainedValue(Double remainedValue) {
		this.remainedValue = remainedValue;
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
