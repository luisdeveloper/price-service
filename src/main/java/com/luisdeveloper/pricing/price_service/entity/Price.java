package com.luisdeveloper.pricing.price_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Table(name = "prices")
@Entity
@IdClass(PriceId.class)
public class Price {
	@Id
	@Column(name = "brand_id")
	private Integer brandId;
	
	@Id
	@Column(name = "product_id")
	private Integer productId;
	
	@Id
	@Column(name = "price_list")
	private Integer priceList;
	
	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;
	
	@Column(name = "end_date", nullable = false)
	private LocalDateTime endDate;
	
	@Column(name = "priority", nullable = false)
	private Integer priority;
	
	@Column(name = "price", precision = 10, scale = 2, nullable = false)
	private BigDecimal price;
	
	@Column(name = "curr", length = 3, nullable = false)
	private String curr;

	protected Price() {

	}

	public Price(Integer brandId, Integer productId, Integer priceList, LocalDateTime startDate, LocalDateTime endDate,
			Integer priority, BigDecimal price, String curr) {
		super();
		this.brandId = brandId;
		this.productId = productId;
		this.priceList = priceList;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.price = price;
		this.curr = curr;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPriceList() {
		return priceList;
	}

	public void setPriceList(Integer priceList) {
		this.priceList = priceList;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCurr() {
		return curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}
	
}
