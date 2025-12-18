package com.luisdeveloper.pricing.price_service.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisdeveloper.pricing.price_service.entity.Price;
import com.luisdeveloper.pricing.price_service.entity.PriceId;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {

	Optional<Price> findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
		    Integer productId,
		    Integer brandId,
		    LocalDateTime queryDate1,
		    LocalDateTime queryDate2
		);
}
