-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 29, 2022 at 07:31 PM
-- Server version: 10.5.16-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id19896389_kemussit`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `inte` int(11) NOT NULL,
  `fresh` double DEFAULT 0,
  `renewal` double DEFAULT 0,
  `upgrade` double DEFAULT 0,
  `contribute` double DEFAULT 0,
  `event_pay` double DEFAULT 0,
  `withdraw` double DEFAULT 0,
  `whole` double DEFAULT 0,
  `bal` double DEFAULT 0,
  `entry` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`inte`, `fresh`, `renewal`, `upgrade`, `contribute`, `event_pay`, `withdraw`, `whole`, `bal`, `entry`, `last`) VALUES
(1, 1250, 50, 500, 1200, 500, 1500, 3500, 2000, '2022-11-27 05:37 PM', '2022-11-28 02:41 PM');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adm_id` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adm_id`, `username`, `password`, `date`) VALUES
(1, 'Maala', '5bcc926bb6e944243b919a7cf831d417', '2022-11-27 13:35:41');

-- --------------------------------------------------------

--
-- Table structure for table `announce`
--

CREATE TABLE `announce` (
  `ann` int(11) NOT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subject` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notice` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `closure` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `announce`
--

INSERT INTO `announce` (`ann`, `ses`, `term`, `subject`, `notice`, `closure`, `entry_date`) VALUES
(1, 1, 'Trimester 1 2022', 'PROJECT PRESENTATION', 'Strictly starts at 9.00 am in CL1', 1, '27-11-2022 05:42:33');

-- --------------------------------------------------------

--
-- Table structure for table `cardy`
--

CREATE TABLE `cardy` (
  `id` int(11) NOT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fees` float DEFAULT NULL,
  `description` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cardy`
--

INSERT INTO `cardy` (`id`, `category`, `fees`, `description`, `date`) VALUES
(1, 'FULL MEMBERSHIP', 250, 'non-refundable registration fee of Ksh. 250. There will be a renewal fee of Ksh. 50 per trimester (Ksh. 150 per annum)', '27-11-2022 05:25 PM'),
(2, 'ALUMNI MEMBERSHIP', 500, 'non-refundable registration fee of Ksh. 500. There will be an annual fee of Ksh. 500', '27-11-2022 05:25 PM'),
(4, 'TRIMESTER RENEWAL', 50, 'FULL MEMBERSHIP card Renewal. There will be a renewal fee of Ksh. 50 per trimester (Ksh. 150 per annum)', '28-11-2022 02:25 PM');

-- --------------------------------------------------------

--
-- Table structure for table `cardy_one`
--

CREATE TABLE `cardy_one` (
  `idi` int(11) NOT NULL,
  `ses` int(11) DEFAULT NULL,
  `ended` float DEFAULT 0,
  `pay` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deter` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profile` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `signature` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `classification` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `finsta` float DEFAULT 0,
  `secsta` float DEFAULT 0,
  `lost` float DEFAULT 0,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cardy_one`
--

INSERT INTO `cardy_one` (`idi`, `ses`, `ended`, `pay`, `deter`, `category`, `reg_no`, `fullname`, `phone`, `profile`, `signature`, `department`, `classification`, `email`, `finsta`, `secsta`, `lost`, `date`) VALUES
(1, 1, 1, 'KEMU-1', 'F', 'FULL MEMBERSHIP', 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 'IMG1997635573.jpg', 'IMG502940346.jpg', 'COMPUTER SCIENCE', 'BUSINESS INFORMATION TECHNOLOGY', 'wangoolarnito@gmail.com', 1, 1, 1, '27-11-2022 05:28 PM'),
(2, 1, 1, 'KEMU-2', 'F', 'FULL MEMBERSHIP', 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 'IMG211635663.jpg', 'IMG209604203.jpg', 'AGRICULTURE', 'APPLIED AGRICULTURE', 'esese@gmail.com', 1, 1, 1, '27-11-2022 05:29 PM'),
(3, 1, 1, 'KEMU-3', 'F', 'FULL MEMBERSHIP', 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 'IMG128922031.jpg', 'IMG541579865.jpg', 'COMPUTER SCIENCE', 'COMPUTER INFORMATION SYSTEMS', 'maalaelvira@gmail.com', 1, 1, 1, '27-11-2022 05:32 PM'),
(4, 1, 1, 'KEMU-4', 'F', 'FULL MEMBERSHIP', 'BIS-1-9022-2/2019', 'SHALLOT DAMARY', '0782438494', 'IMG1136337184.jpg', 'IMG855056169.jpg', 'INFORMATION SCIENCE', 'INFORMATION SCIENCE', 'damary@gmail.com', 1, 1, 1, '27-11-2022 05:33 PM'),
(5, 1, 1, 'KEMU-5', 'F', 'FULL MEMBERSHIP', 'MAC-1-9022-2/2019', 'ISABIRYE RODNEY', '0725316802', 'IMG2065215075.jpg', 'IMG1470899450.jpg', 'PURE & APPLIED SCIENCE', 'MATHEMATICS & COMPUTER SCIENCE', 'isabirye@gmail.com', 1, 1, 1, '27-11-2022 05:35 PM'),
(6, 2, 0, 'KEMU-8', 'R', 'TRIMESTER RENEWAL', 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 'IMG1997635573.jpg', 'IMG502940346.jpg', 'COMPUTER SCIENCE', 'BUSINESS INFORMATION TECHNOLOGY', 'wangoolarnito@gmail.com', 1, 1, 0, '28-11-2022 02:28 PM'),
(7, 2, 0, 'KEMU-9', 'U', 'ALUMNI MEMBERSHIP', 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 'IMG1818556079.jpg', 'IMG263893783.jpg', 'COMPUTER SCIENCE', 'COMPUTER INFORMATION SYSTEMS', 'maalaelvira@gmail.com', 1, 1, 0, '28-11-2022 02:38 PM');

