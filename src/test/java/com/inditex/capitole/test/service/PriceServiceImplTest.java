package com.inditex.capitole.test.service;

import com.inditex.capitole.test.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceServiceImplTest
{
    @Autowired
    private PriceService priceService;


    @ParameterizedTest
    @CsvSource({"2020-06-14-10.00.00,35455,1", "2020-06-14-16.00.00,35455,1", "2020-06-14-21.00.00,35455,1", "2020-06-15-10.00.00,35455,1", "2020-06-16-21.00.00,35455,1"})
    void toOk_ShouldReturnValue(String date, Integer productId, Integer brandId)
    {
        var response = priceService.getPrice(date, productId, brandId);

        assertAll(() ->
        {
            assertNotNull(response);
            assertEquals("Zara", response.getBrand());
        });
    }

    @ParameterizedTest
    @CsvSource({"2020-06-14-10.00.00,35455,", "2020-06-14-16.00.00,,1"})
    void toMissingInput_ShouldReturnException(String date, Integer productId, Integer brandId)
    {
        assertThrows(BadRequestException.class, () -> priceService.getPrice(date, productId, brandId));
    }
}