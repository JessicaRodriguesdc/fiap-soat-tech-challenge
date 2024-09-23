package br.com.fiap.tech_challenge.infra.gateway.client.cotroller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class MpPaymentItemQRRequest {

	private String category;

	private String title;

	private String description;

	@JsonProperty("unit_price")
	private BigDecimal unitPrice;

	private Integer quantity;

	@JsonProperty("unit_measure")
	private String unitMeasure;

	@JsonProperty("total_amount")
	private BigDecimal totalAmount;

	public MpPaymentItemQRRequest(String category, String title, String description, BigDecimal unitPrice,
			Integer quantity, String unitMeasure, BigDecimal totalAmount) {
		this.category = category;
		this.title = title;
		this.description = description;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.unitMeasure = unitMeasure;
		this.totalAmount = totalAmount;
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
