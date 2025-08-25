package com.unir.mssearcher.repository;

import com.unir.mssearcher.model.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, String> {

    List<ProductDocument> findByNameContaining(String name);

    List<ProductDocument> findByDescriptionContaining(String description);

    List<ProductDocument> findByCategory(String category);

    List<ProductDocument> findByPriceBetween(Double minPrice, Double maxPrice);

    // Búsqueda full-text
    List<ProductDocument> findByNameContainingOrDescriptionContaining(String name, String description);
}