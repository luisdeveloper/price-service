package com.luisdeveloper.pricing.price_service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.luisdeveloper.pricing.price_service.entity.Price;
import com.luisdeveloper.pricing.price_service.repository.PriceRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PriceRepository repository;

	private LocalDateTime startDate = LocalDateTime.of(2025, 12, 18, 1, 0);
	private LocalDateTime endDate = LocalDateTime.of(2025, 12, 22, 1, 0);

	private Price price = new Price(1, 1, 1, startDate, endDate, 1, BigDecimal.ZERO, "EUR");

	@BeforeEach
	void init() {
		repository.saveAll(List.of(price));
	}

	@AfterEach
	void clean() {
		repository.deleteAll();
	}

	@Test
	public void given_valid_productId_and_brandId_and_date_compatible_with_one_priceList_when_invocking_findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_then_returns_status_200()
			throws Exception {
		mockMvc.perform(get("/price/applicable-price")
		        .param("brandId", "1")
		        .param("productId", "1")
		        .param("applicationDate", startDate.plusDays(1).toString())
		        .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

	}

	@Test
	public void given_valid_productId_and_brandId_and_date_not_compatible_with_any_priceList_when_invocking_findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_then_returns_status_400()
			throws Exception {
		mockMvc.perform(get("/price/applicable-price")
		        .param("brandId", "1")
		        .param("productId", "1")
		        .param("applicationDate", startDate.plusDays(15).toString())
		        .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