-- --------------------------------------------------------

--
-- Table structure for table `club`
--

CREATE TABLE `club` (
  `id` int(11) NOT NULL,
  `club_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `club`
--

INSERT INTO `club` (`id`, `club_name`, `entry_date`) VALUES
(1, 'INNOVATORS', '28-11-2022 10:06:02AM');

-- --------------------------------------------------------

--
-- Table structure for table `contri`
--

CREATE TABLE `contri` (
  `pay` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mpesa` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `money` float DEFAULT NULL,
  `reg_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `expiry` float DEFAULT 0,
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `contri`
--

INSERT INTO `contri` (`pay`, `ses`, `term`, `mpesa`, `money`, `reg_no`, `fullname`, `phone`, `status`, `expiry`, `remarks`, `date`) VALUES
('KEMU-2', 1, 'Trimester 1 2022', 'GDDGVJGD72', 1500, 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 2, 1, NULL, '27-11-2022 05:45 PM'),
('KEMU-3', 1, 'Trimester 1 2022', 'AFGBJGAN60', 1200, 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 1, 1, NULL, '27-11-2022 05:46 PM');

-- --------------------------------------------------------

--
-- Table structure for table `contribution`
--

CREATE TABLE `contribution` (
  `pay` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sm` int(11) DEFAULT NULL,
  `mpesa` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `money` float DEFAULT NULL,
  `reg_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `expiry` float DEFAULT 0,
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `contribution`
--

INSERT INTO `contribution` (`pay`, `ses`, `term`, `sm`, `mpesa`, `money`, `reg_no`, `fullname`, `phone`, `status`, `expiry`, `remarks`, `date`) VALUES
('KEMU-4', 1, 'Trimester 1 2022', 2, 'ASFHGCBH67', 250, 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 1, 1, NULL, '28-11-2022 09:56 AM'),
('KEMU-5', 1, 'Trimester 1 2022', 3, 'YFVJFSFJ78', 250, 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 2, 1, NULL, '28-11-2022 09:57 AM'),
('KEMU-6', 1, 'Trimester 1 2022', 3, 'THUTDVKU78', 250, 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 1, 1, NULL, '28-11-2022 10:00 AM');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `cs` int(11) NOT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `credits` float DEFAULT NULL,
  `department` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `quote` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `closure` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`cs`, `ses`, `term`, `code`, `title`, `credits`, `department`, `quote`, `closure`, `entry_date`) VALUES
(1, 1, 'Trimester 1 2022', 'CISY 110', 'Introduction to programming', 3, 'COMPUTER SCIENCE', 'Note that if you have a Retake you cannot register a course from your portal.', 1, '27-11-2022 04:50:18'),
(2, 1, 'Trimester 1 2022', 'COMM 111', 'Communication skills', 3, 'COMMON UNIT', 'Note that if you have a Retake you cannot register a course from your portal.', 1, '27-11-2022 04:50:44');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `evt` int(11) NOT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `theme` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `venue` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `land` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `site` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `money` float DEFAULT NULL,
  `member` float DEFAULT NULL,
  `opened` float DEFAULT NULL,
  `status` float DEFAULT 0,
  `approval` float DEFAULT 0,
  `comment` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `closure` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`evt`, `ses`, `term`, `theme`, `venue`, `land`, `site`, `date`, `time`, `money`, `member`, `opened`, `status`, `approval`, `comment`, `closure`, `entry_date`) VALUES
(1, 1, 'Trimester 1 2022', 'CODING', 'Nairobi', 'KeMU', 'CL2', '30-11-2022', '09:00AM', 250, 0, 2, 1, 1, 'Approved. With doubt this event is legit', 1, '28-11-2022 09:33:12');

-- --------------------------------------------------------

--
-- Table structure for table `expired`
--

CREATE TABLE `expired` (
  `expi` int(11) NOT NULL,
  `iss` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idi` int(11) DEFAULT NULL,
  `ses` int(11) DEFAULT NULL,
  `reg_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profile` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `signature` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `classification` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `issue_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expiry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `expired`
--

INSERT INTO `expired` (`expi`, `iss`, `idi`, `ses`, `reg_no`, `fullname`, `phone`, `profile`, `signature`, `department`, `classification`, `issue_date`, `expiry_date`, `category`, `entry_date`) VALUES
(1, 'KEMU-0', 2, 1, 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 'IMG211635663.jpg', 'IMG209604203.jpg', 'AGRICULTURE', 'APPLIED AGRICULTURE', '27-11-2022', '25-02-2023', 'FULL MEMBERSHIP', '28-11-2022 02:13:56'),
(2, 'KEMU-1', 1, 1, 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 'IMG1997635573.jpg', 'IMG502940346.jpg', 'COMPUTER SCIENCE', 'BUSINESS INFORMATION TECHNOLOGY', '27-11-2022', '25-02-2023', 'FULL MEMBERSHIP', '28-11-2022 02:13:56'),
(3, 'KEMU-6', 5, 1, 'MAC-1-9022-2/2019', 'ISABIRYE RODNEY', '0725316802', 'IMG2065215075.jpg', 'IMG1470899450.jpg', 'PURE & APPLIED SCIENCE', 'MATHEMATICS & COMPUTER SCIENCE', '28-11-2022', '25-02-2023', 'FULL MEMBERSHIP', '28-11-2022 02:13:56'),
(4, 'KEMU-8', 4, 1, 'BIS-1-9022-2/2019', 'SHALLOT DAMARY', '0782438494', 'IMG1136337184.jpg', 'IMG855056169.jpg', 'INFORMATION SCIENCE', 'INFORMATION SCIENCE', '27-11-2022', '25-02-2023', 'FULL MEMBERSHIP', '28-11-2022 02:13:56'),
(5, 'KEMU-9', 3, 1, 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 'IMG128922031.jpg', 'IMG541579865.jpg', 'COMPUTER SCIENCE', 'COMPUTER INFORMATION SYSTEMS', '27-11-2022', '25-02-2023', 'FULL MEMBERSHIP', '28-11-2022 02:13:56');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `fd` int(11) NOT NULL,
  `stud_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `move` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `current` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`fd`, `stud_id`, `phone`, `name`, `message`, `move`, `date`, `time`, `current`) VALUES
