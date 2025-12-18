package com.luisdeveloper.pricing.price_service.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luisdeveloper.pricing.price_service.dto.PriceQueryDto;
import com.luisdeveloper.pricing.price_service.dto.PriceResponseDto;
import com.luisdeveloper.pricing.price_service.service.PriceService;

@RestController
public class PriceController {
	
	@Autowired
	private PriceService service;
	
	@GetMapping("price/applicable-price")
	public ResponseEntity<PriceResponseDto> getApplicablePrice(@RequestParam LocalDateTime applicationDate, @RequestParam Integer brandId, @RequestParam Integer productId) {
		var dto = new PriceQueryDto(applicationDate, brandId, productId);
		return ResponseEntity.of(service.getApplicablePrice(dto));
	}
}
