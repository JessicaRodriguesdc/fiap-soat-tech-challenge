package br.com.fiap.tech_challenge.core.domain.ports;

import java.math.BigDecimal;

public interface PaymentGateway {

	String generatePixQrCode(BigDecimal value);

	Boolean verifyPaymentStatus(String qrCode);

}
