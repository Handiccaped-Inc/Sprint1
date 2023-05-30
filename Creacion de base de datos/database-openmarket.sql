/**
* Autores:
*       Santiago Agredo Vallejo 
*       Pablo Jose Restrepo Ruiz
*       Jojan Esteban Serna Serna
*       Arturo Restrepo Ruiz
*/

create table rol(
    rol_id integer primary key,
    rol_name text not null
);

create table user(
    user_id integer primary key,
    rol_id integer not null, 
    user_birth_date date not null,
    user_email text not null,
    user_phone text not null,
    user_card text not null,
    user_realname text not null,
    user_username text not null,
    user_password text not null,
    user_address text not null,
    constraint fk_user_rol FOREIGN key (rol_id) REFERENCES rol(rol_id)
);

create table category(
    category_id integer primary key,
    category_name text not null
);

create table state_product(
    state_product_id integer primary key,
    state_product_name text not null,
    constraint ckc_state_product CHECK (state_product_name IN ('disponible','no disponible'))
);

create table product(
    product_id integer primary key,
    user_id integer not null,
    category_id integer not null,
    state_product_id integer not null,
    product_name text not null,
    product_description text not null,
    product_price real not null,
    product_stock integer not null,
    product_latitude REAL not null,
    product_longitude REAL not null,
    constraint fk_product_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    constraint fk_product_category FOREIGN KEY (category_id) REFERENCES category(category_id),
    constraint fk_product_state FOREIGN KEY (state_product_id) REFERENCES state_product(state_product_id)
);

create table shopping_cart(
    shopping_cart_id integer primary key,
    user_id integer not null,
    product_id integer not null,
    shopping_cart_quantity integer not null,
    constraint fk_shopping_cart_user FOREIGN KEY (user_id) REFERENCES user(user_id),
    constraint fk_shopping_cart_product FOREIGN KEY (product_id) REFERENCES product(product_id)
);

 create table order_status (
    order_status_id integer primary key,
    order_status_name text not null,
    constraint ckc_order_status CHECK (order_status_name IN ('entregado','cancelado','en espera'))
 );

CREATE TABLE orders(
  orders_id INTEGER primary key, ----Incrementativa
  user_id INTEGER not null,
  product_id INTEGER not null,
  order_status_id INTEGER not null,
  orders_status TEXT not null,
  orders_price REAL not null,
  orders_qualification REAL,
  constraint fk_orders_user FOREIGN KEY (user_id) REFERENCES user(user_id),
  constraint fk_orders_product FOREIGN KEY (product_id) REFERENCES product(product_id),
  constraint fk_orders_order_status FOREIGN KEY (order_status_id) REFERENCES order_status(order_status_id)
);

create table delivery(
    delivery_id integer primary key, ----Incrementativa
    orders_id integer not null,
    repartidor_id integer not null,
    delivery_receptor text not null,
    delivery_date date not null,
    constraint fk_delivery_orders FOREIGN KEY (orders_id) REFERENCES orders(orders_id),
    constraint fk_delivery_user FOREIGN KEY (repartidor_id) REFERENCES user(user_id)
);
