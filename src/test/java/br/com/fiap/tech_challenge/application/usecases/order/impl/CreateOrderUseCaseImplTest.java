package br.com.fiap.tech_challenge.application.usecases.order.impl;

import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.application.gateway.client.PaymentClient;
import br.com.fiap.tech_challenge.application.persistence.CustomerPersistence;
import br.com.fiap.tech_challenge.application.persistence.OrderPersistence;
import br.com.fiap.tech_challenge.application.persistence.ProductPersistence;
import br.com.fiap.tech_challenge.application.usecase.order.dto.CreateOrderDTO;
import br.com.fiap.tech_challenge.application.usecase.order.impl.CreateOrderUseCaseImpl;
import br.com.fiap.tech_challenge.domain.models.Customer;
import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.Product;
import br.com.fiap.tech_challenge.domain.models.enums.ProductCategoryEnum;
import br.com.fiap.tech_challenge.domain.models.enums.ProductStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseImplTest {

	@Mock
	private OrderPersistence orderPersistence;

	@Mock
	private ProductPersistence productPersistence;

	@Mock
	private CustomerPersistence customerPersistence;

	@Mock
	private PaymentClient paymentClient;

	@InjectMocks
	private CreateOrderUseCaseImpl createOrderUseCase;

	private UUID customerId;

	private UUID productId;

	private BigDecimal price;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
	@DisplayName("Should create an order successfully for an identified customer with valid products")
	void shouldCreateOrderToIdentifiedCustomerSuccessfully() {
		CreateOrderDTO.OrderProducts orderProductDTO = new CreateOrderDTO.OrderProducts(productId, "Extra cheese");
		CreateOrderDTO createOrderDTO = new CreateOrderDTO(customerId, List.of(orderProductDTO, orderProductDTO));

		Customer customer = buildCustomer();
		Product product = buildProduct();

		when(customerPersistence.findById(customerId)).thenReturn(Optional.of(customer));
		when(productPersistence.findById(productId)).thenReturn(Optional.of(product));
		when(paymentClient.generatePixQrCode(any(BigDecimal.class))).thenReturn("qrCode123");
		when(orderPersistence.create(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Order order = createOrderUseCase.create(createOrderDTO);

		assertNotNull(order);
		assertEquals("qrCode123", order.getPaymentId());
		assertEquals(price, order.getAmount());
	}

	@Test
	@DisplayName("Should create an order successfully even when the customer is not identified")
	void shouldCreateOrderSuccessfullyForUnidentifiedCustomer() {
		CreateOrderDTO.OrderProducts orderProductDTO = new CreateOrderDTO.OrderProducts(productId, "Extra cheese");
		CreateOrderDTO createOrderDTO = new CreateOrderDTO(null, List.of(orderProductDTO, orderProductDTO));

		Product product = buildProduct();

		when(productPersistence.findById(productId)).thenReturn(Optional.of(product));
		when(paymentClient.generatePixQrCode(any(BigDecimal.class))).thenReturn("qrCode123");
		when(orderPersistence.create(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Order order = createOrderUseCase.create(createOrderDTO);

		assertNotNull(order);
		assertEquals("qrCode123", order.getPaymentId());
		assertEquals(price, order.getAmount());

		verify(customerPersistence, never()).findById(any(UUID.class));
	}

	@Test
	@DisplayName("Should successfully calculate total amount to an order")
	void shouldCalculateOrderAmount() {
		CreateOrderDTO.OrderProducts orderProductDTO = new CreateOrderDTO.OrderProducts(productId, "Extra cheese");
		CreateOrderDTO createOrderDTO = new CreateOrderDTO(customerId,
				List.of(orderProductDTO, orderProductDTO, orderProductDTO, orderProductDTO, orderProductDTO));

		Customer customer = buildCustomer();
		Product product = buildProduct();

		when(customerPersistence.findById(customerId)).thenReturn(Optional.of(customer));
		when(productPersistence.findById(productId)).thenReturn(Optional.of(product));
		when(paymentClient.generatePixQrCode(any(BigDecimal.class))).thenReturn("qrCode123");
		when(orderPersistence.create(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Order order = createOrderUseCase.create(createOrderDTO);

		assertEquals(BigDecimal.valueOf(499.95), order.getAmount());
	}

	@Test
	@DisplayName("Should throw DoesNotExistException when customer does not exist during order creation")
	void shouldThrowExceptionWhenCustomerNotFound() {
		CreateOrderDTO.OrderProducts orderProductDTO = new CreateOrderDTO.OrderProducts(productId, "Extra cheese");
		CreateOrderDTO createOrderDTO = new CreateOrderDTO(customerId, List.of(orderProductDTO));

		when(customerPersistence.findById(customerId)).thenReturn(Optional.empty());

		DoesNotExistException exception = assertThrows(DoesNotExistException.class,
				() -> createOrderUseCase.create(createOrderDTO));
		assertEquals("Customer not found with ID: " + customerId, exception.getMessage());
	}

	@Test
	@DisplayName("Should throw DoesNotExistException when product does not exist during order creation")
	void shouldThrowExceptionWhenProductNotFound() {
		CreateOrderDTO.OrderProducts orderProductDTO = new CreateOrderDTO.OrderProducts(productId, "Extra cheese");
		CreateOrderDTO createOrderDTO = new CreateOrderDTO(customerId, List.of(orderProductDTO));
		Customer customer = buildCustomer();

		when(customerPersistence.findById(customerId)).thenReturn(Optional.of(customer));
		when(productPersistence.findById(productId)).thenReturn(Optional.empty());

		DoesNotExistException exception = assertThrows(DoesNotExistException.class,
				() -> createOrderUseCase.create(createOrderDTO));
		assertEquals("Product not found with ID: " + productId, exception.getMessage());
	}

	private void buildArranges() {
		this.customerId = UUID.randomUUID();
		this.productId = UUID.randomUUID();
		this.price = BigDecimal.valueOf(199.98);
	}

	private Customer buildCustomer() {
		return new Customer(customerId, "John Doe", "31739380037", "john.doe@example.com");
	}

	private Product buildProduct() {
		return new Product(productId, "Sanduíche de Frango", ProductCategoryEnum.MAIN_COURSE, new BigDecimal("99.99"),
				"Sanduíche de frango com salada", ProductStatusEnum.ACTIVE, LocalDateTime.now());
	}

}