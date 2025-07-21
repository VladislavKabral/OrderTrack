CREATE TABLE Products (
    id              UUID                PRIMARY KEY,
    name            VARCHAR(50)         NOT NULL,
    description     VARCHAR(50)         NOT NULL,
    price           NUMERIC(10, 2)      NOT NULL
)