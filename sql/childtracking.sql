-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 10, 2016 at 06:37 PM
-- Server version: 5.1.44
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `childtracking`
--

-- --------------------------------------------------------

--
-- Table structure for table `PARENT`
--

CREATE TABLE IF NOT EXISTS `PARENT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentname` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `emailid` varchar(45) DEFAULT NULL,
  `imie` varchar(45) DEFAULT NULL,
  `contactno` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contactno` (`contactno`),
  UNIQUE KEY `emailid` (`emailid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `PARENT`
--

INSERT INTO `PARENT` (`id`, `parentname`, `address`, `emailid`, `imie`, `contactno`, `password`) VALUES
(10, NULL, NULL, NULL, NULL, '(+91) 916-793-7075', '01dino@1988am');
