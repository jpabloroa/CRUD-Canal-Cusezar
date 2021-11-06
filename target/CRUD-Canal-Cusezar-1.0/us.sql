CREATE DATABASE `CUSEZAR`;
USE `CUSEZAR`;
CREATE TABLE `clientes` (
    `diaDeCreacion` INT(2) NOT NULL,
    `mesDeCreacion` INT(2) NOT NULL,
    `agnoDeCreacion` INT(4) NOT NULL,
    `codigoConteo` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `viable` BOOLEAN NULL DEFAULT TRUE,
    `nombre` VARCHAR(50) NOT NULL,
    `correo` VARCHAR(50) NULL,
    `celular` VARCHAR(50) NULL,
    `medioPublicitario` VARCHAR(50) NOT NULL,
    `zonaBusqueda` VARCHAR(50) NULL,
    `proyectoDeInteres` VARCHAR(50) NULL,
    `gestionDesdeSalaDeVentas` BOOLEAN NULL DEFAULT FALSE,
    `habeasData` BOOLEAN NULL DEFAULT FALSE,
    `fechaUltimoContacto` VARCHAR(30) NULL,
    `contactoEfectivo` BOOLEAN NULL DEFAULT FALSE,
    `proyectoCalificado` VARCHAR(50) NULL,
    `diaVisita` INT(2) NOT NULL,
    `mesVisita` INT(2) NOT NULL,
    `agnoVisita` INT(4) NOT NULL,
    `visitaEfectiva` BOOLEAN NULL DEFAULT FALSE,
    `estado` VARCHAR(20) NULL
) ENGINE = InnoDB CHARSET = utf8 COLLATE utf8_unicode_ci;