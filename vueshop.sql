-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: vueshop
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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `user_id` bigint NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorys`
--

DROP TABLE IF EXISTS `categorys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorys` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorys`
--

LOCK TABLES `categorys` WRITE;
/*!40000 ALTER TABLE `categorys` DISABLE KEYS */;
INSERT INTO `categorys` VALUES (1,'DELL','https://lapc.vn/wp-content/uploads/2021/06/logo-dell.jpg'),(2,'LENOVO','https://upload.wikimedia.org/wikipedia/commons/thumb/0/03/Lenovo_Global_Corporate_Logo.png/2560px-Lenovo_Global_Corporate_Logo.png'),(3,'ASUS','https://inkythuatso.com/uploads/thumbnails/800/2021/11/logo-asus-inkythuatso-2-01-26-09-21-11.jpg');
/*!40000 ALTER TABLE `categorys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` int NOT NULL,
  `quanty` int NOT NULL,
  `price` double NOT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (27,16,2,1,17000000,'2024-04-18 15:29:19'),(28,17,8,1,15900000,'2024-04-18 15:29:56'),(29,18,2,1,17000000,'2024-04-18 15:31:34');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `total_price` double NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (16,12,17000000,'0292837','8283719','jksihdaweqwe','2024-04-18 15:29:19'),(17,12,15900000,'0292832328','iopoeirwqniu','jihfiuchoehrw','2024-04-18 15:29:56'),(18,12,17000000,'092832837','hiudwqiuei','uiuhdqweqwe','2024-04-18 15:31:34');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `cate_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cate_id` (`cate_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`cate_id`) REFERENCES `categorys` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Laptop Inspiron 1212332 i7 - 1235U','CPU Intel Core i5-1235U Processor (12MB Cache, up to 4.4 GHz)\r\nRAM 16GB (2x8GB) DDR4 2666MHz (2x SO-DIMM socket, up to 16GB SDRAM)\r\nỔ cứng 512GB SSD NVMe PCIe (1 Slot)\r\nCard đồ họa Intel Iris Xe Graphics (with dual channel memory)','https://down-vn.img.susercontent.com/file/683f6e9f72eb92d61b4f1c0e19ca7390',87600000,1),(2,'Laptop 2020 Dell Latitude 5400 i5','Bộ vi xử lí: Core i5-8365U 1.6GHz vPro Ram :8GB DDR4 2400MHz Đĩa cứng :256GB SSD PCIe','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lq60n3gyvidu35',17000000,1),(3,'Laptop Dell Inspiron 3530','Laptop Dell Inspiron 3530 N5I5791W1 (Core i5-1335U | 16GB | 512GB | Intel UHD | 15.6 inch FHD 120Hz | Win 11 | Office | Đen)','https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lgn7xoq4pe7ma0',17900000,1),(4,'Laptop Giá Rẻ Dell Latitude 7390','Cấu hình nổi bật Bộ vi xử lí : i5 8250U 1.7Ghz with vPro Ram : 16GB ddr4 Đĩa cứng : 256GB SSD Màn hình : 13.3\" FHD IPS','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lqnc4jgq33yq1c',14000000,1),(5,'Laptop Dell Latitude 5420 14 Inch','Laptop Dell Latitude 5420 14 Inch 2021 (I5-113G7 I 8GB I 256GB I 14\" FHD) - Hàng Chính Hãng - BH 1 năm - Có Xuất VAT','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lq1nyejrdb6a65',23400000,1),(6,'Laptop Lenovo ThinkPad E14','CPU Intel Core i7-1255U 1.7GHz up to 4.7GHz 12MB RAM 8GB Onboard DDR4 3200MHz (1x SO-DIMM socket, up to 40GB SDRAM)','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lmczvtvz98f336',19600000,2),(7,'Laptop xách tay giá rẻ Thinkpad','CPU :Intel® Core™ i5-8350U CPU 8*1.8Ghz, (8M Cache, up to 4.0 GHz) Memory : 16 GB Hard disk : SSD 512 GB NVME','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lqq0zm0gun6ae1',12400000,2),(8,'Laptop Lenovo IdeaPad Slim 5','Laptop Lenovo IdeaPad 5 14IAH8 - 83BF002NVN là một sản phẩm laptop đang được ưa chuộng hiện nay.','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lq1lgoi05kuq78',15900000,2),(9,'Laptop Lenovo IdeaPad 3','CPU: Intel Core i3-1215U Màn hình: 14\" TN (1920 x 1080) RAM: 1 x 8GB Onboard DDR4 3200MHz','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lss5er2x5b4p0d',12400000,2),(10,'Laptop Lenovo LOQ 15IAX9','CPU: Intel Core i5-12450HX Màn hình: 15.6\" IPS (1920 x 1080),144Hz RAM: 1 x 8GB DDR5 4800MHz','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lss5um8qgd5lb5',18900000,2),(11,'Laptop ASUS VivoBook 15 OLED','CPU Intel Core i5-13500H 2.6GHz up to 4.7GHz 18MB RAM 16GB (8GB Onboard + 8GB) DDR4 3200MHz (1x SO-DIMM socket, up to 16GB SDRAM)Ổ cứng	512GB M.2 NVMe™ PCIe® 3.0 SSD (1 slot, support M.2 2280 PCIe 4.0x4)','https://down-vn.img.susercontent.com/file/vn-11134201-7qukw-leo4dc3ea8j75f',17100000,3),(12,'Laptop Asus Vivobook Go 14','CPU: AMD Ryzen 3 7320U Màn hình: 14\" (1920 x 1080) RAM: 8GB Onboard LPDDR5 5500MHz','https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lohyx24gxqd346',8900000,3),(13,'Laptop Asus X415EA-EK047T','CPU: Intel® Core™ i3-1115G4 (upto 4.10GHz, 6MB) RAM: 4GB DDR4 + 1slot Ổ cứng: 256GB M.2 NVMe™ PCIe® 3.0 SSD','https://down-vn.img.susercontent.com/file/46a61cf3a904af83c00e8942aed3e3b4',12000000,3),(14,'Laptop ASUS TUF Gaming A15','CPU	AMD Ryzen™ 5 7535HS 3.3GHz up to 4.55GHz 16MB RAM	16GB (8x2) DDR5 4800MHz (2x SO-DIMM socket, up to 32GB SDRAM)','https://down-vn.img.susercontent.com/file/sg-11134201-7rcc3-lsjtucqckfuf98',23400000,3),(15,'Laptop Asus Zenbook UX431','CPU: Core i7-8565u max turbo 4.90Ghz Max turbo, 4 nhân 8 luồng thế hệ 8( chọn loại cpu chọn cấu hình khi mua) Bộ nhớ ram: 16gb DDR4 buss 2400Mhz','https://down-vn.img.susercontent.com/file/16f05318012eaea83c7c2de266d65f87',13800000,3),(20,'Laptop Dell XPS 15','Chiếc laptop đầu tiên Phong Vũ muốn giới thiệu là Dell XPS 15.','https://phongvu.vn/cong-nghe/wp-content/uploads/sites/2/2020/09/Dell-XPS-15-1024x572.jpg',21000000,1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES (7,'b740bf92-4376-4bb4-a95b-cbfbe77e6595',11,'2024-04-16 13:43:24'),(10,'8bf20a46-e3a9-419f-8d3a-6b071aba4ebc',14,'2024-04-16 14:17:12'),(12,'336ad0f6-5214-4a73-a335-ebd25b7bd2cb',15,'2024-04-16 15:42:43');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'test1@gmail.com','test1','jkdoasi','93279E3308BDBBEED946FC965017F67A','ROLE_USER'),(12,'user@gmail.com','user','user','93279E3308BDBBEED946FC965017F67A','ROLE_USER'),(13,'admin@gmail.com','admin','admin','93279E3308BDBBEED946FC965017F67A','ROLE_ADMIN'),(14,'test2@gmail.com','test2','tets','93279E3308BDBBEED946FC965017F67A','ROLE_ADMIN'),(15,'remove@gmail.com','remove','axzzdd','93279E3308BDBBEED946FC965017F67A','ROLE_USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-21 15:52:47
