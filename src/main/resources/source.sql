-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.5.12-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para agenda
DROP DATABASE IF EXISTS `agenda`;
CREATE DATABASE IF NOT EXISTS `agenda` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `agenda`;

-- Volcando estructura para tabla agenda.phone_type
DROP TABLE IF EXISTS `phone_type`;
CREATE TABLE IF NOT EXISTS `phone_type` (
  `phone_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`phone_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla agenda.phone_type: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `phone_type` DISABLE KEYS */;
INSERT INTO `phone_type` (`phone_type_id`, `name`, `status`) VALUES
	(1, 'Teléfono casa', 'ACTIVO'),
	(2, 'Teléfono trabajo', 'ACTIVO'),
	(3, 'Teléfono celular', 'ACTIVO');
/*!40000 ALTER TABLE `phone_type` ENABLE KEYS */;

-- Volcando estructura para tabla agenda.contact_type
DROP TABLE IF EXISTS `contact_type`;
CREATE TABLE IF NOT EXISTS `contact_type` (
  `contact_type_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`contact_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla agenda.contact_type: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `contact_type` DISABLE KEYS */;
INSERT INTO `contact_type` (`contact_type_id`, `name`, `status`) VALUES
	(1, 'Familiar', 'ACTIVO'),
	(2, 'Escolar', 'ACTIVO'),
	(3, 'Laboral', 'ACTIVO');
/*!40000 ALTER TABLE `contact_type` ENABLE KEYS */;

-- Volcando estructura para tabla agenda.contact
DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `contact_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `age` tinyint NOT NULL DEFAULT 0,
  `address` text NOT NULL,
  `contact_type_id` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`contact_id`),
  KEY `fk_contact_type` (`contact_type_id`),
  CONSTRAINT `fk_contact_type` FOREIGN KEY (`contact_type_id`) REFERENCES `contact_type` (`contact_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla agenda.contact: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`contact_id`, `name`, `last_name`, `age`, `address`, `contact_type_id`) VALUES
  (1, 'Victor', 'Garcia', 36, 'Av. Siempre Viva 123', 3),
  (2, 'Sandra', 'Sanchez', 34, 'Camino viejo a Rodeo 234', 2),
  (3, 'Ana', 'Serna', 50, 'Carr. Mich a+t 345', 1);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;

-- Volcando estructura para tabla agenda.means_contact
DROP TABLE IF EXISTS `means_contact`;
CREATE TABLE IF NOT EXISTS `means_contact` (
  `means_contact_id` int NOT NULL AUTO_INCREMENT,
  `value` varchar(300) NOT NULL,
  `contact_id` int NOT NULL,
  `phone_type_id` int NOT NULL,
  PRIMARY KEY (`means_contact_id`),
  KEY `fk_phone_type` (`phone_type_id`),
  KEY `fk_contact` (`contact_id`),
  CONSTRAINT `fk_contact` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`contact_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_phone_type` FOREIGN KEY (`phone_type_id`) REFERENCES `phone_type` (`phone_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla agenda.means_contact: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `means_contact` DISABLE KEYS */;
INSERT INTO `means_contact` (`means_contact_id`, `value`, `contact_id`, `phone_type_id`) VALUES
  (1, 'A', 3, 3),
  (2, 'B', 1, 2),
  (3, 'C', 2, 1);
/*!40000 ALTER TABLE `means_contact` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;