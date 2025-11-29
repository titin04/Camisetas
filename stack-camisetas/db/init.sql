CREATE DATABASE `camisetas`;

USE `camisetas`;

CREATE TABLE `camiseta` (
        `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        `talla` VARCHAR(4) NOT NULL,
        `color` VARCHAR(15) NOT NULL,
        `marca` VARCHAR(50) NOT NULL,
        `stock` INT NOT NULL,
        `sexo` ENUM('chico', 'chica', 'unisex', 'niño', 'niña', 'unisex_infantil') NOT NULL,
        `precio` DECIMAL(8,2) NOT NULL,
        `activo` BOOLEAN
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `usuario` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `telefono` VARCHAR(20),
    `direccion` VARCHAR(255),
    `activo` BOOLEAN,
    `tipo` ENUM ('OPERARIO', 'CLIENTE')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pedido` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `estado` ENUM('carrito', 'pagado', 'procesado', 'enviado', 'recibido') NOT NULL,
    `cliente` INT UNSIGNED NOT NULL,
    `total` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (`cliente`) REFERENCES `usuario`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `linea_pedido` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `pedido` INT UNSIGNED NOT NULL,
    `camiseta` INT UNSIGNED NOT NULL,
    `precio_venta` DECIMAL(8,2) NOT NULL,
    FOREIGN KEY (`pedido`) REFERENCES `pedido`(`id`),
    FOREIGN KEY (`camiseta`) REFERENCES `camiseta`(`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- juan Secreto_123

INSERT INTO `usuario` (`username`, `password`, `email`, `telefono`, `direccion`, `activo`, `tipo`)
VALUES ('Antonio', '$2a$10$rD6bJW8v9GoQvXSknS8PPO7lAvh0xNdWA0EMpdFbHSGrJQoO3PGo.', 'antonio@gmail.com', '634956874', 'Nueva direccion', '1', 'OPERADOR');

