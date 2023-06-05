-- Inserción en la tabla "account"
INSERT INTO account (account_id, account_card, account_available_money)
VALUES (1, '1234567890', 5000),
       (2, '9876543210', 10000),
       (3, '5678901234', 7500),
       (4, 'openmarket', 7500);

-- Inserción en la tabla "transactions"
INSERT INTO transactions (transactions_id, transactions_date, transactions_ammount, account_id_sender, account_id_receiver)
VALUES (1, '2023-05-27', 1000, 1, 2),
       (2, '2023-05-28', 2000, 1, 3),
       (3, '2023-05-28', 500, 2, 1),
       (4, '2023-05-27', 1500, 3, 2);

