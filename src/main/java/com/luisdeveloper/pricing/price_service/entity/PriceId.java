package com.luisdeveloper.pricing.price_service.entity;

import java.io.Serializable;
import java.util.Objects;

public class PriceId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3305179007803548286L;
	
	private Integer brandId;
    private Integer productId;
    private Integer priceList;

    public PriceId() {}

    public PriceId(Integer brandId, Integer productId, Integer priceList) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceId)) return false;
        PriceId that = (PriceId) o;
        return Objects.equals(brandId, that.brandId)
            && Objects.equals(productId, that.productId)
            && Objects.equals(priceList, that.priceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, productId, priceList);
    }
}
