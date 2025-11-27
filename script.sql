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
	victims INT,
	severity VARCHAR(20) NOT NULL,
	status VARCHAR(20) NOT NULL,
	comments TEXT,
	creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	update_at TIMESTAMP,
	finished_at TIMESTAMP
);

CREATE TABLE users_emergencies(
	user_id INT REFERENCES users(id) NOT NULL,
	emergency_id INT REFERENCES emergencies(id) NOT NULL,
	PRIMARY KEY (user_id,emergency_id)	
);

-- TRIGGER PARA CAMBIAR LA DISPONIBILIDAD DE LOS USUARIOS CUANDO SE LES ASIGNA UNA EMERGENCIA
CREATE OR REPLACE FUNCTION set_user_unavailable()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE users
    SET availability = false
    WHERE id = NEW.user_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_set_user_unavailable
AFTER INSERT ON users_emergencies
FOR EACH ROW
EXECUTE FUNCTION set_user_unavailable();



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

