package br.com.fiap.tech_challenge.application.usecase.order;

public interface UpdateOrderStatusUseCase {

	void updateStatusByPaymentDataId(String paymentDataId);

	void updateOrderStatus();

}
