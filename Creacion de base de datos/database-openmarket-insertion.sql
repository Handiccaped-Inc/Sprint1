-- Inserts para la tabla rol
INSERT INTO rol (rol_name) VALUES
('vendedor'),
('comprador'),
('repartidor'),
('administrador');

-- Inserts para la tabla user
INSERT INTO user (rol_id, user_birth_date, user_email, user_phone, user_card, user_realname, user_username, user_password, user_address) VALUES
(2, '1990-01-01', 'johndoe@gmail.com', '123456789', '1234567890', 'John Doe', 'johndoe', '0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e', 'Dirección 1'),
(3, '1990-02-02', 'janesmith@yahoo.com', '987654321', '9876543210', 'Jane Smith', 'janesmith', '6cf615d5bcaac778352a8f1f3360d23f02f34ec182e259897fd6ce485d7870d4', 'Dirección 2'),
(1, '1990-03-03', 'robertjohnson@hotmail.com', '555555555', '5678901234', 'Robert Johnson', 'robertjohnson', '5906ac361a137e2d286465cd6588ebb5ac3f5ae955001100bc41577c3d751764', 'Dirección 3'),
(2, '1990-03-03', 'robertjohnson@gmail.com', '555555555', '3453453455', 'Robert Johnson', 'robertjohnson', 'b97873a40f73abedd8d685a7cd5e5f85e4a9cfb83eac26886640a0813850122b', 'Dirección 3'),
(3, '1991-04-04', 'michaelbrown@gmail.com', '123456789', '1357924680', 'Michael Brown', 'michaelbrown', '8b2c86ea9cf2ea4eb517fd1e06b74f399e7fec0fef92e3b482a6cf2e2b092023', 'Dirección 5'),
(1, '1991-05-05', 'jessicadavis@yahoo.com', '987654321', '2468013579', 'Jessica Davis', 'jessicadavis', '598a1a400c1dfdf36974e69d7e1bc98593f2e15015eed8e9b7e47a83b31693d5', 'Dirección 6'),
(2, '1991-06-06', 'davidwilson@gmail.com', '555555555', '9081726354', 'David Wilson', 'davidwilson', '5860836e8f13fc9837539a597d4086bfc0299e54ad92148d54538b5c3feefb7c', 'Dirección 7'),
(3, '1992-07-07', 'jennifermiller@yahoo.com', '555555555', '5432167890', 'Jennifer Miller', 'jennifermiller', '57f3ebab63f156fd8f776ba645a55d96360a15eeffc8b0e4afe4c05fa88219aa', 'Dirección 8'),
(1, '1992-08-08', 'williamjohnson@hotmail.com', '123456789', '9876543210', 'William Johnson', 'williamjohnson', '9323dd6786ebcbf3ac87357cc78ba1abfda6cf5e55cd01097b90d4a286cac90e', 'Dirección 9'),
(3, '1993-10-10', 'thomasdavis@gmail.com', '555555555', '2468135790', 'Thomas Davis', 'thomasdavis', '53d453b0c08b6b38ae91515dc88d25fbecdd1d6001f022419629df844f8ba433', 'Dirección 11'),
(1, '1993-11-11', 'elizabethwilson@yahoo.com', '555555555', '0123456789', 'Elizabeth Wilson', 'elizabethwilson', 'b3d17ebbe4f2b75d27b6309cfaae1487b667301a73951e7d523a039cd2dfe110', 'Dirección 12'),
(2, '1993-12-12', 'christophersmith@gmail.com', '123456789', '9876543210', 'Christopher Smith', 'christophersmith', '48caafb68583936afd0d78a7bfd7046d2492fad94f3c485915f74bb60128620d', 'Dirección 13'),
(3, '1994-01-13', 'amyjohnson@yahoo.com', '987654321', '5432167890', 'Amy Johnson', 'amyjohnson', 'c6863e1db9b396ed31a36988639513a1c73a065fab83681f4b77adb648fac3d6', 'Dirección 14'),
(1, '1994-02-14', 'danieldavis@hotmail.com', '555555555', '1357924680', 'Daniel Davis', 'danieldavis', 'c63c2d34ebe84032ad47b87af194fedd17dacf8222b2ea7f4ebfee3dd6db2dfb', 'Dirección 15'),
(2, '1994-03-15', 'sarahwilson@gmail.com', '555555555', '1234567890', 'Sarah Wilson', 'sarahwilson', '17a3379984b560dc311bb921b7a46b28aa5cb495667382f887a44a7fdbca7a7a', 'Dirección 16'),
(3, '1995-04-16', 'matthewmiller@yahoo.com', '123456789', '9081726354', 'Matthew Miller', 'matthewmiller', '69bfb918de05145fba9dcee9688dfb23f6115845885e48fa39945eebb99d8527', 'Dirección 17'),
(1, '1995-05-17', 'emilyjohnson@gmail.com', '987654321', '1357924680', 'Emily Johnson', 'emilyjohnson', 'd2042d75a67922194c045da2600e1c92ff6d87e8fb6e0208606665f2d1dfa892', 'Dirección 18'),
(2, '1995-06-18', 'andrewdavis@hotmail.com', '555555555', '2468013579', 'Andrew Davis', 'andrewdavis', '5790ac3d0b8ae8afc72c2c6fb97654f2b73651c328de0a3b74854ade562dd17a', 'Dirección 19'),
(3, '1996-07-19', 'oliviawilson@yahoo.com', '555555555', '5678901234', 'Olivia Wilson', 'oliviawilson', '7535d8f2d8c35d958995610f971287288ab5e8c82a3c4fdc2b6fb5d757a5b9f8', 'Dirección 20'),
(1, '1996-08-20', 'jamessmith@gmail.com', '123456789', '0123456789', 'James Smith', 'jamessmith', '91a9ef3563010ea1af916083f9fb03a117d4d0d2a697f82368da1f737629f717', 'Dirección 21'),
(2, '1996-09-21', 'sophiajohnson@gmail.com', '987654321', '9876543210', 'Sophia Johnson', 'sophiajohnson', 'd23c1038532dc71d0a60a7fb3d330d7606b7520e9e5ee0ddcdb27ee1bd5bc0cd', 'Dirección 22'),
(3, '1997-10-22', 'benjamindavis@hotmail.com', '555555555', '5432167890', 'Benjamin Davis', 'benjamindavis', '8b807aa0505a00b3ef49e26a2ade8e31fcd6c700d1a3aeee971b49d73da8e8ff', 'Dirección 23'),
(1, '1997-11-23', 'miawilson@yahoo.com', '555555555', '1357924680', 'Mia Wilson', 'miawilson', 'fc8a9296208a0b281f84690423c5d77785e493f435e6292cc322840f543729d3', 'Dirección 24'),
(2, '1997-12-24', 'henrysmith@gmail.com', '123456789', '1234567890', 'Henry Smith', 'henrysmith', '0b544d6d8da1d1af25318e97e0ac5f6bc70bba49919463dc0074ede01a49d381', 'Dirección 25'),
(3, '1998-01-25', 'avajohnson@yahoo.com', '987654321', '9081726354', 'Ava Johnson', 'avajohnson', '869f2a98b0e3a6ea928ff0542330ea3c1e0ff8591801693350f1fc3f1e57e4c5', 'Dirección 26'),
(1, '1998-02-26', 'josephdavis@hotmail.com', '555555555', '5432167890', 'Joseph Davis', 'josephdavis', '9c7568513b9c85e73f3650c8b00e3259501096ccee9d3dbdae6ff5e84aabe3af', 'Dirección 27'),
(2, '1998-03-27', 'isabellawilson@gmail.com', '555555555', '9876543210', 'Isabella Wilson', 'isabellawilson', '6f5ea1c4acc7a563ea8cb3381a55f0183a2394d679ebb7db8312e047bbf87e0d', 'Dirección 28'),
(3, '1999-04-28', 'danielmiller@yahoo.com', '123456789', '2468135790', 'Daniel Miller', 'danielmiller', '48a94846b2a7386432b90ad13bcf9c66f1efdd3a97e0e14968c262c412fe22c8', 'Dirección 29'),
(1, '1999-05-29', 'oliviajohnson@gmail.com', '987654321', '0123456789', 'Olivia Johnson', 'oliviajohnson', '7c682dea8e934e04343374e3d25cd51edce9cbeb47f7034296052cb5cd6bed84', 'Dirección 30');


