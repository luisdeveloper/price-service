package com.luisdeveloper.pricing.price_service.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisdeveloper.pricing.price_service.dto.PriceQueryDto;
import com.luisdeveloper.pricing.price_service.dto.PriceResponseDto;
import com.luisdeveloper.pricing.price_service.entity.Price;
import com.luisdeveloper.pricing.price_service.repository.PriceRepository;
import com.luisdeveloper.pricing.price_service.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository repository;
	
	@Override
	public Optional<PriceResponseDto> getApplicablePrice(PriceQueryDto dto) {
		return  repository
				.findApplicablePrices(
						dto.brandId(), dto.productId(), dto.applicationDate()).stream().limit(1).map(x->toDto(x)).findFirst();
		
	}

	PriceResponseDto toDto(Price price) {
		return new PriceResponseDto(price.getBrandId(), price.getProductId(), price.getPriceList(),
				price.getStartDate(), price.getEndDate(), price.getPrice(), price.getCurr());
	}

}
