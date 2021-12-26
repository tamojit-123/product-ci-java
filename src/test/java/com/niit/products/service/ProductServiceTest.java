package com.niit.products.service;

import com.niit.products.exception.ProductAlreadyExistsException;
import com.niit.products.exception.ProductNotFoundException;
import com.niit.products.model.Product;
import com.niit.products.model.ProductDetails;
import com.niit.products.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private Product product1, product2;
    private ProductDetails productDetails1, productDetails2;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {

        product1 = new Product(1, "laptop", productDetails1);
        productDetails1 = new ProductDetails(101, "Acer", 200);

        product2 = new Product(2, "speaker", productDetails2);
        productDetails2 = new ProductDetails(102, "Dolby", 100);
    }

    @AfterEach
    public void tearDown() {

        product1 = null;
        product2 = null;
        productDetails1 = null;
        productDetails2 = null;
    }

    @Test
    public void givenProductToSaveReturnSavedProduct() throws ProductAlreadyExistsException {
        when(productRepository.findById(product1.getProductId())).thenReturn(Optional.empty());
        when(productRepository.save(product1)).thenReturn(product1);
        assertEquals(product1, productService.saveProduct(product1));
        verify(productRepository, times(1)).save(any());
        verify(productRepository, times(1)).findById(any());

    }

    @Test
    public void givenProductToSaveReturnProductFailure() {
        when(productRepository.findById(product1.getProductId())).thenReturn(Optional.ofNullable(product1));
        assertThrows(ProductAlreadyExistsException.class, () -> productService.saveProduct(product1));
        verify(productRepository, times(0)).save(any());
        verify(productRepository, times(1)).findById(any());

    }

    @Test
    public void givenProductToDeleteShouldDeleteSuccess() throws ProductNotFoundException {

        when(productRepository.findById(product1.getProductId())).thenReturn(Optional.ofNullable(product1));
        boolean flag = productService.deleteProduct(product1.getProductId());
        assertTrue(flag);
        verify(productRepository, times(1)).findById(anyInt());
        verify(productRepository, times(1)).deleteById(anyInt());

    }


}
