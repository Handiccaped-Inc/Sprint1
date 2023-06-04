-- Inserts para la tabla rol
INSERT INTO rol (rol_name) VALUES
('vendedor'),
('comprador'),
('repartidor'),
('administrador');

-- Inserts para la tabla user
INSERT INTO user (rol_id, user_birth_date, user_email, user_phone, user_card, user_realname, user_username, user_password, user_address) VALUES
(2, '1990-01-01', 'usuario1@example.com', '123456789', '1234567890', 'Usuario 1', 'usuario1', 'password1', 'Dirección 1'),
(3, '1990-02-02', 'usuario2@example.com', '987654321', '0987654321', 'Usuario 2', 'usuario2', 'password2', 'Dirección 2'),
(1, '1990-03-03', 'usuario3@example.com', '555555555', '1111111111', 'Usuario 3', 'usuario3', 'password3', 'Dirección 3'),
(2, '1990-03-03', 'usuario10@example.com', '555555555', '1111111111', 'Usuario 3', 'usuario3', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'Dirección 3');

-- Inserts para la tabla category
INSERT INTO category (category_name) VALUES
('Categoría 1'),
('Categoría 2'),
('Categoría 3');

-- Inserts para la tabla state_product
INSERT INTO state_product (state_product_name) VALUES
('disponible'),
('no disponible');

-- Inserts para la tabla product
INSERT INTO product (user_id, category_id, state_product_id, product_name, product_description, product_price, product_stock, product_latitude, product_longitude) VALUES
(1, 1, 1, 'Producto 1', 'Descripción del producto 1', 10.99, 50, 123.456, 789.012),
(2, 2, 1, 'Producto 2', 'Descripción del producto 2', 19.99, 20, 345.678, 901.234),
(3, 3, 2, 'Producto 3', 'Descripción del producto 3', 5.99, 10, 567.890, 123.456);

-- Inserts para la tabla shopping_cart
INSERT INTO shopping_cart (user_id, product_id, shopping_cart_quantity) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 3);

-- Inserts para la tabla order_status
INSERT INTO order_status (order_status_name) VALUES
('entregado'),
('cancelado'),
('en espera');

-- Inserts para la tabla orders
INSERT INTO orders (user_id, product_id, order_status_id, orders_price, orders_qualification, orders_date) VALUES
(1, 1, 1, 10.99, NULL, '2023-05-29'),
(2, 2, 2, 19.99, NULL, '2023-05-29'),
(3, 3, 3, 5.99, NULL, '2023-05-29');
-- Inserts para la tabla delivery
INSERT INTO delivery (orders_id, deliveryman_id, delivery_receiver, delivery_date) VALUES
(1, 3, 'Receptor 1', '2023-05-29'),
(2, 2, 'Receptor 2', '2023-05-29'),
(3, 1, 'Receptor 3', '2023-05-29');
