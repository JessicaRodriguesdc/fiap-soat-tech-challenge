package br.com.fiap.tech_challenge.application.usecases.customer.impl;

import br.com.fiap.tech_challenge.ConstantTimes;
import br.com.fiap.tech_challenge.infra.gateway.database.repository.impl.CustomerPersistenceImpl;
import br.com.fiap.tech_challenge.application.usecase.customer.impl.FindCustomerByDocumentUseCaseImpl;
import br.com.fiap.tech_challenge.application.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.domain.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindCustomerByDocumentUseCaseImplTest {

	@Mock
	private CustomerPersistenceImpl customerPersistence;

	@InjectMocks
	private FindCustomerByDocumentUseCaseImpl findCustomerByDocumentUseCase;

	private Customer customerExpected;

	@BeforeEach
	void setUp() {
		this.buildArranges();
	}

	@Test
    @DisplayName("Should return a new Customer")
    void shouldReturnACustomer(){
        when(customerPersistence.findByDocument(customerExpected.getDocument())).thenReturn(Optional.of(customerExpected));

        var customerFound = findCustomerByDocumentUseCase.findByDocument(customerExpected.getDocument());

        verify(customerPersistence,times(ConstantTimes.ONLY_ONCE)).findByDocument(any());
        verifyNoMoreInteractions(customerPersistence);

        assertNotNull(customerFound);
        assertNotNull(customerFound.getId());
        assertNotNull(customerFound.getName());
        assertNotNull(customerFound.getDocument());
        assertNotNull(customerFound.getEmail());
    }

	@Test
    @DisplayName("Shouldn't throw exception when Customer Was Found")
    void shouldNotThrowExceptionWhenCustomerWasFound(){
        when(customerPersistence.findByDocument(customerExpected.getDocument())).thenReturn(Optional.of(customerExpected));

        assertDoesNotThrow(() -> {
            findCustomerByDocumentUseCase.findByDocument(customerExpected.getDocument());
        });

        verify(customerPersistence,times(ConstantTimes.ONLY_ONCE)).findByDocument(any());
        verifyNoMoreInteractions(customerPersistence);
    }

	@Test
    @DisplayName("Should throws DoesNotExistException when Customer Doesn't Exist")
    void shouldThrowsDoesNotExistExceptionWhenCustomerDoesNotExist(){
        when(customerPersistence.findByDocument(customerExpected.getDocument())).thenReturn(Optional.empty());

        assertThrows(DoesNotExistException.class, () -> {
            findCustomerByDocumentUseCase.findByDocument(customerExpected.getDocument());
        });

        verify(customerPersistence,times(ConstantTimes.ONLY_ONCE)).findByDocument(any());
        verifyNoMoreInteractions(customerPersistence);
    }

	private void buildArranges() {
		customerExpected = new Customer(UUID.randomUUID(), "Walter White", "31739380037", "heisenberg@gmail.com");
	}

}