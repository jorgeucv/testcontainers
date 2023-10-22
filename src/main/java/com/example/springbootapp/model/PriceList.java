package com.example.springbootapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PriceList {
    @Id
    private Long id;
    @OneToMany(mappedBy = "priceList")
    private List<Price> prices;
    private String name;
    private String description;
    private Long priority;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}