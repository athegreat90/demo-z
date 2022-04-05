package com.inditex.capitole.test.service;

import com.inditex.capitole.test.domain.Prices;
import com.inditex.capitole.test.dto.PriceResponse;
import com.inditex.capitole.test.exception.BadRequestException;
import com.inditex.capitole.test.mapper.PriceMapper;
import com.inditex.capitole.test.repository.PriceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.springframework.util.StringUtils.*;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService
{
    private final PriceRepo priceRepo;
    private final PriceMapper mapper;

    @Override
    public PriceResponse getPrice(String date, Integer productId, Integer brandId)
    {
        validateRequest(date, productId, brandId);

        List<Prices> pricesList = priceRepo.getPrices(date, productId, brandId);

        var pricesData = pricesList.stream().max(Comparator.comparing(Prices::getPriority)).orElseThrow(NoSuchElementException::new);

        return mapper.getResponse(pricesData);
    }

    private void validateRequest(String date, Integer productId, Integer brandId)
    {
        if (!hasText(date) || "null".equalsIgnoreCase(date))
        {
            throw new BadRequestException("Date argument is missing");
        }
        else if (Objects.isNull(productId))
        {
            throw new BadRequestException("productId argument is missing");
        }
        else if (Objects.isNull(brandId))
        {
            throw new BadRequestException("productId argument is missing");
        }
    }
}
