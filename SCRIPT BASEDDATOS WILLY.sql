CREATE DATABASE IF NOT EXISTS expo;
USE expo;

DROP TABLE IF EXISTS Materia_Prima;
DROP TABLE IF EXISTS Productos_Terminados;


CREATE TABLE Materia_Prima (
    idMateria_Prima INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Unidad_de_medida VARCHAR(20),
    Cantidad_disponible INT,
    Cantidad_minima INT,
    Precio_unitario DECIMAL(10,2),
    Ultima_actualizacion_inv DATETIME
);

CREATE TABLE Productos_Terminados (
    idProductos INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Descripcion VARCHAR(250),
    Cantidad_disponible INT,
    Costo_produccion DECIMAL(10,2),
    Precio_venta DECIMAL(10,2),
    Fecha_produccion DATE,
    Ultima_actualizacion_prod DATETIME
);


INSERT INTO Materia_Prima (
    idMateria_Prima,
    Nombre,
    Unidad_de_medida,
    Cantidad_disponible,
    Cantidad_minima,
    Precio_unitario,
    Ultima_actualizacion_inv
) VALUES
(1, 'Cera de Soya', 'kg', 120, 50, 25.50, '2025-06-10 10:00:00'),
(2, 'Mechas de Algodon', 'unidad', 500, 200, 1.00, '2025-06-09 09:30:00'),
(3, 'Aceite Esencial de Lavanda', 'ml', 1000, 300, 0.15, '2025-06-08 14:00:00'),
(4, 'Frascos de Vidrio', 'unidad', 150, 50, 6.00, '2025-06-07 13:20:00'),
(5, 'Colorante para Velas', 'ml', 800, 200, 0.10, '2025-06-06 11:45:00');

INSERT INTO Productos_Terminados (
    idProductos,
    Nombre,
    Descripcion,
    Cantidad_disponible,
    Costo_produccion,
    Precio_venta,
    Fecha_produccion,
    Ultima_actualizacion_prod
) VALUES
(1, 'Vela de Lavanda', 'Vela aromatica con esencia de lavanda en frasco de vidrio', 80, 12.50, 20.00, '2025-06-09', '2025-06-10 08:15:00'),
(2, 'Vela de Canela', 'Vela artesanal con aroma a canela y decoraciones naturales', 60, 10.00, 18.00, '2025-06-08', '2025-06-09 11:00:00'),
(3, 'Vela de Vainilla', 'Vela perfumada de cera de soya con aroma a vainilla', 100, 11.00, 19.50, '2025-06-07', '2025-06-08 13:10:00'),
(4, 'Vela de Eucalipto', 'Vela ecologica con esencia natural de eucalipto', 45, 9.75, 16.00, '2025-06-06', '2025-06-07 15:00:00'),
(5, 'Vela de Rosas', 'Vela decorativa con fragancia de rosas y petalos secos', 70, 13.00, 21.50, '2025-06-05', '2025-06-06 17:30:00');


select * from Materia_Prima;
select * from Productos_Terminados;