
-- Kiểm tra nếu bảng category rỗng thì mới insert
INSERT INTO project.category (name)
SELECT 'Mac' FROM DUAL
WHERE NOT EXISTS (
        SELECT * FROM project.category WHERE name = 'Mac'
    )
UNION ALL
SELECT 'Ipad' FROM DUAL
WHERE NOT EXISTS (
        SELECT * FROM project.category WHERE name = 'Ipad'
    )
UNION ALL
SELECT 'Iphone' FROM DUAL
WHERE NOT EXISTS (
        SELECT * FROM project.category WHERE name = 'Iphone'
    )
UNION ALL
SELECT 'Laptop' FROM DUAL
WHERE NOT EXISTS (
        SELECT * FROM project.category WHERE name = 'Laptop'
    )
UNION ALL
SELECT 'TV' FROM DUAL
WHERE NOT EXISTS (
        SELECT * FROM project.category WHERE name = 'TV'
    )
UNION ALL
SELECT 'PlayStation' FROM DUAL
WHERE NOT EXISTS (
        SELECT * FROM project.category WHERE name = 'PlayStation'
    );

-- Kiểm tra nếu đã tồn tại trong bảng product thì không insert
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Our thinnest, lightest notebook, completely transformed by the Apple M1 chip. CPU speeds up to 3.5x faster. GPU speeds up to 5x faster. ', 'macbook-air-space-gray.jpg', 'MacBook Air', '999', '1290','No Branch','Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Liquid Retina display', 'ipad-pro-12-select-202003.png', 'IPad Pro 12.9 inch', '535', '641','No Branch','Red');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('', 'iphone-12-mini-select-2020.jpg', 'IPhone 12 mini', '699', '133','No Branch','Yellow');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Iphone 11', 'iphone11.png', 'IPhone 11 64Gb', '599', '194','No Branch','BLue');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('iPhone SE packs our powerful A13 Bionic chip into our most popular size at our most affordable price. It’s just what you’ve been waiting for.', 'iphone-se-family-select-2020.jpg', 'IPhone SE 64Gb', '399', '148','No Branch','Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('this is macbook', 'mbp16touch-space-select-201911.jpg', 'MacBook Pro 16"', '2399', '2000','No Branch','white');

INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Experience the world in your hands with our flagship Android phone. With its incredible camera, stunning design, and lightning-fast performance, you wont find a better phone.', 'galaxy-s21-ultra-5g.png', 'Samsung Galaxy S21 Ultra', '1199', '227','Samsung','Phantom Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Looking for an affordable, high-performance laptop? Look no further than the Dell XPS 13. With its powerful Intel Core processor and stunning InfinityEdge display, you`ll get everything you need and more.', 'dell-xps-13-2020.jpg', 'Dell XPS 13', '899', '1200','Dell','Silver');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Experience the ultimate in gaming performance with the MSI GE75 Raider. Featuring a powerful NVIDIA graphics card and lightning-fast processor, you`ll dominate the competition in any game.', 'msi-ge75-raider-10sf-441us-hero.jpg', 'MSI GE75 Raider', '1599', '5100','MSI','Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('The ultimate wireless headphones. With noise-cancelling technology and incredible sound quality, you`ll never want to take these off.', 'airpods-max-select-2020.jpg', 'Apple AirPods Max', '549', '386','Apple','Silver');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Get the perfect shot every time with the Sony Alpha a7 III. With its advanced autofocus system and full-frame sensor, you`ll be able to capture stunning photos and videos.', 'sony-alpha-a7-iii.jpg', 'Sony Alpha a7 III', '1999', '650','Sony','Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Experience the power of a desktop in a sleek, portable package with the HP Spectre x360. With its 4K display and 10th-generation Intel Core processor, you`ll be able to do more than ever before.', 'hp-spectre-x360-13t-aw200-hero.jpg', 'HP Spectre x360', '1299', '1130','HP','Silver');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('The ultimate smartwatch. With its advanced health tracking features and always-on display, you`ll never miss a beat.', 'apple-watch-series-6-40mm-blue-aluminum-blue-sport-band.jpg', 'Apple Watch Series 6', '399', '47','Apple','Blue');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Experience the power of 5G with the Google Pixel 5. With its incredible camera and lightning-fast performance, you`ll be able to do more than ever before.', 'pixel-5-jet-black.png', 'Google Pixel 5', '699', '151','Google','Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('Experience lightning-fast loading with an ultra-high speed SSD, deeper immersion with support for haptic feedback, adaptive triggers and 3D Audio, and an all-new generation of incredible PlayStation games.', 'playstation5.jpg', 'PlayStation 5', '499', '4.5','Sony','White');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('The Xbox Series X is fast, powerful, and designed for speed and performance. With a custom-designed processor, it delivers high-quality graphics and stunning visuals at up to 120 frames per second. ', 'xbox-series-x.jpg', 'Xbox Series X', '499', '4.45','Microsoft','Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('The Samsung Galaxy S21 Ultra 5G is a premium smartphone with a 6.8-inch AMOLED display, Exynos 2100 chipset, 12GB of RAM, and 256GB of storage. It also has a quad-camera setup with a 108MP primary sensor, a 10MP periscope lens with 10x hybrid zoom, a 10MP telephoto lens with 3x zoom, and a 12MP ultra-wide lens.', 'samsung-galaxy-s21-ultra.jpg', 'Samsung Galaxy S21 Ultra', '1199', '227','Samsung','Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('The LG CX OLED TV is one of the best TVs on the market today. It has an amazing picture quality with perfect blacks and wide viewing angles, making it ideal for watching movies or playing games. It also has HDMI 2.1 ports that support 4K at 120Hz, VRR, and ALLM, making it a great choice for gamers. ', 'lg-cx-oled-tv.jpg', 'LG CX OLED TV', '1999', '25.1','LG','Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('The Bose QuietComfort 35 II is a wireless noise-canceling headphone that has excellent sound quality and impressive noise cancellation. It also has a comfortable and lightweight design, making it perfect for long listening sessions. It comes with a built-in Google Assistant and Alexa voice control for easy hands-free use. ', 'bose-quietcomfort-35-ii.jpg', 'Bose QuietComfort 35 II', '299', '0.68','Bose','Black');
INSERT INTO project.product (description, image_name, name, price, weight, brand, color) VALUES
('The GoPro HERO9 Black is a versatile action camera that can shoot stunning 5K videos and 20MP photos. It also has HyperSmooth 3.0 video stabilization and TimeWarp 3.0 for smooth and stable footage. It is waterproof up to 33ft (10m) and has a built-in mounting system for easy attachment to helmets, bikes, and more. ', 'gopro-hero9-black.jpg', 'GoPro HERO9 Black', '449', '0.15','GoPro','Black');


INSERT INTO project.product_category (product_id,category_id) VALUES ('1','1');
INSERT INTO project.product_category (product_id,category_id) VALUES ('6','1');
INSERT INTO project.product_category (product_id,category_id) VALUES ('2','2');
INSERT INTO project.product_category (product_id,category_id) VALUES ('3','3');
INSERT INTO project.product_category (product_id,category_id) VALUES ('4','3');
INSERT INTO project.product_category (product_id,category_id) VALUES ('5','3');
INSERT INTO project.product_category (product_id,category_id) VALUES ('8','4');
INSERT INTO project.product_category (product_id,category_id) VALUES ('9','4');
INSERT INTO project.product_category (product_id,category_id) VALUES ('12','4');
INSERT INTO project.product_category (product_id,category_id) VALUES ('16','4');
INSERT INTO project.product_category (product_id,category_id) VALUES ('18','5');
INSERT INTO project.product_category (product_id,category_id) VALUES ('19','5');
INSERT INTO project.product_category (product_id,category_id) VALUES ('20','5');
INSERT INTO project.product_category (product_id,category_id) VALUES ('15','6');
INSERT INTO project.product_category (product_id,category_id) VALUES ('7','3');
INSERT INTO project.product_category (product_id,category_id) VALUES ('14','3');
INSERT INTO project.product_category (product_id,category_id) VALUES ('10','1');
INSERT INTO project.product_category (product_id,category_id) VALUES ('13','1');
INSERT INTO project.product_category (product_id,category_id) VALUES ('11','2');
INSERT INTO project.product_category (product_id,category_id) VALUES ('17','2');

INSERT INTO `project`.`roles` (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO `project`.`roles` (`name`) VALUES ('ROLE_USER');

INSERT INTO `project`.`users` (`id`,`address`,`email`,`name`,`password`) VALUES ('1','20/7 viện lúa ĐBSCL, xã Tân Thạnh, Huyện Thới Lai,...','admin@gmail.com','phạm duy khoa','$2a$10$AWd9Mhu0CXArKnQs2dsGjOQ/Tm39uXZna7o4gIXMvbXUYlOwwlzOy');
INSERT INTO `project`.`users` (`id`,`address`,`email`,`name`,`password`,`phone_number`) VALUES ('2','20/7 viện lúa ĐBSCL, xã Tân Thạnh, Huyện Thới Lai, Thành Phố Cần Thơ','pham.duykhoa1303@gmail.com','phạm duy khoa','$2a$10$VD1wcwPLGeuB7Zg0Wco3x.XOxqLvih47toQyP33QnEetZMVRG/DXK','0562852109');


INSERT INTO `project`.`user_role`(`user_id`, `role_id`) VALUES ('1','1');
INSERT INTO `project`.`user_role`(`user_id`, `role_id`) VALUES ('2','2');

INSERT INTO `project`.`cart`( `cart_total`, `user_id`) VALUES ('1000','2');

INSERT INTO `project`.`cart_item`( `product_id`, `quantity`, `cart_id`) VALUES ('2','2','1');

-- tạo trigger mỗi khi update quantity trong cart_item thì cart_total được update đảm bảo tính nhất quán cho hệ thống
CREATE TRIGGER update_cart_total AFTER UPDATE ON cart_item FOR EACH ROW UPDATE cart SET cart_total =(SELECT SUM(cart_item.quantity * product.price) FROM cart_item INNER JOIN product ON cart_item.product_id = product.id WHERE cart_item.cart_id = cart.cart_id);
CREATE TRIGGER insert_cart_total AFTER INSERT ON cart_item FOR EACH ROW UPDATE cart SET cart_total =(SELECT SUM(cart_item.quantity * product.price) FROM cart_item INNER JOIN product ON cart_item.product_id = product.id WHERE cart_item.cart_id = cart.cart_id);
CREATE TRIGGER delete_cart_total AFTER DELETE ON cart_item FOR EACH ROW UPDATE cart SET cart_total =(SELECT SUM(cart_item.quantity * product.price) FROM cart_item INNER JOIN product ON cart_item.product_id = product.id WHERE cart_item.cart_id = cart.cart_id);
