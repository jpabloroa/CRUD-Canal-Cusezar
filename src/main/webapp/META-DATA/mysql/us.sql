CREATE DATABASE `CUSEZAR`;
USE `CUSEZAR`;
CREATE TABLE `clientes` (
    `fechaDeCreacion` DATE NOT NULL,
    `codigoConteo` INT NULL AUTO_INCREMENT PRIMARY KEY,
    `viable` BOOLEAN NULL DEFAULT TRUE,
    `nombre` VARCHAR(50) NOT NULL,
    `correo` VARCHAR(50) NULL,
    `celular` VARCHAR(50) NULL,
    `medioPublicitario` VARCHAR(50) NOT NULL,
    `zonaBusqueda` VARCHAR(50) NULL,
    `proyectoDeInteres` VARCHAR(50) NULL,
    `gestionDesdeSalaDeVentas` BOOLEAN NULL DEFAULT FALSE,
    `habeasData` BOOLEAN NULL DEFAULT FALSE,
    `fechaDeContacto` DATE NULL,
    `contactoEfectivo` BOOLEAN NULL DEFAULT FALSE,
    `fechaDeContactoEfectivo` DATE NULL,
    `proyectoCalificado` VARCHAR(50) NULL,
    `fechaVisitaAgendada` DATE NULL,
    `visitaEfectiva` BOOLEAN NULL DEFAULT FALSE,
    `fechaVisitaEfectiva` DATE NULL,
    `estado` VARCHAR(20) NULL,
    `fechaModificacionEstado` DATE NULL,
    `asignadoA` VARCHAR(50) NULL
) ENGINE = InnoDB CHARSET = utf8 COLLATE utf8_unicode_ci;