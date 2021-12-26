package com.niit.products.repository;

import com.niit.products.model.Product;
import com.niit.products.model.ProductDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product, product2;
    private ProductDetails productDetails1, productDetails2;

    @BeforeEach
    public void setUp() {
        product2 = new Product(1, "laptop", productDetails1);
        productDetails1 = new ProductDetails(101, "Acer", 200);

        product = new Product(2, "speaker", productDetails2);
        productDetails2 = new ProductDetails(102, "Dolby", 100);

    }

    @AfterEach
    public void tearDown() {
        product = null;
        product2 = null;
        productDetails1 = null;
        productDetails2 = null;
        productRepository.deleteAll();
    }

    @Test
    public void givenProductToSaveReturnProduct() {
        productRepository.insert(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        //assertNotNull(product1);
        assertEquals(product.getProductId(), product1.getProductId());
    }


    @Test
    public void givenTrackReturnAllProduct() {
        productRepository.insert(product2);
        List<Product> list = productRepository.findAll();
        assertEquals(1, list.size());
        assertEquals("laptop", list.get(0).getProductName());
    }


    @Test
    public void givenProductToDeleteShouldReturnDeleteProduct() {
        productRepository.insert(product);
        Product product1 = productRepository.findById(product.getProductId()).get();
        productRepository.delete(product1);
        assertEquals(Optional.empty(), productRepository.findById(product1.getProductId()));

    }
}
