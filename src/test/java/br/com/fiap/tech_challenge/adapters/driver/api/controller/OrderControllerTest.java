package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CreateOrderResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.handler.ControllerAdvice;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.OrderMapper;
import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.Customer;
import br.com.fiap.tech_challenge.core.domain.models.OrderProduct;
import br.com.fiap.tech_challenge.core.domain.models.enums.OrderStatusEnum;
import br.com.fiap.tech_challenge.core.domain.models.Order;
import br.com.fiap.tech_challenge.core.domain.usecases.order.CreateOrderUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.order.FindPaidOrdersUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.order.dto.CreateOrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CreateOrderUseCase createOrderUseCase;

	@Mock
	private FindPaidOrdersUseCase findPaidOrdersUseCase;

	@Mock
	private OrderMapper mapper;

	@InjectMocks
	private OrderController orderController;

	private final String baseUrl = "/v1/orders";

	private CreateOrderDTO createOrderDTO;

	private Order order;

	private CreateOrderResponseDTO createOrderResponseDTO;

	@BeforeEach
	void setUp() {
		buildOrder();
		buildRequest();
		buildResponse();

		mockMvc = MockMvcBuilders.standaloneSetup(orderController).setControllerAdvice(new ControllerAdvice()).build();
	}

	@Test
    @DisplayName("Should Create A New Order")
    void shouldCreateANewOrder() throws Exception {
        when(mapper.toCreateOrder(any())).thenReturn(createOrderDTO);
        when(createOrderUseCase.create(createOrderDTO)).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(order))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value(createOrderResponseDTO.orderId().toString()))
                .andExpect(jsonPath("$.qrCode").value(createOrderResponseDTO.qrCode()));
    }

	@Test
    @DisplayName("Should return NotFound when any product of order or identified customer not found")
    void shouldReturnNotFoundWhenProductOrIdentifiedCustomerNotFound() throws Exception {
        when(mapper.toCreateOrder(any())).thenReturn(createOrderDTO);
        when(createOrderUseCase.create(createOrderDTO)).thenThrow(DoesNotExistException.class);

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(order))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void buildOrder() {
		var id = UUID.randomUUID();
		var paymentId = "paymentIdMock";
		var amount = new BigDecimal("200.00");
		var status = OrderStatusEnum.PREPARING;
		var customer = new Customer(id, "Walter White", "31739380037", "heisenberg@gmail.com");
		OrderProduct orderProduct1 = new OrderProduct(UUID.randomUUID(), new BigDecimal("100.00"), "Customization 1",
				UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
		OrderProduct orderProduct2 = new OrderProduct(UUID.randomUUID(), new BigDecimal("100.00"), "Customization 2",
				UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now());
		var products = List.of(orderProduct1, orderProduct2);

		order = new Order(id, amount, 2, status, true, products, customer, paymentId, null, null);
	}

	private void buildRequest() {
		CreateOrderDTO.OrderProducts product = new CreateOrderDTO.OrderProducts(UUID.randomUUID(), "mock observation");
		createOrderDTO = new CreateOrderDTO(UUID.randomUUID(), List.of(product, product)

		);
	}

	private void buildResponse() {
		createOrderResponseDTO = new CreateOrderResponseDTO(order.getId(), order.getPaymentId());
	}

}