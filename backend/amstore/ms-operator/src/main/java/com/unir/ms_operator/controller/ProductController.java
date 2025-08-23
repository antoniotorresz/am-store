package com.unir.ms_operator.controller;

import com.unir.ms_operator.entity.Product;
import com.unir.ms_operator.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Product entities.
 * Provides endpoints for CRUD operations and additional queries.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return a ResponseEntity containing a list of all products.
     */
    @GetMapping
    private ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return a ResponseEntity containing the product if found, or a 404 status if not found.
     */
    @GetMapping("/{id}")
    private ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> productOpt = productService.findProductById(id);
        return productOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Saves a new product.
     *
     * @param product the product to save.
     * @return a ResponseEntity containing the saved product with a 201 status.
     */
    @PostMapping
    private ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(201).body(savedProduct);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     * @return a ResponseEntity with a 204 status if the deletion is successful.
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing product by its ID.
     *
     * @param id      the ID of the product to update.
     * @param product the updated product data.
     * @return a ResponseEntity containing the updated product.
     */
    @PutMapping("/{id}")
    private ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Retrieves products by their category.
     *
     * @param categoryName the name of the category to filter products by.
     * @return a ResponseEntity containing a list of products in the specified category.
     */
    @GetMapping("/category/{categoryName}")
    private ResponseEntity<List<Product>> findProductsByCategory(@PathVariable String categoryName) {
        List<Product> products = productService.findByCategory(categoryName);
        return ResponseEntity.ok(products);
    }
}