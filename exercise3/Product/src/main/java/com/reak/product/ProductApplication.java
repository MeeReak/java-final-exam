package com.reak.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class ProductApplication {

    private List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return products;
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = products.stream().filter(product -> product.getId().equals(id)).findFirst();
        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(updatedProduct.getName());
            productToUpdate.setDescription(updatedProduct.getDescription()); // Change to fit your product data
            // Add more setters if your Product class has additional fields
            return productToUpdate;
        } else {
            return null;
        }
    }
}

class Product {
    private Long id;
    private String name;
    private String description; // Example additional field

    public Product(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
