package br.com.fiap.tech_challenge.infra.gateway.client.cotroller.request;

import br.com.fiap.tech_challenge.application.exceptions.ApiClientRequestException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class MpPaymentQRRequest {

	private String description;

	@JsonProperty("expiration_date")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private OffsetDateTime expirationDate;

	@JsonProperty("external_reference")
	private UUID externalReference;

	private List<MpPaymentItemQRRequest> items;

	private String title;

	@JsonProperty("total_amount")
	private BigDecimal totalAmount;

	@JsonProperty("notification_url")
	private String notificationUrl;

	public MpPaymentQRRequest(String description, OffsetDateTime expirationDate, UUID externalReference,
			List<MpPaymentItemQRRequest> items, String title, BigDecimal totalAmount, String notificationUrl) {
		this.description = description;
		this.expirationDate = expirationDate;
		this.externalReference = externalReference;
		this.items = items;
		this.title = title;
		this.totalAmount = totalAmount;
		this.notificationUrl = notificationUrl;
	}

	public String getDescription() {
		return description;
	}

	public OffsetDateTime getExpirationDate() {
		return expirationDate;
	}

	public UUID getExternalReference() {
		return externalReference;
	}

	public List<MpPaymentItemQRRequest> getItems() {
		return items;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public String getNotificationUrl() {
		return notificationUrl;
	}

	public String toJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		try {
			return objectMapper.writeValueAsString(this);
		}
		catch (Exception e) {
			throw new ApiClientRequestException("Erro ao serializar para JSON: " + e.getMessage());
		}
	}

}
