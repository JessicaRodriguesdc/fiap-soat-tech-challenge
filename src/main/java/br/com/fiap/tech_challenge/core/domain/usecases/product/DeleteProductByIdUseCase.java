package br.com.fiap.tech_challenge.core.domain.usecases.product;

import java.util.UUID;

public interface DeleteProductByIdUseCase {

    void delete(UUID id);
}
