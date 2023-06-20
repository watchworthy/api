INSERT INTO genre (name) VALUES
('Action'),
('Comedy'),
('Drama'),
('Sci-Fi'),
('Thriller');


INSERT INTO movie_genre (movie_id, genre_id) VALUES
(1, 3),
(1, 5),
(2, 2),
(2, 4),
(3, 1),
(3, 4),
(4, 2),
(4, 4);


INSERT INTO tvshow_genre (tvshow_id, genre_id) VALUES
(1, 1),
(1, 3),
(2, 2),
(2, 4),
(3, 1),
(3, 3),
(4, 2),
(4, 4);
