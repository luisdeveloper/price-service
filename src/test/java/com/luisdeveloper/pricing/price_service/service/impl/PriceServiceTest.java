package com.luisdeveloper.pricing.price_service.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luisdeveloper.pricing.price_service.dto.PriceQueryDto;
import com.luisdeveloper.pricing.price_service.dto.PriceResponseDto;
import com.luisdeveloper.pricing.price_service.entity.Price;
import com.luisdeveloper.pricing.price_service.repository.PriceRepository;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository repository;

    @InjectMocks
    private PriceServiceImpl service;

    private PriceQueryDto queryDto;

    @BeforeEach
    void setUp() {
        queryDto = new PriceQueryDto(
            LocalDateTime.now(),
            35455,
            1
        );
    }

    @Test
    void when_repository_returns_empty_list_then_service_returns_empty_optional() {
        // given
        when(repository.findApplicablePrices(
            queryDto.brandId(),
            queryDto.productId(),
            queryDto.applicationDate()
        )).thenReturn(List.of());

        // when
        Optional<PriceResponseDto> result = service.getApplicablePrice(queryDto);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void when_repository_returns_single_price_then_service_returns_dto_with_that_price_data() {
        // given
        Price price = new Price(
            1,
            35455,
            1,
            null,
            null,
            0,
            BigDecimal.valueOf(35.50),
            "EUR"
        );

        when(repository.findApplicablePrices(
            queryDto.brandId(),
            queryDto.productId(),
            queryDto.applicationDate()
        )).thenReturn(List.of(price));

        // when
        Optional<PriceResponseDto> result = service.getApplicablePrice(queryDto);

        // then
        assertTrue(result.isPresent());

        PriceResponseDto dto = result.get();
        assertEquals(price.getBrandId(), dto.brandId());
        assertEquals(price.getProductId(), dto.productId());
        assertEquals(price.getPriceList(), dto.priceList());
        assertEquals(price.getPrice(), dto.price());
        assertEquals(price.getCurr(), dto.currency());
    }

    @Test
    void when_repository_returns_multiple_prices_then_service_returns_dto_with_highest_priority_price() {
        // given
        Price lowerPriority = new Price(
            1,
            35455,
            1,
            null,
            null,
            0,
            BigDecimal.valueOf(35.50),
            "EUR"
        );

        Price higherPriority = new Price(
            1,
            35455,
            2,
            null,
            null,
            1,
            BigDecimal.valueOf(25.45),
            "EUR"
        );

        // IMPORTANTE:
        // la lista ya viene ordenada por prioridad DESC desde el repository
        when(repository.findApplicablePrices(
            queryDto.brandId(),
            queryDto.productId(),
            queryDto.applicationDate()
        )).thenReturn(List.of(higherPriority, lowerPriority));

        // when
        Optional<PriceResponseDto> result = service.getApplicablePrice(queryDto);

        // then
        assertTrue(result.isPresent());

        PriceResponseDto dto = result.get();
        assertEquals(higherPriority.getPriceList(), dto.priceList());
        assertEquals(higherPriority.getPrice(), dto.price());
        assertEquals(higherPriority.getCurr(), dto.currency());
    }
}
