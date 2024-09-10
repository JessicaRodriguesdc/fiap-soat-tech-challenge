package br.com.fiap.tech_challenge.domain.models.enums;

import java.util.EnumSet;
import java.util.Set;

public enum OrderStatusEnum {

	RECEIVED, PREPARING, READY, FINISHED;

	public Set<OrderStatusEnum> validPreviousStatus() {
		return switch (this) {
			case RECEIVED -> EnumSet.noneOf(OrderStatusEnum.class);
			case PREPARING -> EnumSet.of(RECEIVED);
			case READY -> EnumSet.of(PREPARING);
			case FINISHED -> EnumSet.of(RECEIVED, READY);
		};
	}

}
