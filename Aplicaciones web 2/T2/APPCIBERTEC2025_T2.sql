
DROP DATABASE IF EXISTS APPCIBERTEC2025_T2;
CREATE DATABASE APPCIBERTEC2025_T2;
USE APPCIBERTEC2025_T2;
-- tengo el APPCIBERTEC2025 para las clases
-- por eso agrego el T2

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha DATETIME NOT NULL
);

CREATE TABLE cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(150) NOT NULL,
    creditos TINYINT NOT NULL CHECK (creditos BETWEEN 1 AND 6),
    activo CHAR(1) NOT NULL CHECK (activo IN ('S', 'N')),
    fecha DATETIME NOT NULL
);

INSERT INTO usuario (nombre, email, password, fecha)
VALUES ('Modesto', 'Modesto@cibertec.com', '$2a$10$HLswwY.Dc2fUwdTO7Tg3ie4XbN2tOycTH5Ypqu/E8p4hGfLxT/A7K', 
        STR_TO_DATE('10/02/2025 15:30:45', '%d/%m/%Y %H:%i:%s'));
        

INSERT INTO cursos (descripcion, creditos, activo, fecha)
VALUES ('Desarrollo de Aplicaicones Web II', 4, 'S', 
        STR_TO_DATE('10/02/2025 15:30:45', '%d/%m/%Y %H:%i:%s'));


select * from Usuario;