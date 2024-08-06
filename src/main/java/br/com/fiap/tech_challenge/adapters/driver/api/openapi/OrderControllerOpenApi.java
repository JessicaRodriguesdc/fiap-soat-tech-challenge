package br.com.fiap.tech_challenge.adapters.driver.api.openapi;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.OrderSummaryResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.handler.ErrorsValidateData;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Tag(name = "Order")
public interface OrderControllerOpenApi {

    @Operation(summary = "Find all is Paid Orders")
    @ApiResponse(responseCode = "200", description = "Ok Response", content = @Content(mediaType = "application/json", schema = @Schema(ref = "PageableOrderResponseDTO")))
    @ApiResponse(responseCode = "404", description = "Not Found Response", content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
    @ApiResponse(responseCode = "500", description = "Internal Server Error Response", content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
    ResponseEntity<Page<OrderSummaryResponseDTO>> findAllIsPaidOrders(OrderStatusEnum status, Boolean isPaid, int page, int size);

    @Operation(summary = "Register a Order")
    @ApiResponse(responseCode = "201", description = "Created Response", content = @Content(mediaType = "application/json", schema = @Schema(ref = "CreateOrderResponseDTO")))
    @ApiResponse(responseCode = "400", description = "Bad Request Response", content =
    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ErrorsValidateData.class))))
    @ApiResponse(responseCode = "500", description = "Internal Server Error Response", content = @Content(mediaType = "application/json", schema = @Schema(ref = "ProblemDto")))
    ResponseEntity<CreateOrderResponseDTO> create(CreateOrderRequestDTO orderRequest);
}
