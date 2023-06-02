--Tabla cuenta: Almacena la informacion sobre la cuenta y su dinero
create table account(
    account_id integer primary key,
    account_card text not null,
    account_available_money integer not null
);

--Tabla transacciones: Almacena la informacion de las transacciones
create table transactions(
    transactions_id integer primary key,
    transactions_date date not null,
    transactions_ammount integer not null,
    account_id_sender integer not null,
    account_id_receiver integer not null,
    constraint fk_transactions_account_sender foreign key (account_id_sender) references account(account_id),
    constraint fk_transactions_account_receiver foreign key (account_id_receiver) references account(account_id)
);