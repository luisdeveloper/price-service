package com.luisdeveloper.pricing.price_service.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	private static final int MES = 6;

	private LocalDateTime startDate = LocalDateTime.of(2020, MES, 18, 1, 0);


	@Test
	public void given_valid_productId_and_brandId_and_date_not_compatible_with_any_priceList_when_invocking_findTopByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_then_returns_status_404()
			throws Exception {
		mockMvc.perform(get("/price/applicable-price").param("brandId", "0").param("productId", "0")
				.param("applicationDate", startDate.plusDays(15).toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void test1_10h_14th_dec_35455_brand1() throws Exception {
		Integer brandId = 1;
		Integer productId = 35455;
		Integer priceList = 1;
		mockMvc.perform(get("/price/applicable-price").param("brandId", "1").param("productId", "35455")
				.param("applicationDate", LocalDateTime.of(2020, MES, 14, 10, 0).toString())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("brandId", is(brandId))).andExpect(jsonPath("productId", is(productId)))
				.andExpect(jsonPath("priceList", is(priceList)));

	}

	@Test
	public void test2_16h_14th_dec_35455_brand1() throws Exception {
		Integer brandId = 1;
		Integer productId = 35455;
		Integer priceList = 2;
		mockMvc.perform(get("/price/applicable-price").param("brandId", "1").param("productId", "35455")
				.param("applicationDate", LocalDateTime.of(2020, MES, 14, 16, 0).toString())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("brandId", is(brandId))).andExpect(jsonPath("productId", is(productId)))
				.andExpect(jsonPath("priceList", is(priceList)));

	}

	@Test
	public void test3_21h_14th_dec_35455_brand1() throws Exception {
		Integer brandId = 1;
		Integer productId = 35455;
		Integer priceList = 1;
		mockMvc.perform(get("/price/applicable-price").param("brandId", "1").param("productId", "35455")
				.param("applicationDate", LocalDateTime.of(2020, MES, 14, 21, 0).toString())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andExpect(jsonPath("brandId", is(brandId))).andExpect(jsonPath("productId", is(productId)))
				.andExpect(jsonPath("priceList", is(priceList)));

	}

	@Test
	public void test4_10h_15th_dec_35455_brand1() throws Exception {
		Integer brandId = 1;
		Integer productId = 35455;
		Integer priceList = 3;
		mockMvc.perform(get("/price/applicable-price").param("brandId", "1").param("productId", "35455")
				.param("applicationDate", LocalDateTime.of(2020, MES, 15, 10, 0).toString())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andExpect(jsonPath("brandId", is(brandId))).andExpect(jsonPath("productId", is(productId)))
				.andExpect(jsonPath("priceList", is(priceList)));

	}

	@Test
	public void test5_21h_16th_dec_35455_brand1() throws Exception {
		Integer brandId = 1;
		Integer productId = 35455;
		Integer priceList = 4;
		mockMvc.perform(get("/price/applicable-price").param("brandId", "1").param("productId", "35455")
				.param("applicationDate", LocalDateTime.of(2020, MES, 16, 21, 0).toString())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andExpect(jsonPath("brandId", is(brandId))).andExpect(jsonPath("productId", is(productId)))
				.andExpect(jsonPath("priceList", is(priceList)));

	}

}
