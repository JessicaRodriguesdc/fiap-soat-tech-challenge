package br.com.fiap.tech_challenge.adapters.driven.infra.repository.impl;

import br.com.fiap.tech_challenge.ConstantTimes;
import br.com.fiap.tech_challenge.adapters.driven.infra.entities.OrderEntity;
import br.com.fiap.tech_challenge.adapters.driven.infra.mapper.PageMapper;
import br.com.fiap.tech_challenge.adapters.driven.infra.repository.OrderRepository;
import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.pageable.CustomPage;
import br.com.fiap.tech_challenge.core.domain.models.pageable.CustomPageable;
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
	@DisplayName("Should Find is paid Order by status and pageable")
	void shouldFindPaidOrderByStatusAndPagination() {
		var isPaid = true;
		var orderStatus = OrderStatusEnum.PREPARING;
		var pageable = PageRequest.of(0, 10);

		Page<OrderEntity> orderEntityPage = new PageImpl<>(List.of(new OrderEntity(order)));

		when(repository.findByIsPaidAndStatus(any(), any(), any())).thenReturn(orderEntityPage);
		when(mapper.toDomainPage(any())).thenReturn(orderPage);

		var orderFoundOpt = orderPersistence.findByIsPaidAndStatus(isPaid, orderStatus, pageable.getPageNumber(),
				pageable.getPageSize());

		List<Order> orderFound = orderFoundOpt.content();

		verify(repository, times(ConstantTimes.ONLY_ONCE)).findByIsPaidAndStatus(isPaid, orderStatus, pageable);

		verifyNoMoreInteractions(repository);

		assertNotNull(orderFound);
		assertEquals(orderFound.size(), orderPage.content().size());
		assertEquals(orderFoundOpt.page().size(), orderPage.page().size());
		assertEquals(orderFoundOpt.page().totalElements(), orderPage.page().totalElements());
		assertEquals(orderFoundOpt.page().numberOfElements(), orderPage.page().numberOfElements());
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

		order = new Order(UUID.randomUUID(), new BigDecimal("200.00"), 2, OrderStatusEnum.RECEIVED, true,
				List.of(orderProduct1, orderProduct1), null, "paymentIdMock", LocalDateTime.now(), LocalDateTime.now());

		orderPage = new CustomPageable<>(List.of(order), new CustomPage(1L, 1L, 1L, 1L, true, false, 1L, false));
	}

}