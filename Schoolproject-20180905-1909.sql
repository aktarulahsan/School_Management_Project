-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema school_management_project
--

CREATE DATABASE IF NOT EXISTS school_management_project;
USE school_management_project;

--
-- Definition of table `s_class`
--

DROP TABLE IF EXISTS `s_class`;
CREATE TABLE `s_class` (
  `cl_id` int(10) unsigned NOT NULL auto_increment,
  `ses_id` int(10) unsigned NOT NULL,
  `all_c_id` int(10) unsigned NOT NULL,
  `c_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`cl_id`),
  KEY `FK_s_class_ses_id` (`ses_id`),
  CONSTRAINT `FK_s_class_ses_id` FOREIGN KEY (`ses_id`) REFERENCES `session` (`ses_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `s_class`
--

/*!40000 ALTER TABLE `s_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `s_class` ENABLE KEYS */;


--
-- Definition of table `s_classes`
--

DROP TABLE IF EXISTS `s_classes`;
CREATE TABLE `s_classes` (
  `all_c_id` int(10) unsigned NOT NULL auto_increment,
  `c_name` varchar(30) NOT NULL,
  PRIMARY KEY  USING BTREE (`all_c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `s_classes`
--

/*!40000 ALTER TABLE `s_classes` DISABLE KEYS */;
INSERT INTO `s_classes` (`all_c_id`,`c_name`) VALUES 
 (1,'vl'),
 (2,'x'),
 (3,'vl'),
 (4,'vl'),
 (5,'vll'),
 (6,'lx'),
 (7,'xll');
/*!40000 ALTER TABLE `s_classes` ENABLE KEYS */;


--
-- Definition of table `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE `session` (
  `ses_id` int(10) unsigned NOT NULL auto_increment,
  `ses_year` varchar(45) NOT NULL,
  PRIMARY KEY  (`ses_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session`
--

/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session` (`ses_id`,`ses_year`) VALUES 
 (1,'2009-2010');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;


--
-- Definition of table `student_seat_limit`
--

DROP TABLE IF EXISTS `student_seat_limit`;
CREATE TABLE `student_seat_limit` (
  `seat_l_id` int(10) unsigned NOT NULL auto_increment,
  `all_c_id` int(10) unsigned NOT NULL,
  `ses_id` int(10) unsigned NOT NULL,
  `s_limit` int(10) unsigned default '0',
  PRIMARY KEY  (`seat_l_id`),
  KEY `FK_student_seat_limit_cla_id` (`all_c_id`),
  KEY `FK_student_seat_limit_ses_id` (`ses_id`),
  CONSTRAINT `FK_student_seat_limit_cla_id` FOREIGN KEY (`all_c_id`) REFERENCES `s_classes` (`all_c_id`),
  CONSTRAINT `FK_student_seat_limit_ses_id` FOREIGN KEY (`ses_id`) REFERENCES `session` (`ses_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_seat_limit`
--

/*!40000 ALTER TABLE `student_seat_limit` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_seat_limit` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
