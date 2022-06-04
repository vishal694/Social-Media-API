-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2022 at 07:41 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `basic`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `about` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `about`, `email`, `user_name`, `password`) VALUES
(2, 'I am java developer', 'vmodi@gmail.com', 'vishal', '$2a$10$ljy8psErQq49QTUMmuVhke2H.CFQ3oPZWt36k.a71sz8sU7cv3zzG'),
(3, 'I am react-js developer', 'rmodi@gmail.com', 'revi', '$2a$10$ljy8psErQq49QTUMmuVhke2H.CFQ3oPZWt36k.a71sz8sU7cv3zzG'),
(7, 'I am Angular developer', 'ypatel@gmail.com', 'Yash', '$2a$10$DbEZVt2VBdahpLIAE6uLrOJHJM/1YhrKYJv.08C4ITsqBs27QXDtK'),
(8, 'I am Android developer', 'Gshrinivash@gmail.com', 'Gaurav', '$2a$10$sYM6/pF4CNODTYTfUv.0eOyqF0G8txdYAdoik.vBsSO/UsTDO7bbO'),
(9, 'I am python developer', 'deep@gmail.com', 'Deep', '$2a$10$WUoPemsVeozckU4u.2iwOOq.DtqwlJg6gtTUTzrppSiweqgNdUzlG');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
