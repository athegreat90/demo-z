package com.inditex.capitole.test.controller;

import com.inditex.capitole.test.dto.PriceResponse;
import com.inditex.capitole.test.dto.ResponseGenericDto;
import com.inditex.capitole.test.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/price")
public class PriceController
{
    private final PriceService priceService;

    @GetMapping("/")
    public ResponseGenericDto<PriceResponse> getPrice(
            @RequestParam(value = "date", required = true) String date,
            @RequestParam(value = "productId", required = true) Integer productId,
            @RequestParam(value = "brandId", required = true) Integer brandId)
    {
        return new ResponseGenericDto<>(0, "OK", this.priceService.getPrice(date.trim(), productId, brandId));
    }
}
