package br.com.fiap.tech_challenge.infra.gateway.client.cotroller.dto;

public class PaymentStatusDTO {

	private String externalReference;

	private Long id;

	private String status;

	public PaymentStatusDTO(String externalReference, Long id, String status) {
		this.externalReference = externalReference;
		this.id = id;
		this.status = status;
	}

	public String getExternalReference() {
		return externalReference;
	}

	public Long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

}
