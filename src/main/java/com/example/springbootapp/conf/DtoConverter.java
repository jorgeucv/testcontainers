package com.example.springbootapp.conf;

import com.example.springbootapp.domain.dto.PriceDto;
import com.example.springbootapp.domain.dto.QueryPriceDto;
import com.example.springbootapp.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public @Mapper(componentModel = "spring")
interface DtoConverter {
    @Mapping(target = "product", source = "product.name")
    @Mapping(target = "brand", source = "product.brand.name")
    @Mapping(target = "priceList", source = "priceList.name")
    @Mapping(target = "currency", source = "priceList.currency")
    PriceDto mapToDto(Price price);

    @Mapping(target = "date", ignore = true)
    @Mapping(target = "currency", source = "priceList.currency")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "brandId", source = "product.brand.id")
    @Mapping(target = "priceList", source = "priceList.name")
    @Mapping(target = "priority", source = "priceList.priority")
    @Mapping(target = "startDate", source = "priceList.startDate")
    @Mapping(target = "endDate", source = "priceList.endDate")
    QueryPriceDto mapToQueryPriceDto(Price price);
}

