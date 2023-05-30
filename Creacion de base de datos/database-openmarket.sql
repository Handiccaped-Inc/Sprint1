/**
* Autores:
*       Santiago Agredo Vallejo 
*       Pablo Jose Restrepo Ruiz
*       Jojan Esteban Serna Serna
*       Arturo Restrepo Ruiz
*/

-- Tabla rol: Almacena los roles de los usuarios
CREATE TABLE rol (
    rol_id INTEGER PRIMARY KEY,
    rol_name TEXT NOT NULL,
    CONSTRAINT ckc_rol CHECK (rol_name IN ('vendedor', 'comprador', 'repartidor', 'administrador'))
);

-- Tabla user: Almacena los datos de los usuarios
CREATE TABLE user (
    user_id INTEGER PRIMARY KEY,
    rol_id INTEGER NOT NULL,
    user_birth_date DATE NOT NULL,
    user_email TEXT NOT NULL,
    user_phone TEXT NOT NULL,
    user_card TEXT NOT NULL,
    user_realname TEXT NOT NULL,
    user_username TEXT NOT NULL,
    user_password TEXT NOT NULL,
    user_address TEXT NOT NULL,
    CONSTRAINT fk_user_rol FOREIGN KEY (rol_id) REFERENCES rol (rol_id)
);

-- Tabla category: Almacena las categorías de productos
CREATE TABLE category (
    category_id INTEGER PRIMARY KEY,
    category_name TEXT NOT NULL
);

-- Tabla state_product: Almacena los estados de los productos
CREATE TABLE state_product (
    state_product_id INTEGER PRIMARY KEY,
    state_product_name TEXT NOT NULL,
    CONSTRAINT ckc_state_product CHECK (state_product_name IN ('disponible','no disponible'))
);

-- Tabla product: Almacena los datos de los productos
CREATE TABLE product (
    user_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    state_product_id INTEGER NOT NULL,
    product_id INTEGER PRIMARY KEY,
    product_name TEXT NOT NULL,
    product_description TEXT NOT NULL,
    product_price REAL NOT NULL,
    product_stock INTEGER NOT NULL,
    product_latitude REAL NOT NULL,
    product_longitude REAL NOT NULL,
    CONSTRAINT fk_product_user FOREIGN KEY (user_id) REFERENCES user (user_id),
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (category_id),
    CONSTRAINT fk_product_state FOREIGN KEY (state_product_id) REFERENCES state_product (state_product_id)
);

-- Tabla shopping_cart: Almacena los productos en el carrito de compras de los usuarios
CREATE TABLE shopping_cart (
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    shopping_cart_id INTEGER PRIMARY KEY,
    shopping_cart_quantity INTEGER NOT NULL,
    CONSTRAINT fk_shopping_cart_user FOREIGN KEY (user_id) REFERENCES user (user_id),
    CONSTRAINT fk_shopping_cart_product FOREIGN KEY (product_id) REFERENCES product (product_id)
);

-- Tabla order_status: Almacena los estados de los pedidos
CREATE TABLE order_status (
    order_status_id INTEGER PRIMARY KEY,
    order_status_name TEXT NOT NULL,
    CONSTRAINT ckc_order_status CHECK (order_status_name IN ('entregado','cancelado','en espera'))
);

-- Tabla orders: Almacena los pedidos realizados por los usuarios
CREATE TABLE orders (
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    orders_id INTEGER PRIMARY KEY AUTOINCREMENT,
    order_status_id INTEGER NOT NULL,
    orders_price REAL NOT NULL,
    orders_qualification REAL,
    orders_date DATE NOT NULL,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES user (user_id),
    CONSTRAINT fk_orders_product FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT fk_orders_order_status FOREIGN KEY (order_status_id) REFERENCES order_status (order_status_id)
);

-- Tabla delivery: Almacena la información de los envíos
CREATE TABLE delivery (
    orders_id INTEGER NOT NULL,
    repartidor_id INTEGER NOT NULL,
    delivery_id INTEGER PRIMARY KEY AUTOINCREMENT,
    delivery_receptor TEXT NOT NULL,
    delivery_date DATE NOT NULL,
    CONSTRAINT fk_delivery_orders FOREIGN KEY (orders_id) REFERENCES orders (orders_id),
    CONSTRAINT fk_delivery_user FOREIGN KEY (repartidor_id) REFERENCES user (user_id)
);