-- Inserts para la tabla category
INSERT INTO category (category_name) VALUES
('Electrónica'),
('Ropa'),
('Hogar'),
('Deportes'),
('Música'),
('Libros'),
('Automóviles'),
('Juguetes'),
('Belleza'),
('Alimentación'),
('Electrodomésticos'),
('Mascotas'),
('Jardinería'),
('Salud'),
('Arte'),
('Películas'),
('Videojuegos'),
('Viajes'),
('Instrumentos musicales'),
('Fotografía'),
('Joyas'),
('Moda'),
('Tecnología'),
('Decoración'),
('Fitness'),
('Cocina'),
('Cervezas y vinos'),
('Bicicletas'),
('Camping'),
('Oficina'),
('Cuidado personal');

-- Inserts para la tabla state_product
INSERT INTO state_product (state_product_name) VALUES
('disponible'),
('no disponible');

-- Inserts para la tabla product
INSERT INTO product (user_id, category_id, state_product_id, product_name, product_description, product_price, product_stock, product_latitude, product_longitude) VALUES
(6, 1, 1, 'iPhone 12', 'El último modelo de iPhone con pantalla OLED y cámara dual', 99.99, 10, 37.7749, -122.4194),
(6, 2, 1, 'Camiseta Nike', 'Camiseta deportiva de alta calidad con logo de Nike', 29.99, 50, 40.7128, -74.0060),
(3, 3, 2, 'Sartén antiadherente', 'Sartén de cerámica de 12 pulgadas con revestimiento antiadherente', 39.99, 20, 51.5074, -0.1278),
(9, 4, 1, 'Guitarra acústica', 'Guitarra acústica de cuerpo de caoba y diapasón de palisandro', 199.99, 5, 34.0522, -118.2437),
(6, 5, 2, 'Álbum de vinilo', 'Clásico álbum de vinilo remasterizado de una banda legendaria', 24.99, 30, 52.5200, 13.4050),
(3, 6, 1, 'Novela de suspense', 'Bestseller internacional lleno de giros y misterio', 14.99, 15, 48.8566, 2.3522),
(1, 7, 2, 'Coche deportivo', 'Coche deportivo de alta gama con motor de alto rendimiento', 999.99, 2, 37.7749, -122.4194),
(14, 8, 1, 'Set de bloques de construcción', 'Set de 100 bloques de construcción para niños', 19.99, 100, 40.7128, -74.0060),
(3, 9, 2, 'Set de maquillaje', 'Set completo de maquillaje profesional para un look perfecto', 49.99, 10, 51.5074, -0.1278),
(6, 10, 1, 'Caja de cereales', 'Caja de cereales orgánicos de trigo integral', 4.99, 50, 34.0522, -118.2437),
(3, 11, 2, 'Refrigerador', 'Refrigerador de acero inoxidable con capacidad de 20 pies cúbicos', 899.99, 8, 52.5200, 13.4050),
(11, 12, 1, 'Comida para perros', 'Alimento seco para perros de raza mediana', 29.99, 25, 48.8566, 2.3522),
(1, 13, 2, 'Plantas de interior', 'Set de 3 plantas de interior de fácil cuidado', 24.99, 40, 37.7749, -122.4194),
(6, 14, 1, 'Suplemento vitamínico', 'Suplemento vitamínico completo para una vida saludable', 9.99, 100, 40.7128, -74.0060),
(17, 15, 2, 'Pintura al óleo', 'Pintura al óleo original de un artista reconocido', 199.99, 3, 51.5074, -0.1278),
(3, 16, 1, 'Blu-ray de película', 'Edición especial en Blu-ray de una película aclamada', 14.99, 20, 34.0522, -118.2437),
(14, 17, 2, 'Videojuego de aventura', 'Último videojuego de aventura con gráficos de última generación', 59.99, 10, 52.5200, 13.4050),
(6, 18, 1, 'Paquete de viaje', 'Paquete de viaje todo incluido a un destino paradisíaco', 199.99, 2, 48.8566, 2.3522),
(3, 19, 2, 'Piano de cola', 'Piano de cola de concierto de alta calidad y sonido excepcional', 999.99, 1, 37.7749, -122.4194),
(9, 20, 1, 'Cámara DSLR', 'Cámara DSLR profesional con múltiples funciones y lentes intercambiables', 799.99, 5, 40.7128, -74.0060),
(17, 1, 1, 'Smart TV 4K', 'Televisor inteligente con resolución 4K y pantalla de 55 pulgadas', 799.99, 10, 37.7749, -122.4194),
(9, 2, 1, 'Zapatos deportivos', 'Zapatos deportivos para correr con tecnología de amortiguación', 89.99, 30, 40.7128, -74.0060),
(3, 3, 2, 'Juego de ollas y sartenes', 'Set de cocina con ollas y sartenes antiadherentes', 149.99, 15, 51.5074, -0.1278),
(17, 4, 1, 'Bicicleta de montaña', 'Bicicleta todoterreno con suspensión y frenos de disco', 499.99, 5, 34.0522, -118.2437),
(17, 5, 2, 'Altavoz Bluetooth', 'Altavoz portátil con conexión Bluetooth y batería de larga duración', 39.99, 50, 52.5200, 13.4050),
(3, 6, 1, 'Set de libros de fantasía', 'Colección de libros de fantasía de renombrado autor', 59.99, 20, 48.8566, 2.3522),
(20, 7, 2, 'Motocicleta deportiva', 'Motocicleta de alto rendimiento con diseño aerodinámico', 12999.99, 2, 37.7749, -122.4194),
(6, 8, 1, 'Muñeca de peluche', 'Muñeca de peluche suave y tierna para niños pequeños', 14.99, 100, 40.7128, -74.0060),
(9, 9, 2, 'Perfume masculino', 'Fragancia exclusiva para hombres con notas amaderadas', 79.99, 10, 51.5074, -0.1278),
(3, 10, 1, 'Café gourmet', 'Café gourmet de origen único, tostado y molido', 19.99, 50, 34.0522, -118.2437),
(14, 11, 2, 'Lavadora y secadora', 'Combo de lavadora y secadora con múltiples funciones', 899.99, 8, 52.5200, 13.4050),
(6, 12, 1, 'Comedero automático para mascotas', 'Comedero automático programable para perros y gatos', 49.99, 25, 48.8566, 2.3522),
(20, 13, 2, 'Plantas de exterior', 'Set de 5 plantas de exterior resistentes y fáciles de cuidar', 34.99, 40, 37.7749, -122.4194),
(11, 14, 1, 'Multivitamínico para mujeres', 'Suplemento multivitamínico diseñado específicamente para mujeres', 9.99, 100, 40.7128, -74.0060),
(3, 15, 2, 'Cuadro de arte abstracto', 'Cuadro de arte abstracto pintado a mano en lienzo', 249.99, 3, 51.5074, -0.1278),
(9, 16, 1, 'Videojuego de rol', 'Videojuego de rol épico con un vasto mundo abierto', 49.99, 10, 34.0522, -118.2437),
(6, 17, 2, 'Consola de juegos', 'Consola de juegos de última generación con control inalámbrico', 299.99, 5, 52.5200, 13.4050),
(20, 18, 1, 'Paquete de vacaciones', 'Paquete de vacaciones de lujo en un resort de playa', 2499.99, 2, 48.8566, 2.3522),
(3, 19, 2, 'Guitarra eléctrica', 'Guitarra eléctrica de alta calidad para músicos profesionales', 1499.99, 1, 37.7749, -122.4194),
(11, 20, 1, 'Cámara sin espejo', 'Cámara sin espejo de fotograma completo con grabación de video 4K', 1499.99, 5, 40.7128, -74.0060);

