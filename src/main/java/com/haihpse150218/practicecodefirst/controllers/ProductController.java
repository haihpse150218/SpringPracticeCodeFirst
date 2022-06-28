package com.haihpse150218.practicecodefirst.controllers;

import com.haihpse150218.practicecodefirst.models.Product;
import com.haihpse150218.practicecodefirst.models.ResponseObject;
import com.haihpse150218.practicecodefirst.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products*")
public class ProductController {
    //dependency injection
    @Autowired // tu dong tao ra repository ngay khi khoi tao
    private ProductRepository repository;

    @GetMapping("")
        // http://localhost:8080/api/v1/Products
    List<Product> getAllProduct() {
        return repository.findAll();
    }

    //get detail product
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query Product Successfully", foundProduct)
        ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot Find product with id = " + id, foundProduct)
                );
    }

    //insert product
    @PostMapping("insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        //product name must be Unique
        List<Product> foundProduct = repository.findByProductName(newProduct.getProductName().trim());
        if (foundProduct.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product Name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product Successfully", repository.save(newProduct))
        );
    }

    //update, upsert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updateProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setpYear(newProduct.getpYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setProductId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product Successfully", updateProduct)
        );
    }

    //delete a product
    @DeleteMapping("{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if (exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete successfully", "")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Can not find to delete", ""));
        }

    }
}
