package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductPersistence {

    Product create(Product product);
    List<Product> findAllByIds(List<UUID> ids);

}