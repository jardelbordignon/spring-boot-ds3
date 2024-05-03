CREATE TABLE categories_products (
    category_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    CONSTRAINT unique_category_product UNIQUE (category_id, product_id)
);