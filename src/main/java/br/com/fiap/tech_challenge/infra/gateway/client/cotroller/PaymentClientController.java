package br.com.fiap.tech_challenge.infra.gateway.client.cotroller;

import br.com.fiap.tech_challenge.application.exceptions.ApiClientRequestException;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.request.MpPaymentQRRequest;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.response.MpPaymentGetResponse;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.response.MpPaymentQRResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class PaymentClientController {

	@Value("${external.api.token}")
	private String TOKEN;

	@Value("${external.api.create.qr}")
	private String CREATE_QR_URL;

	@Value("${external.api.get.payment}")
	private String GET_PAYMENT_URL;

	private final URI baseUri;

	private final HttpClient httpClient;

	private final ObjectMapper objectMapper;

	@Autowired
	public PaymentClientController(URI baseUri, HttpClient httpClient, ObjectMapper objectMapper) {
		this.baseUri = baseUri;
		this.httpClient = httpClient;
		this.objectMapper = objectMapper;
	}

	public MpPaymentQRResponse createQr(MpPaymentQRRequest payload) {
		String url = baseUri + CREATE_QR_URL;
		HttpRequest request = buildPostRequest(url, payload.toJson());

		return request(request, MpPaymentQRResponse.class, 201);
	}

	public MpPaymentGetResponse getPayment(String dataId) {
		if (dataId == null || dataId.isEmpty()) {
			throw new ApiClientRequestException("Data ID cannot be null or empty");
		}

		String url = baseUri + GET_PAYMENT_URL + dataId;
		HttpRequest request = buildGetRequest(url);

		return request(request, MpPaymentGetResponse.class, 200);
	}

	private HttpRequest buildPostRequest(String url, String json) {
		return HttpRequest.newBuilder()
			.uri(URI.create(url))
			.header("Content-Type", "application/json")
			.header("Authorization", TOKEN)
			.POST(HttpRequest.BodyPublishers.ofString(json))
			.build();
	}

	private HttpRequest buildGetRequest(String url) {
		return HttpRequest.newBuilder()
			.uri(URI.create(url))
			.header("Content-Type", "application/json")
			.header("Authorization", TOKEN)
			.GET()
			.build();
	}

	private <T> T request(HttpRequest request, Class<T> responseType, int successStatusCode) {
		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == successStatusCode) {
				return objectMapper.readValue(response.body(), responseType);
			}
			else {
				throw new ApiClientRequestException("Request error: Status code " + response.statusCode());
			}
		}
		catch (IOException | InterruptedException e) {
			throw new ApiClientRequestException("Error executing request: " + e.getMessage());
		}
	}

}
