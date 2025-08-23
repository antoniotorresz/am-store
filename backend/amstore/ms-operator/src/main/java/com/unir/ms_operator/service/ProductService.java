package com.unir.ms_operator.service;

import com.unir.ms_operator.entity.Product;
import com.unir.ms_operator.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Product entities.
 * Provides methods for CRUD operations and additional business logic.
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Retrieves all products from the repository.
     *
     * @return a list of all products.
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product to find.
     * @return an Optional containing the product if found, or empty if not found.
     */
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Saves a new product to the repository.
     *
     * @param product the product to save.
     * @return the saved product.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Updates an existing product with new data.
     *
     * @param id             the ID of the product to update.
     * @param updatedProduct the product data to update with.
     * @return the updated product.
     * @throws RuntimeException if the product with the given ID is not found.
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();
            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setCantidad(updatedProduct.getCantidad());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    /**
     * Finds products by their category.
     *
     * @param category the category to filter products by.
     * @return a list of products in the specified category.
     */
    public List<Product> findByCategory(String category) {
        return productRepository.findAll().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}