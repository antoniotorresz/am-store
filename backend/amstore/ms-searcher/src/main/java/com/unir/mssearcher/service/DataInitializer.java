package com.unir.mssearcher.service;

import com.unir.mssearcher.model.ProductDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private SearchService searchService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadInitialData() {
        // Datos de prueba
        ProductDocument[] products = {
                new ProductDocument("1", "Nike Air Max", "Zapatillas deportivas Nike", 129.99, "zapatillas", 25),
                new ProductDocument("2", "Nike Running", "Tenis para correr Nike", 89.99, "running", 15),
                new ProductDocument("3", "Adidas Ultraboost", "Zapatillas Adidas cómodas", 139.99, "zapatillas", 30),
                new ProductDocument("4", "Puma RS-X", "Zapatillas Puma modernas", 79.99, "zapatillas", 20),
                new ProductDocument("5", "Nike Jordan", "Zapatillas de baloncesto", 159.99, "basket", 10)
        };

        for (ProductDocument product : products) {
            searchService.indexProduct(product);
            System.out.println("Producto indexado: " + product.getName());
        }

        System.out.println("✅ Datos iniciales cargados en Elasticsearch");
    }
}