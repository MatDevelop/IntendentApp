package com.intendentapp.dbservices;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.intendentapp.dtomodel.CardRozchodEntity;
import com.intendentapp.repository.CardRozchodRepository;

@Repository
@Transactional
public class CardRozchodService {
	
	private final CardRozchodRepository cardRozchodRepository;
	
	public CardRozchodService(CardRozchodRepository cardRozchodRepository) {
		this.cardRozchodRepository = cardRozchodRepository;
	}
	
	public List<CardRozchodEntity> findAll(){
		return cardRozchodRepository.findAll();
	}
	
	public CardRozchodEntity findCardPrzychod(Integer id) {
		return cardRozchodRepository.findOne(id);
	}
	
	public void save(CardRozchodEntity cardRozchodEntity) {
		cardRozchodRepository.save(cardRozchodEntity);
	}
	
	public void delete(Integer id) {
		cardRozchodRepository.delete(id);
	}
}
