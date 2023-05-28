-- Insertar datos en la tabla 'rol'
INSERT INTO rol (rol_id, rol_name) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- Insertar datos en la tabla 'user'
INSERT INTO user (user_id, rol_id, user_birth_date, user_email, user_phone, user_card, user_realname, user_username, user_password) VALUES
(1, 1, '1990-01-01', 'admin@example.com', '123456789', '1234567890123456', 'Administrador', 'admin', 'admin123'),
(2, 2, '1995-05-10', 'user@example.com', '987654321', '9876543210987654', 'Usuario', 'user', 'user123');

-- Insertar datos en la tabla 'category'
INSERT INTO category (category_id, category_name) VALUES
(1, 'Electrónica'),
(2, 'Ropa'),
(3, 'Hogar');

-- Insertar datos en la tabla 'product'
INSERT INTO product (product_id, user_id, category_id, product_name, product_description, product_price, product_stock, product_latitude, product_length) VALUES
(1, 2, 1, 'Televisor', 'Televisor de alta definición', 599.99, 10, 123, 456),
(2, 1, 2, 'Camiseta', 'Camiseta de algodón', 19.99, 50, 789, 012);

-- Insertar datos en la tabla 'orders'
INSERT INTO orders (orders_id, user_id, product_id, orders_status, orders_price) VALUES
(1, 2, 1, 'entregado', 599.99),
(2, 1, 2, 'en espera', 19.99);

-- Insertar datos en la tabla 'delivery'
INSERT INTO delivery (delivery_id, orders_id, repartidor_id, delivery_receptor, delivery_date) VALUES
(1, 1, 2, 'Juan Pérez', '2023-05-27'),
(2, 2, 1, 'María Gómez', '2023-05-28');
