DROP TABLE order_product IF EXISTS;
DROP TABLE orders IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE recipes IF EXISTS;

CREATE TABLE users (
  	email VARCHAR(50) PRIMARY KEY,
  	name VARCHAR(50),
  	password VARCHAR(20)
);

CREATE TABLE products (
  	id VARCHAR(100) PRIMARY KEY,
  	product_name VARCHAR(50) NOT NULL,
  	price DOUBLE(10,2) NOT NULL,
	description VARCHAR(200) NOT NULL,
  	image VARCHAR(200) NOT NULL
);

CREATE TABLE recipes (
  	id VARCHAR(100) PRIMARY KEY,
  	name VARCHAR(50) NOT NULL,
  	description DOUBLE(10,2) NOT NULL,
	product_id VARCHAR(100),
	foreign key (product_id) references products(id)
);

CREATE TABLE orders (
	id VARCHAR(100) PRIMARY KEY,
	email VARCHAR(50) NOT NULL,
	address VARCHAR(80),
	totalamount DOUBLE(10,2),
	foreign key (email) references users(email)
);

CREATE TABLE order_product (
	order_id VARCHAR(100),
	product_id VARCHAR(100),
	quantity INTEGER,
	PRIMARY KEY(id, product_id),
	foreign key (order_id) references orders(id),
	foreign key (product_id) references products(id)
);
