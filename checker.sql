-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2021 at 02:24 PM
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
(1, 3),
(1, 3),
(3, 1),
(2, 1),
(1, 2),
(6, 1),
(1, 6),
(78, 1),
(1, 78),
(84, 1),
(1, 84),
(1, 95),
(95, 1),
(84, 86),
(86, 84);

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
(10, 'Bac_Van', 'lalaldkao', '18-11-2021 21:25:31', 1),
(11, 'Bac_Giang', 'Mamuasdik', '18-11-2021 21:27:15', 84),
(13, 'Chien_Ha', NULL, '20-11-2021 13:51:45', 78);

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
(44, 'addfriend', 'Nguyen Ngoc Thanh muốn kết bạn', NULL, 1, 3, 1),
(45, 'addfriend', 'Nguyen Ngoc Thanh muốn kết bạn', NULL, 1, 2, 1),
(46, 'addfriend', 'Nguyen Ngoc Thanh muốn kết bạn', NULL, 1, 2, 1),
(47, 'addfriend', 'Thanh Nam muốn kết bạn', NULL, 0, 98, 101),
(48, 'addfriend', 'Long muốn kết bạn', NULL, 1, 3, 2),
(49, 'addfriend', 'Long muốn kết bạn', NULL, 1, 1, 2),
(50, 'joingroup', 'Hưng muốn gia nhập group', NULL, 0, 98, 3),
(51, 'joingroup', 'Hưng muốn gia nhập group', NULL, 0, 98, 3),
(52, 'joingroup', 'Cuong muốn gia nhập group', NULL, 1, 1, 6),
(53, 'addfriend', 'Tran Van Tra muốn kết bạn', NULL, 1, 1, 78),
(54, 'joingroup', 'Tran Tri Dung muốn gia nhập group', NULL, 1, 1, 80),
(55, 'addfriend', 'Tham muốn kết bạn', NULL, 1, 1, 84),
(56, 'addfriend', 'Nguyen Ngoc Thanh muốn kết bạn', NULL, 1, 95, 1),
(57, 'joingroup', 'Ho muốn gia nhập group', NULL, 1, 1, 95),
(58, 'addfriend', 'Tham muốn kết bạn', NULL, 1, 86, 84),
(59, 'joingroup', 'Hưng muốn gia nhập group', NULL, 1, 84, 3),
(60, 'joingroup', 'Cuong muốn gia nhập group', NULL, 1, 84, 6),
(61, 'joingroup', 'Linh muốn gia nhập group', NULL, 1, 84, 81),
(62, 'joingroup', 'Tran Tri Dung muốn gia nhập group', NULL, 1, 84, 80),
(63, 'joingroup', 'Le Thi Bay muốn gia nhập group', NULL, 1, 84, 79),
(64, 'joingroup', 'Le Thi Bay muốn gia nhập group', NULL, 1, 84, 79),
(65, 'joingroup', 'Xuan muốn gia nhập group', NULL, 1, 84, 99),
(66, 'joingroup', 'Le Van Van muốn gia nhập group', NULL, 1, 1, 77),
(67, 'joingroup', 'The Tung muốn gia nhập group', NULL, 1, 1, 100),
(68, 'joingroup', 'Hoang muốn gia nhập group', NULL, 1, 1, 92),
(69, 'joingroup', 'Van Phu muốn gia nhập group', NULL, 0, 84, 93),
(70, 'joingroup', 'Van Phu muốn gia nhập group', NULL, 1, 1, 93),
(71, 'joingroup', 'Le Van Van muốn gia nhập group', NULL, 1, 1, 77);

-- --------------------------------------------------------

--
-- Table structure for table `tblrank`
--

