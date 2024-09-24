package br.com.fiap.tech_challenge.infra.gateway.client.cotroller;

import br.com.fiap.tech_challenge.application.usecase.order.UpdateOrderStatusUseCase;
import br.com.fiap.tech_challenge.infra.entrypoint.controller.openapi.WebhookPaymentControllerOpenApi;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.request.WebHookPaymentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/webhook-payment")
public class WebHookPaymentController implements WebhookPaymentControllerOpenApi {

	private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

	public WebHookPaymentController(UpdateOrderStatusUseCase updateOrderStatusUseCase) {
		this.updateOrderStatusUseCase = updateOrderStatusUseCase;
	}

	@PostMapping
	public ResponseEntity<Void> handleWebhook(@RequestParam("data.id") String dataId, @RequestParam("type") String type,
			@RequestBody WebHookPaymentRequest request) {
		if (dataId.equals(request.data().id()) && type.equals(request.type())) {
			updateOrderStatusUseCase.updateStatusByPaymentDataId(dataId);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
