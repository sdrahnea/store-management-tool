package com.smt.controller;

import com.smt.exception.ProductNotFoundException;
import com.smt.model.Product;
import com.smt.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    Product createNewProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @GetMapping("/{id}")
    ResponseEntity getOneProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setUpdateDate(Instant.now());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    newProduct.setCreateDate(Instant.now());
                    return productRepository.save(newProduct);
                });
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

}
