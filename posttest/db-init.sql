-- Drop tables if they exist
DROP TABLE IF EXISTS lottery CASCADE;
DROP TABLE IF EXISTS user_ticket CASCADE;

CREATE TABLE lottery
(
    id              SERIAL PRIMARY KEY,
    ticket_number   VARCHAR(6) NOT NULL,
    price           INTEGER NOT NULL,
    amount          INTEGER NOT NULL

);

CREATE TABLE user_ticket
(
    id              SERIAL PRIMARY KEY,
    user_id          VARCHAR(10) NOT NULL,
    ticket_id        INTEGER NOT NULL,
    ticket_number    VARCHAR(6) NOT NULL,
    price           INTEGER NOT NULL,
    amount          INTEGER NOT NULL

);

INSERT INTO lottery(ticket_number, price, amount)
VALUES ('000001', 80, 1);

INSERT INTO lottery(ticket_number, price, amount)
VALUES ('000002', 80, 1);

INSERT INTO lottery(ticket_number, price, amount)
VALUES ('123456', 80, 1);