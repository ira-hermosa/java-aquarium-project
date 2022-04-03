CREATE database test_aquarium;
USE test_aquarium;

CREATE TABLE fish(
id int AUTO_INCREMENT,
type VARCHAR(30) NOT NULL,
colour VARCHAR(30) NOT NULL,
length int NOT NULL,
cost float NOT NULL,
PRIMARY KEY(id)
);

DROP TABLE fish;