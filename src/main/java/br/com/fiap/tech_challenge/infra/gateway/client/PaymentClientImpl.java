package br.com.fiap.tech_challenge.infra.gateway.client;

import br.com.fiap.tech_challenge.application.gateway.client.PaymentClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentClientImpl implements PaymentClient {

	@Override
	public String generatePixQrCode(BigDecimal value) {
		if (value.compareTo(new BigDecimal("10.00")) == 0) {
			return "mockedUnpaidPixQRCodeString";
		}
		return "mockedPaidPixQRCodeString";
	}

	@Override
	public Boolean verifyPaymentStatus(String qrCode) {
		return qrCode.equals("mockedPaidPixQRCodeString");
	}

}
