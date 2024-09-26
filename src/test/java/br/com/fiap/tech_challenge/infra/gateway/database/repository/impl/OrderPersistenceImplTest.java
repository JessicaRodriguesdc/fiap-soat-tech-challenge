package br.com.fiap.tech_challenge.infra.gateway.database.repository.impl;

import br.com.fiap.tech_challenge.ConstantTimes;
import br.com.fiap.tech_challenge.domain.models.Order;
import br.com.fiap.tech_challenge.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.infra.gateway.database.entities.OrderEntity;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.OrderRepository;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.ProductRepository;
import jakarta.persistence.EntityManager;
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
	private EntityManager entityManager;

	@InjectMocks
	private OrderPersistenceImpl orderPersistence;

	private Order order;

	@BeforeEach
	void setUp() {
		this.buildArranges();
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
				List.of(orderProduct1, orderProduct1), null, "paymentIdMock", "qrMock", LocalDateTime.now(),
				LocalDateTime.now());
	}

}