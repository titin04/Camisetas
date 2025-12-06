INSERT INTO `provincia` (`codigo`, `comunidad`, `nombre_provincia`) VALUES ('23', 'Andalucía', 'Jaén');
INSERT INTO `provincia` (`codigo`, `comunidad`, `nombre_provincia`) VALUES ('29', 'Andalucía', 'Málaga');

INSERT INTO categoria (id, nombre, descripcion, padre_id) VALUES (1, 'PRIMAVERA', 'Ropa para primavera', NULL);
INSERT INTO categoria (id, nombre, descripcion, padre_id) VALUES (2, 'VERANO', 'Ropa para verano', NULL);
INSERT INTO categoria (id, nombre, descripcion, padre_id) VALUES (3, 'OTOÑO', 'Ropa para otoño', NULL);
INSERT INTO categoria (id, nombre, descripcion, padre_id) VALUES (4, 'INVIERNO', 'Ropa para invierno', NULL);

INSERT INTO camiseta (precio, stock, color, marca, sexo, talla, categoria_id) VALUES (19.99, 50, 'Rojo', 'Nike', 'CHICO', 'M', 1);
INSERT INTO camiseta (precio, stock, color, marca, sexo, talla, categoria_id) VALUES (24.50, 30, 'Azul', 'Adidas', 'CHICA', 'S', 2);
INSERT INTO camiseta (precio, stock, color, marca, sexo, talla, categoria_id) VALUES (22.00, 20, 'Negro', 'Puma', 'UNISEX', 'L', 3);
INSERT INTO camiseta (precio, stock, color, marca, sexo, talla, categoria_id) VALUES (18.75, 15, 'Blanco', 'Reebok', 'NIÑO', 'XS', 4);
INSERT INTO camiseta (precio, stock, color, marca, sexo, talla, categoria_id) VALUES (21.00, 25, 'Verde', 'Under Armour', 'NIÑA', 'M', 1);
INSERT INTO camiseta (precio, stock, color, marca, sexo, talla, categoria_id) VALUES (20.50, 10, 'Amarillo', 'Nike', 'UNISEX_INFANTIL', 'XXS', 2);
INSERT INTO camiseta (precio, stock, color, marca, sexo, talla, categoria_id) VALUES (23.99, 40, 'Gris', 'Adidas', 'CHICO', 'XL', 3);
INSERT INTO camiseta (precio, stock, color, marca, sexo, talla, categoria_id) VALUES (19.50, 35, 'Morado', 'Puma', 'CHICA', 'S', 4);


insert into usuario (username, password, enabled, rol, email, telefono, nif, nombre, apellido) values ('admin', '$2a$08$w8gu7OiJ0gs6y/Xjy1XXneI2kzsgXE1kNZbjE3CtCKknjFiP03MHS', true, 'ADMIN', 'admin@tienda.com', 600000000, '12345678A', 'Admin', 'User');
insert into usuario (username, password, enabled, rol, email, telefono, nif, nombre, apellido) values ('titin', '$2a$08$w8gu7OiJ0gs6y/Xjy1XXneI2kzsgXE1kNZbjE3CtCKknjFiP03MHS', true, 'CLIENTE', 'titin@tienda.com', 600000001, '12345648A', 'titin', 'titin');


INSERT INTO cod_postal (cp, municipio, provincia_codigo) VALUES ("23001", "Jaén", 23);
INSERT INTO cod_postal (cp, municipio, provincia_codigo) VALUES ("23002", "Jaén", 23);
INSERT INTO cod_postal (cp, municipio, provincia_codigo) VALUES ("23003", "Jaén", 23);
INSERT INTO cod_postal (cp, municipio, provincia_codigo) VALUES ("23100", "Mancha Real", 23);
INSERT INTO cod_postal (cp, municipio, provincia_codigo) VALUES ("23110", "Pegalajar", 23);
