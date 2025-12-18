package com.luisdeveloper.pricing.price_service.service;

import java.util.Optional;

import com.luisdeveloper.pricing.price_service.dto.PriceQueryDto;
import com.luisdeveloper.pricing.price_service.dto.PriceResponseDto;

public interface PriceService {
	Optional<PriceResponseDto> getApplicablePrice(PriceQueryDto dto);
}
