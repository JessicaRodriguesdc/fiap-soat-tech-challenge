package br.com.fiap.tech_challenge.infra.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.http.HttpClient;

@Configuration
public class PaymentClientConfig {

	@Value("${external.api.host}")
	private String host;

	@Bean
	public URI mercadoPagoBaseUri() {
		return URI.create(host);
	}

	@Bean
	public HttpClient httpClient() {
		return HttpClient.newHttpClient();
	}

}
