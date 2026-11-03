DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS wishlist;

CREATE TABLE wishlist (
        wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
        wishlist_name VARCHAR(100) NOT NULL UNIQUE,
        description VARCHAR(1000)
);
INSERT INTO wishlist (wishlist_name, description) VALUES ('Birthday', 'Birthday wishes');

CREATE TABLE wish
(
    wish_id     INT PRIMARY KEY AUTO_INCREMENT,
    wishlist_id INT,
    wish_name   VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    price       DECIMAL(10, 2),
    link_url    VARCHAR(1000),
    FOREIGN KEY (wishlist_id) REFERENCES wishlist (wishlist_id)
);

INSERT INTO wish (wishlist_id, wish_name, description, price, link_url) VALUES
        (1, 'Bday', 'First wish', 100.00, 'https://www.chanel.com/dk/ure/p/H10132/j12-ur-28-mm/'),
        (1, 'Wedding', 'Second wish', 200.00, 'https://www.chanel.com/dk/briller/p/A71839X06081S850300POCCI/ovale-solbriller-sort/'),
        (1, 'Christmas', 'Third wish', 300.00, 'https://www2.hm.com/da_dk/productpage.1366553002.html');
