CREATE TABLE IF NOT EXISTS Users (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(250) NOT NULL,
	power DOUBLE NOT NULL
	);
	
INSERT INTO Heroes (id, name, power) VALUES
	(1, 'Spiderman', '50'),
	(2, 'Scarlet Spider', '70'),
	(3, 'Venom', '70'),
	(4, 'Carnage', '80'),
	(5, 'Black Cat', '30');