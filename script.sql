CREATE DATABASE IF NOT EXISTS Expo3;
USE Expo3;

-- Tabla Empresa
CREATE TABLE Empresa (
    idEmpresa INT NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    Color VARCHAR(50) NOT NULL,
    Iniciales VARCHAR(10) NOT NULL,
    Logo VARCHAR(255) NOT NULL,
    Codigo_empresa VARCHAR(20) NOT NULL,
    PRIMARY KEY (idEmpresa)
) ENGINE=InnoDB;    

-- Tabla Usuarios
CREATE TABLE Usuarios (
    idUsuarios INT NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Rol VARCHAR(100) NOT NULL,
    Contrasena VARCHAR(45) NOT NULL,
    idEmpresa INT NOT NULL,
    Estado_Union_Empresa VARCHAR(20),
    PRIMARY KEY (idUsuarios),
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa)
) ENGINE=InnoDB;

-- Tabla Materia Prima
CREATE TABLE Materia_Prima (
    idMateria_Prima INT NOT NULL,
    idEmpresa INT NOT NULL,   -- nuevo campo para relacionar con empresa
    Nombre VARCHAR(100) NOT NULL,
    Unidad_de_medida VARCHAR(20) NOT NULL,
    Cantidad_disponible INT NOT NULL,
    Cantidad_minima INT NOT NULL,
    Precio_unitario DECIMAL(10,2) NOT NULL,
    Ultima_actualización_inv DATETIME NOT NULL,
    PRIMARY KEY (idMateria_Prima),
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa)
) ENGINE=InnoDB;

-- Tabla Material empaque
CREATE TABLE Material_empaque (
    idEmpaque INT NOT NULL,
    idEmpresa INT NOT NULL,   -- nuevo campo para relacionar con empresa
    Nombre VARCHAR(100) NOT NULL,
    Unidad_de_medida VARCHAR(20) NOT NULL,
    Cantidad INT NOT NULL,
    Precio_unitario DECIMAL(10,2) NOT NULL,
    Ultima_actualizacion_empq DATETIME NOT NULL,
    PRIMARY KEY (idEmpaque),
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa)
) ENGINE=InnoDB;

-- Tabla Productos Terminados
CREATE TABLE Productos_Terminados (
    idProductos INT NOT NULL,
    idEmpresa INT NOT NULL,   -- nuevo campo para relacionar con empresa
    Nombre VARCHAR(100) NOT NULL,
    Descripción VARCHAR(250),
    Cantidad_disponible INT NOT NULL,
    Costo_produccion DECIMAL(10,2) NOT NULL,
    Precio_venta DECIMAL(10,2) NOT NULL,
    Fecha_produccion DATE NOT NULL,
    Ultima_actualizacion_prod DATETIME NOT NULL,
    PRIMARY KEY (idProductos),
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa)
) ENGINE=InnoDB;


-- Tabla Movimientos de Inventario (MODIFICADA para permitir ON DELETE SET NULL)
CREATE TABLE Movimientos_Inventario (
    idMovimiento INT NOT NULL,
    Tipo_movimiento VARCHAR(100) NOT NULL,
    Tipo_Item VARCHAR(50) NOT NULL, -- Indica si es 'Materia_Prima', 'Producto_Terminado' o 'Material_Empaque'
    idMateriaPrima_FK INT NULL,    -- Clave foránea para Materia_Prima, permite NULL
    idProductoTerminado_FK INT NULL, -- Clave foránea para Productos_Terminados, permite NULL
    idMaterialEmpaque_FK INT NULL,  -- Clave foránea para Material_empaque, permite NULL
    Cantidad INT NOT NULL,
    FechaMovimiento DATETIME NOT NULL,
    idUsuario INT NOT NULL,
    Costo_Unitario DECIMAL(10,2) NOT NULL,
    Costo_Total DECIMAL(10,2) NOT NULL,
    Comentarios_detalles VARCHAR(200),
    PRIMARY KEY (idMovimiento),
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuarios),
    FOREIGN KEY (idMateriaPrima_FK) REFERENCES Materia_Prima(idMateria_Prima) ON DELETE SET NULL,
    FOREIGN KEY (idProductoTerminado_FK) REFERENCES Productos_Terminados(idProductos) ON DELETE SET NULL,
    FOREIGN KEY (idMaterialEmpaque_FK) REFERENCES Material_empaque(idEmpaque) ON DELETE SET NULL
) ENGINE=InnoDB;


-- 5 Empresas
INSERT INTO Empresa (idEmpresa, Nombre, Tipo, Color, Iniciales, Logo, Codigo_empresa)
VALUES
    (1, 'Velas Aromáticas Luna',     'Fabricación', 'Lavanda',    'VAL', 'logo_velas.png',    'EMP-001'),
    (2, 'Cera y Mechas S.L.',        'Proveedores', 'Blanco',     'CYM', 'logo_cym.png',      'EMP-002'),
    (3, 'Envases EcoGreen',          'Empaque',      'Verde',      'EEG', 'logo_ecogreen.png', 'EMP-003'),
    (4, 'Aromas del Bosque',         'Fabricación', 'Pino',       'ADB', 'logo_aromas.png',    'EMP-004'),
    (5, 'Luz y Fragancia S.A.',      'Distribución','Amarillo',   'LYF', 'logo_luzfrag.png',  'EMP-005');

