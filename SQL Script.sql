CREATE database aquarium;
USE aquarium;

DROP TABLE fish;

CREATE TABLE fish(
id int AUTO_INCREMENT,
type VARCHAR(30) NOT NULL,
colour VARCHAR(30) NOT NULL,
length int NOT NULL,
cost float NOT NULL,
PRIMARY KEY(id)
);

Select * FROM fish;
Select * FROM fish where id = 2;
DELETE FROM fish where id>0;




