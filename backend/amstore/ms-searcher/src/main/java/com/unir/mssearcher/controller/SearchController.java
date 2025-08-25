package com.unir.mssearcher.controller;

import com.unir.mssearcher.model.ProductDocument;
import com.unir.mssearcher.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDocument>> searchProducts(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        List<ProductDocument> results = searchService.searchProducts(query, category, minPrice, maxPrice);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDocument> getProduct(@PathVariable String id) {
        return searchService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/products/index")
    public ResponseEntity<ProductDocument> indexProduct(@RequestBody ProductDocument product) {
        ProductDocument indexed = searchService.indexProduct(product);
        return ResponseEntity.ok(indexed);
    }
}