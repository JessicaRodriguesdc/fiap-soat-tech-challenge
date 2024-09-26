package br.com.fiap.tech_challenge.application.gateway.client;

import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.dto.PaymentDTO;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.dto.PaymentStatusDTO;

public interface PaymentClient {

	String generateQrCode(PaymentDTO dto);

	PaymentStatusDTO verifyPayment(String dataId);

}
