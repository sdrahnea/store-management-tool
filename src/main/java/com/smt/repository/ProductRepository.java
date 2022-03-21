package com.smt.repository;

import com.smt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(name = "select case when count(p)> 0 then true else false end from product p where lower(p.name) = lower(:name)", nativeQuery = true)
    boolean findByName(@Param("name") final String name);
}
