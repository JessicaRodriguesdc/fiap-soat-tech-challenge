package br.com.fiap.tech_challenge.core.domain.usecases.order.impl;

import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.Customer;
import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.core.domain.models.product.Product;
import br.com.fiap.tech_challenge.core.domain.ports.CustomerPersistence;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.ports.PaymentGateway;
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

	private final CustomerPersistence customerPersistence;

	private final PaymentGateway paymentGateway;

	public CreateOrderUseCaseImpl(OrderPersistence persistence, ProductPersistence productPersistence,
			CustomerPersistence customerPersistence, PaymentGateway paymentGateway) {
		this.persistence = persistence;
		this.productPersistence = productPersistence;
		this.customerPersistence = customerPersistence;
		this.paymentGateway = paymentGateway;
	}

	@Override
	public Order create(CreateOrderDTO input) {
		Customer customer = null;

		if (input.customerId() != null) {
			customer = getCustomer(input.customerId());
		}

		var orderProducts = getOrderProducts(input);
		var calculatedAmount = reduceAmount(orderProducts);
		var qrCode = paymentGateway.generatePixQrCode(calculatedAmount);

		var newOrder = Order.create(calculatedAmount, orderProducts, customer, qrCode);

		return persistence.create(newOrder);
	}

	private Customer getCustomer(UUID customerId) {
		return customerPersistence.findById(customerId)
			.orElseThrow(() -> new DoesNotExistException("Customer not found with ID: " + customerId));
	}

	private BigDecimal reduceAmount(List<OrderProduct> products) {
		return products.stream().map(OrderProduct::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private List<OrderProduct> getOrderProducts(CreateOrderDTO order) {
		var productIdList = getListOfProductIds(order);
		var productsMap = getProductMap(findProductListOrThrowException(productIdList));

		return order.products().stream().map(orderProduct -> {
			BigDecimal price = productsMap.get(orderProduct.id()).getPrice();
			return OrderProduct.create(price, orderProduct.observation(), orderProduct.id());
		}).toList();
	}

	private List<Product> findProductListOrThrowException(List<UUID> productIdList) {
		return productIdList.stream()
			.map(uuid -> productPersistence.findById(uuid)
				.orElseThrow(() -> new DoesNotExistException("Product not found with ID: " + uuid)))
			.toList();
	}

	private Map<UUID, Product> getProductMap(List<Product> products) {
		return products.stream()
			.collect(Collectors.toMap(Product::getId, product -> product, (existing, replacement) -> existing));
	}

	private List<UUID> getListOfProductIds(CreateOrderDTO input) {
		return input.products().stream().map(CreateOrderDTO.OrderProducts::id).toList();
	}

}
