package com.niit.products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

    @Id
    private int productId;
    private String productName;
    private ProductDetails productDetails;

    public Product() {
    }

    public Product(int productId, String productName, ProductDetails productDetails) {
        this.productId = productId;
        this.productName = productName;
        this.productDetails = productDetails;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDetails=" + productDetails +
                '}';
    }
}
