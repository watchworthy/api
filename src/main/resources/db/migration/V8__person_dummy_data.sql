INSERT INTO person (name, gender, poster_path, biography) VALUES
('Bryan Cranston', 'Male', 'https://media.gettyimages.com/id/79179466/photo/the-55th-annual-primetime-emmy-awards-access-hollywood-red-carpet.jpg?s=612x612&w=gi&k=20&c=ykv4psjBv1GjqY1qq0L9yJEx0a2L45NdjY3f1zD1tpY=', 'Bryan Cranston is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Aaron Paul', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kOJelnLSb89SeivbOCt1l94Hz2d.jpg', 'Aaron Paul is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Anna Gunn', 'Female', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/usWnHCzbADijULREZYSJ0qfM00y.jpg', 'Anna Gunn is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('RJ Mitte', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xdnstENLdWMPWt9qyhtf695L4t6.jpg', 'RJ Mitte is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Vince Gilligan', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8eTtJ7XVXY0BnEeUaSiTAraTIXd.jpg', 'Vince Gilligan is an American writer, producer, and director.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Masi Oka', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/snk6JiXOOoRjPtHU5VMoy6qbd32.jpg', 'Masi Oka is a Japanese-American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Bob Odenkirk', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wVoSUexYH79igPgxIXKWRlV2uBk.jpg', 'Bob Odenkirk is an American actor and comedian.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Giancarlo Esposito', 'Female', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qUUfTmpZPhy4zIs8oVk7OINLQqu.jpg', 'Giancarlo Esposito is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Krysten Ritter', 'Female', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Aba6EJ1NxhDTsJyO5F0oigTjeuD.jpg', 'Krysten Ritter is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Charlie Cox', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/kTjiABk3TJ3yI0Cto5RsvyT6V3o.jpg', 'Charlie Cox is an English actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Vincent D''Onofrio', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1V4DbU1mfEim4oaA5bvJywDAKwB.jpg', 'Vincent D''Onofrio is an American actor and producer.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Finn Jones', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rjX2Oz3tCZMfSwOoIAyEhdtXnTE.jpg', 'Finn Jones is an English actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Mike Colter', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/iPg0J9UzAlPj1fLEJNllpW9IhGe.jpg', 'Mike Colter is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Rosario Dawson', 'Female', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9blYMaj79VGC6BHTLmJp3V5S8r3.jpg', 'Rosario Dawson is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Scott Glenn', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1K2IvGXFbKsgkExuUsRvy4F0c9e.jpg', 'Scott Glenn is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Sigourney Weaver', 'Female', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xxYawgFO1woBRveH7WL9D1BxB4W.jpg', 'Sigourney Weaver is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Anthony Hopkins', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/ifI2QhpUlAwiWNwdDsFMRlPovsk.jpg', 'Silence of the Lambs is the goat movie.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Jessica Jones', 'Female', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hSXhmslCL6KRfGCj1T0W7LH0DHS.jpg', 'Jessica Jones is a fictional superhero.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Luke Cage', 'Male', 'https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lR6rZnfRUtHHWWISKKdVeXVcVy5.jpg', 'Luke Cage is a fictional superhero.');

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
