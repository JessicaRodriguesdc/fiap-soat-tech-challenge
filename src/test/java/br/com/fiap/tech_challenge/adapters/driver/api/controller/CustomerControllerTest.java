package br.com.fiap.tech_challenge.adapters.driver.api.controller;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.CustomerRequestDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.dto.CustomerResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.handler.ControllerAdvice;
import br.com.fiap.tech_challenge.adapters.driver.api.mapper.CustomerMapper;
import br.com.fiap.tech_challenge.core.domain.exceptions.AlreadyExistsException;
import br.com.fiap.tech_challenge.core.domain.exceptions.DoesNotExistException;
import br.com.fiap.tech_challenge.core.domain.models.Customer;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.CreateCustomerUseCase;
import br.com.fiap.tech_challenge.core.domain.usecases.customer.FindCustomerByDocumentUseCase;
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

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateCustomerUseCase createCustomerUseCase;

    @Mock
    private FindCustomerByDocumentUseCase findCustomerByDocumentUseCase;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerController customerController;

    private final String baseUrl = "/v1/customers";

    private Customer customer;

    private CustomerRequestDTO customerRequestDTO;

    private CustomerResponseDTO customerResponseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .setControllerAdvice(new ControllerAdvice())
                .build();
        this.buildArranges();
    }

    @Test
    @DisplayName("Should Create A New Customer")
    void shouldCreateANewCustomer()  throws Exception{
        when(mapper.toCustomer(customerRequestDTO)).thenReturn(customer);
        when(createCustomerUseCase.create(customer)).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(customerResponseDTO.id().toString()))
                .andExpect(jsonPath("$.name").value(customerResponseDTO.name()))
                .andExpect(jsonPath("$.document").value(customerResponseDTO.document()))
                .andExpect(jsonPath("$.email").value(customerResponseDTO.email()));
    }

    @Test
    @DisplayName("Should return Conflict when Customer already exists")
    void shouldReturnConflictWhenCustomerAlreadyExists() throws Exception {
        when(mapper.toCustomer(customerRequestDTO)).thenReturn(customer);
        when(createCustomerUseCase.create(customer)).thenThrow(AlreadyExistsException.class);

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should Find Customer By Document")
    void shouldFindCustomerByDocument() throws Exception {
        when(findCustomerByDocumentUseCase.findByDocument(customer.getDocument())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/" + customer.getDocument())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerResponseDTO.id().toString()))
                .andExpect(jsonPath("$.name").value(customerResponseDTO.name()))
                .andExpect(jsonPath("$.document").value(customerResponseDTO.document()))
                .andExpect(jsonPath("$.email").value(customerResponseDTO.email()));
    }

    @Test
    @DisplayName("Should return NotFound when Customer Doesn't Exist")
    void shouldReturnNotFoundWhenCustomerDoesNotExist() throws Exception {
        when(findCustomerByDocumentUseCase.findByDocument(customer.getDocument())).thenThrow(DoesNotExistException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/" + customer.getDocument())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private void buildArranges(){
        var id = UUID.randomUUID();
        var name = "Walter White";
        var document = "31739380037";
        var email = "heisenberg@gmail.com";

        customer = new Customer(id, name, document, email);
        customerRequestDTO = new CustomerRequestDTO(name, document, email);
        customerResponseDTO = new CustomerResponseDTO(id, name, document, email);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}