package com.example.springbootapp.controller;


import com.example.springbootapp.domain.dto.PriceDto;
import com.example.springbootapp.domain.dto.QueryPriceDto;
import com.example.springbootapp.exception.GenericException;
import com.example.springbootapp.service.PriceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@RestController
@RequestMapping(value = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class PricesController {
    private final PriceService priceService;

    @GetMapping
    public List<PriceDto> getAllPrices() {
        return priceService.getAllPrices();
    }

    @GetMapping("/{id}")
    public PriceDto getPriceById(@PathVariable Long id) {
        return priceService.getPriceById(id);
    }

    @GetMapping("/query")
    public QueryPriceDto queryPrice(
            @RequestParam Long productId,
            @RequestParam Long brandId,
            @RequestParam @DateTimeFormat(iso = DATE_TIME) LocalDateTime date) {
        return priceService.queryPrice(productId, brandId, date);
    }

    @GetMapping("/explode")
    public void explode() {
        throw new GenericException("This method is not implemented yet");
    }
}
