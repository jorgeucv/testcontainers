package com.example.springbootapp.conf;


import com.example.springbootapp.model.Brand;
import com.example.springbootapp.model.Price;
import com.example.springbootapp.model.PriceList;
import com.example.springbootapp.model.Product;
import com.example.springbootapp.repository.BrandRepository;
import com.example.springbootapp.repository.PriceListRepository;
import com.example.springbootapp.repository.PriceRepository;
import com.example.springbootapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@Slf4j
@AllArgsConstructor
public class Startup implements ApplicationRunner {
    private final BrandRepository brandRepository;
    private final PriceListRepository priceListRepository;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.info("(◕‿◕) Application startup");
        log.info("Loading data sample.");
        log.info("http://localhost:8080/actuator/health");
        log.info("http://localhost:8080/h2-console/");

        Brand zara = brandRepository.save(Brand.builder().id(1L).name("Zara").build());
        Product product35455 = productRepository.save(Product.builder().id(35455L).name("Product #35455").brand(zara).build());
        // 2020-06-14-00.00.00 2020-12-31-23.59.59
        PriceList priceList1 = priceListRepository.save(PriceList.builder()
                .id(1L).name("Price List 1")
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priority(0L).currency("EUR").build());
        // 2020-06-14-15.00.00 2020-06-14-18.30.00
        PriceList priceList2 = priceListRepository.save(PriceList.builder()
                .id(2L).name("Price List 2")
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))

                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
                .priority(1L).currency("EUR").build());
        // 2020-06-15-00.00.00 2020-06-15-11.00.00
        PriceList priceList3 = priceListRepository.save(PriceList.builder()
                .id(3L).name("Price List 3")
                .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                .priority(1L).currency("EUR").build());
        // 2020-06-15-16.00.00 2020-12-31-23.59.59
        PriceList priceList4 = priceListRepository.save(PriceList.builder()
                .id(4L).name("Price List 4")
                .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priority(1L).currency("EUR").build());
        priceRepository.save(Price.builder()
                .id(10000000001L).priceList(priceList1).product(product35455).amount(35.50).build());
        priceRepository.save(Price.builder()
                .id(10000000002L).priceList(priceList2).product(product35455).amount(25.45).build());
        priceRepository.save(Price.builder()
                .id(10000000003L).priceList(priceList3).product(product35455).amount(30.50).build());
        priceRepository.save(Price.builder()
                .id(10000000004L).priceList(priceList4).product(product35455).amount(38.90).build());
    }
}

