package com.smt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Instant createDate;

    private Instant updateDate;

}
