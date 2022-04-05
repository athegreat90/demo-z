package com.inditex.capitole.test.mapper;

import com.inditex.capitole.test.domain.Prices;
import com.inditex.capitole.test.dto.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PriceMapper
{
    @Mapping(target = "brand", source = "priceObj.brand.name")
    PriceResponse getResponse (Prices priceObj);
}