CREATE TABLE `tblrank` (
  `rankId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `battleWin` int(11) DEFAULT NULL,
  `battleLose` int(11) DEFAULT NULL,
  `totalBattle` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `winRate` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tblrank`
--

INSERT INTO `tblrank` (`rankId`, `userId`, `battleWin`, `battleLose`, `totalBattle`, `point`, `winRate`) VALUES
(1, 1, 1, 0, 2, 3, 50),
(2, 2, 3, 2, 5, 9, 60),
(3, 3, 4, 5, 9, 12, 44.44);

-- --------------------------------------------------------

--
-- Table structure for table `tblroom`
--

CREATE TABLE `tblroom` (
  `roomId` int(11) NOT NULL,
  `roomCode` int(11) NOT NULL,
  `roomName` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `roomMasterId` int(11) NOT NULL,
  `playerId` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblroom`
--

INSERT INTO `tblroom` (`roomId`, `roomCode`, `roomName`, `description`, `roomMasterId`, `playerId`, `quantity`) VALUES
(1, 94, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, NULL, 1),
(2, 40, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, NULL, 1),
(3, 45, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, 2, 1),
(4, 91, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, NULL, 1),
(5, 9, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, NULL, 1),
(6, 3, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, NULL, 1),
(7, 76, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, NULL, 1),
(8, 62, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, NULL, 1),
(9, 55, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, 2, 1),
(10, 73, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, NULL, 1),
(11, 67, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, 0, 1),
(12, 80, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, 2, 1),
(13, 76, 'Long', 'Đang chờ', 2, 0, 1),
(14, 45, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, 2, 1),
(15, 70, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, 2, 1),
(16, 928, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, 0, 1),
(17, 909, 'Nguyen Ngoc Thanh', 'Đủ', 1, 2, 2),
(18, 734, 'Nguyen Ngoc Thanh', 'Đủ', 1, 2, 2);

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
(95, '15', '15', 'Ho', 'Player', 'OFFLINE'),
(96, 'test1', '1', 'test1', 'Player', 'OFFLINE'),
(97, 'test2', '12', 'test2', 'Player', 'OFFLINE'),
(98, 'thanhbg@gmail.com', '123456', 'Nguyen  Thanh', 'Player', 'OFFLINE'),
(99, '16', '16', 'Xuan', 'Player', 'OFFLINE'),
(100, '17', '17', 'The Tung', 'Player', 'OFFLINE'),
(101, 'thanhbg1@gmail.com', '123456', 'Thanh Nam', 'Player', 'OFFLINE');

-- --------------------------------------------------------

--
-- Table structure for table `tblusergroup`
--

CREATE TABLE `tblusergroup` (
  `id` int(11) NOT NULL,
  `groupid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblusergroup`
--

INSERT INTO `tblusergroup` (`id`, `groupid`, `userid`, `position`) VALUES
(7, 10, 1, 'owner'),
(8, 11, 84, 'owner'),
(9, 10, 95, 'member'),
(11, 11, 6, 'member'),
(13, 11, 80, 'member'),
(15, 11, 79, 'member'),
(16, 11, 99, 'member'),
(19, 13, 78, 'owner'),
(21, 10, 92, 'member'),
(22, 10, 93, 'member'),
(23, 10, 77, 'member');

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
-- Stand-in structure for view `viewlistallgroup`
-- (See below for the actual view)
--
CREATE TABLE `viewlistallgroup` (
`groupid` int(11)
,`name` varchar(255)
,`notify` varchar(255)
,`createDate` varchar(50)
,`userid` int(11)
,`createName` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `viewmembergroup`
-- (See below for the actual view)
--
CREATE TABLE `viewmembergroup` (
`userid` int(11)
,`fullName` varchar(50)
,`groupid` int(11)
,`nameGroup` varchar(255)
,`position` varchar(50)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `viewrank`
-- (See below for the actual view)
--
CREATE TABLE `viewrank` (
`fullName` varchar(50)
,`userId` int(11)
,`rankId` int(11)
,`battleWin` int(11)
,`battleLose` int(11)
,`totalBattle` int(11)
,`point` int(11)
,`winRate` double
);

-- --------------------------------------------------------

--
-- Structure for view `viewfriend`
--
DROP TABLE IF EXISTS `viewfriend`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewfriend`  AS SELECT `tbluser`.`fullName` AS `fullName`, `tbluser`.`status` AS `status`, `tblfriend`.`userid` AS `userid`, `tblfriend`.`friendid` AS `friendid` FROM (`tblfriend` left join `tbluser` on(`tblfriend`.`friendid` = `tbluser`.`ID`)) ;

-- --------------------------------------------------------

--
-- Structure for view `viewlistallgroup`
--
DROP TABLE IF EXISTS `viewlistallgroup`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewlistallgroup`  AS SELECT `tblgroup`.`groupid` AS `groupid`, `tblgroup`.`name` AS `name`, `tblgroup`.`notify` AS `notify`, `tblgroup`.`createDate` AS `createDate`, `tblgroup`.`userid` AS `userid`, `tbluser`.`fullName` AS `createName` FROM (`tblgroup` join `tbluser` on(`tblgroup`.`userid` = `tbluser`.`ID`)) ;

-- --------------------------------------------------------

--
-- Structure for view `viewmembergroup`
--
DROP TABLE IF EXISTS `viewmembergroup`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewmembergroup`  AS SELECT `tblusergroup`.`userid` AS `userid`, `tbluser`.`fullName` AS `fullName`, `tblusergroup`.`groupid` AS `groupid`, `tblgroup`.`name` AS `nameGroup`, `tblusergroup`.`position` AS `position` FROM ((`tblusergroup` join `tbluser` on(`tbluser`.`ID` = `tblusergroup`.`userid`)) join `tblgroup` on(`tblgroup`.`groupid` = `tblusergroup`.`groupid`)) ;

-- --------------------------------------------------------

--
-- Structure for view `viewrank`
--
DROP TABLE IF EXISTS `viewrank`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `viewrank`  AS SELECT `tbluser`.`fullName` AS `fullName`, `tblrank`.`userId` AS `userId`, `tblrank`.`rankId` AS `rankId`, `tblrank`.`battleWin` AS `battleWin`, `tblrank`.`battleLose` AS `battleLose`, `tblrank`.`totalBattle` AS `totalBattle`, `tblrank`.`point` AS `point`, `tblrank`.`winRate` AS `winRate` FROM (`tblrank` left join `tbluser` on(`tblrank`.`userId` = `tbluser`.`ID`)) ;

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
-- Indexes for table `tblrank`
--
ALTER TABLE `tblrank`
  ADD PRIMARY KEY (`rankId`);

--
-- Indexes for table `tblroom`
--
ALTER TABLE `tblroom`
  ADD PRIMARY KEY (`roomId`);

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
  ADD KEY `userid` (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblgroup`
--
ALTER TABLE `tblgroup`
  MODIFY `groupid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tblnotify`
--
ALTER TABLE `tblnotify`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `tblrank`
--
ALTER TABLE `tblrank`
  MODIFY `rankId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tblroom`
--
ALTER TABLE `tblroom`
  MODIFY `roomId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tbluser`
--
ALTER TABLE `tbluser`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT for table `tblusergroup`
--
ALTER TABLE `tblusergroup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

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
  ADD CONSTRAINT `tblusergroup_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `tbluser` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
