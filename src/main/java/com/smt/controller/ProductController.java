package com.smt.controller;

import com.smt.exception.ProductNotFoundException;
import com.smt.model.Product;
import com.smt.repository.ProductRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/products")
@OpenAPIDefinition(info = @Info(title = "Product API", version = "1.0.0", description = "CRUD product's functions"))
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Operation(description = "Get all products")
    @GetMapping
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Operation(description = "Create a new product")
    @PostMapping
    Product createNewProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get the product by id")
    ResponseEntity getOneProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ResponseEntity.ok(product);
    }

    @Operation(description = "Update the product by id and new product's details")
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

    @Operation(description = "Delete the product by id")
    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

}
