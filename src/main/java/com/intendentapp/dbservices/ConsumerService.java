package com.intendentapp.dbservices;

import com.intendentapp.dtomodel.ConsumerEntity;
import com.intendentapp.repository.ConsumerRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ConsumerService {
	
    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public List<ConsumerEntity> findAll(){
        return consumerRepository.findAll();
    }

    public ConsumerEntity findConsumer(Integer id){
        return consumerRepository.findOne(id);
    }

    public void save(ConsumerEntity consumerEntity){ 
    	consumerRepository.save(consumerEntity);
    }
    
    public void delete(Integer id){
        consumerRepository.delete(id);
    }
}
