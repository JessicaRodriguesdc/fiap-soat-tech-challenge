# Pós Tech - Tech Challenge Microservice

## Descrição

Este microserviço é responsável por gerenciar a entrada de pedidos de um restaurante. Ele lida com o processamento dos
pedidos, gerenciamento de pagamentos e envio dos pedidos para a cozinha após a confirmação do pagamento.

## Tecnologias Utilizadas

- **Java 21**
- **PostgreSQL**
- **Maven**
- **Spring Boot**
- **Spring Data JPA**
- **Docker**

## Arquitetura

O projeto segue a Arquitetura Hexagonal (Ports and Adapters), permitindo maior flexibilidade e facilidade de manutenção.

## Configuração

### Pré-requisitos

- Java 21
- Maven 3.6.3+
- PostgreSQL 13+

### Configuração do Banco de Dados

1. Instale e configure o PostgreSQL.
2. Crie um banco de dados chamado `techchallenge`.

### Arquivo de Configuração

No arquivo `src/main/resources/application.properties`, configure as propriedades do banco de dados:

```shell
spring.application.name=tech-challenge

spring.datasource.url=jdbc:postgresql://localhost:5432/techchallenge
spring.datasource.username={seu_usuario}
spring.datasource.password={sua_senha}

server.port=8357
server.servlet.context-path=/api
```

### Execução

Para compilar e executar o projeto, utilize os seguintes comandos:

mvn clean install
mvn spring-boot:run

O serviço estará disponível em http://localhost:8357.

### Contribuição

Este é um projeto que está em construção pelos desenvolvedores:

- Alexandre Miranda - RM357321
- Diego Ceccon - RM357437
- Jéssica Rodrigues - RM357218
- Rodrigo Sartori - RM358002
- Wilton Souza - RM357991

### Licença

Distribuído sob a licença MIT. Veja LICENSE para mais informações.