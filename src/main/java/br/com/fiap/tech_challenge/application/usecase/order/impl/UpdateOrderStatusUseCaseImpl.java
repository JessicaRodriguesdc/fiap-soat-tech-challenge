package br.com.fiap.tech_challenge.application.usecase.order.impl;

import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.gateway.client.PaymentClient;
import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.UpdateOrderStatusUseCase;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
public class UpdateOrderStatusUseCaseImpl implements UpdateOrderStatusUseCase {

	private final OrderPersistence persistence;

	private final PaymentClient paymentClient;

	public UpdateOrderStatusUseCaseImpl(OrderPersistence persistence, PaymentClient paymentClient) {
		this.persistence = persistence;
		this.paymentClient = paymentClient;
	}

	@Override
	public void updateStatusByPaymentDataId(String dataId) {
		var paymentData = paymentClient.verifyPayment(dataId);
		var orderFound = persistence.findByPaymentId(paymentData.getExternalReference())
			.orElseThrow(() -> new DoesNotExistException("Order does no exist!"));

		if (paymentData.getStatus().equals("approved")) {
			orderFound.setStatus(OrderStatusEnum.PREPARING);
		}

		var isPaid = OrderStatusEnum.PREPARING.equals(orderFound.getStatus()) || orderFound.isPaid();
		var updatedOrder = new Order(orderFound.getId(), orderFound.getAmount(), orderFound.getSequence(),
				orderFound.getStatus(), isPaid, orderFound.getProducts(), orderFound.getCustomer(),
				orderFound.getPaymentId(), orderFound.getQr(), orderFound.getCreatedAt(), orderFound.getUpdatedAt());

		persistence.create(updatedOrder);
	}

	@Scheduled(fixedRate = 600000)
	@Transactional
	@Override
	public void updateOrderStatus() {
		LocalDateTime thirtyMinutesAgo = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
			.minusMinutes(30)
			.toLocalDateTime();
		List<Order> ordersFound = persistence.findByStatusAndCreatedAtBefore(OrderStatusEnum.RECEIVED,
				thirtyMinutesAgo);

		for (Order order : ordersFound) {
			order.setStatus(OrderStatusEnum.FINISHED);

			var updatedOrder = new Order(order.getId(), order.getAmount(), order.getSequence(), order.getStatus(),
					order.isPaid(), order.getProducts(), order.getCustomer(), order.getPaymentId(), order.getQr(),
					order.getCreatedAt(), order.getUpdatedAt());

			persistence.create(updatedOrder);
		}
	}

}
