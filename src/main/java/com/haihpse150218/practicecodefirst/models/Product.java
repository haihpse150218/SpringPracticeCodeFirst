package com.haihpse150218.practicecodefirst.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;


// POJO = Plain Object Java Object
@Entity
@Table(name = "tblProduct")
public class Product {
    //set primarykey
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //using sequence
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1 //increment by 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private long pId;
    //validate
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int pYear;
    private Double price;
    private String url;
    //default constructor

    public Product() {
    }
    //caculated field = transient
    @Transient
    private int age;

    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - pYear;
    }

    public void setAge(int age) {
        this.age = age;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return pId == product.pId
                && pYear == product.pYear
                && age == product.age
                && Objects.equals(productName, product.productName)
                && Objects.equals(price, product.price)
                && Objects.equals(url, product.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pId, productName, pYear, price, url, age);
    }
}
