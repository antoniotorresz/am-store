package com.unir.ms_operator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a Product.
 * Maps to the "products" table in the database.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product {

    /**
     * The unique identifier for the product.
     * Auto-generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title or name of the product.
     */
    private String title;

    /**
     * The price of the product.
     */
    private Double price;

    /**
     * The URL or path to the product's image.
     */
    private String image;

    /**
     * The category to which the product belongs.
     */
    private String category;

    /**
     * The quantity of the product available in stock.
     */
    private Integer cantidad;
}