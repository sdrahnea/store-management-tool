package com.smt.utils;

import com.smt.model.Product;
import com.smt.model.ProductType;

import java.math.BigDecimal;
import java.time.Instant;

public class ProductTestUtils {

    public static Product createDummyProduct(String name, String description, BigDecimal price) {
        return new Product(
                0L,
                name,
                description,
                price,
                ProductType.HARD,
                Instant.now(),
                Instant.now()
        );
    }

    public static Product createDummyProduct(Long id, String name, String description, BigDecimal price) {
        return new Product(
                id,
                name,
                description,
                price,
                ProductType.HARD,
                Instant.now(),
                Instant.now()
        );
    }

}
