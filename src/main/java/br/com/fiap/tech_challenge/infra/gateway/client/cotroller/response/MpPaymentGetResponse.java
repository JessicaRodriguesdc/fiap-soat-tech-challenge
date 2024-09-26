package br.com.fiap.tech_challenge.infra.gateway.client.cotroller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MpPaymentGetResponse {

	@JsonProperty("date_approved")
	private String dateApproved;

	@JsonProperty("date_created")
	private String dateCreated;

	@JsonProperty("date_last_updated")
	private String dateLastUpdated;

	@JsonProperty("description")
	private String description;

	@JsonProperty("external_reference")
	private String externalReference;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("payment_method_id")
	private String paymentMethodId;

	@JsonProperty("payment_type_id")
	private String paymentTypeId;

	@JsonProperty("status")
	private String status;

	@JsonProperty("status_detail")
	private String statusDetail;

	@JsonProperty("store_id")
	private String storeId;

	@JsonProperty("transaction_amount")
	private Double transactionAmount;

	public String getDateApproved() {
		return dateApproved;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public String getDateLastUpdated() {
		return dateLastUpdated;
	}

	public String getDescription() {
		return description;
	}

	public String getExternalReference() {
		return externalReference;
	}

	public Long getId() {
		return id;
	}

	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public String getStoreId() {
		return storeId;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

}
