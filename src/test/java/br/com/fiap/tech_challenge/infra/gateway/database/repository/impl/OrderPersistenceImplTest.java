package br.com.fiap.tech_challenge.infra.gateway.database.repository.impl;

import br.com.fiap.tech_challenge.ConstantTimes;
import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPage;
import br.com.fiap.tech_challenge.domain.models.pageable.CustomPageable;
import br.com.fiap.tech_challenge.infra.gateway.database.entities.OrderEntity;
import br.com.fiap.tech_challenge.infra.gateway.database.entities.ProductEntity;
import br.com.fiap.tech_challenge.infra.gateway.database.mapper.PageMapper;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.OrderRepository;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.ProductRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderPersistenceImplTest {

	@Mock
	private OrderRepository repository;

    @Mock
    private ProductRepository productRepository;

	@Mock
	private PageMapper<Order> mapper;

	@InjectMocks
	private OrderPersistenceImpl orderPersistence;

	private Order order;

	private CustomPageable<Order> orderPage;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
    @DisplayName("Should create and save a new Order")
    void shouldCreateAndSaveNewOrder() {
        when(productRepository.findById(any())).thenReturn(Optional.of(new ProductEntity()));
        when(repository.save(any())).thenReturn(new OrderEntity(order));

        var created = orderPersistence.create(order);

        verify(repository, times(ConstantTimes.ONLY_ONCE)).save(any());
        verifyNoMoreInteractions(repository);

        assertEquals(order.getId(), created.getId());
        assertEquals(order.getAmount(), created.getAmount());
        assertEquals(order.getSequence(), created.getSequence());
        assertEquals(order.getCustomer(), created.getCustomer());
        assertEquals(order.getPaymentId(), created.getPaymentId());
        assertEquals(order.getStatus(), created.getStatus());
        assertEquals(order.getCreatedAt(), created.getCreatedAt());
        assertEquals(order.getUpdatedAt(), created.getUpdatedAt());
    }

	@Test
    @DisplayName("Should Find Order by ID")
    void shouldFindCustomerById() {
        when(repository.findById(order.getId()))
                .thenReturn(Optional.of(new OrderEntity(order)));

        var orderFoundOpt = orderPersistence.findById(order.getId());
        var orderFound = orderFoundOpt.get();

        verify(repository, times(ConstantTimes.ONLY_ONCE)).findById(any());
        verifyNoMoreInteractions(repository);

        assertNotNull(orderFound);
        assertNotNull(orderFound.getId());
        assertNotNull(orderFound.getAmount());
        assertNotNull(orderFound.getPaymentId());
        assertNotNull(orderFound.getStatus());
        assertNotNull(orderFound.getSequence());
    }

	private void buildArranges() {
		OrderProduct orderProduct1 = new OrderProduct(UUID.randomUUID(), new BigDecimal("100.00"), "Customization 1",
				UUID.randomUUID(), "X Bacon", UUID.randomUUID(), LocalDateTime.now());

		order = new Order(UUID.randomUUID(), new BigDecimal("200.00"), 2, OrderStatusEnum.RECEIVED, true,
				List.of(orderProduct1, orderProduct1), null, "paymentIdMock", LocalDateTime.now(), LocalDateTime.now());

		orderPage = new CustomPageable<>(List.of(order), new CustomPage(1L, 1L, 1L, 1L, true, false, 1L, false));
	}

}