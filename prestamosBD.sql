SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- --------------------------------------------------------
-- Creacion de la base de datos: prestamosBD
--

CREATE DATABASE `prestamosBD`; 
USE `prestamosBD`;

-- --------------------------------------------------------
-- Creacion de la tabla: Roles
--
CREATE TABLE IF NOT EXISTS `Roles` (`codRol` int(11) NOT NULL, `nombre` varchar(30) NOT NULL, PRIMARY KEY (`codRol`));

-- --------------------------------------------------------
-- Creacion de la tabla: EstadosUsuarios
--
CREATE TABLE IF NOT EXISTS `EstadosUsuarios` (`codEstadoU` int(11) NOT NULL, `nombre` varchar(30) NOT NULL, `descripcion` varchar(500) DEFAULT NULL, PRIMARY KEY (`codEstadoU`));

-- --------------------------------------------------------
-- Creacion de la tabla: EstadoPrestamo
--
CREATE TABLE IF NOT EXISTS `EstadoPrestamo` (`codEstadoP` int(11) NOT NULL, `nombre` varchar(30) NOT NULL, `descripcion` varchar(500) DEFAULT NULL, PRIMARY KEY (`codEstadoP`));

-- --------------------------------------------------------
-- Creacion de la tabla: EstadoDispositivo
--
CREATE TABLE IF NOT EXISTS `EstadoDispositivo` (`codEstadoD` int(11) NOT NULL, `nombre` varchar(30) NOT NULL, `descripcion` varchar(500) DEFAULT NULL, PRIMARY KEY (`codEstadoD`));

-- --------------------------------------------------------
-- Creacion de la tabla: TipoDispositivo
--
CREATE TABLE IF NOT EXISTS `TipoDispositivo` (`idTipoDisp` int(11) NOT NULL, `nombre` varchar(100) NOT NULL, PRIMARY KEY (`idTipoDisp`));

-- --------------------------------------------------------
-- Creacion de la tabla: Dispositivo
--
CREATE TABLE IF NOT EXISTS `Dispositivo` (`idDispositivo` int(11) NOT NULL AUTO_INCREMENT, `nombre` varchar(100) NOT NULL, `descripcion` varchar(500) NOT NULL, `tipo` int(11) NOT NULL, PRIMARY KEY (`idDispositivo`), FOREIGN KEY (`tipo`) references `TipoDispositivo`(`idTipoDisp`));

-- --------------------------------------------------------
-- Creacion de la tabla: EjemplarDispositivo
--
CREATE TABLE IF NOT EXISTS `EjemplarDispositivo` (`idEjemplar` int(11) NOT NULL AUTO_INCREMENT, `dispositivo` int(11) NOT NULL, `estado` int(11) NOT NULL, PRIMARY KEY (`idEjemplar`), FOREIGN KEY (`dispositivo`) references `Dispositivo`(`idDispositivo`), FOREIGN KEY (`estado`) references `EstadoDispositivo`(`codEstadoD`));

-- --------------------------------------------------------
-- Creacion de la tabla: Usuarios
--
CREATE TABLE IF NOT EXISTS `Usuarios` (`id` int(11) NOT NULL AUTO_INCREMENT, `nombre` varchar(50) NOT NULL, `apellidos` varchar(50) NOT NULL, `clave` varchar(500) NOT NULL, `rol` int(11) NOT NULL, `estado` int(11) NOT NULL, PRIMARY KEY (`id`), FOREIGN KEY (`rol`) references Roles(`codRol`), FOREIGN KEY (`estado`) references EstadosUsuarios(`codEstadoU`));

-- --------------------------------------------------------
-- Creacion de la tabla: Prestamos
--
CREATE TABLE IF NOT EXISTS `Prestamos` (`idPrestamo` int(11) NOT NULL AUTO_INCREMENT, `fechaSolicitud` DATE NOT NULL, `usuarioSolicita` int(11) NOT NULL, `usuarioAprueba` int(11) NOT NULL, `fechaPrestamo` DATE NOT NULL, `fechaEntrega` DATE DEFAULT NULL, `estado` int(11) NOT NULL, PRIMARY KEY (`idPrestamo`), FOREIGN KEY (`usuarioSolicita`) references Usuarios(`id`), FOREIGN KEY (`usuarioAprueba`) references Usuarios(`id`), FOREIGN KEY (`estado`) references EstadoPrestamo(`codEstadoP`));

-- --------------------------------------------------------
-- Creacion de la tabla: ItemsPrestamo
--
CREATE TABLE IF NOT EXISTS `ItemsPrestamo` (`idItem` int(11) NOT NULL AUTO_INCREMENT, `prestamo` int(11) NOT NULL, `ejemplar` int(11) NOT NULL, PRIMARY KEY (`idItem`), FOREIGN KEY (`prestamo`) references `Prestamos`(`idPrestamo`), FOREIGN KEY (`ejemplar`) references `EjemplarDispositivo`(`idEjemplar`));

