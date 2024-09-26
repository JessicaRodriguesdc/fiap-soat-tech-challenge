package br.com.fiap.tech_challenge.infra.gateway.client.cotroller.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentItemDTO {

	private String category;

	private String title;

	private String description;

	private BigDecimal unitPrice;

	private Integer quantity;

	private String unitMeasure;

	private BigDecimal totalAmount;

	public PaymentItemDTO(String category, String title, String description, BigDecimal amount) {
		this.category = category;
		this.title = title;
		this.description = description;
		this.unitPrice = amount.setScale(2, RoundingMode.HALF_UP);
		this.quantity = 1;
		this.unitMeasure = "unit";
		this.totalAmount = amount;
	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getUnitMeasure() {
		return unitMeasure;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

}
