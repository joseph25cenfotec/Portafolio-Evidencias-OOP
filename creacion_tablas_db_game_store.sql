-- Creación de la Base de Datos
CREATE DATABASE db_game_store;

-- Activación de la Base de Datos
USE db_game_store;

-- Creación de las tablas

-- Tienda (una sola fila: el sistema es de una sola tienda)
CREATE TABLE t_store (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- Clientes
CREATE TABLE t_customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Empleados
CREATE TABLE t_employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(100) NOT NULL
);

-- Videojuegos
CREATE TABLE t_games (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    platform VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL
);

-- Rentas
CREATE TABLE t_rentals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_game INT NOT NULL,
    id_customer INT NOT NULL,
    rent_date DATETIME NOT NULL,
    return_date DATETIME NULL,
    FOREIGN KEY (id_game) REFERENCES t_games(id),
    FOREIGN KEY (id_customer) REFERENCES t_customers(id)
);