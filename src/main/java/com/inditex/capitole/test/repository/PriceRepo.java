package com.inditex.capitole.test.repository;

import com.inditex.capitole.test.domain.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PriceRepo extends JpaRepository<Prices, Integer>
{
    @Query(value = "SELECT * FROM PRICES WHERE START_DATE < ?1 AND END_DATE > ?1 AND PRODUCT_ID = ?2 AND BRANDID = ?3", nativeQuery = true)
    List<Prices> getPrices(String dateTime, Integer productId, Integer brandId);
}
