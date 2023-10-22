package com.example.springbootapp.repository;

import com.example.springbootapp.model.Price;
import com.example.springbootapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query(value = "SELECT p from Price p WHERE " +
            "p.priceList.startDate <= ?1 AND " +
            "p.priceList.endDate >= ?1 AND " +
            "p.product = ?2 ORDER BY p.priceList.priority DESC")
    List<Price> findAllByDateAndProduct(LocalDateTime date, Product product);
}
