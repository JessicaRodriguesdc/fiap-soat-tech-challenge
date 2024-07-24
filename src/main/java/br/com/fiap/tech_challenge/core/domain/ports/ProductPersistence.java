package br.com.fiap.tech_challenge.core.domain.ports;

import br.com.fiap.tech_challenge.core.domain.models.Product;

public interface ProductPersistence {

    Product create(Product product);

}