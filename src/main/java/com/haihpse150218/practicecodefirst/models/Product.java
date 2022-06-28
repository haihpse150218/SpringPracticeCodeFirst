package com.haihpse150218.practicecodefirst.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


// POJO = Plain Object Java Object
@Entity
public class Product {
    //set primarykey
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pId;
    private String productName;
    private int pYear;
    private Double price;
    private String url;
    //default constructor

    public Product() {
    }

    public Product(String productName, int year, Double price, String url) {
        this.productName = productName;
        this.pYear = year;
        this.price = price;
        this.url = url;
    }


    public long getProductId() {
        return pId;
    }

    public void setProductId(long productId) {
        this.pId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getpYear() {
        return pYear;
    }

    public void setpYear(int pYear) {
        this.pYear = pYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + pId +
                ", productName='" + productName + '\'' +
                ", year=" + pYear +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}
