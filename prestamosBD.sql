-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2017 a las 22:16:43
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prestamosbd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dispositivo`
--

CREATE TABLE `dispositivo` (
  `idDispositivo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `tipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `dispositivo`
--

INSERT INTO `dispositivo` (`idDispositivo`, `nombre`, `descripcion`, `tipo`) VALUES
(1, 'Portatil', 'Asus Gl552vw 1tb / 4gb / Win 10 /dvd / 15.6', 1),
(2, 'VideoBeam', 'CASIO XJ-V1 / 2700 lumenes', 1),
(3, 'Microscopio', 'NIKON E200 LED', 3),
(4, 'Tableta', 'Lenovo IdeaTab A2109', 1),
(5, 'Barometro', 'Taylor 1735', 3),
(6, 'Termometro infrarojo', 'Fluke 566 - Escala -40 a +650°C', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplardispositivo`
--

CREATE TABLE `ejemplardispositivo` (
  `idEjemplar` int(11) NOT NULL,
  `dispositivo` int(11) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ejemplardispositivo`
--

INSERT INTO `ejemplardispositivo` (`idEjemplar`, `dispositivo`, `estado`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 1),
(4, 2, 1),
(5, 2, 1),
(6, 3, 1),
(7, 3, 1),
(8, 3, 1),
(9, 3, 1),
(10, 4, 1),
(11, 5, 1),
(12, 5, 2),
(13, 6, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadodispositivo`
--

CREATE TABLE `estadodispositivo` (
  `codEstadoD` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadodispositivo`
--

INSERT INTO `estadodispositivo` (`codEstadoD`, `nombre`, `descripcion`) VALUES
(1, 'Disponible', 'El elemento se encuentra disponible para cualquier laboratorio.'),
(2, 'En reparacion', 'El elemento se encuentra en algun tipo de mantenimiento correctivo o preventivo.'),
(3, 'Dado de baja', NULL),
(4, 'Prestado', 'estado prestadod el dispositivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadoprestamo`
--

CREATE TABLE `estadoprestamo` (
  `codEstadoP` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadoprestamo`
--

INSERT INTO `estadoprestamo` (`codEstadoP`, `nombre`, `descripcion`) VALUES
(1, 'Pendiente', 'El prestamo esta a la espera de ser aprobada o rechazada por un administrador.'),
(2, 'Rechazada', 'El prestamo de los elementos fue rechazado.'),
(3, 'Vigente', 'El prestamo fue aprobado y esta habilitado para el investigador.'),
(4, 'Caducada', 'El prestamo ya esta cumplido.'),
(5, 'Prestado', 'ejempalr prestado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosusuarios`
--

CREATE TABLE `estadosusuarios` (
  `codEstadoU` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadosusuarios`
--

INSERT INTO `estadosusuarios` (`codEstadoU`, `nombre`, `descripcion`) VALUES
(1, 'Habilitado', 'El usuario esta libre de sanciones y autorizado en el sistema.'),
(2, 'Sancionado', 'El usuario tiene al menos una sancion vigente por lo cual tiene acceso restringindo al sistema y sus funciones.'),
(3, 'Retirado', 'El usuario ya no pertenece a la organizacion');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `itemsprestamo`
--

CREATE TABLE `itemsprestamo` (
  `idItem` int(11) NOT NULL,
  `prestamo` int(11) NOT NULL,
  `ejemplar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `itemsprestamo`
--

INSERT INTO `itemsprestamo` (`idItem`, `prestamo`, `ejemplar`) VALUES
(8, 90, 12),
(9, 90, 13),
(10, 91, 7),
(11, 91, 8),
(12, 91, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `idPrestamo` int(11) NOT NULL,
  `fechaSolicitud` date NOT NULL,
  `usuarioSolicita` int(11) NOT NULL,
  `usuarioAprueba` int(11) DEFAULT NULL,
  `fechaPrestamo` date NOT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`idPrestamo`, `fechaSolicitud`, `usuarioSolicita`, `usuarioAprueba`, `fechaPrestamo`, `fechaEntrega`, `estado`) VALUES
(90, '2017-05-18', 1, NULL, '2017-05-18', NULL, 1),
(91, '2017-05-18', 2, NULL, '2017-05-18', '2017-05-18', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `codRol` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`codRol`, `nombre`) VALUES
(1, 'Administrador'),
(2, 'Investigador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sanciones`
--

CREATE TABLE `sanciones` (
  `idSancion` int(11) NOT NULL,
  `tipoSancion` varchar(25) NOT NULL,
  `prestamo` int(11) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `usuarioSanciona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodispositivo`
--

CREATE TABLE `tipodispositivo` (
  `idTipoDisp` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipodispositivo`
--

INSERT INTO `tipodispositivo` (`idTipoDisp`, `nombre`) VALUES
(1, 'Tecnologia'),
(2, 'Desechable'),
(3, 'Instrumento de medicion/analisis'),
(4, 'Sensor'),
(5, 'Recipiente'),
(6, 'Otros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `clave` varchar(500) NOT NULL,
  `rol` int(11) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellidos`, `clave`, `rol`, `estado`) VALUES
(1, 'Sebastian', 'Montoya J.', 'fcea920f7412b5da7be0cf42b8c93759', 2, 1),
(2, 'Cesar', 'Muñoz Roldan', '1cca4d5636611e9d77c69132a7ada5fb', 2, 1),
(3, 'Elver', 'Suarez Alzate', 'b8ba10ba180e26dd1a38172b223a25c1', 2, 1),
(4, 'Carlos', 'Estrada Aguirre', '64eb4ddd1f146fb02b4da5548c9a288e', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `dispositivo`
--
ALTER TABLE `dispositivo`
  ADD PRIMARY KEY (`idDispositivo`),
  ADD KEY `tipo` (`tipo`);

--
-- Indices de la tabla `ejemplardispositivo`
--
ALTER TABLE `ejemplardispositivo`
  ADD PRIMARY KEY (`idEjemplar`),
  ADD KEY `dispositivo` (`dispositivo`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `estadodispositivo`
--
ALTER TABLE `estadodispositivo`
  ADD PRIMARY KEY (`codEstadoD`);

--
-- Indices de la tabla `estadoprestamo`
--
ALTER TABLE `estadoprestamo`
  ADD PRIMARY KEY (`codEstadoP`);

--
-- Indices de la tabla `estadosusuarios`
--
ALTER TABLE `estadosusuarios`
  ADD PRIMARY KEY (`codEstadoU`);

--
-- Indices de la tabla `itemsprestamo`
--
ALTER TABLE `itemsprestamo`
  ADD PRIMARY KEY (`idItem`),
  ADD KEY `prestamo` (`prestamo`),
  ADD KEY `ejemplar` (`ejemplar`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`idPrestamo`),
  ADD KEY `usuarioSolicita` (`usuarioSolicita`),
  ADD KEY `usuarioAprueba` (`usuarioAprueba`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`codRol`);

--
-- Indices de la tabla `sanciones`
--
ALTER TABLE `sanciones`
  ADD PRIMARY KEY (`idSancion`),
  ADD KEY `prestamo` (`prestamo`),
  ADD KEY `usuarioSanciona` (`usuarioSanciona`);

--
-- Indices de la tabla `tipodispositivo`
--
ALTER TABLE `tipodispositivo`
  ADD PRIMARY KEY (`idTipoDisp`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rol` (`rol`),
  ADD KEY `estado` (`estado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `dispositivo`
--
ALTER TABLE `dispositivo`
  MODIFY `idDispositivo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `ejemplardispositivo`
--
ALTER TABLE `ejemplardispositivo`
  MODIFY `idEjemplar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `itemsprestamo`
--
ALTER TABLE `itemsprestamo`
  MODIFY `idItem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `idPrestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;
--
-- AUTO_INCREMENT de la tabla `sanciones`
--
ALTER TABLE `sanciones`
  MODIFY `idSancion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `dispositivo`
--
ALTER TABLE `dispositivo`
  ADD CONSTRAINT `dispositivo_ibfk_1` FOREIGN KEY (`tipo`) REFERENCES `tipodispositivo` (`idTipoDisp`);

--
-- Filtros para la tabla `ejemplardispositivo`
--
ALTER TABLE `ejemplardispositivo`
  ADD CONSTRAINT `ejemplardispositivo_ibfk_1` FOREIGN KEY (`dispositivo`) REFERENCES `dispositivo` (`idDispositivo`),
  ADD CONSTRAINT `ejemplardispositivo_ibfk_2` FOREIGN KEY (`estado`) REFERENCES `estadodispositivo` (`codEstadoD`);

--
-- Filtros para la tabla `itemsprestamo`
--
ALTER TABLE `itemsprestamo`
  ADD CONSTRAINT `itemsprestamo_ibfk_1` FOREIGN KEY (`prestamo`) REFERENCES `prestamos` (`idPrestamo`),
  ADD CONSTRAINT `itemsprestamo_ibfk_2` FOREIGN KEY (`ejemplar`) REFERENCES `ejemplardispositivo` (`idEjemplar`);

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`usuarioSolicita`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`usuarioAprueba`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `prestamos_ibfk_3` FOREIGN KEY (`estado`) REFERENCES `estadoprestamo` (`codEstadoP`);

--
-- Filtros para la tabla `sanciones`
--
ALTER TABLE `sanciones`
  ADD CONSTRAINT `sanciones_ibfk_1` FOREIGN KEY (`prestamo`) REFERENCES `prestamos` (`idPrestamo`),
  ADD CONSTRAINT `sanciones_ibfk_2` FOREIGN KEY (`usuarioSanciona`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`rol`) REFERENCES `roles` (`codRol`),
  ADD CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`estado`) REFERENCES `estadosusuarios` (`codEstadoU`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
