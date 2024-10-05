-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-07-2024 a las 00:59:52
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdlancenter`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cliente_id` int(11) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `ApePaterno` varchar(100) NOT NULL,
  `ApeMaterno` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `Minutos_Guardados` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cliente_id`, `nombres`, `ApePaterno`, `ApeMaterno`, `correo`, `dni`, `Minutos_Guardados`) VALUES
(0, 'NOCLIENTE', 'NO', 'NO', 'NOCORREO', '0', 0),
(1, 'Juan', 'Pérez', 'Marquez', 'juan.perez@example.com', '12345678', 0),
(2, 'María', 'García', 'Valera', 'maria.garcia@example.com', '23456789', 0),
(3, 'Carlos', 'Lopez', 'Aliaga', 'carlos.lopez@example.com', '34567890', 249),
(4, 'Ana', 'Martínez', 'Palmer', 'ana.martinez@example.com', '45678901', 0),
(5, 'Pedro', 'Rodríguez', 'Fariñez', 'pedro.rodriguez@example.com', '56789012', 180),
(6, 'Sofía', 'Fernández', 'Huaman', 'sofia.fernandez@example.com', '67890123', 0),
(7, 'Luis', 'González', 'Amorot', 'luis.gonzalez@example.com', '78901234', 240),
(8, 'Laura', 'Sánchez', 'Lainez', 'laura.sanchez@example.com', '89012345', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobante_pago`
--

