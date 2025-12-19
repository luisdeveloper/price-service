package com.luisdeveloper.pricing.price_service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luisdeveloper.pricing.price_service.entity.Price;
import com.luisdeveloper.pricing.price_service.entity.PriceId;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {

	@Query("""
			    SELECT p
			    FROM Price p
			    WHERE p.brandId = :brandId
			      AND p.productId = :productId
			      AND :applicationDate BETWEEN p.startDate AND p.endDate
			    ORDER BY p.priority DESC
			""")
	List<Price> findApplicablePrices(Integer brandId, Integer productId, LocalDateTime applicationDate);
}
