package com.intendentapp.dbservices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.intendentapp.dtomodel.CardPrzychodEntity;
import com.intendentapp.repository.CardPrzychodRepository;

@Repository
@Transactional
public class CardPrzychodService {
	private final CardPrzychodRepository cardPrzychodRepository;
	
	public CardPrzychodService(CardPrzychodRepository cardPrzychodRepository) {
		this.cardPrzychodRepository = cardPrzychodRepository;
	}
	
	public List<CardPrzychodEntity> findAll(){
		return cardPrzychodRepository.findAll();
	}
	
	public CardPrzychodEntity findCardPrzychod(Integer id) {
		return cardPrzychodRepository.findOne(id);
	}
	
	public void save(CardPrzychodEntity cardPrzychodEntity) {
		cardPrzychodRepository.save(cardPrzychodEntity);
	}
	
	public void delete(Integer id) {
		cardPrzychodRepository.delete(id);
	}
}
