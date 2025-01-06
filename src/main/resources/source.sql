
-- Volcando estructura de base de datos para agenda
DROP DATABASE IF EXISTS `agenda`;
CREATE DATABASE IF NOT EXISTS `agenda`;
USE `agenda`;

DROP TABLE IF EXISTS tipo_membresia;
CREATE TABLE IF NOT EXISTS tipo_membresia (
    id_tipo_membresia INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(50) NOT NULL UNIQUE,
    tarifa DECIMAL(10,2) NOT NULL CHECK (tarifa >= 0),
    duracion_dias INT NOT NULL CHECK (duracion_dias > 0)
);

CREATE TABLE IF NOT EXISTS miembro (
    id_miembro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    correo_electronico VARCHAR(100) NOT NULL UNIQUE,
    fecha_nacimiento DATE NOT NULL,
    genero VARCHAR(1) NOT NULL CHECK (genero IN ('M', 'F', 'O')),
    fecha_inscripcion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_tipo_membresia INT NOT NULL,
    CONSTRAINT uq_miembro UNIQUE (nombre, apellidos, fecha_nacimiento, genero),
    CONSTRAINT fk_miembro_tipo_membresia
    FOREIGN KEY (id_tipo_membresia) REFERENCES tipo_membresia (id_tipo_membresia)
);

-- Volcando datos para la tabla tipo_membresia
INSERT INTO tipo_membresia (nombre_tipo, tarifa, duracion_dias) VALUES
    ('Basica', 300.00, 30),
    ('Estandar', 500.00, 30),
    ('Premium', 700.00, 30),
    ('Anual_Basica', 3200.00, 365),
    ('Anual_Premium', 6000.00, 365),
    ('VIP', 1200.00, 30),
    ('Familiar', 900.00, 30),
    ('Estudiantil', 250.00, 30),
    ('Oro', 1000.00, 60),
    ('Platino', 2000.00, 90);

-- Volcado de datos para la tabla miembro
INSERT INTO miembro
(nombre, apellidos, direccion, telefono, correo_electronico, fecha_nacimiento, genero, id_tipo_membresia)
VALUES
    ('Juan', 'Perez', 'Calle 1, Ciudad', '5551234567', 'juan.perez@example.com', '1990-01-01', 'M', 1),
    ('Maria', 'Lopez', 'Calle 2, Ciudad', '5551234568', 'maria.lopez@example.com', '1992-05-10', 'F', 2),
    ('Carlos', 'Gomez', 'Calle 3, Ciudad', '5551234569', 'carlos.gomez@example.com', '1985-03-15', 'M', 3),
    ('Lucia', 'Hernandez', 'Calle 4, Ciudad', '5551234570', 'lucia.hernandez@example.com', '1995-07-20', 'F', 4),
    ('Miguel', 'Ramirez', 'Calle 5, Ciudad', '5551234571', 'miguel.ramirez@example.com', '1988-11-11', 'M', 5),
    ('Sofia', 'Martinez', 'Calle 6, Ciudad', '5551234572', 'sofia.martinez@example.com', '1993-12-05', 'F', 6),
    ('Roberto', 'Jimenez', 'Calle 7, Ciudad', '5551234573', 'roberto.jimenez@example.com', '1980-02-25', 'M', 7),
    ('Ana', 'Castillo', 'Calle 8, Ciudad', '5551234574', 'ana.castillo@example.com', '1994-09-18', 'F', 8),
    ('David', 'Garcia', 'Calle 9, Ciudad', '5551234575', 'david.garcia@example.com', '1986-06-30', 'M', 9),
    ('Elena', 'Morales', 'Calle 10, Ciudad', '5551234576', 'elena.morales@example.com', '1991-04-22', 'F', 10);
