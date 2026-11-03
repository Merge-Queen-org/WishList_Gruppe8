DROP DATABASE IF EXISTS wishlist_db;
CREATE DATABASE wishlist_db;
USE wishlist_db;
 
CREATE TABLE wishlist (
    wishlist_id INT PRIMARY KEY AUTO_INCREMENT,
    wishlist_name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(1000)
);
 
CREATE TABLE wish (
    wish_id INT PRIMARY KEY AUTO_INCREMENT,
    wishlist_id INT,
    wish_name VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    price DECIMAL(10,2),
    link_url VARCHAR(1000),
    FOREIGN KEY (wishlist_id) REFERENCES wishlist(wishlist_id)
);
 
SHOW TABLES;