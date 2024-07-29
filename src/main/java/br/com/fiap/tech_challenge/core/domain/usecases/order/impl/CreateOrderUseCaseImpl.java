package br.com.fiap.tech_challenge.core.domain.usecases.order.impl;

import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.core.domain.models.Product;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.order.CreateOrderUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.order.dto.CreateOrderDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderPersistence persistence;
    private final ProductPersistence productPersistence;

    public CreateOrderUseCaseImpl(OrderPersistence persistence, ProductPersistence productPersistence) {
        this.persistence = persistence;
        this.productPersistence = productPersistence;
    }

    @Override
    public Order create(CreateOrderDTO input) {
        var productIdList = getListOfProductIds(input);
        var products = productPersistence.findAllByIds(productIdList);
        var orderProducts = getOrderProducts(input, products);
        var calculatedAmount = reduceAmount(orderProducts);
        var nextSequence = getNextSequence();

        Order newOrder = Order.create(calculatedAmount, nextSequence, orderProducts, input.customerId(), "paymentMock");

        return persistence.create(newOrder);
    }

    private BigDecimal reduceAmount(List<OrderProduct> products) {
        return products.stream()
                .map(OrderProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Integer getNextSequence() {
        return persistence.getLastSequence() + 1;
    }

    private List<OrderProduct> getOrderProducts(CreateOrderDTO order, List<Product> products) {
        Map<UUID, Product> productsMap = products
                .stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        return order
                .products()
                .stream()
                .map(orderProduct -> {
                    BigDecimal price = productsMap.get(orderProduct.id()).getPrice();
                    return OrderProduct.create(price, orderProduct.observation(), orderProduct.id());
                }).toList();
    }

    private List<UUID> getListOfProductIds(CreateOrderDTO input) {
        return input.products()
                .stream()
                .map(CreateOrderDTO.OrderProducts::id)
                .collect(Collectors.toList());
    }
}
