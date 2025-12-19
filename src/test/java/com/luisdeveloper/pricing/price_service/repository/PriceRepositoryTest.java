package com.luisdeveloper.pricing.price_service.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.luisdeveloper.pricing.price_service.entity.Price;


@DataJpaTest
@Transactional
public class PriceRepositoryTest {

	@Autowired
	private PriceRepository repository;

	private Integer minPriority = 1, maxPriority = 10;

	private LocalDateTime startDate = LocalDateTime.of(2025, 12, 18, 1, 0);
	private LocalDateTime endDate = LocalDateTime.of(2025, 12, 22, 1, 0);

	private Price pMinPriority = new Price(1, 1, 1, startDate, endDate, minPriority, BigDecimal.ZERO, "EUR");
	private Price pMaxPriority = new Price(1, 1, 2, startDate, endDate, maxPriority, BigDecimal.ONE, "EUR");
	private Price pNoConflict = new Price(1, 2, 3, startDate, endDate, minPriority, BigDecimal.ZERO, "EUR");

	@BeforeEach
	void init() {
		repository.saveAll(List.of(pMinPriority, pMaxPriority, pNoConflict));
	}

	@AfterEach
	void clean() {
		repository.deleteAll();
	}

	@Test
	public void given_date_compatible_with_overlaping_priceLists_when_invocking_findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_then_returns_price_equal_to_pMaxPriority() {
		// when
		var result = repository
				.findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(1,
						1, LocalDateTime.now(), LocalDateTime.now());

		// then
		assertTrue(result.isPresent());
		assertEquals(pMaxPriority.getPriceList(), result.get().getPriceList());
		assertEquals(maxPriority, result.get().getPriority());
	}

	@Test
	public void given_valid_productId_and_brandId_and_date_compatible_with_one_priceList_when_invocking_findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_then_returns_price_equal_to_pNoConflict() {
		// when
		var result = repository
				.findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(1,
						2, startDate.plusDays(1),
						startDate.plusDays(1));

		// then
		assertTrue(result.isPresent());
		assertEquals(pNoConflict.getPriceList(), result.get().getPriceList());
	}

	@Test
	public void given_valid_productId_and_brandId_and_date_not_compatible_with_any_priceList_when_invocking_findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_then_returns_empty() {
		// when
		var result = repository
				.findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(1,
						2, LocalDateTime.now().minus(5, ChronoUnit.WEEKS),
						LocalDateTime.now().minus(5, ChronoUnit.WEEKS));

		// then
		assertTrue(result.isEmpty());
	}
}
