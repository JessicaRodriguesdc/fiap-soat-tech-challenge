package br.com.fiap.tech_challenge.infra.gateway.client.cotroller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record WebHookPaymentRequest(@Schema(example = "payment.created") String action,
		@Schema(example = "v1") String api_version,
		@Schema(example = "{\"id\":\"87847869771\"}") WebHookPaymentDataRequest data,
		@Schema(example = "2024-09-17T23:29:26Z") String date_created, @Schema(example = "115915708642") Long id,
		@Schema(example = "true") boolean live_mode, @Schema(example = "payment") String type,
		@Schema(example = "1986357239") String user_id) {
}
