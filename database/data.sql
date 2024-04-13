USE `catering`;

SET FOREIGN_KEY_CHECKS = 0;
-- Insert data into the items table
INSERT INTO items (id, name, section_id, qnty, val, status, create_date) VALUES 
(1,'Margherita Pizza',1,NULL,3.99,'IN_STOCK','2024-03-21 20:20:31'),
(2,'BBQ Pizza',1,NULL,2.49,'IN_STOCK','2024-03-21 20:20:31'),
(3,'Chicken Quesadilla',2,NULL,6.99,'IN_STOCK','2024-03-21 20:20:31'),
(4,'Beef Tacos',2,NULL,8.49,'OUT_OF_STOCK','2024-03-21 20:20:31'),
(5,'Cheese & Onion Crisps',5,5,1.29,'IN_STOCK','2024-03-21 20:20:31'),
(6,'Fanta',5,12,0.99,'IN_STOCK','2024-03-21 20:20:31'),
(7,'Sour Patch Candy',5,10,0.79,'OUT_OF_STOCK','2024-03-21 20:20:31'),
(8,'Instant Ramen',5,8,0.89,'IN_STOCK','2024-03-21 20:20:31'),
(9,'Microwave Popcorn',5,6,1.49,'IN_STOCK','2024-03-21 20:20:31'),
(10,'Bottled Water',5,20,0.69,'IN_STOCK','2024-03-21 20:20:31');

-- Insert data into the queue table
INSERT INTO queue (id, section_id, user_id, val, ord_date) VALUES 
(1,2,3,42,'2024-04-13 15:18:36'),
(2,3,4,20,'2024-04-13 15:18:44'),
(5,1,4,NULL,'2024-04-13 15:19:25');

-- Insert data into the roles table
INSERT INTO roles (id, name, section_id) VALUES 
(1,'guest',NULL),
(2,'admin',NULL),
(3,'Pizza staff',1);

-- Insert data into the section table
INSERT INTO section (id, name, description, create_date) VALUES 
(1,'Pizza Shop','All types of pizzas, fast and piping hot. You can customize base, cheeses, and toppings','2024-03-21 17:26:49'),
(2,'Mexican Food Shop','Offers a variety of Mexican dishes, including vegetarian options.','2024-03-21 17:26:49'),
(3,'Coffee','Serves a range of coffee beverages and light snacks.','2024-03-21 17:26:49'),
(4,'Chicken Shop','Snack boxes, and fried chicken sandwiches to go','2024-03-21 17:26:49'),
(5,'Supermarket','Offers a wide selection of groceries, including fresh produce, dairy, and packaged goods.','2024-03-21 17:26:49');

-- Insert data into the stor_occup table
INSERT INTO stor_occup (id, num, time_stamp) VALUES 
(1,20,'2024-04-13 08:15:00'),
(2,15,'2024-04-13 11:30:00'),
(3,18,'2024-04-13 14:45:00'),
(4,22,'2024-04-14 09:00:00'),
(5,25,'2024-04-14 12:20:00');

-- Insert data into the users table
INSERT INTO users (id, fname, username, password, email, phone, role_id, create_date) VALUES 
(1,'Student 1','usernameone','$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.','student.one@mail.dcu.ie','353861234568',1,'2024-03-21 17:46:22'),
(2,'Lanny George','usernametwo','$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.','lann.ge@mail.dcu.ie','353881234570',2,'2024-03-21 17:46:22'),
(3,'Student 2','usernamethree','$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.','student.two@mail.dcu.ie','+353871234567',1,'2024-03-21 17:46:22'),
(4,'Jessica lamor','usernamefour','e8e65d12aa52d0d7b200eddaebaf0cf2223365d1a0c3c5f0768ec3b3fbec63d3','student.three@mail.dcu.ie','+353854354568',3,'2024-03-21 17:46:22');

-- Insert data into the voucher table
INSERT INTO voucher (id, name, description, val) VALUES 
(1,'20%OFF','Get 20% off on your next order when you spend $50 or more',50.00),
(2,'MEALDEAL','Buy a sandwich, chips, and a drink for a 15% discount',15.00),
(3,'First10','Students get 10% off on first time purchase with a valid student ID',10.00);

-- Insert data into the voucher_users table
INSERT INTO voucher_users (id, user_id, voucher_id, code, status, valid_until) VALUES 
(1,1,1,'ABCD01','READY','2024-06-30'),
(2,2,2,'EFGH02','READY','2024-07-15'),
(3,3,3,'IJKL03','EXPIRED','2023-12-31');
SET FOREIGN_KEY_CHECKS = 1;