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

insert  into `admininfo`(`adminId`,`adminName`,`adminPwd`) values (1001,'张三','123456'),(1002,'admi','123456'),(1003,'abc','123456');

/*Table structure for table `loginlog` */

DROP TABLE IF EXISTS `loginlog`;

CREATE TABLE `loginlog` (
  `llogId` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志 编号',
  `llogTime` varchar(20) NOT NULL COMMENT '登录日志 时间',
  `llogDetails` varchar(30) NOT NULL COMMENT '登录日志 内容',
  PRIMARY KEY (`llogId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `loginlog` */

insert  into `loginlog`(`llogId`,`llogTime`,`llogDetails`) values (5,'2022-03-03 09:30:14','管理员[张三]登录系统'),(6,'2022-03-03 09:30:48','管理员[张三]退出系统'),(7,'2022-03-03 09:31:18','管理员[abc]登录系统'),(8,'2022-03-03 09:39:20','管理员[abc]登录系统'),(9,'2022-03-03 09:44:08','管理员[abc]登录系统'),(10,'2022-03-03 09:45:33','管理员[abc]登录系统'),(11,'2022-03-03 09:51:08','管理员[abc]退出系统');

/*Table structure for table `operinfo` */

DROP TABLE IF EXISTS `operinfo`;

CREATE TABLE `operinfo` (
  `operId` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作员用户编号（主键）',
  `operName` varchar(20) NOT NULL COMMENT '操作员用户名称',
  `operPwd` varchar(20) NOT NULL COMMENT '操作员用户密码',
  `adminId` int(11) NOT NULL COMMENT '管理员ID',
  PRIMARY KEY (`operId`)
) ENGINE=InnoDB AUTO_INCREMENT=2004 DEFAULT CHARSET=utf8;

/*Data for the table `operinfo` */

insert  into `operinfo`(`operId`,`operName`,`operPwd`,`adminId`) values (2003,'员工2','123456',1003);

/*Table structure for table `operlog` */

DROP TABLE IF EXISTS `operlog`;

CREATE TABLE `operlog` (
  `ologId` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工工作日志 编号',
  `ologTime` varchar(20) NOT NULL COMMENT '员工工作日志 时间',
  `ologDetails` varchar(30) NOT NULL COMMENT '员工工作日志 内容',
  PRIMARY KEY (`ologId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `operlog` */

insert  into `operlog`(`ologId`,`ologTime`,`ologDetails`) values (1,'2022-03-02 11:15:13','操作员[xxx]。。。。');

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
