package br.com.fiap.tech_challenge.infra.entrypoint.controller.dto;

import br.com.fiap.tech_challenge.domain.models.OrderProduct;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderProductWorkItemDto(@Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") UUID id,
                                      @Schema(example = "ab69e046-fb5a-4a79-98d6-363efdf20e11") UUID productId,
                                      @Schema(example = "X - Burguer") String productName,
                                      @Schema(example = "Sem queijo") String customization) {
    public OrderProductWorkItemDto(OrderProduct orderProduct) {
        this(orderProduct.getId(), orderProduct.getProductId(), orderProduct.getProductName(),
                orderProduct.getCustomization());
    }
}
