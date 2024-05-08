INSERT INTO users (first_name, last_name, email, password) VALUES ('John', 'Doe', 'johndoe@email.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO users (first_name, last_name, email, password) VALUES ('Jessie', 'Lane', 'jessielane@email.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO roles (authority) VALUES ('OPERATOR');
INSERT INTO roles (authority) VALUES ('ADMIN');

INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);
INSERT INTO roles_users (role_id, user_id) VALUES (1, 2);
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);

INSERT INTO categories (name) VALUES ('Pizza'), ('Risoto'), ('Macarrão');

INSERT INTO products (name, price, img_url, description) VALUES ('Pizza Bacon', 49.9, 'https://raw.githubusercontent.com/devsuperior/sds2/master/assets/pizza_bacon.jpg', 'Pizza de bacon com mussarela, orégano, molho especial e tempero da casa.');
INSERT INTO products (name, price, img_url, description) VALUES ('Pizza Moda da Casa', 59.9, 'https://raw.githubusercontent.com/devsuperior/sds2/master/assets/pizza_moda.jpg', 'Pizza à moda da casa, com molho especial e todos ingredientes básicos, e queijo à sua escolha.');
INSERT INTO products (name, price, img_url, description) VALUES ('Pizza Portuguesa', 45.0, 'https://raw.githubusercontent.com/devsuperior/sds2/master/assets/pizza_portuguesa.jpg', 'Pizza Portuguesa com molho especial, mussarela, presunto, ovos e especiarias.');
INSERT INTO products (name, price, img_url, description) VALUES ('Risoto de Carne', 52.0, 'https://raw.githubusercontent.com/devsuperior/sds2/master/assets/risoto_carne.jpg', 'Risoto de carne com especiarias e um delicioso molho de acompanhamento.');
INSERT INTO products (name, price, img_url, description) VALUES ('Risoto Funghi', 59.95, 'https://raw.githubusercontent.com/devsuperior/sds2/master/assets/risoto_funghi.jpg', 'Risoto Funghi feito com ingredientes finos e o toque especial do chef.');
INSERT INTO products (name, price, img_url, description) VALUES ('Macarrão Espaguete', 35.9, 'https://raw.githubusercontent.com/devsuperior/sds2/master/assets/macarrao_espaguete.jpg', 'Macarrão fresco espaguete com molho especial e tempero da casa.');
INSERT INTO products (name, price, img_url, description) VALUES ('Macarrão Fusili', 38.0, 'https://raw.githubusercontent.com/devsuperior/sds2/master/assets/macarrao_fusili.jpg', 'Macarrão fusili com toque do chef e especiarias.');
INSERT INTO products (name, price, img_url, description) VALUES ('Macarrão Penne', 37.9, 'https://raw.githubusercontent.com/devsuperior/sds2/master/assets/macarrao_penne.jpg', 'Macarrão penne fresco ao dente com tempero especial.');

INSERT INTO categories_products (category_id, product_id) VALUES (1, 1);
INSERT INTO categories_products (category_id, product_id) VALUES (1, 2);
INSERT INTO categories_products (category_id, product_id) VALUES (1, 3);
INSERT INTO categories_products (category_id, product_id) VALUES (2, 4);
INSERT INTO categories_products (category_id, product_id) VALUES (2, 5);
INSERT INTO categories_products (category_id, product_id) VALUES (2, 6);
INSERT INTO categories_products (category_id, product_id) VALUES (3, 7);
INSERT INTO categories_products (category_id, product_id) VALUES (3, 8);
