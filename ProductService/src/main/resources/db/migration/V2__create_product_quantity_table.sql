CREATE TABLE Product_Quantity (
    id              UUID        PRIMARY KEY,
    product_id      UUID        NOT NULL REFERENCES Products(id),
    quantity        INTEGER     NOT NULL
)