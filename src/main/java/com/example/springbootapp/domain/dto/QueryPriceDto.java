package com.example.springbootapp.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class QueryPriceDto {
    private LocalDateTime date;
    private Double amount;
    private String currency;
    private Long productId;
    private Long brandId;
    private String priceList;
    private Long priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}