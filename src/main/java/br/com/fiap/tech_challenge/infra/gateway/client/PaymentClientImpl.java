package br.com.fiap.tech_challenge.infra.gateway.client;

import br.com.fiap.tech_challenge.application.gateway.client.PaymentClient;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.dto.PaymentDTO;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.dto.PaymentStatusDTO;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.request.MpPaymentItemQRRequest;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.request.MpPaymentQRRequest;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.response.MpPaymentGetResponse;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.response.MpPaymentQRResponse;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.PaymentClientController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PaymentClientImpl implements PaymentClient {

	@Value("${external.api.notification.url}")
	private String NOTIFICATION_URL;

	private final PaymentClientController paymentClientController;

	public PaymentClientImpl(PaymentClientController paymentClientController) {
		this.paymentClientController = paymentClientController;
	}

	@Override
	public String generateQrCode(PaymentDTO dto) {
		var request = new MpPaymentQRRequest(dto.getDescription(), dto.getExpirationDate(), dto.getExternalReference(),
				dto.getItems()
					.stream()
					.map(item -> new MpPaymentItemQRRequest(item.getCategory(), item.getTitle(), item.getDescription(),
							item.getUnitPrice(), item.getQuantity(), item.getUnitMeasure(), item.getTotalAmount()))
					.collect(Collectors.toList()),
				dto.getTitle(), dto.getTotalAmount(), NOTIFICATION_URL);

		MpPaymentQRResponse response = paymentClientController.createQr(request);

		dto.setInStoreOrderId(response.getInStoreOrderId());
		dto.setQrData(response.getQrData());
		return dto.getQrData();
	}

	@Override
	public PaymentStatusDTO verifyPayment(String dataId) {
		MpPaymentGetResponse response = paymentClientController.getPayment(dataId);

		return new PaymentStatusDTO(response.getExternalReference(), response.getId(), response.getStatus());
	}

}
