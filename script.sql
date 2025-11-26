-- CREACIÓN DE TABLAS

CREATE TABLE roles(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE users(
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) UNIQUE,
	name VARCHAR(50),
	lastname VARCHAR(50),
	email VARCHAR(100) NOT NULL UNIQUE,
	phone INT UNIQUE,
	password TEXT,
	role_id INT REFERENCES roles(id) NOT NULL,
	availability BOOLEAN DEFAULT true,
	expired BOOLEAN DEFAULT true,
	enabled BOOLEAN DEFAULT true,
	blocked BOOLEAN DEFAULT true,
	creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE emergencies(
	id SERIAL PRIMARY KEY,
	description TEXT,
	victims INT
);

CREATE TABLE users_emergencies(
	user_id INT REFERENCES users(id),
	emergency_id INT REFERENCES emergencies(id),
	severity VARCHAR(20) NOT NULL,
	status VARCHAR(20) NOT NULL,
	comments TEXT,
	creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_at TIMESTAMP,
	finished_at TIMESTAMP
);


-- INSERCIÓN DE DATOS

INSERT INTO roles (name) VALUES('ROLE_ADMIN');
INSERT INTO roles (name) VALUES('ROLE_MEDIC');
INSERT INTO users (email,role_id) VALUES 
('juan.perez@gmail.com',2),
('maria.lopez@yahoo.com',2),
('carlos.gomez@hotmail.com',2),
('sofia.martinez@outlook.com',2),
('lucas.fernandez@gmail.com',2),
('valentina.rodriguez@yahoo.com',2),
('nicolas.sanchez@gmail.com',2),
('camila.torres@hotmail.com',2),
('diego.ramirez@gmail.com',2),
('paula.mendoza@outlook.com',2);

