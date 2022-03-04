/*
SQLyog v10.2 
MySQL - 5.5.41 : Database - librarymanagement
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`librarymanagement` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `librarymanagement`;

/*Table structure for table `administratoruser` */

DROP TABLE IF EXISTS `administratoruser`;

CREATE TABLE `administratoruser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `administratoruser` */

insert  into `administratoruser`(`id`,`name`,`password`) values (1,'hjt','123'),(2,'zs','234');

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

insert  into `bookinformation`(`ISBN`,`typeid`,`bookname`,`author`,`publish`,`publishdate`,`publishtime`,`unitprice`) values ('2308dsdf',2,'图爱放大','但撒谎','不花出版社','1920-02-10',9,19),('2345',2,'环境','好久','归还借款','2000-01-03',32,67),('23454',2,'zh','we','dws','2012-01-02',23,45),('9787111381',2,'西游记','吴承恩','上海译文出版社出版','1999-10-01',8,8.2),('9787532723',1,'老人与海','欧内斯特·海明威','机械工业出版社','2012-05-01',16,100);

/*Table structure for table `booktype` */

DROP TABLE IF EXISTS `booktype`;

CREATE TABLE `booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `booktype` */

insert  into `booktype`(`id`,`typename`) values (1,'文学'),(2,'科幻'),(3,'图书类型3'),(4,'农业'),(5,'建筑');

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

insert  into `borrowbook`(`readerid`,`ISBN`,`borrowdate`,`returndate`,`fine`) values ('002','9787111381','2021-12-27','2022-02-27',20),('001','9787111381','2021-12-19','2022-02-24',20),('002','2345','2021-12-26',NULL,0),('002','23454','2022-02-15','2022-03-04',100),('002','2308dsdf','2022-03-04','2022-03-04',0),('002','2308dsdf','2022-03-04','2022-03-04',0),('001','9787532723','2022-03-04',NULL,0),('001','2308dsdf','2022-03-04','2022-03-04',0);

/*Table structure for table `operationuser` */

DROP TABLE IF EXISTS `operationuser`;

CREATE TABLE `operationuser` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `managerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oa_operationUser_administratorUser` (`managerid`),
  CONSTRAINT `oa_operationUser_administratorUser` FOREIGN KEY (`managerid`) REFERENCES `administratoruser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `operationuser` */

insert  into `operationuser`(`id`,`name`,`password`,`managerid`) values (123,'zhangsan','123456',1),(234,'lisi','123678',2);

/*Table structure for table `penalty` */

DROP TABLE IF EXISTS `penalty`;

CREATE TABLE `penalty` (
  `id` int(11) DEFAULT NULL,
  `typename` varchar(30) DEFAULT NULL,
  `fine` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `penalty` */

insert  into `penalty`(`id`,`typename`,`fine`) values (1,'学生',10),(2,'教师',15);

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

insert  into `readerinformation`(`readerid`,`type`,`name`,`age`,`sex`,`phone`,`dept`,`regdate`) values ('001',2,'u放到啊',2,'vnsa','fsa','fs','2020-02-23'),('002',2,'笔法',2,'男','10894853498','赛车手','2020-10-10'),('003',1,'夏鸣星',20,'男','13498850894','音乐','2009-10-12'),('023',1,'没啊法第四',1,'女','19233887722','下回复步','2021-12-20');

/*Table structure for table `readertype` */

DROP TABLE IF EXISTS `readertype`;

CREATE TABLE `readertype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` char(20) DEFAULT NULL,
  `maxborrownum` int(11) DEFAULT NULL,
  `limit` int(11) DEFAULT NULL,
  `penalMoney` int(11) DEFAULT '10' COMMENT '逾期未还书每天需支付金额',
  `borrowMoney` int(11) DEFAULT '200' COMMENT '每类读者的图书借阅总金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `readertype` */

insert  into `readertype`(`id`,`typename`,`maxborrownum`,`limit`,`penalMoney`,`borrowMoney`) values (1,'学生',9,7,5,100),(2,'教师',10,7,5,200),(3,'副校长',NULL,NULL,10,200);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
