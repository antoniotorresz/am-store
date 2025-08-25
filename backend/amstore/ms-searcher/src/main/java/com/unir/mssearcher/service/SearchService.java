package com.unir.mssearcher.service;

import com.unir.mssearcher.model.ProductDocument;
import com.unir.mssearcher.repository.ProductSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    @Autowired
    private ProductSearchRepository productRepository;

    public List<ProductDocument> searchProducts(String query, String category, Double minPrice, Double maxPrice) {
        if (query != null && !query.trim().isEmpty()) {
            return productRepository.findByNameContainingOrDescriptionContaining(query, query);
        } else if (category != null) {
            return productRepository.findByCategory(category);
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        } else {
            return (List<ProductDocument>) productRepository.findAll();
        }
    }

    public Optional<ProductDocument> getProductById(String id) {
        return productRepository.findById(id);
    }

    public ProductDocument indexProduct(ProductDocument product) {
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}