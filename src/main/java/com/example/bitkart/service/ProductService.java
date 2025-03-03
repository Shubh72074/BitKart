package com.example.bitkart.service;

import com.example.bitkart.model.Product;
import com.example.bitkart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product book) {
        return productRepository.save(book);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


}
