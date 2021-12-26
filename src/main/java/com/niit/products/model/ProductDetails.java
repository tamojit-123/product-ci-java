package com.niit.products.model;

public class ProductDetails {

    private int productCode;
    private String productModel;
    private int productPrice;

    public ProductDetails() {
    }

    public ProductDetails(int productCode, String productModel, int productPrice) {
        this.productCode = productCode;
        this.productModel = productModel;
        this.productPrice = productPrice;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "productCode=" + productCode +
                ", productModel='" + productModel + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