CREATE TABLE `comprobante_pago` (
  `comprobante_id` int(11) NOT NULL,
  `alquiler_id` int(11) NOT NULL,
  `fecha_pago` date DEFAULT curdate(),
  `monto` decimal(10,2) NOT NULL,
  `tipo_comprobante` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comprobante_pago`
--

INSERT INTO `comprobante_pago` (`comprobante_id`, `alquiler_id`, `fecha_pago`, `monto`, `tipo_comprobante`) VALUES
(5, 5, '2024-06-05', 85.00, 'Factura'),
(6, 6, '2024-06-06', 95.00, 'Boleta'),
(7, 7, '2024-06-07', 110.00, 'Factura'),
(8, 8, '2024-06-08', 120.00, 'Recibo'),
(9, 14, '2024-07-18', 9.00, 'Recibo'),
(10, 16, '2024-07-18', 15.00, 'Recibo'),
(11, 13, '2024-07-18', 0.00, 'Recibo'),
(12, 11, '2024-07-18', 0.00, 'Recibo'),
(13, 8, '2024-07-18', 0.00, 'Recibo'),
(14, 6, '2024-07-18', 0.00, 'Recibo'),
(15, 5, '2024-07-18', 0.00, 'Recibo'),
(16, 7, '2024-07-18', 0.00, 'Recibo'),
(17, 9, '2024-07-18', 0.00, 'Recibo'),
(18, 10, '2024-07-18', 0.00, 'Recibo'),
(19, 12, '2024-07-18', 0.00, 'Recibo'),
(20, 17, '2024-07-18', 9.00, 'Recibo'),
(21, 18, '2024-07-18', 12.00, 'Recibo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `control_alquiler`
--

CREATE TABLE `control_alquiler` (
  `alquiler_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `equipo_id` int(11) NOT NULL,
  `fecha_alquiler_inicio` date DEFAULT curdate(),
  `hora_inicio` time DEFAULT current_timestamp(),
  `tiempo_alquilado` int(11) DEFAULT NULL,
  `hora_final_estimada` time DEFAULT NULL,
  `fecha_alquiler_final` date DEFAULT NULL,
  `tiempo_restante` int(11) DEFAULT 0,
  `precio_final` double NOT NULL DEFAULT 0,
  `cliente_id` int(11) NOT NULL,
  `id_estado_control` int(11) NOT NULL DEFAULT 2
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `control_alquiler`
--

INSERT INTO `control_alquiler` (`alquiler_id`, `usuario_id`, `equipo_id`, `fecha_alquiler_inicio`, `hora_inicio`, `tiempo_alquilado`, `hora_final_estimada`, `fecha_alquiler_final`, `tiempo_restante`, `precio_final`, `cliente_id`, `id_estado_control`) VALUES
(5, 1, 5, '2024-07-02', '15:00:00', 60, '16:00:00', '2024-07-09', 0, 0, 0, 3),
(6, 1, 6, '2024-07-02', '16:00:00', 45, '16:45:00', '2024-07-09', 0, 0, 6, 3),
(7, 1, 7, '2024-07-02', '17:00:00', 120, '19:00:00', '2024-07-09', 0, 0, 7, 3),
(8, 1, 8, '2024-07-02', '18:00:00', 90, '19:30:00', '2024-07-09', 0, 0, 8, 3),
(9, 1, 2, '2024-07-02', '16:42:08', 60, '17:45:00', '2024-07-09', 0, 0, 5, 3),
(10, 1, 3, '2024-07-02', '16:42:08', 45, '16:45:00', '2024-07-09', 0, 0, 6, 3),
(11, 1, 4, '2024-07-02', '16:42:08', 120, '19:00:00', '2024-07-09', 0, 0, 7, 3),
(12, 1, 1, '2024-07-02', '16:42:08', 90, '19:30:00', '2024-07-09', 0, 0, 8, 3),
(13, 1, 5, '2024-07-02', '16:42:08', 60, '17:00:00', '2024-07-09', 0, 0, 0, 3),
(14, 1, 14, '2024-07-11', '15:25:00', 180, '18:25:00', '2024-07-11', 0, 9, 3, 3),
(16, 1, 5, '2024-07-18', '15:28:34', 300, '20:28:34', '2024-07-18', 0, 15, 3, 3),
(17, 1, 21, '2024-07-18', '16:20:58', 180, '19:20:58', '2024-07-18', 0, 9, 5, 3),
(18, 1, 10, '2024-07-18', '16:21:46', 240, '20:21:46', '2024-07-18', 0, 12, 7, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `equipo_id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `estado_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`equipo_id`, `nombre`, `estado_id`) VALUES
(1, 'PC1', 1),
(2, 'PC2', 1),
(3, 'PC3', 1),
(4, 'PC4', 1),
(5, 'PC5', 1),
(6, 'PC6', 1),
(7, 'PC7', 1),
(8, 'PC8', 1),
(9, 'PC9', 1),
(10, 'PC10', 1),
(11, 'PS5nro1', 3),
(12, 'PS5nro2', 1),
(13, 'PS5nro3', 1),
(14, 'PS5nro4', 1),
(15, 'PS5nro5', 1),
(16, 'PS5nro6', 1),
(17, 'PS5nro7', 1),
(18, 'PS5nro8', 1),
(19, 'PS5nro9', 1),
(20, 'PS5nro10', 1),
(21, 'XBOXnro1', 1),
(22, 'XBOXnro2', 3),
(23, 'XBOXnro3', 1),
(24, 'XBOXnro4', 1),
(25, 'XBOXnro5', 1),
(26, 'XBOXnro6', 1),
(27, 'XBOXnro7', 1),
(28, 'XBOXnro8', 3),
(29, 'XBOXnro9', 1),
(30, 'XBOXnro10', 1),
(31, 'SWITCHnro1', 1),
(32, 'SWITCHnro2', 3),
(33, 'SWITCHnro3', 1),
(34, 'SWITCHnro4', 1),
(35, 'SWITCHnro5', 1),
(36, 'SWITCHnro6', 1),
(37, 'SWITCHnro7', 1),
(38, 'SWITCHnro8', 1),
(39, 'SWITCHnro9', 1),
(40, 'SWITCHnro10', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_control_alquiler`
--

CREATE TABLE `estado_control_alquiler` (
  `id_estado_control` int(11) NOT NULL,
  `nombre_estado` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_control_alquiler`
--

INSERT INTO `estado_control_alquiler` (`id_estado_control`, `nombre_estado`) VALUES
(1, 'POR COBRAR'),
(2, 'TIEMPO ACTIVO'),
(3, 'COBRADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_equipo`
--

CREATE TABLE `estado_equipo` (
  `estado_id` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_equipo`
--

INSERT INTO `estado_equipo` (`estado_id`, `descripcion`) VALUES
(1, 'LIBRE'),
(2, 'ALQUILADO'),
(3, 'FUERA DE SERVICIO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `informacion_usuario`
--

CREATE TABLE `informacion_usuario` (
  `usuario_id` int(11) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `ApePaterno` varchar(100) NOT NULL,
  `ApeMaterno` varchar(100) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `informacion_usuario`
--

INSERT INTO `informacion_usuario` (`usuario_id`, `Nombres`, `ApePaterno`, `ApeMaterno`, `direccion`, `telefono`, `correo`) VALUES
(1, 'Jhordan', 'Alvarez', 'Ventino', 'Avenida Central 456', '+9876543210', 'admin@example.com'),
(2, 'Luis', 'Marquez', 'Patroclo', 'Calle Principal 123', '+1234567890', 'recepcion@example.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `rol_id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`rol_id`, `nombre`) VALUES
(1, 'administrador'),
(2, 'recepcionista');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `usuario_id` int(11) NOT NULL,
  `nombre_usuario` varchar(100) NOT NULL,
  `clave_usuario` varchar(100) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `rol_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usuario_id`, `nombre_usuario`, `clave_usuario`, `estado`, `rol_id`) VALUES
(1, 'Usuario Recepcionista', '1234', 1, 2),
(2, 'Usuario Administrador', '1234', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cliente_id`);

--
-- Indices de la tabla `comprobante_pago`
--
ALTER TABLE `comprobante_pago`
  ADD PRIMARY KEY (`comprobante_id`),
  ADD KEY `alquiler_id` (`alquiler_id`);

--
-- Indices de la tabla `control_alquiler`
--
ALTER TABLE `control_alquiler`
  ADD PRIMARY KEY (`alquiler_id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `equipo_id` (`equipo_id`),
  ADD KEY `fk_control_alquiler_cliente` (`cliente_id`),
  ADD KEY `fk_control_alquiler_estado` (`id_estado_control`);

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`equipo_id`),
  ADD KEY `estado_id` (`estado_id`);

--
-- Indices de la tabla `estado_control_alquiler`
--
ALTER TABLE `estado_control_alquiler`
  ADD PRIMARY KEY (`id_estado_control`);

--
-- Indices de la tabla `estado_equipo`
--
ALTER TABLE `estado_equipo`
  ADD PRIMARY KEY (`estado_id`);

--
-- Indices de la tabla `informacion_usuario`
--
ALTER TABLE `informacion_usuario`
  ADD PRIMARY KEY (`usuario_id`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`rol_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario_id`),
  ADD KEY `rol_id` (`rol_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cliente_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `comprobante_pago`
--
ALTER TABLE `comprobante_pago`
  MODIFY `comprobante_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `control_alquiler`
--
ALTER TABLE `control_alquiler`
  MODIFY `alquiler_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `equipo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `estado_control_alquiler`
--
ALTER TABLE `estado_control_alquiler`
  MODIFY `id_estado_control` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `estado_equipo`
--
ALTER TABLE `estado_equipo`
  MODIFY `estado_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `rol_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comprobante_pago`
--
ALTER TABLE `comprobante_pago`
  ADD CONSTRAINT `comprobante_pago_ibfk_1` FOREIGN KEY (`alquiler_id`) REFERENCES `control_alquiler` (`alquiler_id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `control_alquiler`
--
ALTER TABLE `control_alquiler`
  ADD CONSTRAINT `control_alquiler_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`),
  ADD CONSTRAINT `control_alquiler_ibfk_2` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`equipo_id`),
  ADD CONSTRAINT `fk_control_alquiler_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`),
  ADD CONSTRAINT `fk_control_alquiler_estado` FOREIGN KEY (`id_estado_control`) REFERENCES `estado_control_alquiler` (`id_estado_control`);

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`estado_id`) REFERENCES `estado_equipo` (`estado_id`);

--
-- Filtros para la tabla `informacion_usuario`
--
ALTER TABLE `informacion_usuario`
  ADD CONSTRAINT `informacion_usuario_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`rol_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
