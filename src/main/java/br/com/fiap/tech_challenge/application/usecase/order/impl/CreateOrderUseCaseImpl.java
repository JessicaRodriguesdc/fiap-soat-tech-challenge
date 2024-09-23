package br.com.fiap.tech_challenge.application.usecase.order.impl;

import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.gateway.client.PaymentClient;
import br.com.fiap.tech_challenge.application.persistence.CustomerPersistence;
import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.CreateOrderUseCase;
import br.com.fiap.tech_challenge.application.usecase.order.dto.CreateOrderDTO;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.dto.PaymentDTO;
import br.com.fiap.tech_challenge.infra.gateway.client.cotroller.dto.PaymentItemDTO;
import br.com.fiap.tech_challenge.domain.models.Customer;
import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.domain.models.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

	private final OrderPersistence persistence;

	private final ProductPersistence productPersistence;

	private final CustomerPersistence customerPersistence;

	private final PaymentClient paymentClient;

	public CreateOrderUseCaseImpl(OrderPersistence persistence, ProductPersistence productPersistence,
			CustomerPersistence customerPersistence, PaymentClient paymentClient) {
		this.persistence = persistence;
		this.productPersistence = productPersistence;
		this.customerPersistence = customerPersistence;
		this.paymentClient = paymentClient;
	}

	@Override
	public Order create(CreateOrderDTO input) {
		Customer customer = null;

		if (input.customerId() != null) {
			customer = getCustomer(input.customerId());
		}

		var orderProducts = getOrderProducts(input);
		var calculatedAmount = reduceAmount(orderProducts);
		var externalPaymentId = UUID.randomUUID();
		var pixDto = createPayment(input, calculatedAmount, externalPaymentId);
		var qrCode = paymentClient.generateQrCode(pixDto);

		var newOrder = Order.create(calculatedAmount, orderProducts, customer, externalPaymentId.toString(), qrCode);

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

	private PaymentDTO createPayment(CreateOrderDTO input, BigDecimal totalAmount, UUID externalPaymentId) {
		List<UUID> ids = input.products().stream().map(CreateOrderDTO.OrderProducts::id).collect(Collectors.toList());
		List<Product> products = findProductListOrThrowException(ids);

		return new PaymentDTO(products.stream()
			.map(product -> new PaymentItemDTO(product.getCategory().toString(), product.getName(),
					product.getDescription(), product.getPrice()))
			.collect(Collectors.toList()), totalAmount, externalPaymentId);
	}

}
