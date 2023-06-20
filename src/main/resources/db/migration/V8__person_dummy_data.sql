INSERT INTO person (name, gender, poster_path, biography) VALUES
('Bryan Cranston', 'Male', 'https://media.gettyimages.com/id/79179466/photo/the-55th-annual-primetime-emmy-awards-access-hollywood-red-carpet.jpg?s=612x612&w=gi&k=20&c=ykv4psjBv1GjqY1qq0L9yJEx0a2L45NdjY3f1zD1tpY=', 'Bryan Cranston is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Aaron Paul', 'Male', 'https://www.gettyimages.com/detail/news-photo/actor-aaron-paul-arrives-at-the-hellion-premiere-party-at-news-photo/463395839', 'Aaron Paul is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Anna Gunn', 'Female', 'https://www.gettyimages.com/detail/news-photo/actress-anna-gunn-arrives-at-the-20th-annual-screen-actors-news-photo/463807371', 'Anna Gunn is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('RJ Mitte', 'Male', 'https://www.gettyimages.com/detail/news-photo/actor-rj-mitte-poses-for-a-picture-at-the-parke-ronen-mens-news-photo/480987452', 'RJ Mitte is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Vince Gilligan', 'Male', 'https://www.gettyimages.com/detail/news-photo/writer-and-producer-vince-gilligan-is-photographed-for-los-news-photo/453605110', 'Vince Gilligan is an American writer, producer, and director.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Masi Oka', 'Male', 'https://www.gettyimages.com/detail/news-photo/writer-and-producer-vince-gilligan-is-photographed-for-los-news-photo/453605110', 'Masi Oka is a Japanese-American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Bob Odenkirk', 'Male', 'https://www.gettyimages.com/detail/news-photo/bob-odenkirk-attends-the-2020-vanity-fair-oscar-party-news-photo/1205182893', 'Bob Odenkirk is an American actor and comedian.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Giancarlo Esposito', 'Male', 'https://www.gettyimages.com/detail/news-photo/giancarlo-esposito-news-photo/1466243335', 'Giancarlo Esposito is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Krysten Ritter', 'Female', 'https://www.gettyimages.com/detail/news-photo/actress-krysten-ritter-arrives-at-the-los-angeles-premiere-news-photo/91275423', 'Krysten Ritter is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Charlie Cox', 'Male', 'https://www.gettyimages.com/detail/news-photo/charlie-cox-attends-abc-and-marvel-honor-stan-lee-at-new-news-photo/1179689197', 'Charlie Cox is an English actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Vincent D''Onofrio', 'Male', 'https://www.gettyimages.com/detail/news-photo/vincent-donofrio-attends-the-magnificent-seven-premiere-at-news-photo/608565988', 'Vincent D''Onofrio is an American actor and producer.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Finn Jones', 'Male', 'https://www.gettyimages.com/detail/news-photo/netflix-presents-marvels-iron-fist-at-new-york-comic-con-news-photo/613396396', 'Finn Jones is an English actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Mike Colter', 'Male', 'https://www.gettyimages.com/detail/news-photo/mike-colter-attends-the-2019-cbs-upfront-at-the-plaza-on-news-photo/1149387812', 'Mike Colter is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Rosario Dawson', 'Female', 'https://www.gettyimages.com/detail/news-photo/rosario-dawson-attends-the-28th-screen-actors-guild-awards-news-photo/1373200076', 'Rosario Dawson is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Scott Glenn', 'Male', 'https://www.gettyimages.com/detail/news-photo/scott-glenn-discusses-the-leftovers-and-marvels-the-news-photo/687862510', 'Scott Glenn is an American actor.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Sigourney Weaver', 'Female', 'https://www.gettyimages.com/detail/news-photo/sigourney-weaver-attends-the-glamour-women-of-the-year-news-photo/538692422', 'Sigourney Weaver is an American actress.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Anthony Hopkins', 'Male', 'https://www.gettyimages.com/detail/news-photo/sir-anthony-hopkins-attends-the-us-premiere-of-transformers-news-photo/699006524', 'Silence of the Lambs is the goat movie.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Jessica Jones', 'Female', 'https://www.gettyimages.com/detail/news-photo/actress-krysten-ritter-attends-the-netflixfysee-event-for-news-photo/960262428', 'Jessica Jones is a fictional superhero.');

INSERT INTO person (name, gender, poster_path, biography) VALUES
('Luke Cage', 'Male', 'https://www.gettyimages.com/detail/news-photo/mustafa-shakir-attends-the-luke-cage-season-2-premiere-at-news-photo/980883478', 'Luke Cage is a fictional superhero.');

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
