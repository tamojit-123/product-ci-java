package com.niit.products.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.products.exception.ProductAlreadyExistsException;
import com.niit.products.model.Product;
import com.niit.products.model.ProductDetails;
import com.niit.products.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    Product product1, product2;

    ProductDetails productDetails1, productDetails2;

    @BeforeEach
    public void setUp() {
        product1 = new Product(1, "laptop", productDetails1);
        productDetails1 = new ProductDetails(101, "Acer", 200);

        product2 = new Product(2, "speaker", productDetails2);
        productDetails2 = new ProductDetails(102, "Dolby", 100);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

    }

    @AfterEach
    public void tearDown() {

        product1 = null;
        product2 = null;
        productDetails1 = null;
        productDetails2 = null;
    }

    //localhost:8080/api/
    @Test
    public void givenProductToSaveReturnSaveProduct() throws Exception {
        when(productService.saveProduct(any())).thenReturn(product1);
        mockMvc.perform(post("/api/v1/products/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product1)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(productService, times(1)).saveProduct(any());
    }

    @Test
    public void givenProductToSaveProductFailure() throws Exception {
        when(productService.saveProduct(any())).thenThrow(ProductAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/products/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product1)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(productService, times(1)).saveProduct(any());
    }


    @Test
    public void givenProductCodeDeleteProduct() throws Exception {
        when(productService.deleteProduct(anyInt())).thenReturn(true);
        mockMvc.perform(delete("/api/v1/products/product/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(productService, times(1)).deleteProduct(anyInt());
    }

    private static String jsonToString(final Object o) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(o);
            result = jsonContent;
            return result;

        } catch (JsonProcessingException e) {
            result = "JsonProcessingException";
        }
        return result;
    }


}
