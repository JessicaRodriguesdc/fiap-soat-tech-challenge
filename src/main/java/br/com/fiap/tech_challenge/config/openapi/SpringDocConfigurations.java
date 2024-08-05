package br.com.fiap.tech_challenge.config.openapi;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.*;
import br.com.fiap.tech_challenge.config.openapi.schemas.PageableOrderResponseDTO;
import br.com.fiap.tech_challenge.config.openapi.schemas.PageableProductResponseDTO;
import br.com.fiap.tech_challenge.adapters.driver.api.handler.ProblemDTO;
import br.com.fiap.tech_challenge.config.openapi.schemas.PageablePageableResponseDTO;
import br.com.fiap.tech_challenge.config.openapi.schemas.PageableSortResponseDTO;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringDocConfigurations {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Tech Challenge FIAP API").version("v1").description("""
				API Rest for Tech Challenge of Master's Degree in Software Architecture \n
				Developed by:\n
				 - Alexandre Miranda - RM357321\n
				 - Diego Ceccon - RM357437\n
				 - Jéssica Rodrigues - RM357218\n
				 - Rodrigo Sartori - RM358002\n
				 - Wilton Souza - RM357991\n
				""").contact(new Contact().name("SOAT 8 Group").email("soat-group@gmail.com")))
			.components(new Components().schemas(this.generateSchemas()));
	}

	@SuppressWarnings("rawtypes")
	private Map<String, Schema> generateSchemas() {
		final Map<String, Schema> schemaMap = new HashMap<>();

		Map<String, Schema> problemSchema = ModelConverters.getInstance().read(ProblemDTO.class);
		Map<String, Schema> customerResponseDto = ModelConverters.getInstance().read(CustomerResponseDTO.class);
		Map<String, Schema> orderResponseDto = ModelConverters.getInstance().read(CreateOrderResponseDTO.class);
		Map<String, Schema> orderProductsResponseDto = ModelConverters.getInstance()
			.read(CreateOrderRequestDTO.OrderProducts.class);
		Map<String, Schema> productResponseDto = ModelConverters.getInstance().read(ProductResponseDTO.class);
		Map<String, Schema> productPageableResponseDto = ModelConverters.getInstance()
			.read(PageableProductResponseDTO.class);
		Map<String, Schema> orderPageableResponseDto = ModelConverters.getInstance()
			.read(PageableOrderResponseDTO.class);
		Map<String, Schema> pageableSortResponseDto = ModelConverters.getInstance().read(PageableSortResponseDTO.class);
		Map<String, Schema> pageablePageableResponseDto = ModelConverters.getInstance()
			.read(PageablePageableResponseDTO.class);
		Map<String, Schema> orderSummaryResponseDTO = ModelConverters.getInstance().read(OrderSummaryResponseDTO.class);
		Schema errorsValidateDataArraySchema = new ArraySchema()
			.items(new Schema<>().$ref("#/components/schemas/ErrorsValidateData"));

		schemaMap.putAll(problemSchema);
		schemaMap.putAll(customerResponseDto);
		schemaMap.putAll(orderResponseDto);
		schemaMap.putAll(orderProductsResponseDto);
		schemaMap.putAll(productResponseDto);
		schemaMap.putAll(orderPageableResponseDto);
		schemaMap.putAll(productPageableResponseDto);
		schemaMap.putAll(pageableSortResponseDto);
		schemaMap.putAll(pageablePageableResponseDto);
		schemaMap.putAll(orderSummaryResponseDTO);
		schemaMap.put("ErrorsValidateDataList", errorsValidateDataArraySchema);

		return schemaMap;
	}

}