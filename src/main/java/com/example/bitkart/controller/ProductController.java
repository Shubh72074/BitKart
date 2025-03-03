package com.example.bitkart.controller;

import com.example.bitkart.model.Product;
import com.example.bitkart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public Product create(@RequestBody Product book) {
        return productService.save(book);
    }
}
