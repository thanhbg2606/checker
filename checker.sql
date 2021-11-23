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
(1, 94, 'Nguyen Ngoc Thanh', 'Đang chờ', 1, NULL, 1);


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
(6, '4', '4', 'Cuong', 'Player', 'OFFLINE');

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
(7, 10, 1, 'owner');

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