-- Inserts para la tabla shopping_cart
INSERT INTO shopping_cart (user_id, product_id, shopping_cart_quantity) VALUES
(1, 1, 2),
(2, 2, 1),
(4, 3, 3),
(5, 4, 2),
(7, 5, 1),
(8, 6, 3),
(10, 7, 2),
(12, 8, 1);

-- Inserts para la tabla order_status
INSERT INTO order_status (order_status_name) VALUES
('entregado'),
('cancelado'),
('en espera');

-- Inserts para la tabla orders
INSERT INTO orders (user_id, product_id, order_status_id, orders_price, orders_qualification, orders_date) VALUES
(1, 1, 1, 10.99, NULL, 'Mon May 29 03:10:30 COT 2023'),
(2, 2, 2, 19.99, NULL, 'Tue May 30 03:10:30 COT 2023'),
(4, 3, 3, 5.99, NULL, 'Wed May 31 03:10:30 COT 2023'),
(5, 4, 1, 14.99, NULL, 'Thu Jun 01 03:10:30 COT 2023'),
(7, 5, 2, 39.99, NULL, 'Fri Jun 02 03:10:30 COT 2023'),
(8, 6, 3, 59.99, NULL, 'Sat Jun 03 03:10:30 COT 2023'),
(10, 7, 1, 129.99, NULL, 'Sun Jun 04 03:10:30 COT 2023'),
(12, 8, 2, 14.99, NULL, 'Mon Jun 05 03:10:30 COT 2023'),
(13, 9, 3, 79.99, NULL, 'Mon Jun 05 03:10:31 COT 2023'),
(15, 10, 1, 19.99, NULL, 'Mon Jun 05 03:10:32 COT 2023'),
(16, 11, 2, 89.99, NULL, 'Mon Jun 05 03:10:33 COT 2023'),
(18, 12, 1, 49.99, NULL, 'Mon Jun 05 03:10:34 COT 2023'),
(19, 13, 3, 34.99, NULL, 'Mon Jun 05 03:10:35 COT 2023');


