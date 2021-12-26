package com.niit.products.repository;

import com.niit.products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

    @Query("{'product.productId':{$in:[?0]}}")
    List<Product> findAllProductFromCode(int productId);
}
