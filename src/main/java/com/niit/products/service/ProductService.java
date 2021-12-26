package com.niit.products.service;

import com.niit.products.exception.ProductAlreadyExistsException;
import com.niit.products.exception.ProductNotFoundException;
import com.niit.products.model.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product) throws ProductAlreadyExistsException;

    boolean deleteProduct(int productId) throws ProductNotFoundException;

    List<Product> getProductDetails() throws Exception;

    List<Product> getAllProduct(int product) throws ProductNotFoundException;

    Product updateProduct(Product product, int productId);
}
