-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2021 at 01:24 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `checker`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblfriend`
--

CREATE TABLE `tblfriend` (
  `userid` int(10) NOT NULL,
  `friendid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblfriend`
--

INSERT INTO `tblfriend` (`userid`, `friendid`) VALUES
(1, 6),
(6, 1),
(3, 1),
(1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tblgroup`
--

CREATE TABLE `tblgroup` (
  `groupid` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `notify` varchar(255) DEFAULT NULL,
  `createDate` varchar(50) DEFAULT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblgroup`
--

INSERT INTO `tblgroup` (`groupid`, `name`, `notify`, `createDate`, `userid`) VALUES
(1, 'Thanh', NULL, '17-11-2021 19:17:15', 98);

-- --------------------------------------------------------

--
-- Table structure for table `tblnotify`
--

CREATE TABLE `tblnotify` (
  `ID` int(10) NOT NULL,
  `type` varchar(25) NOT NULL,
  `content` varchar(250) NOT NULL,
  `time` date DEFAULT NULL,
  `status` int(10) NOT NULL,
  `userid` int(10) DEFAULT NULL,
  `idsend` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblnotify`
--

INSERT INTO `tblnotify` (`ID`, `type`, `content`, `time`, `status`, `userid`, `idsend`) VALUES
(42, 'addfriend', 'Nguyen Ngoc Thanh muốn kết bạn', NULL, 1, 2, 1),
(43, 'addfriend', 'Hưng muốn kết bạn', NULL, 1, 1, 3),
(44, 'addfriend', 'Nguyen Ngoc Thanh muốn kết bạn', NULL, 0, 3, 1),
(45, 'addfriend', 'Nguyen Ngoc Thanh muốn kết bạn', NULL, 1, 2, 1),
(46, 'addfriend', 'Nguyen Ngoc Thanh muốn kết bạn', NULL, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbluser`
--

CREATE TABLE `tbluser` (
  `ID` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fullName` varchar(50) NOT NULL,
  `position` varchar(250) NOT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbluser`
--

INSERT INTO `tbluser` (`ID`, `username`, `password`, `fullName`, `position`, `status`) VALUES
(1, 'admin', 'admin', 'Nguyen Ngoc Thanh', 'Admin', 'OFFLINE'),
(2, '1', '1', 'Long', 'Player', 'OFFLINE'),
(3, '2', '2', 'Hưng', 'Player', 'OFFLINE'),
(4, '3', '3', 'The', 'Player', 'OFFLINE'),
(6, '4', '4', 'Cuong', 'Player', 'OFFLINE'),
(77, '5', '5', 'Le Van Van', 'Player', 'OFFLINE'),
(78, '6', '6', 'Tran Van Tra', 'Player', 'OFFLINE'),
(79, '7', '7', 'Le Thi Bay', 'Player', 'OFFLINE'),
(80, '8', '8', 'Tran Tri Dung', 'Player', 'OFFLINE'),
(81, '9', '9', 'Linh', 'Player', 'OFFLINE'),
(84, '10', '10', 'Tham', 'Player', 'OFFLINE'),
(86, '11', '11', 'Hien', 'Player', 'OFFLINE'),
(92, '12', '12', 'Hoang', 'Player', 'OFFLINE'),
(93, '13', '13', 'Van Phu', 'Player', 'OFFLINE'),
(94, '14', '14', 'Tran Ha', 'Player', 'OFFLINE'),
(95, '15', '15', 'Ho', 'Player', 'OFFLINE'),
(96, 'test1', '1', 'test1', 'Player', 'OFFLINE'),
(97, 'test2', '12', 'test2', 'Player', 'OFFLINE'),
(98, 'thanhbg@gmail.com', '123456', 'Nguyen  Thanh', 'Player', 'ONLINE'),
(99, '16', '16', 'Xuan', 'Player', 'OFFLINE'),
(100, '17', '17', 'The Tung', 'Player', 'OFFLINE');

-- --------------------------------------------------------

--
-- Table structure for table `tblusergroup`
--

CREATE TABLE `tblusergroup` (
  `id` int(11) NOT NULL,
  `groupid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `position` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Stand-in structure for view `viewfriend`
-- (See below for the actual view)
--
CREATE TABLE `viewfriend` (
`fullName` varchar(50)
,`status` varchar(20)
,`userid` int(10)
,`friendid` int(10)
);

-- --------------------------------------------------------

--
-- Structure for view `viewfriend`
--
DROP TABLE IF EXISTS `viewfriend`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewfriend`  AS SELECT `tbluser`.`fullName` AS `fullName`, `tbluser`.`status` AS `status`, `tblfriend`.`userid` AS `userid`, `tblfriend`.`friendid` AS `friendid` FROM (`tblfriend` left join `tbluser` on(`tblfriend`.`friendid` = `tbluser`.`ID`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblfriend`
--
ALTER TABLE `tblfriend`
  ADD KEY `friendid` (`friendid`);

--
-- Indexes for table `tblgroup`
--
ALTER TABLE `tblgroup`
  ADD PRIMARY KEY (`groupid`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `userid` (`userid`);

--
-- Indexes for table `tblnotify`
--
ALTER TABLE `tblnotify`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `userid` (`userid`);

--
-- Indexes for table `tbluser`
--
ALTER TABLE `tbluser`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tblusergroup`
--
ALTER TABLE `tblusergroup`
  ADD PRIMARY KEY (`id`),
  ADD KEY `groupid` (`groupid`),
  ADD KEY `userid` (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblgroup`
--
ALTER TABLE `tblgroup`
  MODIFY `groupid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tblnotify`
--
ALTER TABLE `tblnotify`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `tbluser`
--
ALTER TABLE `tbluser`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `tblusergroup`
--
ALTER TABLE `tblusergroup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblfriend`
--
ALTER TABLE `tblfriend`
  ADD CONSTRAINT `tblfriend_ibfk_1` FOREIGN KEY (`friendid`) REFERENCES `tbluser` (`ID`);

--
-- Constraints for table `tblgroup`
--
ALTER TABLE `tblgroup`
  ADD CONSTRAINT `tblgroup_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `tbluser` (`ID`);

--
-- Constraints for table `tblnotify`
--
ALTER TABLE `tblnotify`
  ADD CONSTRAINT `tblnotify_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `tbluser` (`ID`);

--
-- Constraints for table `tblusergroup`
--
ALTER TABLE `tblusergroup`
  ADD CONSTRAINT `tblusergroup_ibfk_1` FOREIGN KEY (`groupid`) REFERENCES `tblgroup` (`groupid`),
  ADD CONSTRAINT `tblusergroup_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `tbluser` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
