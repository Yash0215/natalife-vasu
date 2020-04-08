DROP TABLE order_product IF EXISTS;
DROP TABLE orders IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE recipes IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE blogs IF EXISTS;

CREATE TABLE users (
  	email VARCHAR(50) PRIMARY KEY,
  	fname VARCHAR(50),
	lname VARCHAR(50),
  	password VARCHAR(20),
	isadmin BOOLEAN DEFAULT 0
);

CREATE TABLE blogs (
	id bigint PRIMARY KEY auto_increment,
  	title VARCHAR(50),
  	content VARCHAR(1000),
	username VARCHAR(50),
	email VARCHAR(50)
);

CREATE TABLE products (
	id bigint PRIMARY KEY auto_increment,
  	name VARCHAR(50) NOT NULL,
  	price DECIMAL(10,2) NOT NULL,
	description VARCHAR(300) NOT NULL,
  	image VARCHAR(200) NOT NULL
);

CREATE TABLE recipes (
	id bigint PRIMARY KEY auto_increment,
  	name VARCHAR(50) NOT NULL,
  	description VARCHAR(1000) NOT NULL,
	product_id VARCHAR(100),
	foreign key (product_id) references products(id)
);

CREATE TABLE orders (
	id bigint PRIMARY KEY auto_increment,
	email VARCHAR(50) NOT NULL,
	address VARCHAR(80),
	mobile VARCHAR(50),
	totalamount DECIMAL(10,2),
	foreign key (email) references users(email)
);

CREATE TABLE order_product (
	order_id bigint NOT NULL,
	product_id bigint NOT NULL,
	quantity INTEGER NOT NULL,
	PRIMARY KEY(order_id, product_id),
	foreign key (order_id) references orders(id),
	foreign key (product_id) references products(id)
);
