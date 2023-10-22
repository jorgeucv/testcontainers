package com.example.springbootapp.domain.dto;

import lombok.Data;

@Data
public class PriceDto {
    private String product;
    private String brand;
    private String priceList;
    private Double amount;
    private String currency;
}
