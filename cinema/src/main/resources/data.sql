-- Rooms
INSERT IGNORE INTO rooms (name, max_guests, equipment) VALUES ('Salong 1', 100, '4K projektor, Dolby Atmos');
INSERT IGNORE INTO rooms (name, max_guests, equipment) VALUES ('Salong 2', 50, 'HD projektor, stereo');
INSERT IGNORE INTO rooms (name, max_guests, equipment) VALUES ('Salong 3', 30, '4K projektor, 3D');

-- Movies
INSERT IGNORE INTO movies (title, genre, age_limit, duration_minutes) VALUES ('Inception', 'Sci-Fi', 15, 148);
INSERT IGNORE INTO movies (title, genre, age_limit, duration_minutes) VALUES ('The Dark Knight', 'Action', 15, 152);
INSERT IGNORE INTO movies (title, genre, age_limit, duration_minutes) VALUES ('Interstellar', 'Sci-Fi', 11, 169);
INSERT IGNORE INTO movies (title, genre, age_limit, duration_minutes) VALUES ('Parasite', 'Thriller', 15, 132);
INSERT IGNORE INTO movies (title, genre, age_limit, duration_minutes) VALUES ('The Lion King', 'Animation', 0, 88);

-- Users
INSERT IGNORE INTO users (username, password, role) VALUES ('admin', '$2a$10$8p1NvMF7tg.AuAKyJTtwaOuPkyEFMR1k.yKjiWcvgyTdxKQDp/SeS', 'ROLE_ADMIN');
INSERT IGNORE INTO users (username, password, role) VALUES ('user', '$2a$10$8p1NvMF7tg.AuAKyJTtwaOuPkyEFMR1k.yKjiWcvgyTdxKQDp/SeS', 'ROLE_USER');

-- Customers
INSERT IGNORE INTO customers (username, name) VALUES ('johndoe', 'John Doe');
INSERT IGNORE INTO customers (username, name) VALUES ('janedoe', 'Jane Doe');
INSERT IGNORE INTO customers (username, name) VALUES ('bobsmith', 'Bob Smith');
INSERT IGNORE INTO customers (username, name) VALUES ('alicejones', 'Alice Jones');
INSERT IGNORE INTO customers (username, name) VALUES ('charliebrown', 'Charlie Brown');

-- Addresses
INSERT IGNORE INTO addresses (street, city, postal_code, customer_id) VALUES ('Storgatan 1', 'Stockholm', '11122', 1);
INSERT IGNORE INTO addresses (street, city, postal_code, customer_id) VALUES ('Lillgatan 2', 'Göteborg', '41112', 2);
INSERT IGNORE INTO addresses (street, city, postal_code, customer_id) VALUES ('Kungsgatan 3', 'Malmö', '21113', 3);

-- Events
INSERT IGNORE INTO events (movie_id, room_id, date_time) VALUES (1, 1, '2025-04-10 19:00:00');
INSERT IGNORE INTO events (movie_id, room_id, date_time) VALUES (2, 2, '2025-04-11 20:00:00');
INSERT IGNORE INTO events (movie_id, room_id, date_time) VALUES (3, 3, '2025-04-12 18:00:00');

-- Bookings
INSERT IGNORE INTO bookings (customer_id, event_id, number_of_guests, total_price_sek, total_price_usd, date, equipment) VALUES (1, 1, 10, 1500.0, 142.5, '2025-04-10', '4K projektor');
INSERT IGNORE INTO bookings (customer_id, event_id, number_of_guests, total_price_sek, total_price_usd, date, equipment) VALUES (2, 2, 5, 750.0, 71.25, '2025-04-11', 'HD projektor');