(1, 'BIT-1-9022-2/2019', '0763455134', 'WANGOOLA', 'Amazing experience', 'M', '27-11-2022', '05:46:52PM', '27-11-2022 05:46:52'),
(2, 'BIT-1-9022-2/2019', '0763455134', 'WANGOOLA', 'Thank you', 'R', '27-11-2022', '05:48:17PM', '27-11-2022 05:48:17');

-- --------------------------------------------------------

--
-- Table structure for table `issued`
--

CREATE TABLE `issued` (
  `iss` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `idi` int(11) DEFAULT NULL,
  `ses` int(11) DEFAULT NULL,
  `reg_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profile` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `signature` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `classification` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `issue_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expiry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `ended` float DEFAULT 0,
  `expiry` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `issued`
--

INSERT INTO `issued` (`iss`, `idi`, `ses`, `reg_no`, `fullname`, `phone`, `profile`, `signature`, `department`, `classification`, `issue_date`, `expiry_date`, `category`, `status`, `ended`, `expiry`, `entry_date`) VALUES
('KEMU-0', 2, 1, 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 'IMG211635663.jpg', 'IMG209604203.jpg', 'AGRICULTURE', 'APPLIED AGRICULTURE', '27-11-2022', '25-02-2023', 'FULL MEMBERSHIP', 1, 1, 1, '27-11-2022 05:40:33'),
('KEMU-1', 6, 2, 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 'IMG1997635573.jpg', 'IMG502940346.jpg', 'COMPUTER SCIENCE', 'BUSINESS INFORMATION TECHNOLOGY', '29-11-2022', '26-02-2023', 'FULL MEMBERSHIP', 1, 0, 0, '28-11-2022 02:46:53'),
('KEMU-6', 5, 1, 'MAC-1-9022-2/2019', 'ISABIRYE RODNEY', '0725316802', 'IMG2065215075.jpg', 'IMG1470899450.jpg', 'PURE & APPLIED SCIENCE', 'MATHEMATICS & COMPUTER SCIENCE', '28-11-2022', '25-02-2023', 'FULL MEMBERSHIP', 1, 1, 1, '27-11-2022 05:39:57'),
('KEMU-8', 4, 1, 'BIS-1-9022-2/2019', 'SHALLOT DAMARY', '0782438494', 'IMG1136337184.jpg', 'IMG855056169.jpg', 'INFORMATION SCIENCE', 'INFORMATION SCIENCE', '27-11-2022', '25-02-2023', 'FULL MEMBERSHIP', 1, 1, 1, '27-11-2022 05:40:15'),
('KEMU-9', 7, 2, 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 'IMG1818556079.jpg', 'IMG263893783.jpg', 'COMPUTER SCIENCE', 'COMPUTER INFORMATION SYSTEMS', '28-11-2022', '28-11-2023', 'ALUMNI MEMBERSHIP', 1, 0, 0, '28-11-2022 02:47:35');

-- --------------------------------------------------------

--
-- Table structure for table `lec`
--

CREATE TABLE `lec` (
  `serial_no` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lec`
--

INSERT INTO `lec` (`serial_no`, `fname`, `lname`, `email`, `password`, `phone`, `department`, `status`, `remarks`, `date`) VALUES
('KEMU-2AB22', 'ABALA', 'LORNA', 'abala@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0753188269', 'COMPUTER SCIENCE', 1, '', '2022-11-27 04:08:31 PM'),
('KEMU-3WA22', 'WANJIRA', 'JUMA', 'juma@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0763450040', 'INFORMATION SCIENCE', 1, '', '2022-11-27 04:09:39 PM'),
('KEMU-4MA22', 'MARK', 'WASIKE', 'mark@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0753488504', 'AGRICULTURE', 1, '', '2022-11-27 04:10:37 PM'),
('KEMU-5JA22', 'JAMES', 'ONSERIO', 'onserio@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0763540496', 'PURE & APPLIED SCIENCE', 1, '', '2022-11-27 04:11:13 PM');

-- --------------------------------------------------------

--
-- Table structure for table `mover`
--

CREATE TABLE `mover` (
  `mv` int(11) NOT NULL,
  `evt` int(11) DEFAULT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `theme` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `venue` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `club` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `members` float DEFAULT NULL,
  `money` float DEFAULT NULL,
  `summ` double DEFAULT NULL,
  `pat_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pat_phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pat_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fund` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `closure` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mover`
--

INSERT INTO `mover` (`mv`, `evt`, `ses`, `term`, `theme`, `venue`, `date`, `club`, `members`, `money`, `summ`, `pat_id`, `pat_phone`, `pat_name`, `fund`, `closure`, `entry_date`) VALUES
(1, 1, 1, 'Trimester 1 2022', 'CODING', 'Nairobi/KeMU/CL2', '30-11-2022 09:00AM', 'INNOVATORS', 2, 250, 500, 'KEMU-1PH22', '0763488164', 'PHANICE', 'Disbursed', 1, '28-11-2022 10:08:51');

-- --------------------------------------------------------

--
-- Table structure for table `mpesa`
--

CREATE TABLE `mpesa` (
  `pay` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mv` int(11) DEFAULT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mpesa` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `club_amt` float DEFAULT NULL,
  `utility_token` float DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `club` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pat_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pat_phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pat_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `closure` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `mpesa`
--

INSERT INTO `mpesa` (`pay`, `mv`, `ses`, `term`, `mpesa`, `club_amt`, `utility_token`, `amount`, `club`, `pat_id`, `pat_phone`, `pat_name`, `closure`, `entry_date`) VALUES
('KEMU-7', 1, 1, 'Trimester 1 2022', 'UFHUFSRT14', 500, 1000, 1500, 'INNOVATORS', 'KEMU-1PH22', '0763488164', 'PHANICE', 1, '28-11-2022 10:33 AM');

-- --------------------------------------------------------

--
-- Table structure for table `official`
--

CREATE TABLE `official` (
  `stud_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `classification` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `official`
--

INSERT INTO `official` (`stud_id`, `fname`, `lname`, `gender`, `email`, `password`, `phone`, `role`, `department`, `classification`, `status`, `remarks`, `date`) VALUES
('AGR-1-9022-2/2019', 'ESESE', 'AMMON', 'Male', 'esese@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0785135463', 'Secretary', 'AGRICULTURE', 'APPLIED AGRICULTURE', 1, '', '2022-11-27 04:12:03 PM'),
('CIS-1-9022-2/2019', 'MAALA', 'ELVIRA', 'Female', 'maalaelvira@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0794217969', 'Organization', 'COMPUTER SCIENCE', 'COMPUTER INFORMATION SYSTEMS', 1, '', '2022-11-27 04:12:39 PM'),
('KEMU-1PH22', 'PHANICE', 'JOY', 'gender', 'phanice@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0763488164', 'Patron', 'Department', 'Class', 1, '', '2022-11-27 04:04:14 PM'),
('MAC-1-9022-2/2019', 'ISABIRYE', 'RODNEY', 'Male', 'isabirye@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0725316802', 'Treasurer', 'PURE & APPLIED SCIENCE', 'MATHEMATICS & COMPUTER SCIENCE', 1, '', '2022-11-27 04:19:02 PM');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `pay` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `deter` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `year` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mpesa` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fees` float DEFAULT NULL,
  `reg_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `expiry` float DEFAULT 0,
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`pay`, `deter`, `ses`, `term`, `year`, `mpesa`, `fees`, `reg_no`, `fullname`, `phone`, `status`, `expiry`, `remarks`, `date`) VALUES
('KEMU-1', 'F', 1, 'Trimester 1', '2022', 'GVDSAYLI98', 250, 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 1, 1, NULL, '27-11-2022 05:28 PM'),
('KEMU-2', 'F', 1, 'Trimester 1', '2022', 'WAJHVUEL30', 250, 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 1, 1, NULL, '27-11-2022 05:29 PM'),
('KEMU-3', 'F', 1, 'Trimester 1', '2022', 'JBGAOSJV94', 250, 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 1, 1, NULL, '27-11-2022 05:32 PM'),
('KEMU-4', 'F', 1, 'Trimester 1', '2022', 'KOACBELF71', 250, 'BIS-1-9022-2/2019', 'SHALLOT DAMARY', '0782438494', 1, 1, NULL, '27-11-2022 05:33 PM'),
('KEMU-5', 'F', 1, 'Trimester 1', '2022', 'JSJSOBDJ62', 250, 'MAC-1-9022-2/2019', 'ISABIRYE RODNEY', '0725316802', 1, 1, NULL, '27-11-2022 05:35 PM'),
('KEMU-8', 'R', 2, 'Trimester 2', '2022', 'HDBFXGBD46', 50, 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 1, 0, NULL, '28-11-2022 02:28 PM'),
('KEMU-9', 'U', 2, 'Trimester 2', '2022', 'BDERBJYD36', 500, 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 1, 0, NULL, '28-11-2022 02:38 PM');

-- --------------------------------------------------------

--
-- Table structure for table `quick_alrt`
--

CREATE TABLE `quick_alrt` (
  `id` int(11) NOT NULL,
  `user` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `alrt` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `quick_alrt`
--

INSERT INTO `quick_alrt` (`id`, `user`, `alrt`, `reg_date`) VALUES
(1, 'MAC-1-9022-2/2019', 'Your card payment was approved.', '2022-11-27 05:37 PM'),
(2, 'BIS-1-9022-2/2019', 'Your card payment was approved.', '2022-11-27 05:37 PM'),
(3, 'CIS-1-9022-2/2019', 'Your card payment was approved.', '2022-11-27 05:37 PM'),
(4, 'AGR-1-9022-2/2019', 'Your card payment was approved.', '2022-11-27 05:37 PM'),
(5, 'BIT-1-9022-2/2019', 'Your card payment was approved.', '2022-11-27 05:37 PM'),
(6, 'MAC-1-9022-2/2019', 'Your card was processed successfully.', '27-11-2022 05:39:57'),
(7, 'MAC-1-9022-2/2019', 'Your card was processed successfully.', '27-11-2022 05:40:01'),
(8, 'BIS-1-9022-2/2019', 'Your card was processed successfully.', '27-11-2022 05:40:15'),
(9, 'CIS-1-9022-2/2019', 'Your card was processed successfully.', '27-11-2022 05:40:26'),
(10, 'AGR-1-9022-2/2019', 'Your card was processed successfully.', '27-11-2022 05:40:33'),
(11, 'BIT-1-9022-2/2019', 'Your card was processed successfully.', '27-11-2022 05:40:40'),
(12, 'BIT-1-9022-2/2019', 'Your contribution was approved.', '2022-11-27 05:50 PM'),
(13, 'BIT-1-9022-2/2019', 'Your contribution was rejected.', '2022-11-27 05:50 PM'),
(14, 'BIT-1-9022-2/2019', 'Your contribution was rejected.', '2022-11-27 05:50 PM'),
(15, 'AGR-1-9022-2/2019', 'Your Event payment was rejected.', '2022-11-28 09:58 AM'),
(16, 'CIS-1-9022-2/2019', 'Your Event payment was approved.', '2022-11-28 09:58 AM'),
(17, 'AGR-1-9022-2/2019', 'Your Event payment was approved.', '2022-11-28 10:01 AM'),
(18, 'CIS-1-9022-2/2019', 'You were a member of the INNOVATORS for the event themed CODING. Venue Nairobi', '28-11-2022 10:06:02AM'),
(19, 'AGR-1-9022-2/2019', 'You were a member of the INNOVATORS for the event themed CODING. Venue Nairobi', '28-11-2022 10:06:02AM'),
(20, 'AGR-1-9022-2/2019', 'Your Session has expired. Please Report a new session.', '28-11-2022 02:13:56'),
(21, 'BIT-1-9022-2/2019', 'Your Session has expired. Please Report a new session.', '28-11-2022 02:13:56'),
(22, 'MAC-1-9022-2/2019', 'Your Session has expired. Please Report a new session.', '28-11-2022 02:13:56'),
(23, 'BIS-1-9022-2/2019', 'Your Session has expired. Please Report a new session.', '28-11-2022 02:13:56'),
(24, 'CIS-1-9022-2/2019', 'Your Session has expired. Please Report a new session.', '28-11-2022 02:13:56'),
(25, 'AGR-1-9022-2/2019', 'Your CARD has reached expiry. Please Renew your card.', '28-11-2022 02:13:56'),
(26, 'BIT-1-9022-2/2019', 'Your CARD has reached expiry. Please Renew your card.', '28-11-2022 02:13:56'),
(27, 'MAC-1-9022-2/2019', 'Your CARD has reached expiry. Please Renew your card.', '28-11-2022 02:13:56'),
(28, 'BIS-1-9022-2/2019', 'Your CARD has reached expiry. Please Renew your card.', '28-11-2022 02:13:56'),
(29, 'CIS-1-9022-2/2019', 'Your CARD has reached expiry. Please Renew your card.', '28-11-2022 02:13:56'),
(30, 'BIT-1-9022-2/2019', 'Your card renewal payment was approved.', '2022-11-28 02:41 PM'),
(31, 'CIS-1-9022-2/2019', 'Your card payment was approved.', '2022-11-28 02:41 PM'),
(32, 'BIT-1-9022-2/2019', 'Your card renewal was processed successfully.', '28-11-2022 02:46:53'),
(33, 'CIS-1-9022-2/2019', 'Your card renewal was processed successfully.', '28-11-2022 02:47:35');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `rep` int(11) NOT NULL,
  `ses` int(11) DEFAULT NULL,
  `user_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userphone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `year` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `report` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ended` float DEFAULT 0,
  `ending` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`rep`, `ses`, `user_id`, `username`, `userphone`, `term`, `year`, `report`, `status`, `entry_date`, `ended`, `ending`, `end_date`) VALUES
(1, 1, 'KEMU-1PH22', 'PHANICE JOY', '0763488164', 'Trimester 1', '2022', '28-11-2022', 1, '27-11-2022 05:14:11', 1, '25-02-2023', '28-11-2022 02:13:56'),
(2, 1, 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 'Trimester 1', '2022', '28-11-2022', 1, '27-11-2022 05:20:26', 1, '25-02-2023', '28-11-2022 02:13:56'),
(3, 1, 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 'Trimester 1', '2022', '28-11-2022', 1, '27-11-2022 05:21:26', 1, '25-02-2023', '28-11-2022 02:13:56'),
(4, 1, 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 'Trimester 1', '2022', '28-11-2022', 1, '27-11-2022 05:31:38', 1, '25-02-2023', '28-11-2022 02:13:56'),
(5, 1, 'BIS-1-9022-2/2019', 'SHALLOT DAMARY', '0782438494', 'Trimester 1', '2022', '28-11-2022', 1, '27-11-2022 05:32:38', 1, '25-02-2023', '28-11-2022 02:13:56'),
(6, 1, 'MAC-1-9022-2/2019', 'ISABIRYE RODNEY', '0725316802', 'Trimester 1', '2022', '28-11-2022', 1, '27-11-2022 05:33:45', 1, '25-02-2023', '28-11-2022 02:13:56'),
(7, 2, 'KEMU-1PH22', 'PHANICE JOY', '0763488164', 'Trimester 2', '2022', '28-11-2022', 1, '28-11-2022 02:21:30', 0, '26-02-2023', NULL),
(8, 2, 'MAC-1-9022-2/2019', 'ISABIRYE RODNEY', '0725316802', 'Trimester 2', '2022', '28-11-2022', 1, '28-11-2022 02:22:13', 0, '26-02-2023', NULL),
(9, 2, 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 'Trimester 2', '2022', '28-11-2022', 1, '28-11-2022 02:22:57', 0, '26-02-2023', NULL),
(10, 2, 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 'Trimester 2', '2022', '28-11-2022', 1, '28-11-2022 02:23:32', 0, '26-02-2023', NULL),
(11, 2, 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 'Trimester 2', '2022', '28-11-2022', 1, '28-11-2022 02:25:46', 0, '26-02-2023', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `id` int(11) NOT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `year` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `report` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lec_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lec_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lec_phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ended` float DEFAULT 0,
  `ending` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lec_ide` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lec_namee` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lec_phonee` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`id`, `term`, `year`, `report`, `status`, `entry_date`, `lec_id`, `lec_name`, `lec_phone`, `ended`, `ending`, `lec_ide`, `lec_namee`, `lec_phonee`, `end_date`) VALUES
(1, 'Trimester 1', '2022', '28-11-2022', 1, '27-11-2022 04:48:51', 'KEMU-2AB22', 'ABALA LORNA', '0753188269', 1, '25-02-2023', 'KEMU-2AB22', 'ABALA LORNA', '0753188269', '28-11-2022 02:13:56'),
(2, 'Trimester 2', '2022', '28-11-2022', 1, '28-11-2022 02:17:11', 'KEMU-2AB22', 'ABALA LORNA', '0753188269', 0, '26-02-2023', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `stud_id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `department` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `classification` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `remarks` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`stud_id`, `fname`, `lname`, `gender`, `email`, `password`, `phone`, `department`, `classification`, `status`, `remarks`, `date`) VALUES
('AGR-1-9022-2/2019', 'ESESE', 'AMMON', 'Male', 'esese@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0785135463', 'AGRICULTURE', 'APPLIED AGRICULTURE', 1, '', '2022-11-27 03:57:06 PM'),
('BIS-1-9022-2/2019', 'SHALLOT', 'DAMARY', 'Female', 'damary@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0782438494', 'INFORMATION SCIENCE', 'INFORMATION SCIENCE', 1, '', '2022-11-27 03:46:09 PM'),
('BIT-1-9022-2/2019', 'WANGOOLA', 'RONITO', 'Male', 'wangoolarnito@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0763455134', 'COMPUTER SCIENCE', 'BUSINESS INFORMATION TECHNOLOGY', 1, '', '2022-11-27 03:58:06 PM'),
('CIS-1-9022-2/2019', 'MAALA', 'ELVIRA', 'Female', 'maalaelvira@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0794217969', 'COMPUTER SCIENCE', 'COMPUTER INFORMATION SYSTEMS', 1, '', '2022-11-27 03:55:59 PM'),
('MAC-1-9022-2/2019', 'ISABIRYE', 'RODNEY', 'Male', 'isabirye@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0725316802', 'PURE & APPLIED SCIENCE', 'MATHEMATICS & COMPUTER SCIENCE', 1, '', '2022-11-27 03:59:53 PM'),
('MAC-1-9022-3/2019', 'ISABIRYE', 'RODNEY', 'Male', 'isabiye@gmail.com', '25d55ad283aa400af464c76d713c07ad', '0725396802', 'PURE & APPLIED SCIENCE', 'AGRICULTURAL & RURAL DEVELOPMENT', 2, 'Wrong Classification', '2022-11-27 04:03:40 PM');

-- --------------------------------------------------------

--
-- Table structure for table `summer`
--

CREATE TABLE `summer` (
  `sm` int(11) NOT NULL,
  `evt` int(11) DEFAULT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `theme` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `venue` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `land` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `site` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `money` float DEFAULT NULL,
  `reg_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` float DEFAULT 0,
  `comment` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pay` float DEFAULT 0,
  `approval` float DEFAULT 0,
  `org` float DEFAULT 0,
  `org_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `org_phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `org_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `club` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `pat` float DEFAULT 0,
  `closure` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `summer`
--

INSERT INTO `summer` (`sm`, `evt`, `ses`, `term`, `theme`, `venue`, `land`, `site`, `date`, `time`, `money`, `reg_no`, `fullname`, `phone`, `status`, `comment`, `pay`, `approval`, `org`, `org_no`, `org_phone`, `org_name`, `club`, `pat`, `closure`, `entry_date`) VALUES
(1, 1, 1, 'Trimester 1 2022', 'CODING', 'Nairobi', 'KeMU', 'CL2', '30-11-2022', '09:00AM', 250, 'BIT-1-9022-2/2019', 'WANGOOLA RONITO', '0763455134', 2, 'Indisciplined', 0, 0, 0, NULL, NULL, NULL, 'Pending', 0, 1, '28-11-2022 09:38:28'),
(2, 1, 1, 'Trimester 1 2022', 'CODING', 'Nairobi', 'KeMU', 'CL2', '30-11-2022', '09:00AM', 250, 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 1, 'Your registration was approved.', 1, 1, 1, 'CIS-1-9022-2/2019', '0794217969', 'MAALA ELVIRA', 'INNOVATORS', 1, 1, '28-11-2022 09:39:10'),
(3, 1, 1, 'Trimester 1 2022', 'CODING', 'Nairobi', 'KeMU', 'CL2', '30-11-2022', '09:00AM', 250, 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 1, 'Your registration was approved.', 1, 1, 1, 'CIS-1-9022-2/2019', '0794217969', 'MAALA ELVIRA', 'INNOVATORS', 1, 1, '28-11-2022 09:47:45');

-- --------------------------------------------------------

--
-- Table structure for table `walk`
--

CREATE TABLE `walk` (
  `wk` int(11) NOT NULL,
  `evt` int(11) DEFAULT NULL,
  `ses` int(11) DEFAULT NULL,
  `term` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `theme` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `venue` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `club` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `org_no` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `org_phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `org_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pat_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pat_phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pat_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'Pending',
  `closure` float DEFAULT 0,
  `entry_date` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `walk`
--

INSERT INTO `walk` (`wk`, `evt`, `ses`, `term`, `theme`, `venue`, `date`, `club`, `reg_no`, `fullname`, `phone`, `org_no`, `org_phone`, `org_name`, `pat_id`, `pat_phone`, `pat_name`, `end`, `closure`, `entry_date`) VALUES
(1, 1, 1, 'Trimester 1 2022', 'CODING', 'Nairobi/KeMU/CL2', '30-11-2022 09:00AM', 'INNOVATORS', 'CIS-1-9022-2/2019', 'MAALA ELVIRA', '0794217969', 'CIS-1-9022-2/2019', '0794217969', 'MAALA ELVIRA', 'KEMU-1PH22', '0763488164', 'PHANICE', 'Pending', 1, '28-11-2022 10:08:51'),
(2, 1, 1, 'Trimester 1 2022', 'CODING', 'Nairobi/KeMU/CL2', '30-11-2022 09:00AM', 'INNOVATORS', 'AGR-1-9022-2/2019', 'ESESE AMMON', '0785135463', 'CIS-1-9022-2/2019', '0794217969', 'MAALA ELVIRA', 'KEMU-1PH22', '0763488164', 'PHANICE', 'Pending', 1, '28-11-2022 10:08:51');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`inte`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adm_id`);

--
-- Indexes for table `announce`
--
ALTER TABLE `announce`
  ADD PRIMARY KEY (`ann`);

--
-- Indexes for table `cardy`
--
ALTER TABLE `cardy`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cardy_one`
--
ALTER TABLE `cardy_one`
  ADD PRIMARY KEY (`idi`);

--
-- Indexes for table `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contri`
--
ALTER TABLE `contri`
  ADD PRIMARY KEY (`pay`);

--
-- Indexes for table `contribution`
--
ALTER TABLE `contribution`
  ADD PRIMARY KEY (`pay`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`cs`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`evt`);

--
-- Indexes for table `expired`
--
ALTER TABLE `expired`
  ADD PRIMARY KEY (`expi`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`fd`);

--
-- Indexes for table `issued`
--
ALTER TABLE `issued`
  ADD PRIMARY KEY (`iss`);

--
-- Indexes for table `lec`
--
ALTER TABLE `lec`
  ADD PRIMARY KEY (`serial_no`);

--
-- Indexes for table `mover`
--
ALTER TABLE `mover`
  ADD PRIMARY KEY (`mv`);

--
-- Indexes for table `mpesa`
--
ALTER TABLE `mpesa`
  ADD PRIMARY KEY (`pay`);

--
-- Indexes for table `official`
--
ALTER TABLE `official`
  ADD PRIMARY KEY (`stud_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`pay`);

--
-- Indexes for table `quick_alrt`
--
ALTER TABLE `quick_alrt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`rep`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`stud_id`);

--
-- Indexes for table `summer`
--
ALTER TABLE `summer`
  ADD PRIMARY KEY (`sm`);

--
-- Indexes for table `walk`
--
ALTER TABLE `walk`
  ADD PRIMARY KEY (`wk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `inte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adm_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `announce`
--
ALTER TABLE `announce`
  MODIFY `ann` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cardy`
--
ALTER TABLE `cardy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `cardy_one`
--
ALTER TABLE `cardy_one`
  MODIFY `idi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `club`
--
ALTER TABLE `club`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `cs` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `evt` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `expired`
--
ALTER TABLE `expired`
  MODIFY `expi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `fd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mover`
--
ALTER TABLE `mover`
  MODIFY `mv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `quick_alrt`
--
ALTER TABLE `quick_alrt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `rep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `summer`
--
ALTER TABLE `summer`
  MODIFY `sm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `walk`
--
ALTER TABLE `walk`
  MODIFY `wk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