-- --------------------------------------------------------
-- Creacion de la tabla: Sanciones
--
CREATE TABLE IF NOT EXISTS `Sanciones` (`idSancion` int(11) NOT NULL AUTO_INCREMENT, `tipoSancion` varchar(25) NOT NULL, `prestamo` int(11) NOT NULL, `descripcion` varchar(200) DEFAULT NULL, `usuarioSanciona` int(11) NOT NULL, PRIMARY KEY (`idSancion`), FOREIGN KEY (`prestamo`) references `Prestamos`(`idPrestamo`), FOREIGN KEY (`usuarioSanciona`) references `Usuarios`(`id`));

-- --------------------------------------------------------
-- Inserccion de datos de prueba
-- 
INSERT INTO `Roles`(`codRol`,`nombre`) VALUES
(1, 'Administrador'),
(2, 'Investigador');

INSERT INTO `EstadosUsuarios`(`codEstadoU`,`nombre`,`descripcion`) VALUES
(1, 'Habilitado', 'El usuario esta libre de sanciones y autorizado en el sistema.'),
(2, 'Sancionado', 'El usuario tiene al menos una sancion vigente por lo cual tiene acceso restringindo al sistema y sus funciones.'),
(3, 'Retirado', 'El usuario ya no pertenece a la organizacion');

INSERT INTO `EstadoPrestamo`(`codEstadoP`,`nombre`,`descripcion`) VALUES
(1, 'Pendiente', 'El prestamo esta a la espera de ser aprobada o rechazada por un administrador.'),
(2, 'Rechazada', 'El prestamo de los elementos fue rechazado.'),
(3, 'Vigente', 'El prestamo fue aprobado y esta habilitado para el investigador.'),
(4, 'Caducada', 'El prestamo ya esta cumplido.');

INSERT INTO `EstadoDispositivo`(`codEstadoD`,`nombre`,`descripcion`) VALUES
(1, 'Disponible', 'El elemento se encuentra disponible para cualquier laboratorio.'),
(2, 'En reparacion', 'El elemento se encuentra en algun tipo de mantenimiento correctivo o preventivo.'),
(3, 'Dado de baja', NULL);

INSERT INTO `TipoDispositivo`(`idTipoDisp`,`nombre`) VALUES
(1, 'Tecnologia'),
(2, 'Desechable'),
(3, 'Instrumento de medicion/analisis'),
(4, 'Sensor'),
(5, 'Recipiente'),
(6, 'Otros');

INSERT INTO `Dispositivo`(`nombre`,`descripcion`,`tipo`) VALUES
('Portatil','Asus Gl552vw 1tb / 4gb / Win 10 /dvd / 15.6', 1),
('VideoBeam','CASIO XJ-V1 / 2700 lumenes',1),
('Microscopio','NIKON E200 LED',3),
('Tableta','Lenovo IdeaTab A2109',1),
('Barometro','Taylor 1735',3),
('Termometro infrarojo','Fluke 566 - Escala -40 a +650°C',4);

INSERT INTO `EjemplarDispositivo`(`dispositivo`,`estado`) VALUES
(1,1),
(1,2),
(1,1),
(2,1),
(2,1),
(3,1),
(3,3),
(3,1),
(3,1),
(4,1),
(5,1),
(5,2),
(6,2);

INSERT INTO `Usuarios`(`nombre`,`apellidos`,`clave`,`rol`,`estado`) VALUES
('Sebastian', 'Montoya J.', 'fcea920f7412b5da7be0cf42b8c93759', 2, 1),
('Cesar', 'Muñoz Roldan', '1cca4d5636611e9d77c69132a7ada5fb', 2, 2),
('Elver', 'Suarez Alzate', 'b8ba10ba180e26dd1a38172b223a25c1', 1, 1),
('Carlos', 'Estrada Aguirre', '64eb4ddd1f146fb02b4da5548c9a288e', 1, 3);

INSERT INTO `Prestamos`(`fechaSolicitud`,`usuarioSolicita`,`usuarioAprueba`,`fechaPrestamo`,`fechaEntrega`,`estado`) VALUES
('2017-05-02 10:40:15', 1, 3, '2017-05-16 8:00:00', NULL, 1),
('2017-05-02 17:35:02', 2, 3, '2017-05-29 11:30:00', '2017-05-30 6:00:00', 4);

INSERT INTO `ItemsPrestamo`(`prestamo`,`ejemplar`) VALUES
(1,3),
(1,4),
(1,11),
(2,1),
(2,2);

INSERT INTO `Sanciones`(`tipoSancion`,`prestamo`,`descripcion`,`usuarioSanciona`) VALUES
('Mora en entrega', 2, 'Entrego al dia siguiente.', 2);
