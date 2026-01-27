package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public List<String> getProducts() {
        return List.of("Notebook", "Phone", "Tablet");
    }
}
