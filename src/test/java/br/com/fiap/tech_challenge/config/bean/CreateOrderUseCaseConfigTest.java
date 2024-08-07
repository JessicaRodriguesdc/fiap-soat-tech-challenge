package br.com.fiap.tech_challenge.config.bean;

import br.com.fiap.tech_challenge.core.domain.ports.CustomerPersistence;
import br.com.fiap.tech_challenge.core.domain.ports.OrderPersistence;
import br.com.fiap.tech_challenge.core.domain.ports.PaymentGateway;
import br.com.fiap.tech_challenge.core.domain.ports.ProductPersistence;
import br.com.fiap.tech_challenge.core.domain.usecases.order.impl.CreateOrderUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseConfigTest {

    @Mock
    OrderPersistence orderPersistence;
    @Mock
    ProductPersistence productPersistence;
    @Mock
    CustomerPersistence customerPersistence;
    @Mock
    PaymentGateway paymentGateway;

    @InjectMocks
    private CreateOrderUseCaseConfig createOrderUseCaseConfig;

    @Test
    @DisplayName("Should Create a Singleton Instance Of CreateOrderUseCaseImpl")
    void shouldCreateSingletonInstanceOfCreateOrderUseCaseImpl() {
        var createOrderUseCaseImpl =
                createOrderUseCaseConfig.createOrderUseCaseImpl(
                        orderPersistence,
                        productPersistence,
                        customerPersistence,
                        paymentGateway
                );

        assertNotNull(createOrderUseCaseImpl);
        assertNotNull(orderPersistence);
        assertNotNull(productPersistence);
        assertNotNull(customerPersistence);
        assertNotNull(paymentGateway);
        assertInstanceOf(CreateOrderUseCaseImpl.class, createOrderUseCaseImpl);
    }
}