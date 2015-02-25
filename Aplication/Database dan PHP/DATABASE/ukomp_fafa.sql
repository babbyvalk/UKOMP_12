-- MySQL dump 10.13  Distrib 5.6.21, for Win32 (x86)
--
-- Host: localhost    Database: b_aqua
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `kalender`
--

DROP TABLE IF EXISTS `kalender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kalender` (
  `tgl` varchar(10) NOT NULL,
  `bulan` varchar(50) NOT NULL,
  `tahun` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kalender`
--

LOCK TABLES `kalender` WRITE;
/*!40000 ALTER TABLE `kalender` DISABLE KEYS */;
INSERT INTO `kalender` VALUES ('01','Januari','2014'),('02','Februari','2015'),('03','Maret','2016'),('04','April','2017'),('05','Mei','2018'),('06','Juni','2019'),('07','July','2020'),('08','Agustus','2021'),('09','September','2022');
/*!40000 ALTER TABLE `kalender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kuantitas`
--

DROP TABLE IF EXISTS `kuantitas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kuantitas` (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `qty` varchar(10) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kuantitas`
--

LOCK TABLES `kuantitas` WRITE;
/*!40000 ALTER TABLE `kuantitas` DISABLE KEYS */;
INSERT INTO `kuantitas` VALUES (1,'1 PCS'),(2,'2 PCS'),(3,'3 PCS'),(4,'4 PCS'),(5,'5 PCS'),(6,'6 PCS'),(7,'7 PCS'),(8,'8 PCS'),(9,'9 PCS'),(10,'10 PCS'),(11,'11 PCS'),(12,'12 PCS'),(13,'13 PCS'),(14,'14 PCS'),(15,'15 PCS'),(16,'16 PCS'),(17,'17 PCS'),(18,'18 PCS'),(19,'19 PCS'),(20,'20 PCS');
/*!40000 ALTER TABLE `kuantitas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_aqua`
--

DROP TABLE IF EXISTS `list_aqua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_aqua` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nama_barang` varchar(200) NOT NULL,
  `harga_barang` varchar(100) NOT NULL,
  `stock_barang` varchar(100) NOT NULL,
  `stock` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_aqua`
--

LOCK TABLES `list_aqua` WRITE;
/*!40000 ALTER TABLE `list_aqua` DISABLE KEYS */;
INSERT INTO `list_aqua` VALUES (1,'Aqua Galon 15 Liter','17000','0','10 PCS'),(2,'Ale - Ale 1 kardus','15000','12','10 PCS'),(3,'Frutang 1 kerdus','17000','10','10 PCS'),(4,'Teh Botol 1 Rak','20000','12','10 PCS'),(5,'Arinda 1 kerdus','15000','20','10 PCS'),(6,'lejing','20000','10','10 PCS');
/*!40000 ALTER TABLE `list_aqua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penjualan`
--

DROP TABLE IF EXISTS `penjualan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `penjualan` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nama_barang` varchar(100) NOT NULL,
  `harga_barang` varchar(100) NOT NULL,
  `qty` varchar(10) NOT NULL,
  `total_harga` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jalan` varchar(100) NOT NULL,
  `rt` varchar(10) NOT NULL,
  `rw` varchar(10) NOT NULL,
  `no_rumah` varchar(10) NOT NULL,
  `tgl` varchar(10) NOT NULL,
  `bln` varchar(10) NOT NULL,
  `tahun` varchar(10) NOT NULL,
  `waktu` time DEFAULT NULL,
  `keterangan` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penjualan`
--

LOCK TABLES `penjualan` WRITE;
/*!40000 ALTER TABLE `penjualan` DISABLE KEYS */;
INSERT INTO `penjualan` VALUES (2,'Aqua Galon 15 Liter','17000','1','17000','Apria','bt','05','05','32','12','Januari','2015',NULL,'KIRIM'),(5,'Aqua Galon 15 Liter','17000','2','34000','Fafa','batu ampar 7','05','05','32','17','Februari','2015','05:44:05','KIRIM'),(6,'Teh Botol 1 Rak','20000','1','20000','Reza','kalibata','07','07','22','17','Februari','2015','09:58:15','PROSES'),(7,'Aqua Galon 15 Liter','17000','2','34000','anas','sini','03','03','22','17','Februari','2015','09:59:34','PROSES'),(8,'Aqua Galon 15 Liter','17000','3','51000','ar','ra','3','4','5','17','Februari','2015','10:09:16','PROSES'),(9,'Aqua Galon 15 Liter','17000','2','34000','q','w','4','4','21','17','Februari','2015','10:18:40','PROSES'),(10,'Aqua Galon 15 Liter','17000','2','34000','rara','riri','44','55','44','17','Februari','2015','10:26:13','PROSES'),(11,'Aqua Galon 15 Liter','17000','2','34000','po','so','3','4','4','17','Februari','2015','10:28:26','PROSES'),(12,'Aqua Galon 15 Liter','17000','3','51000','rr','fg','3','5','6','17','Februari','2015','10:30:50','PROSES'),(14,'Aqua Galon 15 Liter','17000','2','34000','faaaa','qqq','11','222','33','17','Februari','2015','13:18:12','PROSES'),(15,'Aqua Galon 15 Liter','17000','2','34000','fafa','Batu Ampar','05','05','32','17','Februari','2015','13:28:22','KIRIM'),(16,'Aqua Galon 15 Liter','17000','2','34000','Fafa','Batu ampar','5','5','5','20','Februari','2015','13:38:03','KIRIM'),(17,'Aqua Galon 15 Liter','17000','1','17000','fafa','hln','2','2','2','20','Februari','2015','13:49:12','KIRIM'),(18,'Aqua Galon 15 Liter','17000','1','17000','faa','jl','2','2','22','23','Februari','2015','13:56:14','KIRIM');
/*!40000 ALTER TABLE `penjualan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waktu`
--

DROP TABLE IF EXISTS `waktu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waktu` (
  `id` int(5) NOT NULL,
  `nama` varchar(10) NOT NULL,
  `wkt` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waktu`
--

LOCK TABLES `waktu` WRITE;
/*!40000 ALTER TABLE `waktu` DISABLE KEYS */;
INSERT INTO `waktu` VALUES (2,'d',NULL),(23,'d','00:00:00'),(3,'sd','00:00:00'),(3,'sd','14:15:01'),(4,'s','19:37:09');
/*!40000 ALTER TABLE `waktu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-25 13:14:39
