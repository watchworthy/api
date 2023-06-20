INSERT INTO person (name, gender, poster_path, biography) VALUES
('Bryan Cranston', 'Male', 'https://media.gettyimages.com/id/79179466/photo/the-55th-annual-primetime-emmy-awards-access-hollywood-red-carpet.jpg?s=612x612&w=gi&k=20&c=ykv4psjBv1GjqY1qq0L9yJEx0a2L45NdjY3f1zD1tpY=', 'Bryan Cranston is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Aaron Paul', 'Male', 'https://image.tmdb.org/t/p/original//9zsvVAkFM7LQyzi6qvku6Kq6nC.jpg', 'Aaron Paul is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Anna Gunn', 'Female', 'https://image.tmdb.org/t/p/original//c3aylrRHxSljyjrVoygkUz5H7c7.jpg', 'Anna Gunn is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('RJ Mitte', 'Male', 'https://image.tmdb.org/t/p/original//jVXAMWbPZkOG7gPsQ9RYLv3EkDb.jpg', 'RJ Mitte is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Vince Gilligan', 'Male', 'https://image.tmdb.org/t/p/original//3Dpvee0zZ5leAvuK0YbJLQCMc9r.jpg', 'Vince Gilligan is an American writer, producer, and director.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Masi Oka', 'Male', 'https://image.tmdb.org/t/p/original//wZZ7vR2kE0p2lnZNysrpxQ29jJY.jpg', 'Masi Oka is a Japanese-American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Bob Odenkirk', 'Male', 'https://image.tmdb.org/t/p/original//o0DpCZy2msd6YxMfcX8rqJm2zdW.jpg', 'Bob Odenkirk is an American actor and comedian.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Giancarlo Esposito', 'Male', 'https://image.tmdb.org/t/p/original//cUjxikP8GLeuWjcSoprdvEbU2w3.jpg', 'Giancarlo Esposito is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Krysten Ritter', 'Female', 'https://image.tmdb.org/t/p/original//47qZHaP1aXlJDdJxHhqXLuoCefz.jpg', 'Krysten Ritter is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Charlie Cox', 'Male', 'https://image.tmdb.org/t/p/original//c0eMHuPdVd1HbS5iFdQixGBYwN4.jpg', 'Charlie Cox is an English actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Vincent D''Onofrio', 'Male', 'https://image.tmdb.org/t/p/original//sFs5bz35a3QxwP8KxHVFr31QOFr.jpg', 'Vincent D''Onofrio is an American actor and producer.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Finn Jones', 'Male', 'https://image.tmdb.org/t/p/original//y6zZnPX5XhOXTw8qLjGKys7s1BN.jpg', 'Finn Jones is an English actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Mike Colter', 'Male', 'https://image.tmdb.org/t/p/original//7RjozB4V7oPJqpGxOOY6FmljgEn.jpg', 'Mike Colter is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Rosario Dawson', 'Female', 'https://image.tmdb.org/t/p/original//k0mtW7okNorfhCG6Cxg8Pr5viST.jpg', 'Rosario Dawson is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Scott Glenn', 'Male', 'https://image.tmdb.org/t/p/original//6SYHpQpq6hKPuip91Y2FzWpM0j2.jpg', 'Scott Glenn is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Sigourney Weaver', 'Female', 'https://image.tmdb.org/t/p/original//pNFe5Yz02nkHDsJIJdtSn0x3a0d.jpg', 'Sigourney Weaver is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Daredevil', 'Male', 'https://image.tmdb.org/t/p/original//s0IWv1DZBwv4RVy1RYTkTkN6eyM.jpg', 'Daredevil is a fictional superhero.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Jessica Jones', 'Female', 'https://image.tmdb.org/t/p/original//g53eUGpeENl0Ri1XQZOYwzLyRFp.jpg', 'Jessica Jones is a fictional superhero.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Luke Cage', 'Male', 'https://image.tmdb.org/t/p/original//5h5pOcUhLFSk5axbt6MXDCYgo5o.jpg', 'Luke Cage is a fictional superhero.');

-- movie_person table
INSERT INTO movie_person (movie_id, person_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 6),
(3, 7),
(3, 8);

-- tvshow_person table
INSERT INTO tvshow_person (tvshow_id, person_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 6),
(3, 7),
(3, 8),
(4, 9),
(4, 10),
(4, 11),
(4, 12),
(4, 13),
(4, 14),
(4, 15),
(4, 16);
