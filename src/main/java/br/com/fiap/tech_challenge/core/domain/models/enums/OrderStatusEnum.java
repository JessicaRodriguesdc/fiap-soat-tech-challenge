package br.com.fiap.tech_challenge.core.domain.models.enums;

public enum OrderStatusEnum {

	RECEIVED, PREPARING, READY, FINISHED;

	public OrderStatusEnum previous() {
		return switch (this) {
			case RECEIVED -> null;
			case PREPARING -> RECEIVED;
			case READY -> PREPARING;
			case FINISHED -> READY;
		};
	}

}
