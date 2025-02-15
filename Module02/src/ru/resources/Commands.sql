CREATE TABLE IF NOT EXISTS study_geekbrains.CHAT_CLIENTS (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(15) NOT NULL,
    login VARCHAR(15) NOT NULL,
    password VARCHAR(15) NOT NULL);

INSERT INTO study_geekbrains.CHAT_CLIENTS  VALUES (DEFAULT, 'Barboss', 'l1', 'p1');
INSERT INTO study_geekbrains.CHAT_CLIENTS  VALUES (DEFAULT, 'Kelvin', 'l2', 'p2');
INSERT INTO study_geekbrains.CHAT_CLIENTS  VALUES (DEFAULT, 'Nicky', 'l3', 'p3');
INSERT INTO study_geekbrains.CHAT_CLIENTS  VALUES (DEFAULT, 'Klaus', 'l4', 'p4');

