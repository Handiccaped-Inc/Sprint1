/**
* Autores:
*       Santiago Agredo Vallejo 
*       Pablo Jose Restrepo Ruiz
*       Jojan Esteban Serna Serna
*       Arturo Restrepo Ruiz
*/

create table user(
    user_id integer primary key;
    user_birth_date date not null;
    user_email text not null;
    user_phone integer not null
    
)

create table rol(
    rol_id integer primary key;
    rol_name text;
)

create table product(
    product_id integer primary key,
    product_name text,
    product_description text,
    product_price integer,
    product_stock integer,
    product_latitude integer,
    product_length integer,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE order (
  id INTEGER PRIMARY KEY,
  deliver_date TEXT,
  recipient_name TEXT,
  status TEXT,
  price REAL,
  user_id INTEGER,
  product_id INTEGER,
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);


