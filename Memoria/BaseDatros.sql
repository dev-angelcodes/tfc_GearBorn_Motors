-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: tfc_bd_gearborn
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(75) DEFAULT NULL,
  `contrasena` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `dni_nif` varchar(9) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(75) NOT NULL,
  `numero_telefono` int NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `UK1c96wv36rk2hwui7qhjks3mvg` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Gómez Sánchez','9b263a2dd18b174501b407089d6b72d8','Calle Falsa 123, Madrid','12345678A','laura@example.com','Laura',612345678),(2,'Vázquez','bbf2dead374654cbb32a917afd236656','Calle Falsa 123, Madrid','12345678B','Andrea@gmail.com','Andrea',612335678),(3,'Vazquez','bbf2dead374654cbb32a917afd236656','','12343165B','AndreaVazquez@gmail.com','Andrea',676143298),(4,'fernandez blanco','bbf2dead374654cbb32a917afd236656','Direccion, ejemplo 3','54703005N','angelfernandez@gmail.com','Angel',689704681),(5,'apellido1 apellido2','d40dc261069d6ffe494cdd8e582243e6','Calle Ejemplo 1','11111111A','ejemplo@ejemplo.com','Ejemplo',111111111);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `id_empleado` int NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(75) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fecha_contrato` datetime NOT NULL,
  `fecha_nacimiento` datetime NOT NULL,
  `nombre` varchar(75) NOT NULL,
  `numero_telefono` int NOT NULL,
  `suspendido` bit(1) NOT NULL,
  `tipo_empleado` varchar(25) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `UKgdkxcplgdjv6ny0g0vu8f8dcm` (`dni`),
  UNIQUE KEY `UK6fdpo2x5rmegfbngre7xb3yoh` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,'Fernandez Blanco','bbf2dead374654cbb32a917afd236656','54703005N','angelGearBorn@gmail.com','2024-04-15 00:00:00','2005-12-10 00:00:00','Angel',689704681,_binary '\0','Administrador'),(2,'Martínez Ruiz','423236c9026d31e908df8120e19e60cf','98765432B','nuevoempleado@example.com','2024-04-15 00:00:00','1992-08-10 00:00:00','Lucía',699887766,_binary '','Administrador'),(3,'Castro','ef44da5d3102b0f8463c974ceefa7ac2','12345678A','adrian@gmail.com','2025-04-28 00:00:00','2003-02-12 00:00:00','Adrian',666999666,_binary '\0','Comercial de ventas'),(7,'Sanchez','c2e22dc705f2833ab3a2a1aa418d133d','23456789B','Jacobo@gmail.com','2025-05-19 00:00:00','2005-04-10 00:00:00','jacobo',444523444,_binary '\0','Comercial de ventas'),(8,'Vazquez','e3830a63d0bd41e56cce0f28088afe5d','12457891V','andrea@gmail.com','2025-06-02 00:00:00','2005-11-14 00:00:00','Andrea',919272346,_binary '','Comercial de ventas');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gastos`
--

DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gastos` (
  `id_gasto` int NOT NULL AUTO_INCREMENT,
  `compra_vehiculo` bit(1) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `importe` decimal(9,2) NOT NULL,
  `nombre_proveedor` varchar(50) NOT NULL,
  `id_empleado` int NOT NULL,
  `id_vehiculo` int NOT NULL,
  PRIMARY KEY (`id_gasto`),
  KEY `FKsoed3h3vvcrqa0g6519y9cfao` (`id_empleado`),
  KEY `FKfvaavdw04ac35wx3bwp1w2mhx` (`id_vehiculo`),
  CONSTRAINT `FKfvaavdw04ac35wx3bwp1w2mhx` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`),
  CONSTRAINT `FKsoed3h3vvcrqa0g6519y9cfao` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gastos`
--

LOCK TABLES `gastos` WRITE;
/*!40000 ALTER TABLE `gastos` DISABLE KEYS */;
INSERT INTO `gastos` VALUES (1,_binary '','Precio de compra del vehiculo: 8500.0€ \nVendedor: Juan Pérez\nFecha de compra: 2025-04-30\nVehiculo: 2\nEmpleado: 1','2025-04-30 00:00:00',8500.00,'Juan Pérez',1,2),(2,_binary '','Precio de compra del vehiculo: 25000.0€ \nVendedor: Angel Fernandez\nFecha de compra: 2025-04-30\nVehiculo: 5\nEmpleado: 1','2025-04-30 00:00:00',25000.00,'Angel Fernandez',1,5),(3,_binary '','Precio de compra del vehiculo: 11900.0€ \nVendedor: Taller Hermanos Soto\nFecha de compra: 2025-05-02\nVehiculo: 6\nEmpleado: 1','2025-05-02 00:00:00',11900.00,'Taller Hermanos Soto',1,6),(4,_binary '','Precio de compra del vehiculo: 6850.0€ \nVendedor: Motostreet Madrid\nFecha de compra: 2025-05-02\nVehiculo: 7\nEmpleado: 2','2025-05-02 00:00:00',6850.00,'Motostreet Madrid',2,7),(5,_binary '','Precio de compra del vehiculo: 50000.0€ \nVendedor: coches.net\nFecha de compra: 2025-05-05\nVehiculo: 8\nEmpleado: 1','2025-05-05 00:00:00',50000.00,'coches.net',1,8),(6,_binary '','Precio de compra del vehiculo: 7800.0€ \nVendedor: Motos JP Import\nFecha de compra: 2025-05-06\nVehiculo: 9\nEmpleado: 1','2025-05-06 00:00:00',7800.00,'Motos JP Import',1,9),(7,_binary '','Precio de compra del vehiculo: 8250.0€ \nVendedor: Motos Extremadura\nFecha de compra: 2025-05-06\nVehiculo: 10\nEmpleado: 1','2025-05-06 00:00:00',8250.00,'Motos Extremadura',1,10),(8,_binary '','Precio de compra del vehiculo: 12500.0€ \nVendedor: Angel Fernandez\nFecha de compra: 2025-05-29\nVehiculo: 11\nEmpleado: 1','2025-05-29 00:00:00',12500.00,'Angel Fernandez',1,11),(11,_binary '\0','cambio neumáticos','2025-06-02 00:00:00',650.00,'Angel Talleres',1,8),(12,_binary '\0','Revisión completa para ITV','2025-06-02 00:00:00',1200.00,'Adrián talleres',1,5),(13,_binary '\0','Nuevos retrovisores','2025-06-02 00:00:00',500.00,'Ruben Talleres',1,8);
/*!40000 ALTER TABLE `gastos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculos` (
  `id_vehiculo` int NOT NULL AUTO_INCREMENT,
  `anio` int NOT NULL,
  `color` varchar(255) NOT NULL,
  `estado` varchar(25) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  `km` double NOT NULL,
  `marca` varchar(50) NOT NULL,
  `matricula` varchar(7) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `tipo_vehiculo` varchar(7) NOT NULL,
  `tipo_cambio` varchar(25) NOT NULL,
  `tipo_combustible` varchar(25) NOT NULL,
  PRIMARY KEY (`id_vehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculos`
--

LOCK TABLES `vehiculos` WRITE;
/*!40000 ALTER TABLE `vehiculos` DISABLE KEYS */;
INSERT INTO `vehiculos` VALUES (1,2020,'Azul','Vendido','toyota_corolla_2020.png',15000.5,'Toyota','1234ABC','Corolla','Coche','Automático','Gasolina'),(2,2008,'0x80801aff','Reservado','5051FNP-Toyota.png',128000,'Toyota','5051FNP','Corolla Sedan','Coche','Manual','Diesel'),(3,2020,'0xccccccff','Disponible','124CBA-BNW.png',200000,'BMW','124CBA','M4 competition','Coche','Manual','Gasolina'),(5,2015,'0x000000ff','Disponible','3456-Audi.png',250000,'Audi','3456','A5','Coche','Manual','Diesel'),(6,2019,'0x808080ff','Vendido','2057LCN-Seat.png',64500,'Seat','2057LCN','Leon','Coche','Manual','Diesel'),(7,2021,'0x000000ff','Vendido','2940LBN-Yamaha.png',18300,'Yamaha','2940LBN','MT-07','Moto','Manual','Gasolina'),(8,2022,'0xff0000ff','Disponible','1234LMT-Toyota.png',12500,'Toyota','1234LMT','Supra Mk5','Coche','Automático','Gasolina'),(9,2023,'0x0000ffff','Disponible','4567LMP-Yamaha.png',3200,'Yamaha','4567LMP','MT-07 SP','Moto','Manual','Gasolina'),(10,2022,'0x008000ff','Disponible','9823KWT-Kawasaki.png',5400,'Kawasaki','9823KWT','Ninja 650','Moto','Manual','Gasolina'),(11,2018,'0xb31a1aff','Vendido','1234CBA-Toyota.png',85000,'Toyota','1234CBA','Corolla Sedán','Coche','Manual','Gasolina');
/*!40000 ALTER TABLE `vehiculos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `id_venta` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `garantia` datetime NOT NULL,
  `importe` decimal(9,2) NOT NULL,
  `id_cliente` int NOT NULL,
  `id_empleado` int NOT NULL,
  `id_vehiculo` int NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `FKleerof1mym3gc1ah8hsarel3f` (`id_cliente`),
  KEY `FKdujgs0r79h0u5qisoskj3epx2` (`id_empleado`),
  KEY `FKmkgs56jf6k96uxeao6y14yp47` (`id_vehiculo`),
  CONSTRAINT `FKdujgs0r79h0u5qisoskj3epx2` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`),
  CONSTRAINT `FKleerof1mym3gc1ah8hsarel3f` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `FKmkgs56jf6k96uxeao6y14yp47` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (4,'2025-05-27 12:30:00','2027-05-27 12:30:00',25500.75,4,3,1),(5,'2025-05-29 06:40:17','2027-05-29 06:40:17',8905.00,4,7,7),(6,'2025-05-29 09:15:42','2027-05-29 09:15:42',15625.00,4,3,11),(7,'2025-06-02 20:25:57','2027-06-02 20:25:57',14875.00,4,7,6);
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-08 19:55:12
