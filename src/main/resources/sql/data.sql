USE wishlist_db;
 
-- Insert 3 wishlists

 INSERT INTO wishlist (wishlist_name, description) VALUES

 ('Birthday Wishes', 'Things I would love for my birthday'),

 ('Christmas Wishlist', 'Gift ideas for Christmas'),

 ('Travel Wishlist', 'Things I need for my next adventure');
 
-- Insert 9 wishes

 INSERT INTO wish

 (wishlist_id, wish_name, description, price, link_url)

 VALUES

 -- Birthday Wishes (wishlist_id = 1)

 (1, 'Wireless Headphones', 'Noise-cancelling headphones', 899.00, 'https://example.com/headphones'),

 (1, 'Running Shoes', 'Comfortable shoes for running', 750.00, 'https://example.com/running-shoes'),

 (1, 'Coffee Machine', 'A small coffee machine for home', 1299.00, 'https://example.com/coffee-machine'),
 
-- Christmas Wishlist (wishlist_id = 2)

 (2, 'Wool Sweater', 'A warm sweater for winter', 599.00, 'https://example.com/sweater'),

 (2, 'Kindle', 'An e-reader for reading books', 1099.00, 'https://example.com/kindle'),

 (2, 'Board Game', 'A fun game to play with friends', 299.00, 'https://example.com/board-game'),
 
-- Travel Wishlist (wishlist_id = 3)

 (3, 'Hiking Backpack', 'A lightweight hiking backpack', 850.00, 'https://example.com/backpack'),

 (3, 'Water Bottle', 'A reusable stainless steel bottle', 199.00, 'https://example.com/water-bottle'),

 (3, 'Travel Adapter', 'A universal international adapter', 249.00, 'https://example.com/travel-adapter');
 
-- Check the data

 SELECT * FROM wishlist;

 SELECT * FROM wish;