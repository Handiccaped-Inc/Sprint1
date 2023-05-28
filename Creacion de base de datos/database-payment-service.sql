create table account(
    account_id integer not null,
    account_card text not null,
    account_available_money integer not null,
    constraint pk_account primary key (account_id)
);

create table transactions(
    transactions_id integer not null,
    transactions_date date not null,
    transactions_ammount integer not null,
    account_id_sender integer not null,
    account_id_receiver integer not null,
    constraint pk_transactions primary key (transactions_id),
    constraint fk_transactions_account_sender foreign key (account_id_sender) references account(account_id),
    constraint fk_transactions_account_receiver foreign key (account_id_receiver) references account(account_id)
);