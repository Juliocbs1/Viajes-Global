Plataforma digital de viajes
BD:
create database viajesglobal;

USE viajesglobal;

CREATE TABLE Lugares (
id_lugar INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
pais VARCHAR(100) NOT NULL,
ciudad VARCHAR(100) NOT NULL
);

-- Creación de la tabla Rutas (relación entre Origenes y Destinos)
CREATE TABLE Rutas (
id_ruta INT AUTO_INCREMENT PRIMARY KEY,
id_origen INT,
id_destino INT,
duracion_estimada INT,  -- Duración estimada del viaje en horas
costo DECIMAL(10,2),    -- Costo del viaje entre origen y destino
FOREIGN KEY (id_origen) REFERENCES Lugares(id_lugar) ON DELETE CASCADE,
FOREIGN KEY (id_destino) REFERENCES Lugares(id_lugar) ON DELETE CASCADE
);



-- Creación de las demás tablas (no cambian)
CREATE TABLE Usuarios (
id_usuario INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
correo VARCHAR(100) NOT NULL UNIQUE,
telefono VARCHAR(20),
contrasena VARCHAR(255) NOT NULL,
preferencia_notificacion ENUM('SMS', 'Correo', 'Push') DEFAULT 'Correo',
fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Vuelos (
id_vuelo INT AUTO_INCREMENT PRIMARY KEY,
id_ruta INT,  -- Relación con la ruta entre origen y destino
numero_vuelo VARCHAR(20) NOT NULL,
fecha_salida DATETIME NOT NULL,
asientos_totales INT NOT NULL,  -- Capacidad total del vuelo
asientos_disponibles INT NOT NULL,  -- Asientos disponibles para el vuelo
costo_asiento DECIMAL(10, 2) NOT NULL,
FOREIGN KEY (id_ruta) REFERENCES Rutas(id_ruta) ON DELETE CASCADE
);

-- Creación de la tabla Paquetes
CREATE TABLE Paquetes (
id_paquete INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
descripcion TEXT,
precio DECIMAL(10,2) NOT NULL,
disponibilidad INT NOT NULL,
id_vuelo INT,  -- Relación directa con un vuelo específico
fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

FOREIGN KEY (id_vuelo) REFERENCES Vuelos(id_vuelo) ON DELETE CASCADE
);

-- Creación de la tabla Reservas


CREATE TABLE Reservas (
id_reserva INT AUTO_INCREMENT PRIMARY KEY,
id_usuario INT,
id_paquete INT NULL,   -- Permite NULL si el cliente no selecciona un paquete
fecha_inicio DATE NOT NULL,
fecha_fin DATE NULL,   -- Permite viajes de ida, por lo que fecha_fin es NULL
fecha_reserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
estado ENUM('Pendiente', 'Confirmada', 'Cancelada') DEFAULT 'Pendiente',
total_pago DECIMAL(10,2) NOT NULL, -- Precio final de la reserva basado en paquete o ruta
id_vuelo INT,  
cantidad_asientos INT NOT NULL DEFAULT 1,
FOREIGN KEY (id_vuelo) REFERENCES Vuelos(id_vuelo) ON DELETE CASCADE,
FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE,
FOREIGN KEY (id_paquete) REFERENCES Paquetes(id_paquete) ON DELETE SET NULL
);




CREATE TABLE Pagos (
id_pago INT AUTO_INCREMENT PRIMARY KEY,
id_reserva INT,
monto DECIMAL(10,2) NOT NULL,
estado_pago ENUM('Pendiente', 'Completado', 'Fallido') DEFAULT 'Pendiente',
id_transaccion_securepay VARCHAR(100),
fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (id_reserva) REFERENCES Reservas(id_reserva) ON DELETE CASCADE
);

CREATE TABLE Notificaciones (
id_notificacion INT AUTO_INCREMENT PRIMARY KEY,
id_usuario INT,
tipo ENUM('SMS', 'Correo', 'Push') NOT NULL,
contenido TEXT NOT NULL,
estado ENUM('Pendiente', 'Enviada', 'Fallida') DEFAULT 'Pendiente',
fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);

CREATE TABLE Servicios_Adicionales (
id_servicio INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
descripcion TEXT,
precio DECIMAL(10,2) NOT NULL
);

CREATE TABLE Paquete_Servicios (
id_paquete_servicio INT AUTO_INCREMENT PRIMARY KEY,
id_paquete INT,
id_servicio INT,
cantidad INT NOT NULL,
FOREIGN KEY (id_paquete) REFERENCES Paquetes(id_paquete) ON DELETE CASCADE,
FOREIGN KEY (id_servicio) REFERENCES Servicios_Adicionales(id_servicio) ON DELETE CASCADE
);
