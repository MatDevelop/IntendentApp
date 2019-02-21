package com.intendentapp.dbservices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.intendentapp.dtomodel.CardEntity;
import com.intendentapp.repository.CardRepository;

@Repository
@Transactional
public class CardService {
	private final CardRepository cardRepository;

	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	public List<CardEntity> findAll(){
		return cardRepository.findAll();
	}
	
	public CardEntity findCard(Integer id) {
		return cardRepository.findOne(id);
	}
	
	public void save(CardEntity cardEntity) {
		cardRepository.save(cardEntity);
	}
	
}
