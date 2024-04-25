INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_ADMIN');

INSERT INTO users (id, username, email, password) VALUES (1, 'testUser123', 'email123@mailserver.com', '$2y$10$bXfx5lrkoBgn/d7JWMhoWuPW0RTshUxbYl8fJEAHMimTUVUYlwuIm');
INSERT INTO user_roles (role_id, user_id) VALUES (3, 1);
INSERT INTO book (id, title, author, genre) VALUES (1011, 'Becoming a Digital Library', 'Susan J. Barnes','Science' );
INSERT INTO book (id, title, author, genre) VALUES (2022, 'Global Librarianship', 'Martin A. Kesselman', 'Politics');
INSERT INTO book (id, title, author, genre) VALUES (3033, 'Dune', 'Frank Herbert', 'SciFi');
INSERT INTO book (id, title, author, genre) VALUES (4044, 'Get a Life, Chloe Brown', 'Talia Hibbert', 'Romance');
INSERT INTO book (id, title, author, genre) VALUES (5055, 'Home Owner Magazine', 'SA Home Owner Magazine', 'Magazine');
INSERT INTO book (id, title, author, genre) VALUES (6066, 'The Haunting of Hill House', 'Shirley Jackson', 'Horror');
INSERT INTO book (id, title, author, genre) VALUES (7077, 'Good Omens', 'John Kennedy Toole', 'Comedy');