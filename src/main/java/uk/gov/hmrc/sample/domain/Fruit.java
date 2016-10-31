package uk.gov.hmrc.sample.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by otom on 30/10/2016.
 */

public class Fruit implements Serializable {
    private String name;
    private BigDecimal price;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
