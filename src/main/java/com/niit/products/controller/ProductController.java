package com.niit.products.controller;


import com.niit.products.exception.ProductAlreadyExistsException;
import com.niit.products.exception.ProductNotFoundException;
import com.niit.products.model.Product;
import com.niit.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products/")
public class ProductController {

    private ProductService productService;

    private ResponseEntity responseEntity;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("product")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) throws ProductAlreadyExistsException {
        try {
            productService.saveProduct(product);
            responseEntity = new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (ProductAlreadyExistsException e) {
            throw new ProductAlreadyExistsException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error try save after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

    @GetMapping("product")
    public ResponseEntity<?> getProductDetails() {
        try {
            List<Product> productList = productService.getProductDetails();
            responseEntity = new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error try save after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) throws ProductNotFoundException {
        try {
            productService.deleteProduct(productId);
            responseEntity = new ResponseEntity<>("Successfully deleted!!!!", HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("product/{productCode}")
    public ResponseEntity<?> getAllProduct(@PathVariable int productCode) {
        try {
            responseEntity = new ResponseEntity<>(productService.getAllProduct(productCode), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error try save after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @PutMapping("product/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("productId") int productId) {
        return new ResponseEntity<>(productService.updateProduct(product, productId), HttpStatus.OK);
    }
}