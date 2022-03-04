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
) ENGINE=InnoDB AUTO_INCREMENT=2147483647 DEFAULT CHARSET=utf8;

/*Data for the table `admininfo` */

insert  into `admininfo`(`adminId`,`adminName`,`adminPwd`) values (1001,'张三','1234'),(1002,'admi','123456'),(1003,'abc','123456');

/*Table structure for table `loginlog` */

DROP TABLE IF EXISTS `loginlog`;

CREATE TABLE `loginlog` (
  `llogId` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志 编号',
  `llogTime` varchar(20) NOT NULL COMMENT '登录日志 时间',
  `llogDetails` varchar(30) NOT NULL COMMENT '登录日志 内容',
  PRIMARY KEY (`llogId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `loginlog` */

insert  into `loginlog`(`llogId`,`llogTime`,`llogDetails`) values (1,'2022-03-04 19:23:35','操作员[员工2]登录系统'),(2,'2022-03-04 19:26:28','操作员[员工2]登录系统'),(3,'2022-03-04 19:29:30','操作员[员工2]登录系统'),(4,'2022-03-04 19:31:34','操作员[员工2]登录系统'),(5,'2022-03-04 19:37:54','操作员[员工2]登录系统'),(6,'2022-03-04 19:42:14','操作员[员工2]登录系统'),(7,'2022-03-04 19:57:29','操作员[员工2]退出系统'),(8,'2022-03-04 19:57:54','管理员[张三]登录系统'),(9,'2022-03-04 19:59:40','管理员[张三]退出系统'),(10,'2022-03-04 20:00:51','管理员[张三]登录系统'),(11,'2022-03-04 20:00:56','管理员[张三]退出系统');

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

insert  into `operinfo`(`operId`,`operName`,`operPwd`,`adminId`) values (2003,'员工2','123456',1001),(2004,'哦吼','123456',1001);

/*Table structure for table `operlog` */

DROP TABLE IF EXISTS `operlog`;

CREATE TABLE `operlog` (
  `ologId` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工工作日志 编号',
  `ologTime` varchar(20) NOT NULL COMMENT '员工工作日志 时间',
  `ologDetails` varchar(100) NOT NULL COMMENT '员工工作日志 内容',
  PRIMARY KEY (`ologId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `operlog` */

insert  into `operlog`(`ologId`,`ologTime`,`ologDetails`) values (1,'2022-03-04 19:24:20','读者编号（001）借阅图书isbn（2308dsdf）,操作员[员工2]'),(2,'2022-03-04 19:26:57','读者编号（001）归还图书isbn（2308dsdf）,操作员[员工2]'),(3,'2022-03-04 19:29:45','读者编号（001）归还图书isbn（2308dsdf）,操作员[员工2]'),(4,'2022-03-04 19:38:07','读者编号（002）归还图书isbn（23454）,操作员[员工2]'),(5,'2022-03-04 19:42:50','读者编号（002）归还图书isbn（23454）,操作员[员工2]'),(6,'2022-03-04 19:44:15','读者编号（002）归还图书isbn（23454）,操作员[员工2]'),(7,'2022-03-04 19:46:38','操作员[员工2]修改罚金设置'),(8,'2022-03-04 19:56:23','操作员[员工2]修改图书类型图书类型3'),(9,'2022-03-04 19:57:57','管理员[张三]查看员工工作日志'),(10,'2022-03-04 19:58:27','管理员[张三]修改图书借阅金额的设定'),(11,'2022-03-04 19:59:08','管理员[张三]修改操作员信息[员工2]'),(12,'2022-03-04 19:59:25','管理员[张三]查看图书逾期罚金账单'),(13,'2022-03-04 19:59:34','管理员[张三]查看员工工作日志');

/*Table structure for table `readertype` */

DROP TABLE IF EXISTS `readertype`;

CREATE TABLE `readertype` (
  `rtId` int(11) NOT NULL COMMENT '读者类型编号（主键）',
  `rtName` varchar(20) DEFAULT NULL COMMENT '读者类型名称',
  `maxborrowNum` int(11) DEFAULT NULL COMMENT '最大可借阅图书数量',
  `limit` int(11) DEFAULT NULL COMMENT '最长可借阅天数',
  `borrowMoney` int(11) DEFAULT NULL COMMENT '图书借阅金额（亦为罚款金额）',
  PRIMARY KEY (`rtId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `readertype` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
