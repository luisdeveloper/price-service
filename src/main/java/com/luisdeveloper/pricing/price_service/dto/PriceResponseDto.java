package com.luisdeveloper.pricing.price_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponseDto(
		Integer brandId,
		Integer productId,
	    Integer priceList,
	    LocalDateTime startDate,
	    LocalDateTime endDate,
	    BigDecimal price,
	    String currency
	) {}

