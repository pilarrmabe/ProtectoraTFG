CREATE DATABASE IF NOT EXISTS protectoraanimales;

USE protectoraanimales;

-- Tabla: Refugio
CREATE TABLE IF NOT EXISTS Refugio (
    idRefugio INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    ciudad VARCHAR(100),
    codigoPostal VARCHAR(10),
    responsable VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    capacidad INT,
    fechaApertura DATE,
    logoRefugio LONGBLOB
);

-- Tabla: Centro Veterinario
CREATE TABLE IF NOT EXISTS CentroVeterinario (
    idCentro INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    ciudad VARCHAR(100),
    codigoPostal VARCHAR(10),
    telefono VARCHAR(20),
    email VARCHAR(100),
    fechaRegistro DATE,
    servicioUrgencias BOOLEAN,
    fotoCentro LONGBLOB
);

-- Tabla: Veterinario
CREATE TABLE IF NOT EXISTS Veterinario (
    idVeterinario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    especialidad VARCHAR(100),
    aniosExperiencia INT,
    foto LONGBLOB,
    idCentroVeterinario INT,
    FOREIGN KEY (idCentroVeterinario) REFERENCES CentroVeterinario(idCentro)
);

-- Tabla: Adoptante
CREATE TABLE IF NOT EXISTS Adoptante (
    idAdoptante INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100),
    dni VARCHAR(20) UNIQUE,
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    email VARCHAR(100),
    fechaRegistro DATE,
    foto LONGBLOB
);

-- Tabla: Animal
CREATE TABLE IF NOT EXISTS Animal (
    idAnimal INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(50),
    raza VARCHAR(50),
    sexo VARCHAR(10),
    fechaNacimiento DATE,
    fechaIngreso DATE,
    edad INT,
    peso DECIMAL(5,2),
    salud VARCHAR(100),
    comportamiento VARCHAR(200),
    necesidades VARCHAR(300),
    estado VARCHAR(50),
    numeroMicrochip VARCHAR(50) UNIQUE,
    foto LONGBLOB,
    idRefugio INT,
    idVeterinario INT,
    idAdoptante INT,
    FOREIGN KEY (idRefugio) REFERENCES Refugio(idRefugio),
    FOREIGN KEY (idVeterinario) REFERENCES Veterinario(idVeterinario),
    FOREIGN KEY (idAdoptante) REFERENCES Adoptante(idAdoptante)
);

-- Tabla: Adopcion
CREATE TABLE IF NOT EXISTS Adopcion (
    idAdopcion INT PRIMARY KEY AUTO_INCREMENT,
    idAnimal INT,
    idAdoptante INT,
    fechaAdopcion DATE,
    estadoAdopcion VARCHAR(50),
    observaciones TEXT,
    documentoAdopcion LONGBLOB,
    FOREIGN KEY (idAnimal) REFERENCES Animal(idAnimal),
    FOREIGN KEY (idAdoptante) REFERENCES Adoptante(idAdoptante)
);

-- Tabla: Usuarios
CREATE TABLE IF NOT EXISTS Usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    tipoUsuario VARCHAR(13) NOT NULL
);
