package com.niit.products.service;

import com.niit.products.exception.ProductAlreadyExistsException;
import com.niit.products.exception.ProductNotFoundException;
import com.niit.products.model.Product;
import com.niit.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product saveProduct(Product product) throws ProductAlreadyExistsException {
        if (productRepository.findById(product.getProductId()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int productId) throws ProductNotFoundException {
        boolean flag = false;
        if (productRepository.findById(productId).isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            productRepository.deleteById(productId);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Product> getProductDetails() throws Exception {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProduct(int product) throws ProductNotFoundException {
        if (productRepository.findAllProductFromCode(product).isEmpty()) {
            throw new ProductNotFoundException();
        }
        return productRepository.findAllProductFromCode(product);
    }

    @Override
    public Product updateProduct(Product product, int productId) {
        if (productRepository.findById(productId).isEmpty()) {
            return null;
        }
        return productRepository.save(product);
    }
}
