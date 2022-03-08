/*
SQLyog v10.2 
MySQL - 5.5.41 : Database - book2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `book2`;

/*Table structure for table `admininfo` */

DROP TABLE IF EXISTS `admininfo`;

CREATE TABLE `admininfo` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员用户编号（主键）',
  `adminName` varchar(20) NOT NULL COMMENT '管理员用户名称',
  `adminPwd` varchar(20) NOT NULL COMMENT '管理员用户密码',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

/*Data for the table `admininfo` */

insert  into `admininfo`(`adminId`,`adminName`,`adminPwd`) values (1001,'张三','123456'),(1002,'admin','123456'),(1003,'abc','123456');

/*Table structure for table `bookinformation` */

DROP TABLE IF EXISTS `bookinformation`;

CREATE TABLE `bookinformation` (
  `ISBN` char(10) NOT NULL,
  `typeid` int(11) DEFAULT NULL,
  `bookname` varchar(30) DEFAULT NULL,
  `author` varchar(30) DEFAULT NULL,
  `publish` varchar(30) DEFAULT NULL,
  `publishdate` date DEFAULT NULL,
  `publishtime` int(11) DEFAULT NULL,
  `unitprice` double DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `it_bookInformation_booktype` (`typeid`),
  CONSTRAINT `it_bookInformation_booktype` FOREIGN KEY (`typeid`) REFERENCES `booktype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bookinformation` */

insert  into `bookinformation`(`ISBN`,`typeid`,`bookname`,`author`,`publish`,`publishdate`,`publishtime`,`unitprice`) values ('isbn2391',2,'熊出没','制作住','种花家传视出版社','2022-01-20',3,9.02);

/*Table structure for table `booktype` */

DROP TABLE IF EXISTS `booktype`;

CREATE TABLE `booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `booktype` */

insert  into `booktype`(`id`,`typename`) values (1,'文学'),(2,'美工'),(3,'农业'),(4,'建筑'),(5,'美工');

/*Table structure for table `borrowbook` */

DROP TABLE IF EXISTS `borrowbook`;

CREATE TABLE `borrowbook` (
  `readerid` char(8) DEFAULT NULL,
  `ISBN` char(10) DEFAULT NULL,
  `borrowdate` date DEFAULT NULL,
  `returndate` date DEFAULT NULL,
  `fine` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `borrowbook` */

insert  into `borrowbook`(`readerid`,`ISBN`,`borrowdate`,`returndate`,`fine`) values ('222','isbn2391','2022-03-08',NULL,0),('222','isbn2391','2022-03-08',NULL,0);

/*Table structure for table `loginlog` */

DROP TABLE IF EXISTS `loginlog`;

CREATE TABLE `loginlog` (
  `llogId` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志 编号',
  `llogTime` varchar(20) NOT NULL COMMENT '登录日志 时间',
  `llogDetails` varchar(30) NOT NULL COMMENT '登录日志 内容',
  PRIMARY KEY (`llogId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `loginlog` */

insert  into `loginlog`(`llogId`,`llogTime`,`llogDetails`) values (12,'2022-03-08 11:15:36','操作员[operate]登录系统'),(13,'2022-03-08 11:17:01','操作员[operate]退出系统'),(14,'2022-03-08 11:29:09','管理员[admin]登录系统'),(15,'2022-03-08 11:29:52','管理员[admin]退出系统'),(16,'2022-03-08 11:30:11','管理员[张三]登录系统'),(17,'2022-03-08 11:34:26','管理员[张三]退出系统'),(18,'2022-03-08 11:34:50','管理员[张三]登录系统'),(19,'2022-03-08 11:40:44','管理员[张三]退出系统'),(20,'2022-03-08 11:41:21','操作员[员工2]登录系统'),(21,'2022-03-08 16:02:30','操作员[员工2]登录系统'),(22,'2022-03-08 16:15:05','操作员[operate]登录系统'),(23,'2022-03-08 16:35:21','操作员[员工2]登录系统'),(24,'2022-03-08 16:53:28','操作员[员工2]退出系统'),(25,'2022-03-08 16:53:35','管理员[张三]登录系统'),(26,'2022-03-08 16:55:53','管理员[张三]退出系统'),(27,'2022-03-08 16:56:50','操作员[员工2]登录系统'),(28,'2022-03-08 17:00:17','操作员[员工2]登录系统'),(29,'2022-03-08 17:00:54','操作员[员工2]登录系统'),(30,'2022-03-08 17:21:35','操作员[员工2]退出系统'),(31,'2022-03-08 17:21:52','操作员[员工2]登录系统'),(32,'2022-03-08 17:30:04','操作员[员工2]退出系统');

/*Table structure for table `operinfo` */

DROP TABLE IF EXISTS `operinfo`;

CREATE TABLE `operinfo` (
  `operId` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作员用户编号（主键）',
  `operName` varchar(20) NOT NULL COMMENT '操作员用户名称',
  `operPwd` varchar(20) NOT NULL COMMENT '操作员用户密码',
  `adminId` int(11) NOT NULL COMMENT '管理员ID',
  PRIMARY KEY (`operId`)
) ENGINE=InnoDB AUTO_INCREMENT=2005 DEFAULT CHARSET=utf8;

/*Data for the table `operinfo` */

insert  into `operinfo`(`operId`,`operName`,`operPwd`,`adminId`) values (2003,'员工2','123456',1003),(2004,'operate','123456',1001);

/*Table structure for table `operlog` */

DROP TABLE IF EXISTS `operlog`;

CREATE TABLE `operlog` (
  `ologId` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工工作日志 编号',
  `ologTime` varchar(20) NOT NULL COMMENT '员工工作日志 时间',
  `ologDetails` varchar(100) NOT NULL COMMENT '员工工作日志 内容',
  PRIMARY KEY (`ologId`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `operlog` */

insert  into `operlog`(`ologId`,`ologTime`,`ologDetails`) values (14,'2022-03-08 11:16:10','操作员[operate]添加图书类型文学'),(15,'2022-03-08 11:16:15','操作员[operate]添加图书类型科幻'),(16,'2022-03-08 11:16:22','操作员[operate]添加图书类型农业'),(17,'2022-03-08 11:16:29','操作员[operate]添加图书类型建筑'),(18,'2022-03-08 11:29:11','管理员[admin]查看员工工作日志'),(19,'2022-03-08 11:29:46','管理员[admin]查看员工工作日志'),(20,'2022-03-08 11:30:12','管理员[张三]查看员工工作日志'),(21,'2022-03-08 11:30:25','管理员[张三]修改图书借阅金额的设定'),(22,'2022-03-08 11:31:22','管理员[张三]查看员工工作日志'),(23,'2022-03-08 11:34:20','管理员[张三]查看员工工作日志'),(24,'2022-03-08 11:39:08','管理员[张三]修改操作员信息[员工2]'),(25,'2022-03-08 11:39:24','管理员[张三]查看图书逾期罚金账单'),(26,'2022-03-08 11:41:52','操作员[员工2]添加图书类型美工'),(27,'2022-03-08 11:42:23','操作员[员工2]修改图书类型美工'),(28,'2022-03-08 16:09:59','操作员[员工2]添加读者类型学生'),(29,'2022-03-08 16:16:26','操作员[operate]删除读者类型jiaoshi'),(30,'2022-03-08 16:16:34','操作员[operate]添加读者类型教师'),(31,'2022-03-08 16:23:46','操作员[operate]修改读者类型学生'),(32,'2022-03-08 16:36:31','操作员[员工2]添加图书加油，达芬奇！'),(33,'2022-03-08 16:38:56','操作员[员工2]修改图书加油，达芬奇！'),(34,'2022-03-08 16:39:53','操作员[员工2]删除图书加油，达芬奇！'),(35,'2022-03-08 16:44:48','操作员[员工2]添加读者（编号765）欧克'),(36,'2022-03-08 16:53:36','管理员[张三]查看员工工作日志'),(37,'2022-03-08 16:53:55','管理员[张三]修改图书借阅金额的设定'),(38,'2022-03-08 16:55:44','管理员[张三]查看员工工作日志'),(39,'2022-03-08 16:55:48','管理员[张三]查看图书逾期罚金账单'),(40,'2022-03-08 16:59:38','操作员[员工2]添加读者（编号222）42'),(41,'2022-03-08 17:10:31','操作员[员工2]修改读者（编号222）戚继'),(42,'2022-03-08 17:11:52','操作员[员工2]添加图书今日说法'),(43,'2022-03-08 17:23:09','操作员[员工2]添加图书熊出没'),(44,'2022-03-08 17:26:53','操作员[员工2]修改图书熊出没'),(45,'2022-03-08 17:28:00','读者编号（222）借阅图书isbn（isbn2391）,操作员[员工2]'),(46,'2022-03-08 17:28:46','读者编号（234）借阅图书isbn（isbn2391）,操作员[员工2]'),(47,'2022-03-08 17:29:55','操作员[员工2]添加读者（编号234）骑兵粉丝');

/*Table structure for table `readerinformation` */

DROP TABLE IF EXISTS `readerinformation`;

CREATE TABLE `readerinformation` (
  `readerid` char(8) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `name` char(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` char(4) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `dept` varchar(20) DEFAULT NULL,
  `regdate` date DEFAULT NULL,
  PRIMARY KEY (`readerid`),
  KEY `rt_reader_type` (`type`),
  CONSTRAINT `rt_reader_type` FOREIGN KEY (`type`) REFERENCES `readertype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `readerinformation` */

insert  into `readerinformation`(`readerid`,`type`,`name`,`age`,`sex`,`phone`,`dept`,`regdate`) values ('222',1,'戚继',1,'男','18377226666','西部系部','2020-02-23'),('234',3,'骑兵粉丝',90,'男','13478457845','米斯达系部','2021-12-31');

/*Table structure for table `readertype` */

DROP TABLE IF EXISTS `readertype`;

CREATE TABLE `readertype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` char(20) DEFAULT NULL,
  `maxborrownum` int(11) DEFAULT '30',
  `limit` int(11) DEFAULT '7',
  `penalMoney` int(11) DEFAULT '10' COMMENT '逾期未还书每天需支付金额',
  `borrowMoney` int(11) DEFAULT '200' COMMENT '每类读者的图书借阅总金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `readertype` */

insert  into `readertype`(`id`,`typename`,`maxborrownum`,`limit`,`penalMoney`,`borrowMoney`) values (1,'学生',30,7,10,200),(3,'教师',30,7,10,200);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
