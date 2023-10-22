package com.example.springbootapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Price {
    @Id
    private Long id;
    @ManyToOne
    private PriceList priceList;
    @ManyToOne
    private Product product;
    private Double amount;
}


