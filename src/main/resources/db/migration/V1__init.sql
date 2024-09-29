
CREATE TABLE IF NOT EXISTS product (
                                       id BIGSERIAL PRIMARY KEY,
                                       title VARCHAR(255),
                                        price INT
    );

CREATE TABLE IF NOT EXISTS basket (
                                      id BIGSERIAL PRIMARY KEY,
                                      count_products INT,
                                      product_id BIGINT UNIQUE,
                                      FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
    );


INSERT INTO product (title, price)
VALUES
    ('Apple iPhone 13', 999),
    ('Samsung Galaxy S21', 799),
    ('Google Pixel 6', 599),
    ('OnePlus 9', 729),
    ('Sony WH-1000XM4', 349),
    ('Bose QuietComfort 35 II', 299),
    ('Apple MacBook Air', 999),
    ('Dell XPS 13', 949),
    ('HP Spectre x360', 1099),
    ('Lenovo ThinkPad X1', 1399),
    ('Sony A7 III Camera', 1999),
    ('Canon EOS R5', 3899),
    ('Nikon Z6', 1799),
    ('GoPro HERO9', 399),
    ('DJI Mavic Air 2', 799),
    ('Apple Watch Series 6', 399),
    ('Samsung Galaxy Watch 3', 399),
    ('Fitbit Versa 3', 229),
    ('Garmin Forerunner 245', 349),
    ('Microsoft Surface Pro 7', 749),
    ('Apple iPad Pro', 1099),
    ('Samsung Galaxy Tab S7', 649),
    ('Amazon Kindle Paperwhite', 139),
    ('Oculus Quest 2', 299),
    ('PlayStation 5', 499),
    ('Xbox Series X', 499),
    ('Nintendo Switch', 299),
    ('Razer Blade 15', 1999),
    ('Asus ROG Zephyrus G14', 1499),
    ('Acer Swift 3', 699),
    ('Apple AirPods Pro', 249),
    ('Sony WF-1000XM4', 279),
    ('Jabra Elite 75t', 199),
    ('Beats Solo Pro', 299),
    ('Marshall Major IV', 149),
    ('LG OLED55CXPUA', 1399),
    ('Samsung QN90A', 1499),
    ('Sony A80J OLED', 1799),
    ('Bose SoundLink Revolve+', 299),
    ('Sonos One (Gen 2)', 199),
    ('Amazon Echo (4th Gen)', 99),
    ('Google Nest Hub', 99),
    ('Apple HomePod mini', 99),
    ('Philips Hue White and Color Starter Kit', 199),
    ('Ring Video Doorbell Pro 2', 249),
    ('Nest Learning Thermostat', 249),
    ('Arlo Pro 4', 199),
    ('Wyze Cam v3', 36),
    ('Ecovacs Deebot T8', 499),
    ('iRobot Roomba 694', 299);



INSERT INTO basket(count_products, product_id)
VALUES (1, 5), (2, 3), (4, 2), (5, 1);