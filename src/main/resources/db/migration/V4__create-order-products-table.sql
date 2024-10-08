CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE ORDER_PRODUCT (
    ID UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    ORDER_ID UUID REFERENCES CUSTOMER_ORDER(id),
    PRODUCT_ID UUID REFERENCES PRODUCT(id),
    PRICE DECIMAL(10, 2) NOT NULL,
    CUSTOMIZATION VARCHAR(100),
    CREATED_AT TIMESTAMP
);