-- Inserts para la tabla delivery
INSERT INTO delivery (orders_id, deliveryman_id, delivery_receiver, delivery_date) VALUES
(1, 3, 'Juan Pérez', 'Sat Jun 03 03:10:30 COT 2023'),
(2, 2, 'María Gómez', 'Sun Jun 04 09:25:15 COT 2023'),
(3, 1, 'Carlos Rodríguez', 'Mon Jun 05 14:40:00 COT 2023'),
(4, 4, 'Laura López', 'Tue Jun 06 20:55:45 COT 2023'),
(5, 5, 'Luis Martínez', 'Wed Jun 07 02:10:30 COT 2023'),
(6, 6, 'Ana Sánchez', 'Thu Jun 08 08:25:15 COT 2023'),
(7, 7, 'Pedro Ramírez', 'Fri Jun 09 14:40:00 COT 2023'),
(8, 8, 'Isabel Torres', 'Sat Jun 10 20:55:45 COT 2023'),
(9, 9, 'Manuel Castro', 'Sun Jun 11 02:10:30 COT 2023'),
(10, 10, 'Sofía Morales', 'Mon Jun 12 08:25:15 COT 2023'),
(11, 11, 'Jorge Fernández', 'Tue Jun 13 14:40:00 COT 2023'),
(12, 12, 'Lucía Medina', 'Wed Jun 14 20:55:45 COT 2023'),
(13, 13, 'Gonzalo Herrera', 'Thu Jun 15 02:10:30 COT 2023');
