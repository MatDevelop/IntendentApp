package com.intendentapp.dbservices;

import com.intendentapp.dtomodel.ProductEntity;
import com.intendentapp.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductService {
	
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findAll(){
        List<ProductEntity> productEntities = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()){
            productEntities.add(productEntity);
        }
        return productEntities;
    }

    public List<ProductEntity> findByName(String name){
        return productRepository.findByName(name);
    }

    public ProductEntity findProduct(String number){
        return productRepository.findOne(number);
    }
    public void save(ProductEntity product){
        productRepository.save(product);
    }
    public void delete(String number){
        productRepository.delete(number);
    }
}
