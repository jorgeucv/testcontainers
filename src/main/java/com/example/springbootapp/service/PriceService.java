package com.example.springbootapp.service;

import com.example.springbootapp.conf.DtoConverter;
import com.example.springbootapp.domain.dto.PriceDto;
import com.example.springbootapp.domain.dto.QueryPriceDto;
import com.example.springbootapp.exception.PriceNotFoundException;
import com.example.springbootapp.exception.ProductBrandMismatchException;
import com.example.springbootapp.model.Price;
import com.example.springbootapp.model.Product;
import com.example.springbootapp.repository.PriceRepository;
import com.example.springbootapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PriceService {
    private final DtoConverter dtoConverter;
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    public List<PriceDto> getAllPrices() {
        List<Price> prices = priceRepository.findAll();
        return prices.stream().map(dtoConverter::mapToDto).collect(toList());
    }

    public PriceDto getPriceById(Long id) {
        return dtoConverter.mapToDto(priceRepository.getReferenceById(id));
    }

    public QueryPriceDto queryPrice(Long id) {
        return dtoConverter.mapToQueryPriceDto(priceRepository.getReferenceById(id));
    }

    public QueryPriceDto queryPrice(Long productId, Long brandId, LocalDateTime date) {
        Product product = productRepository.getReferenceById(productId);
        List<Price> prices = priceRepository.findAllByDateAndProduct(date, product);
        if (product.getBrand().getId() != brandId) {
            throw new ProductBrandMismatchException("Product and brand don't match");
        }
        if (prices.size() == 0) {
            throw new PriceNotFoundException(String.format("Price not found for product %s at %s", productId, date));
        }
        QueryPriceDto queryPriceDto = dtoConverter.mapToQueryPriceDto(prices.get(0));
        queryPriceDto.setDate(date);
        return queryPriceDto;
    }
}


