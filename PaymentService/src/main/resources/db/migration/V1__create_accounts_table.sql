CREATE TABLE Accounts (
    id              UUID                PRIMARY KEY,
    customer_id     UUID                NOT NULL,
    balance         NUMERIC(10, 2)      NOT NULL,
    status          VARCHAR(20)         CHECK (status IN ('ACTIVE', 'ARCHIVED', 'BLOCKED'))
)