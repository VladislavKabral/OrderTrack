CREATE TABLE Customers (
    id          UUID            PRIMARY KEY,
    lastname    VARCHAR(50)     NOT NULL,
    firstname   VARCHAR(50)     NOT NULL,
    email       VARCHAR(50)     UNIQUE NOT NULL,
    password    VARCHAR(250)    NOT NULL
)