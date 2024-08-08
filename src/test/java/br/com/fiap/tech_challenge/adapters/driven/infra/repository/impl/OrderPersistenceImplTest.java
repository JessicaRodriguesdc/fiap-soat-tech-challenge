package br.com.fiap.tech_challenge.adapters.driven.infra.repository.impl;

import br.com.fiap.tech_challenge.ConstantTimes;
import br.com.fiap.tech_challenge.adapters.driven.infra.entities.OrderEntity;
import br.com.fiap.tech_challenge.adapters.driven.infra.repository.OrderRepository;
import br.com.fiap.tech_challenge.core.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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

	@InjectMocks
	private OrderPersistenceImpl orderPersistence;

	private Order order;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
    @DisplayName("Should create and save a new Order")
    void shouldCreateAndSaveNewOrder() {
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
				UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());

		var id = UUID.randomUUID();
		var paymentId = "paymentIdMock";
		var amount = new BigDecimal("200.00");
		var createdAt = LocalDateTime.now();
		var updatedAt = LocalDateTime.now();
		var products = List.of(orderProduct1, orderProduct1);
		var sequence = 2;
		var orderStatus = OrderStatusEnum.RECEIVED;

		order = new Order(id, amount, sequence, orderStatus, true, products, null, paymentId, createdAt, updatedAt);
	}

}