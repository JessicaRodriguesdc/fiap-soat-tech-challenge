package br.com.fiap.tech_challenge.application.gateway.client;

import java.math.BigDecimal;

public interface PaymentClient {

	String generatePixQrCode(BigDecimal value);

	Boolean verifyPaymentStatus(String qrCode);

}
