-- Inserciones de datos

INSERT INTO rol (rol_name) VALUES
('Administrador'),
('Usuario Regular'),
('Invitado');

INSERT INTO user (rol_id, user_birth_date, user_email, user_phone, user_card, user_realname, user_username, user_password) VALUES
(1, '1990-05-15', 'santiago@example.com', '1234567890', '1234 5678 9012 3456', 'Santiago Agredo', 'santiago', 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f'),-- password123
(2, '1995-10-20', 'pablo@example.com', '9876543210', '9876 5432 1098 7654', 'Pablo Restrepo', 'pablo', 'c6ba91b90d922e159893f46c387e5dc1b3dc5c101a5a4522f03b987177a24a91'),-- password456
(2, '1988-12-01', 'jojan@example.com', '5555555555', '5555 5555 5555 5555', 'Jojan Serna', 'jojan', '5efc2b017da4f7736d192a74dde5891369e0685d4d38f2a455b6fcdab282df9c'), -- password789
(3, '2000-03-25', 'arturo@example.com', '9999999999', '9999 9999 9999 9999', 'Arturo Restrepo', 'arturo', '6733b7ffeace4887c3b31258079c780d8db3018db9cbc05c500df3521f968df8'); --passwordabc

INSERT INTO category (category_name) VALUES
('Electrónica'),
('Ropa'),
('Hogar');

INSERT INTO state_product (state_product_name) VALUES
('disponible'),
('no disponible');

INSERT INTO product (user_id, category_id, state_product_id, product_name, product_description, product_price, product_stock, product_latitude, product_length) VALUES
(1, 1, 1, 'Teléfono móvil', 'Un teléfono inteligente de última generación', 999.99, 10, 12345, 67890),
(2, 2, 1, 'Camiseta', 'Camiseta de algodón de manga corta', 19.99, 50, 54321, 98765),
(3, 1, 2, 'Portátil', 'Portátil ligero y potente', 1499.99, 5, 98765, 43210),
(4, 3, 1, 'Sofá', 'Un cómodo sofá de tres plazas', 699.99, 3, 24680, 13579);

INSERT INTO shopping_cart (user_id, product_id, shopping_cart_quantity) VALUES
(1, 1, 2),
(2, 3, 1),
(3, 2, 3),
(4, 1, 1);

INSERT INTO order_status (order_status_name) VALUES
('entregado'),
('cancelado'),
('en espera');

INSERT INTO orders (user_id, product_id, order_status_id, orders_status, orders_price) VALUES
(1, 1, 1, 'Pendiente', 999.99),
(2, 2, 2, 'Cancelado', 19.99),
(3, 3, 3, 'En espera', 1499.99),
(4, 4, 1, 'Pendiente', 699.99);

INSERT INTO delivery (orders_id, repartidor_id, delivery_receptor, delivery_date) VALUES
(1, 3, 'Juan Pérez', '2023-05-01'),
(2, 4, 'María López', '2023-05-02'),
(3, 2, 'Carlos Gómez', '2023-05-03'),
(4, 1, 'Ana Martínez', '2023-05-04');