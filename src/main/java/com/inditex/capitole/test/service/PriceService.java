package com.inditex.capitole.test.service;

import com.inditex.capitole.test.dto.PriceResponse;

public interface PriceService
{
    PriceResponse getPrice(String date, Integer productId, Integer brandId);
}
