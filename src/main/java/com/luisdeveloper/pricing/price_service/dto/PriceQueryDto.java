package com.luisdeveloper.pricing.price_service.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public record PriceQueryDto(@NotNull LocalDateTime applicationDate, @NotNull Integer productId, @NotNull Integer brandId) {
}
