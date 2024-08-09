-- Inserindo dados na tabela CUSTOMER
INSERT INTO CUSTOMER (ID, NAME, DOCUMENT, EMAIL, CREATED_AT, UPDATED_AT) VALUES
('3d6fa6be-9dfb-4e51-b4d8-08f9351a2f17', 'João Silva', '12345678901', 'joao.silva@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('91b4b12b-3e3d-42ab-b3a7-c3f29cf1d835', 'Maria Oliveira', '23456789012', 'maria.oliveira@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ac3f8e72-4b7b-4d81-9e5c-4b8e5f2d6b9f', 'Carlos Pereira', '34567890123', 'carlos.pereira@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('bc4a9d21-6c7e-47f1-8f8c-5a9c6f3e8d6e', 'Ana Costa', '45678901234', 'ana.costa@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('cd5b8e43-5d8f-4e92-9f7d-6b9d7e4f7e9c', 'Pedro Martins', '56789012345', 'pedro.martins@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('de6c7f54-4e9a-4f83-8f6e-7c8d8e5f8a7d', 'Lucia Souza', '67890123456', 'lucia.souza@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserindo dados na tabela PRODUCT
INSERT INTO PRODUCT (ID, NAME, CATEGORY, PRICE, DESCRIPTION, STATUS, CREATED_AT, UPDATED_AT) VALUES
('a87f4a68-3c47-4b3c-911f-21b4e68b5b7a', 'Sanduíche de Frango', 'MAIN_COURSE', 25.00, 'Sanduíche de frango com salada', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('b2a1f914-5f68-4e76-9b1d-23f2b56113d9', 'Batata Frita', 'SIDE_DISH', 15.00, 'Batatas fritas crocantes', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('c3b2d5e6-6f7e-4f8b-9d7e-8f6e7d9f6e8b', 'Refrigerante', 'DRINK', 5.00, 'Refrigerante de cola', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('d4c3e7f5-5f8a-4e9c-9d8e-9e7f8a6b7c8d', 'Sorvete', 'DESSERT', 10.00, 'Sorvete de chocolate', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('e5d4f8a6-6f9a-4e8b-8e9d-8e7f9a6b8d9e', 'Salada', 'SIDE_DISH', 20.00, 'Salada verde com molho', 'INACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('f6e5a9b7-7a0b-4f8c-9d0e-9d8e9a6b7c8d', 'Suco de Laranja', 'DRINK', 7.00, 'Suco de laranja natural', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('7091cbdc-2d24-4faf-aadd-995a7bcc6b5b', 'Hambúrguer Clássico', 'MAIN_COURSE', 30.00, 'Hambúrguer com carne bovina, queijo e salada', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('bf00ac4a-dd15-4693-8dde-a20e8babf993', 'Cheeseburger', 'MAIN_COURSE', 28.00, 'Cheeseburger com carne bovina e queijo cheddar', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserindo dados na tabela CUSTOMER_ORDER
INSERT INTO CUSTOMER_ORDER (ID, CUSTOMER_ID, AMOUNT, PAYMENT_ID, STATUS, IS_PAID, CREATED_AT, UPDATED_AT) VALUES
('c5d2a3bc-2e7c-4b8b-8d3d-3c5d6e1f8c7e', '3d6fa6be-9dfb-4e51-b4d8-08f9351a2f17', 80.00, 'PAY123456', 'RECEIVED', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('d8f2b6cd-1e4e-44f1-bb4e-4c5d7e3f9d8f', '91b4b12b-3e3d-42ab-b3a7-c3f29cf1d835', 30.00, 'PAY789012', 'PREPARING', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('e7f3d8c5-3f7e-47f1-8d6e-4c5d6e1f7b8e', 'ac3f8e72-4b7b-4d81-9e5c-4b8e5f2d6b9f', 150.00, 'PAY345678', 'READY', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('0c327510-cf8e-4d01-ad49-56f92ebb76fa', 'bc4a9d21-6c7e-47f1-8f8c-5a9c6f3e8d6e', 100.00, 'PAY901234', 'FINISHED', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('f7705ca8-a01e-4256-956e-1429dda1b22d', 'cd5b8e43-5d8f-4e92-9f7d-6b9d7e4f7e9c', 120.00, 'PAY567890', 'RECEIVED', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('7b551f4b-9fec-444f-9c1e-eed939454f27', 'de6c7f54-4e9a-4f83-8f6e-7c8d8e5f8a7d', 90.00, 'PAY234567', 'FINISHED', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserindo dados na tabela ORDER_PRODUCT
INSERT INTO ORDER_PRODUCT (ID, ORDER_ID, PRODUCT_ID, PRICE, CUSTOMIZATION, CREATED_AT) VALUES
('e7d3a2f1-1f6c-4b8c-8f7d-5e3c6f1a8d7e', 'c5d2a3bc-2e7c-4b8b-8d3d-3c5d6e1f8c7e', 'a87f4a68-3c47-4b3c-911f-21b4e68b5b7a', 25.00, 'Sem customização', CURRENT_TIMESTAMP),
('f9d4b3e2-2e8d-4c9e-9f8e-6e4c7f2a9e8f', 'd8f2b6cd-1e4e-44f1-bb4e-4c5d7e3f9d8f', 'b2a1f914-5f68-4e76-9b1d-23f2b56113d9', 15.00, 'Embalagem especial', CURRENT_TIMESTAMP),
('5c4bff29-0273-424c-9ee7-94366ce29e72', 'e7f3d8c5-3f7e-47f1-8d6e-4c5d6e1f7b8e', 'c3b2d5e6-6f7e-4f8b-9d7e-8f6e7d9f6e8b', 5.00, 'Com mensagem', CURRENT_TIMESTAMP),
('59769cdf-1858-4f38-93a3-34e33b4150e9', '0c327510-cf8e-4d01-ad49-56f92ebb76fa', 'd4c3e7f5-5f8a-4e9c-9d8e-9e7f8a6b7c8d', 10.00, 'Embalagem premium', CURRENT_TIMESTAMP),
('0abfe76a-7465-4ebb-9f32-58d6efff28c9', 'f7705ca8-a01e-4256-956e-1429dda1b22d', 'e5d4f8a6-6f9a-4e8b-8e9d-8e7f9a6b8d9e', 20.00, 'Sem customização', CURRENT_TIMESTAMP),
('ae3b727a-62d6-43b8-960e-f254711229e9', '7b551f4b-9fec-444f-9c1e-eed939454f27', 'f6e5a9b7-7a0b-4f8c-9d0e-9d8e9a6b7c8d', 7.00, 'Com cartão', CURRENT_TIMESTAMP),
('95413534-8dae-4d9c-ace6-32f2b1f61249', '7b551f4b-9fec-444f-9c1e-eed939454f27', '7091cbdc-2d24-4faf-aadd-995a7bcc6b5b', 30.00, 'Sem customização', CURRENT_TIMESTAMP),
('873be086-5336-4cec-bef4-f9102978a2b4', 'd8f2b6cd-1e4e-44f1-bb4e-4c5d7e3f9d8f', 'bf00ac4a-dd15-4693-8dde-a20e8babf993', 28.00, 'Com queijo extra', CURRENT_TIMESTAMP);