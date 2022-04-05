package com.inditex.capitole.test.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "prices")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prices
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer priceList;

    @JoinColumn(name = "BRANDID", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Brand brand;

    private String startDate;

    private String endDate;

    private Integer productId;

    private Integer priority;

    private Double price;

    private String currency;
}
