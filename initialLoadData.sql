-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: autofix
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `chargue_by_type`
--

LOCK TABLES `chargue_by_type` WRITE;
/*!40000 ALTER TABLE `chargue_by_type` DISABLE KEYS */;
INSERT INTO `chargue_by_type` VALUES (1,-1,-1,0,5000,0,0,0,'km',0,0),(2,-1,-1,0.03,12000,5001,0.05,0.03,'km',0.05,0.05),(3,-1,-1,0.07,25000,12001,0.09,0.07,'km',0.09,0.09),(4,-1,-1,0.12,40000,25001,0.12,0.12,'km',0.12,0.12),(5,-1,-1,0.2,-1,40001,0.2,0.2,'km',0.2,0.2),(6,5,0,0,-1,-1,0,0,'antiquity',0,0),(7,10,6,0.05,-1,-1,0.07,0.05,'antiquity',0.07,0.07),(8,15,11,0.09,-1,-1,0.11,0.09,'antiquity',0.11,0.11),(9,-1,16,0.15,-1,-1,0.2,0.15,'antiquity',0.2,0.2);
/*!40000 ALTER TABLE `chargue_by_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `quantity_discount`
--

LOCK TABLES `quantity_discount` WRITE;
/*!40000 ALTER TABLE `quantity_discount` DISABLE KEYS */;
INSERT INTO `quantity_discount` VALUES (1,0.07,0.08,0.05,0.1,2,1),(2,0.12,0.13,0.1,0.15,5,3),(3,0.17,0.18,0.15,0.2,9,6),(4,0.22,0.23,0.2,0.25,-1,10);
/*!40000 ALTER TABLE `quantity_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `repair`
--

LOCK TABLES `repair` WRITE;
/*!40000 ALTER TABLE `repair` DISABLE KEYS */;
INSERT INTO `repair` VALUES (1,'Incluye el reemplazo de pastillas de freno, discos, tambores, líneas de freno y reparación o reemplazo del cilindro maestro de frenos.','Reparaciones del Sistema de Frenos'),(2,'Reparación o reemplazo de radiadores, bombas de agua, termostatos y mangueras, así como la solución de problemas de sobrecalentamiento.','Servicio del Sistema de Refrigeración'),(3,'Desde reparaciones menores como el reemplazo de bujías y cables, hasta reparaciones mayores como la reconstrucción del motor o la reparación de la junta de la culata.','Reparaciones del Motor'),(4,'Incluyen la reparación o reemplazo de componentes de la transmisión manual o automática, cambios de líquido y solución de problemas de cambios de marcha.','Reparaciones de la Transmisión'),(5,'Solución de problemas y reparación de alternadores, arrancadores, baterías y sistemas de cableado, así como la reparación de componentes eléctricos como faros, intermitentes y sistemas de entretenimiento.','Reparación del Sistema Eléctrico'),(6,'Incluye el reemplazo del silenciador, tubos de escape, catalizador y la solución de problemas relacionados con las emisiones.','Reparaciones del Sistema de Escape'),(7,'Reparación de pinchazos, reemplazo de neumáticos, alineación y balanceo de ruedas.','Reparación de Neumáticos y Ruedas'),(8,'Reemplazo de amortiguadores, brazos de control, rótulas y reparación del sistema de dirección asistida.','Reparaciones de la Suspensión y la Dirección'),(9,'Incluye la recarga de refrigerante, reparación o reemplazo del compresor, y solución de problemas del sistema de calefacción.','Reparación del Sistema de Aire Acondicionado y Calefacción'),(10,'Limpieza o reemplazo de inyectores de combustible, reparación o reemplazo de la bomba de combustible y solución de problemas de suministro de combustible.','Reparaciones del Sistema de Combustible'),(11,'Reparación de pequeñas grietas en el parabrisas o reemplazo completo de parabrisas y ventanas dañadas.','Reparación y Reemplazo del Parabrisas y Cristales');
/*!40000 ALTER TABLE `repair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `repair_history`
--

LOCK TABLES `repair_history` WRITE;
/*!40000 ALTER TABLE `repair_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `repair_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `repair_price`
--

LOCK TABLES `repair_price` WRITE;
/*!40000 ALTER TABLE `repair_price` DISABLE KEYS */;
INSERT INTO `repair_price` VALUES (1,120000,220000,120000,180000,1),(2,130000,230000,130000,190000,2),(3,450000,800000,350000,700000,3),(4,210000,300000,210000,300000,4),(5,150000,250000,150000,200000,5),(6,120000,0,100000,450000,6),(7,100000,100000,100000,100000,7),(8,180000,250000,180000,210000,8),(9,150000,180000,150000,180000,9),(10,140000,0,130000,220000,10),(11,80000,80000,80000,80000,11);
/*!40000 ALTER TABLE `repair_price` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-25 19:42:01
