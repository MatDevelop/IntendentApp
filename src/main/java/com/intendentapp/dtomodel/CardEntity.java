package com.intendentapp.dtomodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity(name="card_document")
public class CardEntity implements Serializable{
	
	@Id
	@Column(name = "id_card_document")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCardDocument;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "active")
	private Integer active;
	
	@Column(name = "shop_name")
	private String shopName;
	
	@Column(name = "unit")
	private String unit;
	
	@OneToMany(cascade = CascadeType.ALL,
	        orphanRemoval = true)
	@JoinColumn(name = "id_card_document")
	private List<CardPrzychodEntity> cardPrzychodList;
	
	@OneToMany(cascade = CascadeType.ALL,
	        orphanRemoval = true)
	@JoinColumn(name = "id_card_document")
	private List<CardRozchodEntity> cardRozchodList;
	
	public CardEntity() {}

	public CardEntity(Integer idCardDocument, String name, String number, Integer active, String shopName, String unit,
			List<CardPrzychodEntity> cardPrzychodList, List<CardRozchodEntity> cardRozchodList) {
		super();
		this.idCardDocument = idCardDocument;
		this.name = name;
		this.number = number;
		this.active = active;
		this.shopName = shopName;
		this.unit = unit;
		this.cardPrzychodList = cardPrzychodList;
		this.cardRozchodList = cardRozchodList;
	}

	public Integer getIdCardDocument() {
		return idCardDocument;
	}

	public void setIdCardDocument(Integer idCardDocument) {
		this.idCardDocument = idCardDocument;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<CardPrzychodEntity> getCardPrzychodList() {
		return cardPrzychodList;
	}

	public void setCardPrzychodList(List<CardPrzychodEntity> cardPrzychodList) {
		this.cardPrzychodList = cardPrzychodList;
	}

	public List<CardRozchodEntity> getCardRozchodList() {
		return cardRozchodList;
	}

	public void setCardRozchodList(List<CardRozchodEntity> cardRozchodList) {
		this.cardRozchodList = cardRozchodList;
	}
}
