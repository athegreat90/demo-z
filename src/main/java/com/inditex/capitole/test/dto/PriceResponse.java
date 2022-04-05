package com.inditex.capitole.test.dto;

import lombok.Data;

@Data
public class PriceResponse
{
    private Integer productId;
    private String brand;
    private Double price;
    private String startDate;
    private String endDate;

}
