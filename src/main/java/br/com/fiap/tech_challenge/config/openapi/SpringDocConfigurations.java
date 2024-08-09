package br.com.fiap.tech_challenge.config.openapi;

import br.com.fiap.tech_challenge.adapters.driver.api.dto.*;
import br.com.fiap.tech_challenge.adapters.driver.api.handler.ProblemDTO;
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
		Map<String, Schema> createOrderResponseDto = ModelConverters.getInstance().read(CreateOrderResponseDTO.class);
		Map<String, Schema> orderProductsResponseDto = ModelConverters.getInstance().read(CreateOrderRequestDTO.OrderProducts.class);
		Map<String, Schema> orderPageResponseDto = ModelConverters.getInstance().read(OrderPageResponseDTO.class);
		Map<String, Schema> orderResponseDto = ModelConverters.getInstance().read(OrderResponseDTO.class);
		Map<String, Schema> productPageResponseDto = ModelConverters.getInstance().read(ProductPageResponseDTO.class);
		Map<String, Schema> productResponseDto = ModelConverters.getInstance().read(ProductResponseDTO.class);
		Map<String, Schema> pageResponseDto = ModelConverters.getInstance().read(PageResponseDTO.class);

		Schema errorsValidateDataArraySchema = new ArraySchema()
			.items(new Schema<>().$ref("#/components/schemas/ErrorsValidateData"));

		schemaMap.putAll(problemSchema);
		schemaMap.putAll(customerResponseDto);
		schemaMap.putAll(createOrderResponseDto);
		schemaMap.putAll(orderProductsResponseDto);
		schemaMap.putAll(orderPageResponseDto);
		schemaMap.putAll(orderResponseDto);
		schemaMap.putAll(productPageResponseDto);
		schemaMap.putAll(productResponseDto);
		schemaMap.putAll(pageResponseDto);
		schemaMap.put("ErrorsValidateDataList", errorsValidateDataArraySchema);

		return schemaMap;
	}

}