-- 5 Usuarios (uno por empresa, mix de Admin / Empleado)
INSERT INTO Usuarios (idUsuarios, Nombre, Rol, Contrasena, idEmpresa, Estado_Union_Empresa)
VALUES
    (1, 'Sofía Márquez',   'Administrador', 'admin001', 1, 'Activo'),
    (2, 'Carlos Gómez',    'Empleado',      'emp002',    2, 'Activo'),
    (3, 'Laura Jiménez',   'Empleado',      'emp003',    3, 'Activo'),
    (4, 'Diego Torres',    'Administrador', 'admin004', 4, 'Activo'),
    (5, 'Andrea Ruiz',     'Empleado',      'emp005',    5, 'Activo');

-- 5 Materias Primas (repartidas entre 3 empresas)
INSERT INTO Materia_Prima (idMateria_Prima, idEmpresa, Nombre, Unidad_de_medida, Cantidad_disponible, Cantidad_minima, Precio_unitario, Ultima_actualización_inv)
VALUES
    (1, 1, 'Cera de Soya',              'kg',       120,  20,  35.00, NOW()),
    (2, 2, 'Mechas de Algodón',        'unidad', 600, 100,    1.50, NOW()),
    (3, 3, 'Colorante Natural Violeta','ml', 250,  50,    0.80, NOW()),
    (4, 4, 'Aceite Esencial Pino',     'L',        30,    5, 120.00, NOW()),
    (5, 1, 'Alcohol Isopropílico',     'L',        40,    10,  30.00, NOW());

-- 5 Materiales de Empaque (repartidos entre 3 empresas)
INSERT INTO Material_empaque (idEmpaque, idEmpresa, Nombre, Unidad_de_medida, Cantidad, Precio_unitario, Ultima_actualizacion_empq)
VALUES
    (1, 3, 'Frascos de Vidrio 150ml', 'unidad', 300,  8.50, NOW()),
    (2, 3, 'Tapas Metálicas',         'unidad', 300,  1.20, NOW()),
    (3, 5, 'Cajas de Cartón Pequeñas','unidad', 120,  5.00, NOW()),
    (4, 5, 'Etiquetas Eco',           'unidad', 500,  0.40, NOW()),
    (5, 2, 'Bolsas Plásticas',        'unidad', 200,  0.10, NOW());

-- 5 Productos Terminados (uno por empresa)
INSERT INTO Productos_Terminados (idProductos, idEmpresa, Nombre, Descripción, Cantidad_disponible, Costo_produccion, Precio_venta, Fecha_produccion, Ultima_actualizacion_prod)
VALUES
    (1, 1, 'Vela Lavanda 150g',       'Vela aromática en frasco de vidrio',  50, 45.00,  90.00, '2025-06-01', NOW()),
    (2, 2, 'Kit Mechas y Mechones', 'Pack de 10 mechas + mechones de algodón', 80, 20.00,  40.00, '2025-06-05', NOW()),
    (3, 3, 'Envase 200ml Reutilizable','Envase eco-friendly para velas',       60, 10.00,  25.00, '2025-06-03', NOW()),
    (4, 4, 'Vela Pino Silvestre',     'Vela con aroma a pino natural',        70, 50.00, 100.00, '2025-06-07', NOW()),
    (5, 5, 'Pack Luz y Fragancia',  'Conjunto de 3 velas aromáticas',        40, 75.00, 150.00, '2025-06-08', NOW());

-- 5 Movimientos de Inventario (mezcla de entradas y salidas, usando los nuevos campos _FK y Tipo_Item)
INSERT INTO Movimientos_Inventario (idMovimiento, Tipo_movimiento, Tipo_Item, idMateriaPrima_FK, idProductoTerminado_FK, idMaterialEmpaque_FK, Cantidad, FechaMovimiento, idUsuario, Costo_Unitario, Costo_Total, Comentarios_detalles)
VALUES
    -- Compras de materia prima
    (1, 'Entrada', 'Materia_Prima', 1, NULL, NULL, 60, NOW(), 1, 35.00,  2100.00, 'Refuerzo de cera de soya'),
    (2, 'Entrada', 'Materia_Prima', 2, NULL, NULL, 200, NOW(), 2,  1.50,   300.00, 'Compra de mechas adicionales'),
    -- Salida para producción (Materia Prima)
    (3, 'Salida',  'Materia_Prima', 1, NULL, NULL, 20, NOW(), 1, 35.00,   700.00, 'Uso en elaboración de velas Luna'),
    -- Salida para producción (Producto Terminado)
    (4, 'Salida',  'Productos_Terminados', NULL, 4, NULL, 10, NOW(), 4, 50.00,   500.00, 'Producción de velas pino'),
    -- Uso de empaque
    (5, 'Salida',  'Material_Empaque', NULL, NULL, 3, 15, NOW(), 5, 10.00,   150.00, 'Uso de envases EcoGreen');
