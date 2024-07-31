package br.com.fiap.tech_challenge.adapters.driver.api.openapi;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CustomerResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.FakeCheckoutRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Fake Checkout")
public interface FakeCheckoutControllerOpenApi {

    @Operation(summary = "Change the order status to RECEIVED, and mark as paid")
    @ApiResponse(
            responseCode = "204",
            description = "No Content Response",
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(
            responseCode = "400",
            description = "Bad Request Response",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(ref = "ProblemDto")
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Not Found Response",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(ref = "ProblemDto")
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error Response",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(ref = "ProblemDto")
            )
    )
    ResponseEntity<Void> checkout(@RequestBody FakeCheckoutRequestDTO fakeCheckoutRequestDTO);

}
