-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 28, 2017 at 08:01 م
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mob_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `announce`
--

CREATE TABLE `announce` (
  `id` int(11) NOT NULL,
  `price` varchar(25) DEFAULT NULL,
  `surface` varchar(25) DEFAULT NULL,
  `wilaya` varchar(25) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `image1` varchar(25) DEFAULT NULL,
  `image2` varchar(25) DEFAULT NULL,
  `image3` varchar(25) DEFAULT NULL,
  `image4` varchar(25) DEFAULT NULL,
  `userid` varchar(250) NOT NULL,
  `type` varchar(25) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `announce`
--

INSERT INTO `announce` (`id`, `price`, `surface`, `wilaya`, `address`, `image1`, `image2`, `image3`, `image4`, `userid`, `type`, `lat`, `lng`) VALUES
(2, '2000000DA', '2560m2', 'SETIF', 'Ain Arnat', 'house1.png', 'house2.png', 'house3.png', 'house4.png', 'user@email.com', 'appartement', 36.1756547, 5.1728414),
(3, '2000000DA', '2560m2', 'SETIF', 'Ain Roua', 'house2.png', 'house3.png', 'house4.png', 'house5.png', 'user@email.com', 'appartement', 36.3210121, 5.1289818),
(4, '2000000DA', '2560m2', 'SETIF', 'Bousselam', 'house3.png', 'house4.png', 'house5.png', 'house6.png', 'user1@email.com', 'villa', 36.4752416, 5.0633026),
(5, '2000000DA', '2560m2', 'SETIF', 'El Eulma', 'house3.png', 'house4.png', 'house5.png', 'house6.png', 'user1@email.com', 'duplex', 36.1657393, 5.6199763),
(6, '2000000DA', '2560m2', 'SETIF', 'El-Ouricia', 'house4.png', 'house5.png', 'house6.png', 'house7.png', 'user2@email.com', 'studio', 36.2706725, 5.3146766),
(7, '2000000DA', '2560m2', 'ALGER', 'El Annasser', 'house5.png', 'house6.png', 'house7.png', 'house8.png', 'user2@email.com', 'duplex', 36.7344604, 3.073554),
(8, '2000000DA', '2560m2', 'ALGER', 'Kouba', 'house4.png', 'house6.png', 'house7.png', 'house8.png', 'user2@email.com', 'appartement', 36.7070104, 3.0523343),
(9, '2000000DA', '2560m2', 'ALGER', 'Bir Mourad Rais', 'house7.png', 'house8.png', 'house9.png', 'house10.png', 'user3@email.com', 'villa', 36.730271, 3.0257033),
(10, '2000000DA', '2560m2', 'ALGER', 'Cite Bel Air', 'house8.png', 'house9.png', 'house10.png', 'house11.png', 'user3@email.com', 'studio', 36.201484, 5.3954566),
(11, '2000000DA', '2560m2', 'ALGER', 'Mohammadia', 'house10.png', 'house15.png', 'house11.png', 'house12.png', 'user4@email.com', 'villa', 36.7345314, 3.1359314),
(12, '3200000DA', '2568m2', 'ADRAR', 'ADRAR', 'house3.png', 'house4.png', 'house5.png', 'house6.png', 'user1@email.com', 'appartement', 27.9763303, -0.3403043);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `idAnnounce` int(11) NOT NULL,
  `idSender` varchar(250) NOT NULL,
  `idReciever` varchar(250) NOT NULL,
  `comment` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `idAnnounce`, `idSender`, `idReciever`, `comment`) VALUES
(9, 12, 'user@email.com', 'user1@email.com', 'combien?'),
(10, 12, 'user5@email.com', 'user1@email.com', 'negociable?'),
(11, 12, 'user1@email.com', 'user1@email.com', 'hhhhh'),
(12, 12, 'user1@email.com', 'user1@email.com', 'lol'),
(13, 12, 'user1@email.com', 'user1@email.com', 'hello mob'),
(14, 12, 'user@email.com', 'user1@email.com', 'TP mobile');

-- --------------------------------------------------------

--
-- Table structure for table `meeting`
--

CREATE TABLE `meeting` (
  `id` int(11) NOT NULL,
  `idAnnounce` int(11) NOT NULL,
  `idSender` varchar(250) NOT NULL,
  `idReciever` varchar(250) NOT NULL,
  `date` varchar(25) NOT NULL,
  `time` varchar(25) NOT NULL,
  `status` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meeting`
--

INSERT INTO `meeting` (`id`, `idAnnounce`, `idSender`, `idReciever`, `date`, `time`, `status`) VALUES
(6, 12, 'user@email.com', 'user1@email.com', '17/5/2017', '12:25', 'rejected'),
(7, 11, 'user@email.com', 'user4@email.com', '16/5/2017', '13:25', 'waiting'),
(8, 12, 'user@email.com', 'user1@email.com', '20/5/2017', '10:02', 'accepted'),
(9, 12, 'user@email.com', 'user1@email.com', '20/5/2017', '22:25', 'rejected'),
(10, 12, 'user@email.com', 'user1@email.com', '20/5/2017', '12:18', 'accepted'),
(11, 2, 'user4@email.com', 'user@email.com', '20/5/2017', '10:25', 'accepted'),
(12, 4, 'user4@email.com', 'user1@email.com', '20/5/2017', '10:25', 'rejected'),
(13, 12, 'user@email.com', 'user1@email.com', '21/5/2017', '10:25', 'waiting');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`email`, `password`) VALUES
('user1@email.com', 'pass'),
('user2@email.com', 'pass'),
('user3@email.com', 'pass'),
('user4@email.com', 'pass'),
('user5@email.com', 'pass'),
('user@email.com', 'pass');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `announce`
--
ALTER TABLE `announce`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `meeting`
--
ALTER TABLE `meeting`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `announce`
--
ALTER TABLE `announce`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `meeting`
--
ALTER TABLE `meeting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
