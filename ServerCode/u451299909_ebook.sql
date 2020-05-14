-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 13, 2020 at 01:19 PM
-- Server version: 10.2.30-MariaDB
-- PHP Version: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u451299909_ebook`
--

-- --------------------------------------------------------

--
-- Table structure for table `e_admin`
--

CREATE TABLE `e_admin` (
  `id` int(11) NOT NULL,
  `fullname` varchar(255) CHARACTER SET utf8 NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_admin`
--

INSERT INTO `e_admin` (`id`, `fullname`, `email`, `password`, `status`) VALUES
(1, 'admin', 'admin@gmail.com', '12345', 1);

-- --------------------------------------------------------

--
-- Table structure for table `e_author`
--

CREATE TABLE `e_author` (
  `a_id` int(11) NOT NULL,
  `a_title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `a_bio` text CHARACTER SET utf8 NOT NULL,
  `a_address` text CHARACTER SET utf8 NOT NULL,
  `a_image` text CHARACTER SET utf8 NOT NULL,
  `a_status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_author`
--

INSERT INTO `e_author` (`a_id`, `a_title`, `a_bio`, `a_address`, `a_image`, `a_status`) VALUES
(1, 'Jay vasavada', 'Jay Vasavada is Gujarati language writer, orator and columnist from India. Born in Bhavnagar and brought up in Gondal, Gujarat, he writes columns in various publications since 1996. He has published several books compiling his columns.', 'Far Rockaway, NY', 'avatar-2.png', 'enable'),
(2, 'Tushar Shukla', 'The man behind much loved Gujarati songs like Puchhine thay nahi prem, Mann masti ane motor cycle, Bhaasha Mari Gujarati Chhe, O Rangrasiya and many more Shukla is gifted with the art of language.', 'Los Angeles', 'avatar-3.png', 'enable'),
(3, 'Jack', 'The man behind much loved Gujarati songs like Puchhine thay nahi prem, Mann masti ane motor cycle, Bhaasha Mari Gujarati Chhe, O Rangrasiya and many more Shukla is gifted with the art of language.', 'San Francisco ,California', 'avatar-4.png', 'enable'),
(5, 'Bely Jony', 'Jay Vasavada is Gujarati language writer, orator and columnist from India. Born in Bhavnagar and brought up in Gondal, Gujarat, he writes columns in various publications since 1996. He has published several books compiling his columns.', 'Far Rockaway, NY', 'avatar-17.png', 'enable'),
(6, 'Tom Ledra', 'The man behind much loved Gujarati songs like Puchhine thay nahi prem, Mann masti ane motor cycle, Bhaasha Mari Gujarati Chhe, O Rangrasiya and many more Shukla is gifted with the art of language.', 'Los Angeles', 'avatar-18.png', 'enable'),
(7, 'Mool Johny', 'The man behind much loved Gujarati songs like Puchhine thay nahi prem, Mann masti ane motor cycle, Bhaasha Mari Gujarati Chhe, O Rangrasiya and many more Shukla is gifted with the art of language.', 'San Francisco ,California', '11.png', 'enable');

-- --------------------------------------------------------

--
-- Table structure for table `e_book`
--

CREATE TABLE `e_book` (
  `b_id` int(11) NOT NULL,
  `b_title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `b_description` text CHARACTER SET utf8 NOT NULL,
  `is_paid` int(11) NOT NULL,
  `sample_b_url` text CHARACTER SET utf8 NOT NULL,
  `b_url` text CHARACTER SET utf8 NOT NULL,
  `b_price` int(11) NOT NULL,
  `fcat_id` int(11) NOT NULL,
  `b_image` text CHARACTER SET utf8 NOT NULL,
  `readcnt` int(11) NOT NULL,
  `download` int(11) NOT NULL,
  `is_feature` text CHARACTER SET utf8 NOT NULL,
  `b_status` text CHARACTER SET utf8 NOT NULL,
  `fa_id` int(11) NOT NULL,
  `b_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_book`
--

INSERT INTO `e_book` (`b_id`, `b_title`, `b_description`, `is_paid`, `sample_b_url`, `b_url`, `b_price`, `fcat_id`, `b_image`, `readcnt`, `download`, `is_feature`, `b_status`, `fa_id`, `b_date`) VALUES
(1, 'Jay Shree Krishna (JSK)', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 0, 'book1.epub', 'book1.epub', 0, 2, 'book_00001.jpeg', 31, 101, 'yes', 'enable', 1, '2020-01-04 05:19:14'),
(3, 'FreadOm', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 1, 'book1.epub', 'book1.epub', 50, 4, 'book_00002.png', 50, 24, 'yes', 'enable', 2, '2019-07-08 01:18:37'),
(5, 'Molk Ferrari', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 1, 'book1.epub', 'book1.epub', 95, 5, 'book_00003.jpg', 25, 11, 'yes', 'enable', 3, '2019-07-08 01:22:58'),
(6, 'Rich Dad', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 1, 'book1.epub', 'book1.epub', 100, 6, 'book_00006.jpg', 30, 22, 'yes', 'enable', 1, '2019-07-08 01:22:02'),
(7, 'D-Day', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 0, 'nachiketa_1.pdf', 'nachiketa_1.pdf', 0, 7, 'book_00007.jpg', 12, 5, 'yes', 'enable', 2, '2019-07-15 04:32:22'),
(10, 'MaryKoalrd', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 1, 'book1.epub', 'book1.epub', 5, 2, 'book_00008.jpg', 12, 5, 'yes', 'enable', 3, '2019-07-18 08:26:56'),
(11, 'Gujarati', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 1, 'book1.epub', 'book1.epub', 10, 4, 'book_00009.jpg', 16, 0, 'yes', 'enable', 1, '2019-12-17 07:30:39'),
(12, 'Toll Man', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 0, 'nachiketa_1.pdf', 'nachiketa_1.pdf', 0, 5, 'book_000010.jpg', 10, 0, 'yes', 'enable', 2, '2019-07-23 02:25:04'),
(13, 'Ibrahim', 'Tghbn the same Day installation of the year and I will be in the future of our games are based on the phone with me and I will be in the', 0, 'nachiketa_1.pdf', 'nachiketa_1.pdf', 0, 6, 'book_000011.jpg', 21, 0, 'yes', 'enable', 3, '2019-07-24 12:45:42'),
(14, 'Sokao', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 0, 'book1.epub', 'book1.epub', 0, 7, 'book_000012.jpg', 5, 0, 'yes', 'enable', 1, '2019-07-26 06:08:09'),
(15, 'Warren Buffet', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', 0, 'nachiketa_1.pdf', 'nachiketa_1.pdf', 0, 2, 'book_000013.jpg', 3, 1, 'yes', 'enable', 2, '2019-07-26 06:05:50'),
(16, 'Giii Tome', 'Books are linked automatically if the author name and book title of both editions match exactly. During the publishing process, KDP tries to match book details like title and author name to other editions available in the Amazon catalog. Linking these formats provides the ideal browsing experience for customers, so you\'ll want to make sure the title and author name you entered for your eBook and paperback match exactly.', 0, 'book1.epub', 'book1.epub', 0, 4, '31576502131.jpg', 11, 0, 'yes', 'enable', 3, '2019-12-16 01:15:31');

-- --------------------------------------------------------

--
-- Table structure for table `e_bookmark`
--

CREATE TABLE `e_bookmark` (
  `bo_id` int(11) NOT NULL,
  `bo_user_id` int(11) NOT NULL,
  `bo_b_id` int(11) NOT NULL,
  `bo_datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `e_bookmark`
--

INSERT INTO `e_bookmark` (`bo_id`, `bo_user_id`, `bo_b_id`, `bo_datetime`) VALUES
(5, 4, 3, '2019-07-15 11:38:59'),
(6, 5, 10, '2019-07-15 23:08:15'),
(7, 8, 2, '2019-07-23 23:14:11'),
(8, 11, 1, '2019-07-24 12:31:29'),
(9, 14, 3, '2019-08-03 12:33:17'),
(11, 18, 3, '2019-08-14 13:45:21'),
(12, 19, 6, '2019-08-15 15:48:41'),
(15, 27, 16, '2019-09-03 05:03:33'),
(16, 1, 5, '2019-09-13 08:27:14'),
(19, 37, 14, '2019-10-06 17:41:42'),
(20, 46, 11, '2019-10-19 22:44:29'),
(21, 47, 1, '2019-10-20 23:44:33'),
(22, 40, 15, '2019-10-23 03:41:34'),
(23, 49, 16, '2019-10-31 09:25:17'),
(24, 50, 11, '2019-11-02 20:12:01'),
(25, 52, 1, '2019-11-06 08:33:02'),
(27, 1, 13, '2019-11-13 06:14:58'),
(28, 1, 6, '2019-11-13 06:16:03'),
(29, 55, 3, '2019-11-15 21:14:33'),
(30, 55, 10, '2019-11-15 21:19:52'),
(31, 57, 1, '2019-11-19 10:27:40'),
(32, 58, 3, '2019-11-19 10:32:22'),
(33, 59, 3, '2019-11-19 11:06:00'),
(34, 59, 16, '2019-11-19 11:08:04'),
(35, 60, 1, '2019-11-19 16:38:20'),
(36, 63, 15, '2019-11-22 12:54:23'),
(37, 65, 3, '2019-11-25 09:26:35'),
(39, 1, 14, '2019-12-03 06:03:44'),
(40, 1, 3, '2019-12-03 06:10:49'),
(43, 72, 7, '2019-12-06 08:39:27'),
(48, 73, 5, '2019-12-13 02:39:37'),
(49, 76, 1, '2019-12-17 13:04:32'),
(51, 8, 1, '2019-12-21 06:58:06'),
(52, 8, 3, '2019-12-21 07:30:13'),
(53, 8, 7, '2019-12-21 07:30:18'),
(54, 8, 14, '2019-12-21 07:30:21'),
(56, 83, 15, '2019-12-22 13:48:24'),
(57, 88, 11, '2019-12-26 02:11:00'),
(66, 73, 11, '2019-12-29 14:38:14'),
(69, 75, 16, '2019-12-31 11:07:13'),
(70, 94, 13, '2019-12-31 19:47:53'),
(89, 94, 12, '2020-01-06 06:45:04'),
(90, 99, 6, '2020-01-08 11:58:19'),
(93, 105, 3, '2020-01-12 00:18:00'),
(99, 73, 16, '2020-01-13 05:20:35'),
(100, 110, 14, '2020-01-13 09:19:03'),
(101, 110, 3, '2020-01-13 10:09:42'),
(103, 112, 11, '2020-01-14 13:58:21'),
(105, 112, 5, '2020-01-14 14:14:08'),
(120, 117, 7, '2020-01-16 21:22:52'),
(121, 119, 1, '2020-01-17 08:29:36'),
(122, 1, 10, '2020-01-18 16:32:27'),
(123, 121, 3, '2020-01-18 18:41:04'),
(124, 122, 12, '2020-01-19 03:06:57'),
(125, 129, 3, '2020-01-22 12:38:15'),
(126, 131, 1, '2020-01-22 15:58:47'),
(127, 73, 3, '2020-01-31 13:07:22'),
(130, 150, 3, '2020-02-05 09:51:16'),
(131, 151, 13, '2020-02-05 12:58:38'),
(132, 152, 3, '2020-02-09 06:51:52'),
(133, 153, 1, '2020-02-09 17:31:59'),
(134, 154, 12, '2020-02-10 19:10:31'),
(135, 154, 11, '2020-02-10 19:13:22'),
(137, 154, 14, '2020-02-10 19:13:37'),
(138, 155, 1, '2020-02-10 20:49:37'),
(139, 154, 16, '2020-02-11 13:07:59'),
(140, 158, 3, '2020-02-12 22:00:02'),
(141, 1, 1, '2020-02-13 07:24:22'),
(142, 160, 11, '2020-02-13 10:35:00'),
(143, 150, 11, '2020-02-13 11:46:20');

-- --------------------------------------------------------

--
-- Table structure for table `e_category`
--

CREATE TABLE `e_category` (
  `cat_id` int(11) NOT NULL,
  `cat_name` text CHARACTER SET utf8 NOT NULL,
  `cat_image` text CHARACTER SET utf8 NOT NULL,
  `cat_date` datetime NOT NULL,
  `cat_status` text CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_category`
--

INSERT INTO `e_category` (`cat_id`, `cat_name`, `cat_image`, `cat_date`, `cat_status`) VALUES
(2, 'Technology', 'book2.png', '2019-07-09 10:00:30', 'enable'),
(4, 'Comic', 'book4.png', '2019-07-09 10:00:58', 'enable'),
(5, 'Business', 'book5.jpg', '2019-07-09 10:04:08', 'enable'),
(6, 'Technology', '3.jpg', '2019-06-20 00:00:00', 'enable'),
(7, 'God', '3.jpg', '2019-06-05 00:00:00', 'enable'),
(16, 'Na', 'mit1581102705.jpg', '2020-02-07 07:11:45', 'enable');

-- --------------------------------------------------------

--
-- Table structure for table `e_comment`
--

CREATE TABLE `e_comment` (
  `c_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `comment` text CHARACTER SET utf8 NOT NULL,
  `user_id` int(11) NOT NULL,
  `c_status` tinyint(1) NOT NULL,
  `c_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_comment`
--

INSERT INTO `e_comment` (`c_id`, `b_id`, `comment`, `user_id`, `c_status`, `c_date`) VALUES
(1, 1, 'test', 1, 0, '2019-07-01 15:10:51'),
(2, 1, 'test', 1, 0, '2019-07-01 17:18:40'),
(3, 1, 'test234', 1, 0, '2019-07-01 17:18:43'),
(4, 1, 'Nice One, Awesome', 1, 0, '2019-07-01 12:01:14'),
(5, 3, 'this is testing comment , please check', 1, 0, '2019-07-05 06:58:21'),
(6, 3, 'its really awesome book for all, specially for new comer which start book recently,\nhappy to read this book. highly recommend to this book.\nawesome', 1, 0, '2019-07-05 07:20:33'),
(7, 7, 'Test', 0, 0, '2019-07-15 04:33:19'),
(8, 1, 'hy3', 0, 0, '2019-07-15 11:36:56'),
(9, 3, 'jwjei', 4, 0, '2019-07-15 11:41:46'),
(10, 3, '????', 0, 0, '2019-07-15 15:55:06'),
(11, 3, 'Good', 0, 0, '2019-07-22 13:12:28'),
(12, 3, 'Good', 0, 0, '2019-07-22 13:13:38'),
(13, 4, 'good', 0, 0, '2019-07-22 13:14:38'),
(14, 6, 'nkf', 0, 0, '2019-07-26 09:38:32'),
(15, 2, 'tes', 17, 0, '2019-08-12 04:12:15'),
(16, 15, 'hello', 19, 0, '2019-08-15 16:48:05'),
(17, 3, 'Hi', 12, 0, '2019-08-20 06:35:15'),
(18, 2, 'mfmf', 21, 0, '2019-08-21 14:49:28'),
(19, 5, 'md', 21, 0, '2019-08-21 14:50:24'),
(20, 6, 'j', 0, 0, '2019-08-27 06:54:33'),
(21, 6, 'js', 0, 0, '2019-08-27 06:54:39'),
(22, 16, '????', 27, 0, '2019-09-03 05:03:54'),
(23, 3, 'Salam', 1, 0, '2019-09-07 07:13:10'),
(24, 5, 'ttt', 32, 0, '2019-09-19 03:34:38'),
(25, 3, 'nice', 0, 0, '2019-10-03 15:37:44'),
(26, 1, 'y7yuy7', 0, 0, '2019-10-04 21:16:29'),
(27, 6, 'this is a good app', 36, 0, '2019-10-05 07:24:53'),
(28, 3, 'hello', 0, 0, '2019-10-06 17:40:29'),
(29, 3, 'hai', 0, 0, '2019-10-06 17:40:37'),
(30, 1, 'woe', 37, 0, '2019-10-06 17:43:33'),
(31, 6, 'what a book', 34, 0, '2019-10-08 03:13:05'),
(32, 3, 'test', 40, 0, '2019-10-10 14:07:20'),
(33, 11, 'goode', 41, 0, '2019-10-11 04:53:26'),
(34, 3, 'test', 0, 0, '2019-10-13 06:59:13'),
(35, 11, 'test', 0, 0, '2019-10-13 07:00:02'),
(36, 15, 'test comment', 0, 0, '2019-10-19 17:05:30'),
(37, 1, 'checking', 47, 0, '2019-10-20 23:43:43'),
(38, 1, 'what a great book ', 47, 0, '2019-10-20 23:43:53'),
(39, 15, 'hji', 40, 0, '2019-10-23 03:41:42'),
(40, 16, 'good', 49, 0, '2019-11-02 03:59:07'),
(41, 1, 'tesf', 0, 0, '2019-11-02 14:49:44'),
(42, 10, 'hi', 53, 0, '2019-11-07 14:59:01'),
(43, 3, 'hi', 55, 0, '2019-11-15 21:15:58'),
(44, 7, 'ssd', 55, 0, '2019-11-15 21:17:32'),
(45, 10, 'hello', 0, 0, '2019-11-16 07:25:14'),
(46, 1, 'test', 0, 0, '2019-11-19 10:20:28'),
(47, 3, 'hi', 59, 0, '2019-11-19 11:06:09'),
(48, 1, 'jhi', 0, 0, '2019-11-19 17:31:59'),
(49, 1, 'test', 0, 0, '2019-11-19 17:32:12'),
(50, 1, 'hiii', 0, 0, '2019-11-19 17:32:21'),
(51, 1, 'good', 0, 0, '2019-11-21 12:59:35'),
(52, 1, 'good', 0, 0, '2019-11-21 13:00:39'),
(53, 1, 'sip', 0, 0, '2019-11-25 08:19:08'),
(54, 7, 'sip', 0, 0, '2019-11-25 08:23:43'),
(55, 3, 'Nice app', 66, 0, '2019-11-27 06:11:59'),
(56, 3, 'yes', 0, 0, '2019-11-30 17:12:15'),
(57, 3, 'test', 0, 0, '2019-12-13 16:01:24'),
(58, 3, 'Haaa', 0, 0, '2019-12-16 08:52:12'),
(59, 3, 'Hi', 0, 0, '2019-12-16 08:52:23'),
(60, 3, ') lllllllllll', 0, 0, '2019-12-16 08:52:36'),
(61, 16, 'fugj', 0, 0, '2019-12-17 17:30:33'),
(62, 11, 'nice', 73, 0, '2019-12-20 18:10:13'),
(63, 3, 'tolol', 0, 0, '2019-12-22 08:43:03'),
(64, 3, 'colok meki', 0, 0, '2019-12-22 08:43:26'),
(65, 5, 'hummm', 81, 0, '2019-12-22 10:41:42'),
(66, 3, 'yes', 82, 0, '2019-12-22 11:26:07'),
(67, 5, 'test', 83, 0, '2019-12-22 14:01:55'),
(68, 13, 'g', 81, 0, '2019-12-23 15:32:05'),
(69, 3, 'ggg', 86, 0, '2019-12-25 15:28:37'),
(70, 3, 'Hbhnj', 0, 0, '2019-12-25 15:37:02'),
(71, 5, 'test', 0, 0, '2019-12-26 02:09:40'),
(72, 5, 'hhhhhhhh', 0, 0, '2019-12-26 02:09:47'),
(73, 5, 'ggy', 0, 0, '2019-12-26 02:09:54'),
(74, 11, 'ok', 88, 0, '2019-12-26 02:11:04'),
(75, 15, 'test', 0, 0, '2019-12-29 12:59:24'),
(76, 15, 'ejej', 0, 0, '2019-12-29 12:59:41'),
(77, 1, '123', 0, 0, '2019-12-29 13:00:16'),
(78, 7, 'test', 91, 0, '2019-12-29 13:19:02'),
(79, 1, 'test1', 73, 0, '2019-12-29 13:21:05'),
(80, 1, 'test1', 73, 0, '2019-12-29 13:25:12'),
(81, 1, 'test2', 73, 0, '2019-12-29 13:27:32'),
(82, 1, 'test3', 73, 0, '2019-12-29 13:28:10'),
(83, 1, 'test4', 73, 0, '2019-12-29 13:38:25'),
(84, 5, 'kki', 93, 0, '2019-12-29 21:07:00'),
(85, 3, 'hello', 0, 0, '2019-12-30 11:49:22'),
(86, 6, 'testing ', 75, 0, '2019-12-31 11:08:52'),
(87, 5, 'test', 0, 0, '2019-12-31 19:20:29'),
(88, 3, 'jsnsns', 99, 0, '2020-01-08 11:51:32'),
(89, 11, 'hello', 103, 0, '2020-01-10 20:33:59'),
(90, 3, 'salim', 106, 0, '2020-01-12 11:49:30'),
(91, 15, 'nn', 0, 0, '2020-01-12 16:34:41'),
(92, 15, 'gggg', 0, 0, '2020-01-12 16:34:50'),
(93, 3, 'تطبيق جميل', 0, 0, '2020-01-12 16:36:25'),
(94, 13, 'nice app', 109, 0, '2020-01-12 16:39:37'),
(95, 11, 'is this support epub ?', 110, 0, '2020-01-13 09:18:08'),
(96, 16, 'wow', 100, 0, '2020-01-13 15:27:06'),
(97, 3, 'thankg', 0, 0, '2020-01-14 02:20:25'),
(98, 1, 'good', 112, 0, '2020-01-14 13:52:39'),
(99, 5, 'bsjsns', 99, 0, '2020-01-14 14:40:15'),
(100, 13, 'Lol', 114, 0, '2020-01-14 18:29:15'),
(101, 1, 'Nice', 116, 0, '2020-01-15 21:49:49'),
(102, 1, 'gwhdjwhe', 1, 0, '2020-01-18 14:45:12'),
(103, 12, 'hi', 122, 0, '2020-01-19 03:07:09'),
(104, 3, 'agung', 0, 0, '2020-01-21 10:07:03'),
(105, 1, 'good', 1, 0, '2020-01-23 07:23:22'),
(106, 1, 'تحربة', 0, 0, '2020-01-23 20:31:13'),
(107, 1, 'rtgg', 121, 0, '2020-01-26 05:07:47'),
(108, 1, 'IP', 121, 0, '2020-01-26 05:07:51'),
(109, 13, 'huu', 0, 0, '2020-01-29 12:19:01'),
(110, 13, 'qwerty 21', 0, 0, '2020-01-29 12:19:14'),
(111, 1, 'hi', 152, 0, '2020-02-09 06:50:09'),
(112, 1, 'I like it', 0, 0, '2020-02-10 20:45:19'),
(113, 12, 'fresh', 156, 0, '2020-02-10 20:51:58'),
(114, 3, 'h', 160, 0, '2020-02-13 10:35:21');

-- --------------------------------------------------------

--
-- Table structure for table `e_continue_read`
--

CREATE TABLE `e_continue_read` (
  `co_id` int(11) NOT NULL,
  `co_user_id` int(11) NOT NULL,
  `co_b_id` int(11) NOT NULL,
  `co_datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_continue_read`
--

INSERT INTO `e_continue_read` (`co_id`, `co_user_id`, `co_b_id`, `co_datetime`) VALUES
(1, 1, 1, '2019-07-02 17:34:57'),
(2, 2, 2, '2019-07-02 17:35:14');

-- --------------------------------------------------------

--
-- Table structure for table `e_download`
--

CREATE TABLE `e_download` (
  `bd_id` int(11) NOT NULL,
  `bd_user_id` int(11) NOT NULL,
  `bd_b_id` int(11) NOT NULL,
  `bd_datetime` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_download`
--

INSERT INTO `e_download` (`bd_id`, `bd_user_id`, `bd_b_id`, `bd_datetime`) VALUES
(5, 8, 1, '2020-02-13 12:46:54'),
(6, 8, 15, '2020-02-13 12:47:48');

-- --------------------------------------------------------

--
-- Table structure for table `e_general_setting`
--

CREATE TABLE `e_general_setting` (
  `id` int(11) NOT NULL,
  `key` varchar(255) NOT NULL,
  `value` text CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_general_setting`
--

INSERT INTO `e_general_setting` (`id`, `key`, `value`) VALUES
(10, 'host_email', 'support@divinetechs.com'),
(11, 'app_name', 'EBook'),
(12, 'app_desripation', 'E Book app is an android application.E Book App has user-friendly interface with easy to manage. The Quotes Pro are stored in Server Side for easy editing and better performance. You can create apps Different types of Category and Author.The application is specially optimized to be extremely easy to configure and detailed documentation is provided.'),
(13, 'app_logo', 'App-icon.png'),
(14, 'app_version', '1.0'),
(15, 'Author', 'DivineTechs'),
(16, 'contact', '+91 7984859403'),
(17, 'email', 'support@divinetechs.com'),
(18, 'website', 'www.divinetechs.com'),
(19, 'privacy_policy', 'At Website Name, accessible at Website.com, one of our main priorities is the privacy of our visitors. This Privacy Policy document contains types of information that is collected and recorded by Website Name and how we use it.\r\n\r\nIf you have additional questions or require more information about our Privacy Policy, do not hesitate to contact us through email at Email@Website.com\r\n\r\nLog Files\r\n\r\nWebsite Name follows a standard procedure of using log files. These files log visitors when they visit websites. All hosting companies do this and a part of hosting services\' analytics. The information collected by log files include internet protocol (IP) addresses, browser type, Internet Service Provider (ISP), date and time stamp, referring/exit pages, and possibly the number of clicks. These are not linked to any information that is personally identifiable. The purpose of the information is for analyzing trends, administering the site, tracking users\' movement on the website, and gathering demographic information.\r\n\r\nCookies and Web Beacons\r\nLike any other website, Website Name uses ‘cookies\'. These cookies are used to store information including visitors\' preferences, and the pages on the website that the visitor accessed or visited. The information is used to optimize the users\' experience by customizing our web page content based on visitors\' browser type and/or other information.\r\n\r\nDoubleClick DART Cookie\r\nGoogle is one of a third-party vendor on our site. It also uses cookies, known as DART cookies, to serve ads to our site visitors based upon their visit to www.website.com and other sites on the internet. However, visitors may choose to decline the use of DART cookies by visiting the Google ad and content network Privacy Policy at the following URL – https://policies.google.com/technologies/ads.\r\n\r\nSome of advertisers on our site may use cookies and web beacons. Our advertising partners are listed below. Each of our advertising partners has their own Privacy Policy for their policies on user data. For easier access, we hyperlinked to their Privacy Policies below.'),
(20, 'publisher_id', 'ca-app-pub-3899560992752771~5875212205'),
(21, 'banner_ad', 'yes'),
(22, 'banner_adid', 'ca-app-pub-3899560992752771/8364524612'),
(23, 'interstital_ad', 'yes'),
(24, 'interstital_adid', 'ca-app-pub-3899560992752771/1714166419'),
(25, 'interstital_adclick', '5'),
(26, 'onesignal_apid', '6e703206-bd5e-4aba-9dee-4ddcd6d96db6'),
(27, 'onesignal_rest_key', 'ZjJkMzhiOGQtYTZlOC00ZWRjLTk5MGYtNTNlZGFkNTFjNDgw'),
(28, 'custom_ads', 'no'),
(29, 'custom_image', 'Google-AdMob-Tutorial-Result1576501169.png'),
(30, 'paypal_name', 'DivineTechs'),
(31, 'paypal_client_id', 'AR2Grx4Mmu4IDhhFyrpCfcCaHJt2B2GTj4zdCU_gzUBG0kt3SPrjFMvsgUr9ajT6uuoFxq2xx6iwZRXN'),
(32, 'UPI_Name', 'chirag patel'),
(33, 'UPI', 'ckpatel@icici');

-- --------------------------------------------------------

--
-- Table structure for table `e_rating`
--

CREATE TABLE `e_rating` (
  `rating_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `rating` text CHARACTER SET utf8 NOT NULL,
  `c_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `e_rating`
--

INSERT INTO `e_rating` (`rating_id`, `book_id`, `user_id`, `rating`, `c_date`) VALUES
(26, 3, 1, '4.13179', '2019-10-15 06:31:52'),
(27, 1, 1, '3.4149218', '2019-10-15 06:32:01'),
(28, 6, 1, '4.9974813', '2019-10-15 06:32:09'),
(29, 5, 1, '1.8000001', '2019-10-15 06:32:18'),
(30, 12, 1, '3.697767', '2019-10-15 06:32:30'),
(31, 14, 1, '3.8', '2019-10-15 06:32:37'),
(32, 15, 1, '4.376551', '2019-10-15 06:32:42'),
(33, 3, 0, '4.1307325', '2019-10-15 10:40:33'),
(34, 16, 42, '0.0', '2019-10-16 01:47:01'),
(35, 11, 42, '0.0', '2019-10-16 01:47:12'),
(36, 3, 42, '4.2', '2019-10-16 01:47:17'),
(37, 1, 42, '3.3999999', '2019-10-16 01:52:17'),
(38, 12, 0, '3.697844', '2019-10-16 22:22:05'),
(39, 5, 0, '1.8001555', '2019-10-16 22:22:32'),
(40, 1, 43, '3.3999999', '2019-10-16 22:23:42'),
(41, 16, 43, '0.0', '2019-10-16 22:24:18'),
(42, 1, 0, '3.4149277', '2019-10-17 04:52:23'),
(43, 11, 0, '0.3876691', '2019-10-19 11:10:23'),
(44, 13, 0, '0.9838484', '2019-10-19 11:11:11'),
(45, 1, 44, '3.3999999', '2019-10-19 11:12:35'),
(46, 15, 44, '4.5', '2019-10-19 11:15:01'),
(47, 16, 44, '0.0', '2019-10-19 11:15:26'),
(48, 5, 44, '1.8000001', '2019-10-19 11:15:58'),
(49, 15, 0, '4.376551', '2019-10-19 17:05:13'),
(50, 3, 45, '4.2', '2019-10-19 17:07:17'),
(51, 1, 45, '3.3999999', '2019-10-19 17:07:23'),
(52, 16, 0, '1.1719556', '2019-10-19 22:42:20'),
(53, 3, 46, '4.2', '2019-10-19 22:42:58'),
(54, 1, 46, '3.3999999', '2019-10-19 22:43:01'),
(55, 6, 46, '5.0', '2019-10-19 22:43:39'),
(56, 5, 46, '1.8000001', '2019-10-19 22:43:42'),
(57, 13, 46, '0.0', '2019-10-19 22:43:44'),
(58, 11, 46, '0.0', '2019-10-19 22:43:55'),
(59, 12, 46, '3.7', '2019-10-19 22:45:41'),
(60, 1, 47, '3.3999999', '2019-10-20 23:43:34'),
(61, 3, 47, '4.2', '2019-10-20 23:44:37'),
(62, 11, 47, '0.0', '2019-10-20 23:45:29'),
(63, 16, 47, '0.0', '2019-10-20 23:45:34'),
(64, 15, 47, '4.5', '2019-10-21 12:27:37'),
(65, 6, 0, '4.997242', '2019-10-25 07:37:34'),
(66, 14, 0, '3.8', '2019-10-25 16:40:06'),
(67, 15, 34, '4.5', '2019-10-26 11:54:45'),
(68, 3, 34, '4.2', '2019-10-26 11:54:51'),
(69, 16, 48, '0.0', '2019-10-27 05:59:59'),
(70, 13, 48, '1.95', '2019-10-27 06:00:16'),
(71, 10, 48, '0.0', '2019-10-27 06:00:20'),
(72, 16, 49, '0.0', '2019-10-31 09:24:22'),
(73, 3, 49, '4.2', '2019-10-31 09:25:32'),
(74, 15, 49, '5.0', '2019-10-31 09:26:33'),
(75, 5, 49, '1.8000001', '2019-10-31 09:27:14'),
(76, 3, 50, '4.2', '2019-11-02 20:08:53'),
(77, 11, 50, '0.0', '2019-11-02 20:09:37'),
(78, 16, 50, '0.0', '2019-11-02 20:12:16'),
(79, 1, 51, '3.5375', '2019-11-03 16:03:27'),
(80, 3, 51, '4.2', '2019-11-03 16:03:59'),
(81, 6, 51, '5.0', '2019-11-03 16:04:02'),
(82, 11, 51, '0.0', '2019-11-04 03:42:22'),
(83, 5, 51, '1.8000001', '2019-11-04 03:42:45'),
(84, 12, 51, '3.7', '2019-11-04 03:43:26'),
(85, 10, 0, '0.3299022', '2019-11-05 08:19:38'),
(86, 1, 52, '3.4171877', '2019-11-06 08:31:49'),
(87, 1, 53, '3.4171875', '2019-11-07 14:57:36'),
(88, 6, 53, '5.0', '2019-11-07 14:57:47'),
(89, 10, 53, '0.0', '2019-11-07 14:58:52'),
(90, 16, 53, '3.5', '2019-11-10 12:46:03'),
(91, 14, 53, '3.8', '2019-11-10 12:48:09'),
(92, 7, 53, '0.0', '2019-11-10 12:48:26'),
(93, 13, 1, '0.98216784', '2019-11-13 06:14:54'),
(94, 10, 1, '0.32990322', '2019-11-13 07:38:45'),
(95, 1, 54, '3.418892', '2019-11-14 22:10:17'),
(96, 6, 54, '5.0', '2019-11-14 22:10:27'),
(97, 3, 55, '4.2363634', '2019-11-15 21:14:16'),
(98, 11, 55, '0.0', '2019-11-15 21:16:19'),
(99, 16, 55, '0.43209878', '2019-11-15 21:16:31'),
(100, 7, 55, '0.0', '2019-11-15 21:17:25'),
(101, 15, 55, '4.6', '2019-11-15 21:18:48'),
(102, 10, 55, '0.0', '2019-11-15 21:19:37'),
(103, 7, 0, '0.0', '2019-11-18 15:19:07'),
(104, 10, 56, '0.86', '2019-11-18 17:40:17'),
(105, 5, 56, '1.8000001', '2019-11-18 17:41:56'),
(106, 12, 56, '3.7', '2019-11-18 17:42:00'),
(107, 3, 56, '4.2039638', '2019-11-18 17:45:05'),
(108, 11, 56, '0.0', '2019-11-18 17:47:04'),
(109, 16, 56, '0.43689507', '2019-11-18 17:47:10'),
(110, 1, 57, '3.4175363', '2019-11-19 10:27:36'),
(111, 11, 57, '3.1000001', '2019-11-19 10:29:04'),
(112, 3, 58, '4.2039967', '2019-11-19 10:32:16'),
(113, 15, 57, '4.6', '2019-11-19 10:59:12'),
(114, 3, 59, '4.2039967', '2019-11-19 11:05:49'),
(115, 11, 59, '0.38271606', '2019-11-19 11:07:23'),
(116, 16, 59, '4.8', '2019-11-19 11:07:36'),
(117, 14, 59, '3.8', '2019-11-19 11:08:43'),
(118, 15, 59, '4.6', '2019-11-19 11:09:16'),
(119, 10, 59, '0.86', '2019-11-19 11:09:42'),
(120, 1, 59, '3.4189308', '2019-11-19 11:12:12'),
(121, 1, 60, '3.418922', '2019-11-19 16:38:18'),
(122, 3, 61, '4.2039967', '2019-11-19 17:36:00'),
(123, 3, 54, '4.2039967', '2019-11-19 23:43:36'),
(124, 14, 54, '3.8', '2019-11-19 23:46:43'),
(125, 3, 62, '4.2039967', '2019-11-20 16:19:39'),
(126, 11, 62, '0.38696805', '2019-11-20 16:20:42'),
(127, 16, 54, '0.8335258', '2019-11-20 20:54:39'),
(128, 15, 63, '2.7', '2019-11-22 12:53:58'),
(129, 16, 1, '1.2134701', '2019-11-25 07:39:22'),
(130, 16, 64, '1.0372354', '2019-11-25 08:01:25'),
(131, 3, 64, '4.204019', '2019-11-25 08:19:47'),
(132, 1, 64, '3.418922', '2019-11-25 08:22:43'),
(133, 3, 65, '4.204019', '2019-11-25 09:16:44'),
(134, 1, 65, '3.418922', '2019-11-25 09:17:54'),
(135, 10, 65, '0.29836735', '2019-11-25 09:19:40'),
(136, 5, 65, '1.8000001', '2019-11-25 09:20:08'),
(137, 16, 65, '0.8335433', '2019-11-25 09:23:34'),
(138, 14, 65, '3.8', '2019-11-25 09:24:00'),
(139, 16, 66, '4.5', '2019-11-25 15:43:32'),
(140, 10, 66, '0.29836735', '2019-11-25 15:44:58'),
(141, 14, 66, '3.8', '2019-11-25 15:45:11'),
(142, 3, 66, '4.204019', '2019-11-25 15:47:11'),
(143, 11, 66, '0.38696837', '2019-11-25 15:47:26'),
(144, 16, 67, '0.8335433', '2019-11-26 13:38:21'),
(145, 5, 67, '1.8000001', '2019-11-26 13:38:50'),
(146, 1, 66, '3.418922', '2019-11-27 06:33:00'),
(147, 14, 68, '3.8', '2019-11-28 08:05:54'),
(148, 1, 6, '3.418922', '2019-11-30 16:20:22'),
(149, 13, 64, '0.98854166', '2019-12-03 02:51:34'),
(150, 15, 64, '4.388889', '2019-12-03 02:52:02'),
(151, 11, 1, '0.38791248', '2019-12-03 06:11:27'),
(152, 7, 1, '0.0', '2019-12-03 07:00:42'),
(153, 3, 8, '4.1329494', '2019-12-03 13:56:04'),
(154, 1, 8, '3.4149277', '2019-12-03 13:57:18'),
(155, 6, 8, '4.9974003', '2019-12-03 14:00:01'),
(156, 5, 8, '1.8000001', '2019-12-03 14:03:21'),
(157, 3, 69, '4.2040205', '2019-12-03 14:40:37'),
(158, 13, 69, '0.9829083', '2019-12-03 14:41:23'),
(159, 12, 69, '3.7', '2019-12-03 14:41:28'),
(160, 10, 69, '0.32586303', '2019-12-03 14:41:31'),
(161, 6, 69, '5.0', '2019-12-03 14:42:37'),
(162, 12, 67, '3.7', '2019-12-04 08:11:02'),
(163, 13, 67, '0.9812171', '2019-12-04 08:14:37'),
(164, 1, 70, '3.4189353', '2019-12-04 16:39:09'),
(165, 15, 70, '4.376551', '2019-12-04 16:39:32'),
(166, 3, 71, '4.2040205', '2019-12-05 23:13:16'),
(167, 6, 71, '5.0', '2019-12-05 23:14:26'),
(168, 16, 71, '1.075332', '2019-12-05 23:14:35'),
(169, 1, 72, '3.418932', '2019-12-06 08:36:40'),
(170, 15, 72, '4.376567', '2019-12-06 08:36:57'),
(171, 7, 72, '0.0', '2019-12-06 08:39:16'),
(172, 11, 72, '0.3869684', '2019-12-06 08:40:31'),
(173, 3, 72, '4.2040205', '2019-12-06 08:40:54'),
(174, 6, 64, '5.0', '2019-12-11 23:29:38'),
(175, 3, 73, '4.136833', '2019-12-12 17:07:14'),
(176, 14, 73, '3.8', '2019-12-12 17:07:33'),
(177, 1, 73, '3.4189358', '2019-12-13 02:39:10'),
(178, 15, 73, '4.376552', '2019-12-13 02:39:14'),
(179, 5, 73, '1.8000002', '2019-12-13 02:39:32'),
(180, 15, 74, '4.376567', '2019-12-13 09:15:31'),
(181, 6, 74, '5.0', '2019-12-13 09:15:38'),
(182, 3, 74, '4.2040205', '2019-12-13 09:16:37'),
(183, 16, 74, '1.0753322', '2019-12-13 09:16:45'),
(184, 13, 74, '0.9812171', '2019-12-13 09:16:59'),
(185, 11, 64, '0.3869684', '2019-12-17 07:39:29'),
(186, 11, 75, '0.38734064', '2019-12-17 09:40:55'),
(187, 1, 76, '3.418932', '2019-12-17 13:04:31'),
(188, 3, 76, '4.2040205', '2019-12-17 13:04:44'),
(189, 15, 77, '4.376567', '2019-12-17 14:13:11'),
(190, 1, 77, '3.4189358', '2019-12-17 14:13:19'),
(191, 10, 77, '0.32904792', '2019-12-17 14:13:22'),
(192, 11, 8, '0.38767672', '2019-12-18 04:19:41'),
(193, 14, 75, '3.8', '2019-12-18 05:05:31'),
(194, 16, 75, '1.0719793', '2019-12-18 05:05:37'),
(195, 1, 75, '3.4189372', '2019-12-18 05:52:27'),
(196, 13, 75, '0.98099554', '2019-12-18 05:52:44'),
(197, 7, 75, '0.0', '2019-12-18 05:52:47'),
(198, 12, 75, '3.7', '2019-12-18 06:01:14'),
(199, 3, 75, '4.2035766', '2019-12-18 06:09:45'),
(200, 6, 75, '5.0', '2019-12-18 06:09:51'),
(201, 5, 75, '1.8000001', '2019-12-18 06:09:54'),
(202, 10, 75, '0.3296446', '2019-12-18 06:10:02'),
(203, 15, 75, '4.3765545', '2019-12-18 06:48:40'),
(204, 3, 78, '4.2040205', '2019-12-19 05:48:44'),
(205, 10, 8, '0.32990354', '2019-12-19 13:31:25'),
(206, 3, 79, '4.2040205', '2019-12-20 13:44:44'),
(207, 11, 79, '0.3869684', '2019-12-20 13:47:41'),
(208, 11, 73, '0.3869684', '2019-12-20 18:10:00'),
(209, 13, 8, '0.98216754', '2019-12-21 06:58:49'),
(210, 13, 80, '0.9810104', '2019-12-21 07:13:17'),
(211, 12, 80, '3.7', '2019-12-21 07:13:42'),
(212, 15, 80, '4.376553', '2019-12-21 07:15:31'),
(213, 16, 80, '1.0754123', '2019-12-21 07:15:39'),
(214, 1, 80, '3.418932', '2019-12-21 07:15:55'),
(215, 7, 80, '0.0', '2019-12-21 07:15:59'),
(216, 6, 80, '5.0', '2019-12-21 07:16:34'),
(217, 14, 80, '3.8', '2019-12-21 07:17:19'),
(218, 7, 8, '0.0', '2019-12-21 07:30:17'),
(219, 14, 8, '3.8', '2019-12-21 07:30:20'),
(220, 6, 77, '5.0', '2019-12-21 14:57:49'),
(221, 11, 77, '0.3869684', '2019-12-22 08:21:49'),
(222, 3, 77, '4.2025313', '2019-12-22 08:23:24'),
(223, 12, 77, '3.7', '2019-12-22 08:23:47'),
(224, 5, 77, '1.8000001', '2019-12-22 08:24:35'),
(225, 7, 77, '0.0', '2019-12-22 08:25:00'),
(226, 3, 81, '4.2025704', '2019-12-22 10:17:47'),
(227, 5, 81, '1.8000001', '2019-12-22 10:41:06'),
(228, 1, 81, '3.418932', '2019-12-22 10:47:56'),
(229, 10, 81, '0.3301649', '2019-12-22 10:48:05'),
(230, 11, 81, '0.3869684', '2019-12-22 10:52:37'),
(231, 14, 81, '3.8', '2019-12-22 10:55:44'),
(232, 3, 82, '4.2025704', '2019-12-22 11:25:56'),
(233, 7, 81, '0.0', '2019-12-22 11:33:32'),
(234, 1, 82, '3.418932', '2019-12-22 11:36:38'),
(235, 16, 81, '1.0492573', '2019-12-22 11:38:06'),
(236, 15, 81, '4.376553', '2019-12-22 11:39:05'),
(237, 15, 82, '4.376553', '2019-12-22 11:40:45'),
(238, 12, 81, '3.7', '2019-12-22 11:42:17'),
(239, 6, 82, '5.0', '2019-12-22 11:51:10'),
(240, 13, 82, '0.9810087', '2019-12-22 11:52:28'),
(241, 5, 82, '1.8000001', '2019-12-22 11:52:36'),
(242, 11, 83, '0.38734135', '2019-12-22 13:43:36'),
(243, 1, 83, '3.418932', '2019-12-22 13:48:08'),
(244, 15, 83, '4.376553', '2019-12-22 13:48:21'),
(245, 12, 83, '3.7', '2019-12-22 13:49:23'),
(246, 5, 83, '1.8000001', '2019-12-22 13:50:40'),
(247, 13, 81, '0.9810085', '2019-12-23 11:33:47'),
(248, 5, 84, '1.8000001', '2019-12-24 04:15:45'),
(249, 12, 84, '3.7', '2019-12-24 04:20:35'),
(250, 11, 84, '0.3869684', '2019-12-24 04:34:29'),
(251, 13, 84, '0.9810085', '2019-12-24 04:36:15'),
(252, 3, 6, '4.2025704', '2019-12-24 20:03:50'),
(253, 6, 6, '5.0', '2019-12-24 20:05:25'),
(254, 3, 84, '4.2025704', '2019-12-25 05:05:22'),
(255, 1, 85, '3.418932', '2019-12-25 07:49:41'),
(256, 14, 85, '3.8', '2019-12-25 07:51:13'),
(257, 16, 85, '1.0492573', '2019-12-25 07:52:02'),
(258, 1, 86, '3.418932', '2019-12-25 15:25:15'),
(259, 3, 86, '4.2025704', '2019-12-25 15:27:38'),
(260, 6, 86, '5.0', '2019-12-25 15:30:03'),
(261, 16, 86, '1.0492573', '2019-12-25 15:31:33'),
(262, 13, 86, '0.9810085', '2019-12-25 15:35:04'),
(263, 3, 87, '4.2025704', '2019-12-25 17:28:07'),
(264, 6, 87, '5.0', '2019-12-25 17:28:46'),
(265, 11, 88, '0.39480352', '2019-12-26 02:10:53'),
(266, 3, 88, '4.2025704', '2019-12-26 02:11:15'),
(267, 16, 88, '1.0712276', '2019-12-26 02:12:24'),
(268, 6, 88, '5.0', '2019-12-26 02:12:27'),
(269, 1, 88, '3.418932', '2019-12-26 02:13:05'),
(270, 10, 88, '0.32937893', '2019-12-26 02:13:32'),
(271, 13, 88, '0.9810085', '2019-12-26 02:13:59'),
(272, 3, 89, '4.2025704', '2019-12-26 02:55:21'),
(273, 13, 89, '0.9810085', '2019-12-26 02:57:09'),
(274, 13, 90, '0.9810085', '2019-12-26 17:36:00'),
(275, 1, 90, '3.418932', '2019-12-26 17:36:55'),
(276, 14, 90, '3.8', '2019-12-26 17:38:57'),
(277, 7, 90, '0.0', '2019-12-26 17:52:27'),
(278, 10, 73, '0.32961333', '2019-12-28 08:48:24'),
(279, 7, 73, '0.0', '2019-12-28 08:54:55'),
(280, 6, 91, '5.0', '2019-12-29 13:17:41'),
(281, 5, 91, '1.8000001', '2019-12-29 13:17:57'),
(282, 7, 91, '0.0', '2019-12-29 13:18:28'),
(283, 14, 91, '3.8', '2019-12-29 13:20:12'),
(284, 6, 73, '5.0', '2019-12-29 15:24:39'),
(285, 1, 92, '3.418962', '2019-12-29 20:26:36'),
(286, 5, 93, '1.8000001', '2019-12-29 21:06:44'),
(287, 15, 8, '4.376551', '2019-12-30 16:56:20'),
(288, 16, 8, '1.1719592', '2019-12-30 16:57:01'),
(289, 12, 88, '3.7', '2019-12-30 20:01:52'),
(290, 5, 88, '1.8000001', '2019-12-30 20:02:13'),
(291, 1, 94, '3.4189372', '2019-12-31 19:46:01'),
(292, 13, 94, '0.98099554', '2019-12-31 19:47:08'),
(293, 11, 94, '0.38734', '2019-12-31 19:48:17'),
(294, 1, 95, '3.4189353', '2020-01-01 18:19:15'),
(295, 16, 95, '1.1660395', '2020-01-01 18:19:47'),
(296, 3, 96, '4.131773', '2020-01-02 10:43:32'),
(297, 6, 96, '4.997238', '2020-01-02 10:46:46'),
(298, 5, 96, '1.8001553', '2020-01-02 10:46:50'),
(299, 11, 96, '0.38767654', '2020-01-02 10:46:54'),
(300, 10, 96, '0.3299019', '2020-01-02 10:46:56'),
(301, 1, 96, '3.399101', '2020-01-02 11:34:41'),
(302, 15, 96, '4.376552', '2020-01-02 11:46:53'),
(303, 1, 97, '3.418963', '2020-01-02 12:22:28'),
(304, 3, 97, '4.2035766', '2020-01-02 12:22:46'),
(305, 11, 97, '0.3873413', '2020-01-02 12:23:00'),
(306, 5, 97, '1.8000001', '2020-01-02 13:55:46'),
(307, 10, 97, '0.32964554', '2020-01-03 04:49:12'),
(308, 13, 96, '0.98392266', '2020-01-03 11:50:34'),
(309, 7, 96, '0.0', '2020-01-03 12:04:24'),
(310, 16, 96, '1.1719592', '2020-01-03 12:31:42'),
(311, 14, 96, '3.8', '2020-01-03 12:52:21'),
(312, 12, 96, '3.7', '2020-01-03 12:54:08'),
(313, 3, 94, '4.2036276', '2020-01-06 02:45:51'),
(314, 12, 94, '3.7', '2020-01-06 06:43:02'),
(315, 15, 94, '4.376552', '2020-01-06 06:43:08'),
(316, 7, 94, '0.0', '2020-01-06 06:43:11'),
(317, 6, 94, '5.0', '2020-01-06 06:44:45'),
(318, 10, 94, '0.32964554', '2020-01-06 06:44:52'),
(319, 1, 98, '3.4189358', '2020-01-07 02:23:59'),
(320, 16, 98, '1.0719699', '2020-01-07 02:24:52'),
(321, 11, 98, '0.3873413', '2020-01-07 02:24:57'),
(322, 1, 16, '3.4189358', '2020-01-07 21:46:58'),
(323, 3, 99, '4.1318603', '2020-01-08 11:51:17'),
(324, 13, 99, '0.98216754', '2020-01-08 11:51:45'),
(325, 15, 99, '4.376552', '2020-01-08 11:53:01'),
(326, 6, 99, '4.997317', '2020-01-08 11:56:59'),
(327, 1, 99, '3.4143481', '2020-01-08 13:49:41'),
(328, 11, 101, '0.38734135', '2020-01-09 00:26:15'),
(329, 6, 101, '5.0', '2020-01-09 00:26:47'),
(330, 5, 99, '1.8001555', '2020-01-09 11:00:37'),
(331, 3, 83, '4.203629', '2020-01-09 17:38:12'),
(332, 13, 83, '0.9873878', '2020-01-10 14:36:48'),
(333, 12, 102, '3.7', '2020-01-10 16:18:53'),
(334, 5, 102, '1.8000001', '2020-01-10 16:19:47'),
(335, 7, 102, '0.0', '2020-01-10 16:22:06'),
(336, 1, 103, '3.4189358', '2020-01-10 20:03:37'),
(337, 7, 103, '0.0', '2020-01-10 20:04:26'),
(338, 3, 103, '4.203629', '2020-01-10 20:05:13'),
(339, 5, 103, '1.8000001', '2020-01-10 20:10:28'),
(340, 10, 103, '0.32964644', '2020-01-10 20:11:55'),
(341, 6, 103, '5.0', '2020-01-10 20:13:06'),
(342, 13, 103, '0.9873878', '2020-01-10 20:13:21'),
(343, 11, 103, '0.3873542', '2020-01-10 20:13:44'),
(344, 15, 103, '4.376552', '2020-01-10 20:38:12'),
(345, 3, 102, '4.203629', '2020-01-11 05:30:29'),
(346, 13, 102, '0.9873878', '2020-01-11 05:31:32'),
(347, 16, 102, '1.0719696', '2020-01-11 08:24:46'),
(348, 3, 104, '4.203629', '2020-01-11 09:21:56'),
(349, 6, 102, '5.0', '2020-01-11 13:19:32'),
(350, 3, 105, '4.1318073', '2020-01-12 00:17:44'),
(351, 6, 105, '5.0', '2020-01-12 00:18:28'),
(352, 11, 105, '0.38766888', '2020-01-12 00:18:50'),
(353, 13, 105, '0.9873878', '2020-01-12 00:20:30'),
(354, 5, 105, '1.8000001', '2020-01-12 00:41:32'),
(355, 3, 106, '4.203629', '2020-01-12 11:48:20'),
(356, 10, 106, '0.3296942', '2020-01-12 11:50:24'),
(357, 1, 106, '3.4189358', '2020-01-12 11:50:26'),
(358, 1, 107, '3.4189358', '2020-01-12 12:38:54'),
(359, 6, 107, '5.0', '2020-01-12 12:39:04'),
(360, 11, 108, '0.3873546', '2020-01-12 13:32:28'),
(361, 13, 109, '0.98206943', '2020-01-12 16:39:15'),
(362, 14, 109, '3.8', '2020-01-12 16:43:09'),
(363, 12, 109, '3.7', '2020-01-12 16:44:31'),
(364, 11, 109, '0.3873546', '2020-01-12 16:51:41'),
(365, 3, 110, '1.0', '2020-01-13 09:17:23'),
(366, 1, 111, '3.4189358', '2020-01-13 11:38:13'),
(367, 3, 111, '4.136886', '2020-01-13 11:38:29'),
(368, 16, 100, '3.8999999', '2020-01-13 15:26:58'),
(369, 1, 100, '3.4189358', '2020-01-13 15:28:14'),
(370, 1, 112, '3.4189358', '2020-01-14 13:52:26'),
(371, 10, 112, '0.3296942', '2020-01-14 13:57:05'),
(372, 11, 112, '0.38735464', '2020-01-14 13:57:40'),
(373, 13, 112, '0.9821516', '2020-01-14 14:13:44'),
(374, 5, 112, '1.8039542', '2020-01-14 14:13:55'),
(375, 6, 112, '4.9976344', '2020-01-14 14:14:15'),
(376, 3, 112, '4.1368856', '2020-01-14 14:15:31'),
(377, 15, 112, '4.376552', '2020-01-14 14:53:05'),
(378, 7, 99, '0.0', '2020-01-14 14:54:19'),
(379, 1, 113, '3.4189358', '2020-01-14 16:17:19'),
(380, 6, 113, '4.9290323', '2020-01-14 16:18:21'),
(381, 16, 113, '1.1631936', '2020-01-14 16:22:00'),
(382, 11, 113, '0.38735464', '2020-01-14 16:22:44'),
(383, 15, 114, '4.376552', '2020-01-14 18:27:12'),
(384, 12, 114, '3.7', '2020-01-14 18:27:53'),
(385, 5, 114, '1.8000001', '2020-01-14 18:28:34'),
(386, 10, 114, '0.3296942', '2020-01-14 18:28:43'),
(387, 13, 114, '0.98450756', '2020-01-14 18:28:53'),
(388, 16, 114, '1.1631936', '2020-01-14 18:29:22'),
(389, 1, 114, '3.4189358', '2020-01-14 18:30:58'),
(390, 3, 114, '4.1368856', '2020-01-14 18:31:03'),
(391, 3, 113, '4.1368856', '2020-01-14 18:57:28'),
(392, 13, 113, '0.9821486', '2020-01-14 19:22:56'),
(393, 15, 113, '4.376552', '2020-01-14 19:23:29'),
(394, 14, 113, '3.8', '2020-01-14 19:23:42'),
(395, 12, 112, '3.7', '2020-01-15 05:08:52'),
(396, 1, 116, '3.4189358', '2020-01-15 21:49:37'),
(397, 3, 116, '4.135603', '2020-01-15 21:50:59'),
(398, 10, 116, '0.3296942', '2020-01-15 21:54:03'),
(399, 13, 116, '0.98215497', '2020-01-15 21:55:54'),
(400, 12, 8, '3.7', '2020-01-16 06:32:29'),
(401, 7, 117, '0.0', '2020-01-16 21:22:03'),
(402, 15, 117, '4.376552', '2020-01-16 21:24:05'),
(403, 3, 118, '4.131826', '2020-01-16 23:42:19'),
(404, 11, 118, '0.38735464', '2020-01-16 23:43:10'),
(405, 1, 105, '3.399976', '2020-01-17 08:26:01'),
(406, 1, 119, '3.4189353', '2020-01-17 08:28:17'),
(407, 6, 119, '4.9975557', '2020-01-17 08:30:11'),
(408, 3, 120, '4.1330385', '2020-01-17 10:12:01'),
(409, 3, 121, '4.1330147', '2020-01-18 18:40:24'),
(410, 13, 121, '0.9839665', '2020-01-18 18:41:39'),
(411, 1, 121, '3.4141448', '2020-01-18 18:45:53'),
(412, 13, 122, '0.98216784', '2020-01-19 02:03:38'),
(413, 6, 122, '4.9975557', '2020-01-19 02:04:31'),
(414, 5, 122, '1.8001463', '2020-01-19 02:07:37'),
(415, 12, 122, '3.6545455', '2020-01-19 03:06:47'),
(416, 1, 122, '3.4189353', '2020-01-19 03:08:36'),
(417, 10, 122, '0.32987806', '2020-01-19 03:08:45'),
(418, 13, 95, '0.98216784', '2020-01-19 06:18:35'),
(419, 3, 123, '4.133014', '2020-01-19 12:09:05'),
(420, 1, 123, '3.4189353', '2020-01-19 12:10:37'),
(421, 5, 124, '1.8001463', '2020-01-20 00:58:14'),
(422, 3, 124, '4.133014', '2020-01-20 00:58:52'),
(423, 11, 124, '0.38736567', '2020-01-20 01:03:43'),
(424, 1, 124, '3.4189353', '2020-01-20 01:04:03'),
(425, 16, 124, '1.1717275', '2020-01-20 01:05:10'),
(426, 16, 125, '1.1718109', '2020-01-21 10:11:26'),
(427, 1, 125, '3.4189353', '2020-01-21 10:12:23'),
(428, 5, 125, '1.8001463', '2020-01-21 10:12:42'),
(429, 3, 126, '4.133014', '2020-01-22 04:46:37'),
(430, 1, 126, '3.4189353', '2020-01-22 04:47:01'),
(431, 10, 126, '0.32987806', '2020-01-22 04:47:46'),
(432, 13, 126, '0.98216826', '2020-01-22 04:48:22'),
(433, 15, 128, '4.3765516', '2020-01-22 09:18:29'),
(434, 3, 128, '4.133014', '2020-01-22 09:23:40'),
(435, 3, 129, '4.133014', '2020-01-22 12:15:25'),
(436, 19, 0, '0.0', '2020-01-22 13:42:57'),
(437, 19, 8, '0.0', '2020-01-22 13:43:23'),
(438, 20, 8, '0.0', '2020-01-22 13:51:37'),
(439, 20, 0, '0.0', '2020-01-22 14:24:21'),
(440, 19, 130, '0.0', '2020-01-22 14:27:00'),
(441, 20, 130, '0.0', '2020-01-22 14:27:20'),
(442, 15, 130, '4.3765516', '2020-01-22 14:28:44'),
(443, 11, 129, '0.39428926', '2020-01-22 15:20:28'),
(444, 16, 129, '1.1718109', '2020-01-22 15:20:50'),
(445, 1, 131, '3.4189353', '2020-01-22 15:57:45'),
(446, 13, 131, '0.98216826', '2020-01-22 16:00:20'),
(447, 7, 131, '0.0', '2020-01-22 16:00:35'),
(448, 16, 131, '1.1718109', '2020-01-22 16:00:47'),
(449, 12, 131, '3.697934', '2020-01-22 16:00:54'),
(450, 5, 131, '1.8001466', '2020-01-22 16:00:58'),
(451, 10, 70, '0.32988542', '2020-01-23 06:09:05'),
(452, 11, 70, '0.39109972', '2020-01-23 06:09:37'),
(453, 13, 70, '0.98216826', '2020-01-23 06:09:49'),
(454, 20, 132, '0.0', '2020-01-23 11:48:23'),
(455, 5, 132, '1.8001466', '2020-01-23 11:48:35'),
(456, 5, 133, '1.8001466', '2020-01-24 04:42:32'),
(457, 12, 133, '3.697934', '2020-01-24 04:42:41'),
(458, 3, 134, '4.1329474', '2020-01-24 07:47:57'),
(459, 6, 134, '4.997398', '2020-01-24 07:49:04'),
(460, 1, 135, '3.4189353', '2020-01-24 23:34:55'),
(461, 20, 1, '0.0', '2020-01-25 21:29:12'),
(462, 1, 139, '3.4002914', '2020-01-26 17:38:16'),
(463, 3, 139, '4.1329474', '2020-01-26 17:39:30'),
(464, 16, 99, '1.171811', '2020-01-27 06:16:21'),
(465, 5, 140, '1.800151', '2020-01-27 11:52:52'),
(466, 1, 141, '3.3999858', '2020-01-28 03:29:13'),
(467, 10, 141, '0.32989305', '2020-01-28 03:29:46'),
(468, 5, 141, '1.8001511', '2020-01-28 03:32:10'),
(469, 3, 142, '4.131826', '2020-01-28 04:06:55'),
(470, 15, 141, '4.376551', '2020-01-28 04:18:44'),
(471, 1, 143, '3.399976', '2020-01-29 07:02:19'),
(472, 14, 143, '3.8', '2020-01-29 07:02:24'),
(473, 3, 143, '4.1318073', '2020-01-29 07:02:28'),
(474, 1, 144, '3.399976', '2020-01-29 09:01:27'),
(475, 3, 144, '4.1318073', '2020-01-29 09:01:59'),
(476, 1, 145, '3.399976', '2020-01-30 06:42:36'),
(477, 13, 144, '0.98220104', '2020-01-30 09:13:34'),
(478, 6, 140, '4.9973145', '2020-01-30 20:59:13'),
(479, 16, 105, '1.171811', '2020-01-31 12:43:13'),
(480, 3, 146, '4.131773', '2020-02-02 06:22:17'),
(481, 6, 146, '4.9973145', '2020-02-02 06:22:25'),
(482, 1, 147, '3.3996844', '2020-02-03 02:26:14'),
(483, 5, 147, '1.8001511', '2020-02-03 02:28:21'),
(484, 16, 147, '1.171811', '2020-02-03 02:29:51'),
(485, 3, 147, '4.131773', '2020-02-03 02:30:16'),
(486, 1, 148, '3.3996844', '2020-02-03 23:20:53'),
(487, 16, 148, '1.171811', '2020-02-03 23:22:15'),
(488, 20, 148, '0.0', '2020-02-03 23:31:44'),
(489, 6, 148, '4.9973145', '2020-02-03 23:31:50'),
(490, 20, 96, '0.0', '2020-02-04 07:03:19'),
(491, 1, 149, '3.3993967', '2020-02-04 17:36:24'),
(492, 3, 149, '4.130747', '2020-02-04 17:36:58'),
(493, 3, 150, '4.1307325', '2020-02-04 20:25:36'),
(494, 5, 150, '1.8001552', '2020-02-04 20:26:19'),
(495, 6, 151, '4.997242', '2020-02-05 12:29:08'),
(496, 16, 151, '1.1719592', '2020-02-05 12:29:26'),
(497, 11, 151, '0.38766888', '2020-02-05 12:30:29'),
(498, 13, 151, '1.0431957', '2020-02-05 12:58:06'),
(499, 3, 151, '4.1307325', '2020-02-05 12:59:12'),
(500, 12, 151, '3.697844', '2020-02-05 12:59:28'),
(501, 5, 151, '1.8001552', '2020-02-05 12:59:42'),
(502, 1, 151, '3.3993888', '2020-02-05 12:59:56'),
(503, 15, 151, '4.376551', '2020-02-05 14:54:12'),
(504, 10, 151, '0.3299022', '2020-02-05 14:55:27'),
(505, 1, 152, '3.399105', '2020-02-09 06:49:23'),
(506, 11, 152, '0.3876691', '2020-02-09 06:51:26'),
(507, 3, 152, '4.1307325', '2020-02-09 06:51:51'),
(508, 10, 152, '0.3299022', '2020-02-09 06:53:36'),
(509, 1, 153, '3.399105', '2020-02-09 17:31:26'),
(510, 3, 153, '4.1307325', '2020-02-09 17:33:08'),
(511, 10, 153, '0.3299025', '2020-02-09 17:33:51'),
(512, 11, 153, '0.3876691', '2020-02-09 17:34:03'),
(513, 6, 153, '4.99724', '2020-02-09 17:34:06'),
(514, 14, 153, '3.8', '2020-02-09 17:34:10'),
(515, 16, 153, '1.1719592', '2020-02-09 17:50:14'),
(516, 13, 153, '0.98384947', '2020-02-09 17:51:06'),
(517, 6, 154, '4.997238', '2020-02-10 16:10:01'),
(518, 13, 154, '0.98384947', '2020-02-10 16:10:20'),
(519, 15, 154, '4.376551', '2020-02-10 16:10:52'),
(520, 1, 154, '3.4141448', '2020-02-10 16:11:15'),
(521, 12, 154, '3.697844', '2020-02-10 19:10:18'),
(522, 10, 154, '0.3299025', '2020-02-10 19:11:00'),
(523, 11, 154, '0.3876691', '2020-02-10 19:13:19'),
(524, 16, 154, '2.8999999', '2020-02-10 19:13:26'),
(525, 14, 154, '3.8', '2020-02-10 19:13:32'),
(526, 1, 155, '3.399105', '2020-02-10 20:46:49'),
(527, 12, 156, '3.697844', '2020-02-10 20:51:29'),
(528, 3, 156, '4.1307325', '2020-02-10 21:57:53'),
(529, 11, 156, '0.3876691', '2020-02-10 21:58:38'),
(530, 7, 154, '0.0', '2020-02-11 13:08:13'),
(531, 12, 157, '3.697844', '2020-02-12 07:35:50'),
(532, 14, 157, '3.8', '2020-02-12 07:37:10'),
(533, 6, 157, '4.997238', '2020-02-12 07:37:19'),
(534, 10, 157, '0.3299025', '2020-02-12 07:37:24'),
(535, 10, 121, '0.3299025', '2020-02-12 14:23:57'),
(536, 15, 121, '4.376551', '2020-02-12 14:24:14'),
(537, 6, 121, '4.997238', '2020-02-12 14:24:51'),
(538, 7, 121, '0.0', '2020-02-12 14:40:56'),
(539, 3, 158, '4.1307325', '2020-02-12 20:39:01'),
(540, 16, 158, '1.212582', '2020-02-12 20:42:11'),
(541, 10, 158, '0.3299025', '2020-02-12 20:58:44'),
(542, 5, 159, '1.8001555', '2020-02-13 09:36:16'),
(543, 12, 159, '3.6977644', '2020-02-13 10:18:48'),
(544, 3, 159, '4.1307325', '2020-02-13 10:20:08'),
(545, 3, 160, '4.1307325', '2020-02-13 10:28:10'),
(546, 11, 160, '0.38767672', '2020-02-13 10:34:09'),
(547, 11, 150, '0.38767672', '2020-02-13 11:45:31');

-- --------------------------------------------------------

--
-- Table structure for table `e_transacation`
--

CREATE TABLE `e_transacation` (
  `t_id` int(11) NOT NULL,
  `t_user_id` int(11) NOT NULL,
  `t_fb_id` int(11) NOT NULL,
  `t_currency_code` text CHARACTER SET utf8 NOT NULL,
  `t_description` text CHARACTER SET utf8 NOT NULL,
  `t_payment_id` text CHARACTER SET utf8 NOT NULL,
  `t_state` text CHARACTER SET utf8 NOT NULL,
  `t_datetime` int(11) NOT NULL,
  `t_amount` text CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_transacation`
--

INSERT INTO `e_transacation` (`t_id`, `t_user_id`, `t_fb_id`, `t_currency_code`, `t_description`, `t_payment_id`, `t_state`, `t_datetime`, `t_amount`) VALUES
(4, 1, 1, 'USD', 'Jay Shree Krishna (JSK)', 'PAY-6VW14774C8393214XLUOKIOY', 'approved', 2019, '100'),
(5, 1, 2, 'USD', 'Steave jobs', 'PAY-24J51791P7161721HLUOKJ6A', 'approved', 2019, '100'),
(16, 103, 11, 'INR', 'Gujarati', '11', 'approved', 2019, '10'),
(17, 103, 6, 'INR', 'Rich Dad', '6', 'approved', 2019, '100'),
(18, 82, 3, 'INR', 'FreadOm', 'pay_E3XgMO8cZuE9co', 'approved', 2019, '50'),
(19, 82, 6, 'INR', 'Rich Dad', 'pay_E3YHLa8ELaerh2', 'approved', 2019, '100'),
(20, 73, 3, 'INR', 'FreadOm', 'pay_E3qOKV8V2zCp4c', 'approved', 2019, '50'),
(21, 110, 3, 'INR', 'FreadOm', 'pay_E3uNTcRMPWNl8Z', 'approved', 2019, '50'),
(22, 73, 6, 'INR', 'Rich Dad', 'pay_E5DZzi19LAg91d', 'approved', 2019, '100');

-- --------------------------------------------------------

--
-- Table structure for table `e_user`
--

CREATE TABLE `e_user` (
  `id` int(11) NOT NULL,
  `fullname` text CHARACTER SET utf8 NOT NULL,
  `email` text CHARACTER SET utf8 NOT NULL,
  `password` text CHARACTER SET utf8 NOT NULL,
  `mobile_number` text CHARACTER SET utf8 NOT NULL,
  `status` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT 'enable',
  `c_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `e_user`
--

INSERT INTO `e_user` (`id`, `fullname`, `email`, `password`, `mobile_number`, `status`, `c_date`) VALUES
(1, 'admin', 'admin@gmail.com', '12345', '9898352931', 'enable', '2019-12-19 13:48:16'),
(2, 'john@gmail.com', 'john@gmail.com', '12345', '9898989898', 'enable', '2019-12-18 13:52:03'),
(3, 'nick@gmail.com', 'nick@gmail.com', '12345', '7895684652', 'enable', '2019-12-17 13:52:03'),
(4, 'butth8352@gmail.com', 'butth8352@gmail.com', '14984141', '03107301142', 'enable', '2019-12-15 13:52:03'),
(5, 'diyaayub@gmail.com', 'diyaayub@gmail.com', 'ca920229', '5468828388985', 'enable', '2019-12-15 13:52:03'),
(6, 'amaniagroup@gmail.com', 'amaniagroup@gmail.com', '123456', '8825245586', 'enable', '2019-12-15 13:52:03'),
(7, 'mptechkannada@gmail.com', 'mptechkannada@gmail.com', '8747931874', '9113000803', 'enable', '2019-12-15 13:52:03'),
(8, 'ckpatel219@gmail.com', 'ckpatel219@gmail.com', '12345', '9898352931', 'enable', '2019-12-19 13:52:03'),
(10, 'nareshbishnoi9672@gmail.com', 'nareshbishnoi9672@gmail.com', 'Naresh', '+919672835296', 'enable', '2019-12-19 13:52:03'),
(11, 'egytube7@gmail.com', 'egytube7@gmail.com', '2010100', '01140069331', 'enable', '2019-12-19 13:52:03'),
(12, 'susalohar@gmail.com', 'susalohar@gmail.com', 'zxcvbnm12345678', '9547771119', 'enable', '2019-12-19 13:52:03'),
(14, 'jfhhiji@gmail.com', 'jfhhiji@gmail.com', 'gjxjlk', '3588098852', 'enable', '2019-12-19 13:52:03'),
(15, 'vragg.ru@gmail.com', 'vragg.ru@gmail.com', 'qwerty', '+79997059633', 'enable', '2019-12-19 13:52:03'),
(16, 'amanijosephusa@gmail.com', 'amanijosephusa@gmail.com', '1234t6', '07545545424', 'enable', '2019-12-19 13:52:03'),
(17, 'dewicartica@gmail.com', 'dewicartica@gmail.com', '123', '1236548789', 'enable', '2019-12-19 13:52:03'),
(18, 'ateyjohn@gmail.com', 'ateyjohn@gmail.com', 'deji0727', '07035903855', 'enable', '2019-12-19 13:52:03'),
(19, 'benardsulejmani.info@gmail.com', 'benardsulejmani.info@gmail.com', 'benardel', '0123456789', 'enable', '2019-12-19 13:52:03'),
(20, 'sureshkotwala@gmail.com ', 'sureshkotwala@gmail.com ', 'bokpass77', '9928053351', 'enable', '2019-12-19 13:52:03'),
(21, 'jdue@jsiie.com', 'jdue@jsiie.com', 'akss', '963963963', 'enable', '2019-12-19 13:52:03'),
(22, 'albertojulio.dtm@gmail.com', 'albertojulio.dtm@gmail.com', 'rosi1988', '5581988751362', 'enable', '2019-12-19 13:52:03'),
(23, 'harzi.dj@gmail.com', 'harzi.dj@gmail.com', '1234rewq', '082216888917', 'enable', '2019-12-19 13:52:03'),
(24, 'test124@gmail.com', 'test124@gmail.com', '123456', '1236547890', 'enable', '2019-12-19 13:52:03'),
(25, 'sai.aswale@gmail.com', 'sai.aswale@gmail.com', '123456', '7972870935', 'enable', '2019-12-19 13:52:03'),
(26, 'pera.peric@gmail.com', 'pera.peric@gmail.com', '123456', '381606157995', 'enable', '2019-12-19 13:52:03'),
(27, 'aplikasiatjeh@gmail.com', 'aplikasiatjeh@gmail.com', '123', '08649454545', 'enable', '2019-12-19 13:52:03'),
(28, 'benniemattie@gmail.com', 'benniemattie@gmail.com', 'xxyyZZ32', '08036611816', 'enable', '2019-12-19 13:52:03'),
(29, 'tahmadusman@gmail.com', 'tahmadusman@gmail.com', 'taufik', '+2348142928803', 'enable', '2019-12-19 13:52:03'),
(30, '24clanhub@gmail.com', '24clanhub@gmail.com', 'ahmed1994', '+2348142228805', 'enable', '2019-12-19 13:52:03'),
(31, 'iancox@gmail.com', 'iancox@gmail.com', '123123', '084546466', 'enable', '2019-12-19 13:52:03'),
(32, 'a12345n', 'a12345n', 'a12345n', '2225553232', 'enable', '2019-12-19 13:52:03'),
(33, 'fxroyalmoneysolution@gmail.com', 'fxroyalmoneysolution@gmail.com', '12345678', '123456789', 'enable', '2019-12-19 13:52:03'),
(34, 'neversleep.1809@gmail.com', 'neversleep.1809@gmail.com', 'Pl4st1cum', '08118472129', 'enable', '2019-12-19 13:52:03'),
(35, 'gwhheuehwj@gmail.com', 'gwhheuehwj@gmail.com', '11111111111', '7903005524', 'enable', '2019-12-19 13:52:03'),
(36, 'mygifthulkid@gmail.com ', 'mygifthulkid@gmail.com ', '123456', '6398844390', 'enable', '2019-12-19 13:52:03'),
(37, 'bintang.piaggi44@gmail.com', 'bintang.piaggi44@gmail.com', 'bintang', '0895353077430', 'enable', '2019-12-19 13:52:03'),
(38, 'sdwebmagic@gmail.com', 'sdwebmagic@gmail.com', 'savage', '0812327822', 'enable', '2019-12-19 13:52:03'),
(39, 'bintang.piaggo@gmail.com', 'bintang.piaggo@gmail.com', 'bintang', '897861310', 'enable', '2019-12-19 13:52:03'),
(40, 'mono@gmail.com', 'mono@gmail.com', '12345678', '22883386687', 'enable', '2019-12-19 13:52:03'),
(41, 'the1malaysia@gmail.com', 'the1malaysia@gmail.com', 'maskai02', '01111476679', 'enable', '2019-12-19 13:52:03'),
(42, 'soporte.noeordonez@gmail.com ', 'soporte.noeordonez@gmail.com ', '123456789', '943948476', 'enable', '2019-12-19 13:52:03'),
(43, 'egytube42@gmail.com', 'egytube42@gmail.com', '2010100', '0745552566', 'enable', '2019-12-19 13:52:03'),
(44, 'cpasimokhaled6@gmail.com ', 'cpasimokhaled6@gmail.com ', '1995WYDAD', '0676005054', 'enable', '2019-12-19 13:52:03'),
(45, 'king@moi.com', 'king@moi.com', 'kingking', '859637', 'enable', '2019-12-19 13:52:03'),
(46, 'test@test.com', 'test@test.com', '12345678', '12345678', 'enable', '2019-12-19 13:52:03'),
(47, 'lior797@gmail.com', 'lior797@gmail.com', '1111lior', '000000000', 'enable', '2019-12-19 13:52:03'),
(48, 'testm@gmail.com', 'testm@gmail.com', 'testm@gmail.com', '1234567891', 'enable', '2019-12-19 13:52:03'),
(49, 'kasun', 'kasun', 'kasun', '123', 'enable', '2019-12-19 13:52:03'),
(50, 'mrwin@gmail.com', 'mrwin@gmail.com', 'mrwin@gmail.com', '867676', 'enable', '2019-12-19 13:52:03'),
(51, 'zalandpashton35@gmail.com ', 'zalandpashton35@gmail.com ', 'zaland123', '0767341044', 'enable', '2019-12-19 13:52:03'),
(52, 'rameezmarket4@gmail.com', 'rameezmarket4@gmail.com', 'lolo@123', '+44917488090441', 'enable', '2019-12-19 13:52:03'),
(53, 'justuskiptoo25@gmail.com', 'justuskiptoo25@gmail.com', '12345678', '0715172257', 'enable', '2019-12-19 13:52:03'),
(54, 'Info@socialcastja.com', 'Info@socialcastja.com', 'Get@life123', '8768721046', 'enable', '2019-12-19 13:52:03'),
(55, 'amin@amin.com', 'amin@amin.com', 'amin123456', '9733221114447', 'enable', '2019-12-19 13:52:03'),
(56, 'mbouroudiyekig@yahoo.com', 'mbouroudiyekig@yahoo.com', 'azerty', '07070707', 'enable', '2019-12-19 13:52:03'),
(57, 'test1212@gmail.com', 'test1212@gmail.com', 'qwerty', '7890456321', 'enable', '2019-12-19 13:52:03'),
(58, 'femina.mobilefirst@gmail.com', 'femina.mobilefirst@gmail.com', 'Welcome123', '9898267373', 'enable', '2019-12-19 13:52:03'),
(59, 'ashvinbhaig@rbi.edu.in', 'ashvinbhaig@rbi.edu.in', '123456', '9601518692', 'enable', '2019-12-19 13:52:03'),
(60, 'dhruvamadhani@gmail.com', 'dhruvamadhani@gmail.com', '1234567890', '1234675890', 'enable', '2019-12-19 13:52:03'),
(61, 'abc@gmail.com', 'abc@gmail.com', '12345678', '12345678', 'enable', '2019-12-19 13:52:03'),
(62, 'test456@test.com', 'test456@test.com', 'test456', '+919843731155', 'enable', '2019-12-19 13:52:03'),
(63, 'swapnilshah18@gmail.com', 'swapnilshah18@gmail.com', 'Arihant1?', '7984112011', 'enable', '2019-12-19 13:52:03'),
(64, 'rajesh@gmail.com', 'rajesh@gmail.com', '12345', '78968536421', 'enable', '2019-12-19 13:52:03'),
(65, 'provitious1392@gmail.com', 'provitious1392@gmail.com', 'test@122', '1234567890', 'enable', '2019-12-19 13:52:03'),
(66, 'sambareshriniket@gmail.com', 'sambareshriniket@gmail.com', 'admin@ebooks', '8600044435', 'enable', '2019-12-19 13:52:03'),
(67, 'hbpro@gmail.com', 'hbpro@gmail.com', '123456', '12345678', 'enable', '2019-12-19 13:52:03'),
(68, 'aaa@aaa.aaa', 'aaa@aaa.aaa', '123456', '+109362008333', 'enable', '2019-12-19 13:52:03'),
(69, 'anilsambare@gmail.com', 'anilsambare@gmail.com', 'admin', '8600044435', 'enable', '2019-12-19 13:52:03'),
(70, 'orucmahmood@mail.ru', 'orucmahmood@mail.ru', 'test1234', '1112222544785', 'enable', '2019-12-19 13:52:03'),
(71, 'emily@example.com', 'emily@example.com', '123456', '00000000000', 'enable', '2019-12-19 13:52:03'),
(72, 'ssenogab999@gmail.com', 'ssenogab999@gmail.com', '123456', '0700760705', 'enable', '2019-12-19 13:52:03'),
(73, 'ladaninikunj1@gmail.com', 'ladaninikunj1@gmail.com', 'test#123', '9876543210', 'enable', '2019-12-19 13:52:03'),
(74, 'arupn823@gmail.com', 'arupn823@gmail.com', '112233', '8961788292', 'enable', '2019-12-19 13:52:03'),
(75, 'jayesh@gmail.com', 'jayesh@gmail.com', '12345', '1234567890', 'enable', '2019-12-19 13:52:03'),
(76, 'mobilerast@gmail.com', 'mobilerast@gmail.com', 'test', '905310211223', 'enable', '2019-12-19 13:52:03'),
(77, 'marknestor261@gmail.com', 'marknestor261@gmail.com', '123456', '0784574589', 'enable', '2019-12-19 13:52:03'),
(78, 'arjun@gmail.com', 'arjun@gmail.com', '12345', '98456123', 'enable', '2019-12-19 13:52:03'),
(79, 'mohamedtanna@gmail.com', 'mohamedtanna@gmail.com', '1234567', '+101003920345', 'enable', '2019-12-20 13:44:26'),
(80, 'samdeveloper95@gmail.com', 'samdeveloper95@gmail.com', '12345', '12345', 'enable', '2019-12-21 07:13:08'),
(81, 'Troubleshooting@g.com', 'Troubleshooting@g.com', '123456', '2525252525', 'enable', '2019-12-22 10:16:52'),
(82, '2302567351@qq.com', '2302567351@qq.com', 'lgwl1234', '1877268564', 'enable', '2019-12-22 11:25:50'),
(83, 'ahmedsalah352@gmail.com ', 'ahmedsalah352@gmail.com ', '0162085024', '01004045949', 'enable', '2019-12-22 13:42:00'),
(84, 'd1dsatyam@gmail.com', 'd1dsatyam@gmail.com', '123456789', '7071522566', 'enable', '2019-12-24 04:14:36'),
(85, 'mahavir@gmail.com', 'mahavir@gmail.com', '1234567', '9821245678', 'enable', '2019-12-25 07:49:36'),
(86, 'abhishekkumar7870431592@gmail.com', 'abhishekkumar7870431592@gmail.com', '9955303813', '7992346106', 'enable', '2019-12-25 15:24:10'),
(87, 'jamirworkcloud@gmail.com', 'jamirworkcloud@gmail.com', 'mytimeisnow', '9999999999', 'enable', '2019-12-25 17:28:00'),
(88, 'benz@gmail.com', 'benz@gmail.com', 'qwertyuiop2012', '0698969885', 'enable', '2019-12-26 02:10:46'),
(89, 'pralayjains@gmail.com', 'pralayjains@gmail.com', '772529999', '7415682863', 'enable', '2019-12-26 02:55:12'),
(90, 'niyijameson21@gmail.com', 'niyijameson21@gmail.com', 'moyosore123f', '08104998752', 'enable', '2019-12-26 17:35:49'),
(91, 'zakaria.houssam@gmail.com', 'zakaria.houssam@gmail.com', 'fouroura', '069', 'enable', '2019-12-29 13:17:31'),
(92, 'brian', 'maseseinnocent@gmail.com', '123456', '', 'enable', '2019-12-29 08:25:55'),
(93, 'delire@gmail.com', 'delire@gmail.com', '6535', '5856666', 'enable', '2019-12-29 21:06:19'),
(94, 'busylife1122@gmail.com', 'busylife1122@gmail.com', 'saini123', '+19413646481', 'enable', '2019-12-31 19:45:29'),
(95, 'Donmss21@gmail.com', 'Donmss21@gmail.com', '12345678', '0553304345', 'enable', '2020-01-01 18:18:53'),
(96, 'testing@gmail.com ', 'testing@gmail.com ', '12345', '123457890', 'enable', '2020-01-02 10:43:00'),
(97, 'abcc@gmail.com', 'abcc@gmail.com', '12345', '1234567895', 'enable', '2020-01-02 12:22:24'),
(98, 'ajay@gmail.com', 'ajay@gmail.com', '123456', '1234567899', 'enable', '2020-01-07 02:23:53'),
(99, 'ashrafakib01@gmail.com', 'ashrafakib01@gmail.com', 'akib2355', '+441929158626', 'enable', '2020-01-08 11:50:48'),
(100, 'shah@gmail.com ', 'shah@gmail.com ', '12345678', '03369042728', 'enable', '2020-01-08 14:13:13'),
(101, 'manugmanu121@gmail.com', 'manugmanu121@gmail.com', '123456', '7204774056', 'enable', '2020-01-09 00:25:45'),
(102, 'news.hitech@gmail.com', 'news.hitech@gmail.com', 'mainan123', '628111796661', 'enable', '2020-01-10 16:18:25'),
(103, 'roykos@oncloud.ws', 'roykos@oncloud.ws', '987654321', '9898252540', 'enable', '2020-01-10 20:03:31'),
(104, 'abc@test.com', 'abc@test.com', '123456', '2145236985', 'enable', '2020-01-11 09:21:44'),
(105, 'ishworkhnl@gmail.com', 'ishworkhnl@gmail.com', 'test', '04525744011', 'enable', '2020-01-12 00:17:36'),
(106, 'isoftware.uz@mail.ru', 'isoftware.uz@mail.ru', '123456', '5807788', 'enable', '2020-01-12 11:47:22'),
(107, 'doctortech24@gmail.com', 'doctortech24@gmail.com', '1234567890', '+918619031882', 'enable', '2020-01-12 12:38:34'),
(108, 'benniemattie@yahoo.com ', 'benniemattie@yahoo.com ', 'xxyyZZ32', '08036611817', 'enable', '2020-01-12 13:31:45'),
(109, 'sari.smith40@gmail.com', 'sari.smith40@gmail.com', '19081oi9', '0559531206', 'enable', '2020-01-12 16:38:03'),
(110, 'test@gmail.com', 'test@gmail.com', 'test#123', '1231231230', 'enable', '2020-01-13 09:16:29'),
(111, 'cbbirajit@gmail.com', 'cbbirajit@gmail.com', 'admin01', '8787831567', 'enable', '2020-01-13 11:38:08'),
(112, 'phuongphally@gmail.com', 'phuongphally@gmail.com', 'P@ssw0rd123', '088123123', 'enable', '2020-01-14 13:52:19'),
(113, 'lukekone@gmail.com ', 'lukekone@gmail.com ', 'Hind6969', '0022390059005', 'enable', '2020-01-14 16:16:32'),
(114, 'hestek@gmail.com', 'hestek@gmail.com', 'yinyue10', '0169423669', 'enable', '2020-01-14 18:26:05'),
(115, 'ghghfsfsf', 'gzzxhg@gg.cc', '987987987', '9876553210', 'enable', '2020-01-15 03:24:49'),
(116, 'hr_sat@yahoo.com', 'hr_sat@yahoo.com', '12345678', '07703122222', 'enable', '2020-01-15 21:49:25'),
(117, 'yhona_qc@outlook.es', 'yhona_qc@outlook.es', '123456', '076076258', 'enable', '2020-01-16 21:21:32'),
(118, 'product.miguel@gmail.com', 'product.miguel@gmail.com', '133456789', '7766155', 'enable', '2020-01-16 23:42:07'),
(119, 'mcmc@yahoo.com', 'mcmc@yahoo.com', 'mcmc', '096', 'enable', '2020-01-17 08:28:06'),
(120, 'theprarkz@gmail.vom', 'theprarkz@gmail.vom', 'Nep@lqpp$', '89565665', 'enable', '2020-01-17 10:11:45'),
(121, 'cartrakee@gmail.com', 'cartrakee@gmail.com', '123456', '9368148528', 'enable', '2020-01-18 18:34:00'),
(122, 'rifabest1212@gmail.com', 'rifabest1212@gmail.com', '444444', '07252525222', 'enable', '2020-01-19 02:03:25'),
(123, 'sami@gmail.com', 'sami@gmail.com', '1989', '0617958560', 'enable', '2020-01-19 12:08:47'),
(124, 'lambicreator@gmail.com', 'lambicreator@gmail.com', '12345', '+916280462099', 'enable', '2020-01-20 00:57:49'),
(125, 'prazetyo@gmail.com', 'prazetyo@gmail.com', '1234567890', '08123456789', 'enable', '2020-01-21 10:10:47'),
(126, 'yuvageerthy@gmail.com', 'yuvageerthy@gmail.com', 'viji@123', '8056417348', 'enable', '2020-01-22 04:45:56'),
(127, 'chavansuraj35@gmail.com', 'chavansuraj35@gmail.com', 'Mine236bae', '9821763230', 'enable', '2020-01-22 05:13:56'),
(128, 'muks@gmail.com', 'muks@gmail.com', '123456789', '08888888888', 'enable', '2020-01-22 09:18:05'),
(129, 'juan123@gmail.com', 'juan123@gmail.com', '12345', '09121215555', 'enable', '2020-01-22 12:13:50'),
(130, 'tz@zenmail.eu ', 'tz@zenmail.eu ', 'aga', '00905315588963', 'enable', '2020-01-22 14:26:57'),
(131, 'mail555@mail.com', 'mail555@mail.com', '555', '555', 'enable', '2020-01-22 15:57:07'),
(132, 'jakirliton61@gmail.con', 'jakirliton61@gmail.con', '12345679', '01752354587', 'enable', '2020-01-23 11:47:39'),
(133, 'ran2052001@gmail.com', 'ran2052001@gmail.com', '54321', '8192839378', 'enable', '2020-01-24 04:41:40'),
(134, 'ilovserzh@gmail.com', 'ilovserzh@gmail.com', '123456', '+79997059680', 'enable', '2020-01-24 07:47:44'),
(135, 'pat@mcres.me', 'pat@mcres.me', 'Pat', '1346792580', 'enable', '2020-01-24 23:34:47'),
(136, 'James', 'jame2020@gmail.com', '123456789', '002159587441', 'enable', '2020-01-26 10:11:14'),
(137, 'JAM', 'jamjam@2019@gmail.com', 'jamjam90', '00125148885', 'enable', '2020-01-26 10:14:05'),
(138, 'duttashon@gmail.com', 'duttashon@gmail.com', '123456', '1111111111', 'enable', '2020-01-26 10:41:46'),
(139, 'ankitjaiswal315@gmail.com', 'ankitjaiswal315@gmail.com', 'Ece492.aj', '8374869528', 'enable', '2020-01-26 17:37:49'),
(140, 'onlineicetv@gmail.com', 'onlineicetv@gmail.com', 'Norway456', '09052994102', 'enable', '2020-01-27 11:52:22'),
(141, 'nimphilo18@gmail.com', 'nimphilo18@gmail.com', '1234567', '081428350', 'enable', '2020-01-28 03:29:04'),
(142, 'z@gmail.com', 'z@gmail.com', '654321', '7086482065', 'enable', '2020-01-28 04:06:33'),
(143, 'nareshhalwav@gmail.com', 'nareshhalwav@gmail.com', 'naresh', '+917014767', 'enable', '2020-01-29 07:02:13'),
(144, 'banat.bilal@gmail.com', 'banat.bilal@gmail.com', 'ola_1121991', '085296347', 'enable', '2020-01-29 09:00:44'),
(145, 'tauseeqafzal76@gmail.com', 'tauseeqafzal76@gmail.com', 'Tauseeq67', '03438805590', 'enable', '2020-01-30 06:36:01'),
(146, 'jatt@gmail.com ', 'jatt@gmail.com ', '12345', '12345678888', 'enable', '2020-02-02 06:22:09'),
(147, 'mirqurbon93@mail.ru', 'mirqurbon93@mail.ru', 'sysadmin123', '934515150', 'enable', '2020-02-03 02:23:00'),
(148, 'lesh@hotmail.com', 'lesh@hotmail.com', 'lesh123', '07855423199', 'enable', '2020-02-03 23:20:21'),
(149, 'kuldeepk40@gmail.com', 'kuldeepk40@gmail.com', '123456789', '7979801880', 'enable', '2020-02-04 17:35:54'),
(150, 'mou.ali.hamza@gmail.com', 'mou.ali.hamza@gmail.com', 'gogonir', '096325285', 'enable', '2020-02-04 20:25:08'),
(151, 'linkesperanca@gmail.com', 'linkesperanca@gmail.com', 'adv7k', '3499999999', 'enable', '2020-02-05 12:28:50'),
(152, 'vootbaloch@gmail.com', 'vootbaloch@gmail.com', '123456', '+18324060299', 'enable', '2020-02-09 06:49:14'),
(153, 'sabry_abdelasim@yahoo.com', 'sabry_abdelasim@yahoo.com', '123457', '01015366720', 'enable', '2020-02-09 17:31:21'),
(154, 'iemreyildirim@hotmail.com', 'iemreyildirim@hotmail.com', '12345', '5554575375', 'enable', '2020-02-10 16:09:56'),
(155, 'readnice@gmail.com', 'readnice@gmail.com', 'qwerty', '085236417', 'enable', '2020-02-10 20:46:43'),
(156, 'gggg@gmail.com', 'gggg@gmail.com', 'qwerty', '123652369', 'enable', '2020-02-10 20:50:41'),
(157, 'newdipesh@gmail.com', 'newdipesh@gmail.com', 'Dipesh@123', '7698470327', 'enable', '2020-02-12 07:35:20'),
(158, 'Qazizadah', 'Qazizadah', 'Nloven@2020', '0798932020', 'enable', '2020-02-12 20:38:41'),
(159, 'za_munda@yahoo.fr', 'za_munda@yahoo.fr', 'zamunda', '0033753066862', 'enable', '2020-02-13 09:35:30'),
(160, 'Zain15FIFA@gmail.com', 'Zain15FIFA@gmail.com', '09435120wczr', '01161689487', 'enable', '2020-02-13 10:27:15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `e_admin`
--
ALTER TABLE `e_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `e_author`
--
ALTER TABLE `e_author`
  ADD PRIMARY KEY (`a_id`);

--
-- Indexes for table `e_book`
--
ALTER TABLE `e_book`
  ADD PRIMARY KEY (`b_id`);

--
-- Indexes for table `e_bookmark`
--
ALTER TABLE `e_bookmark`
  ADD PRIMARY KEY (`bo_id`);

--
-- Indexes for table `e_category`
--
ALTER TABLE `e_category`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indexes for table `e_comment`
--
ALTER TABLE `e_comment`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `e_continue_read`
--
ALTER TABLE `e_continue_read`
  ADD PRIMARY KEY (`co_id`);

--
-- Indexes for table `e_download`
--
ALTER TABLE `e_download`
  ADD PRIMARY KEY (`bd_id`);

--
-- Indexes for table `e_general_setting`
--
ALTER TABLE `e_general_setting`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `e_rating`
--
ALTER TABLE `e_rating`
  ADD PRIMARY KEY (`rating_id`);

--
-- Indexes for table `e_transacation`
--
ALTER TABLE `e_transacation`
  ADD PRIMARY KEY (`t_id`);

--
-- Indexes for table `e_user`
--
ALTER TABLE `e_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `e_admin`
--
ALTER TABLE `e_admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `e_author`
--
ALTER TABLE `e_author`
  MODIFY `a_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `e_book`
--
ALTER TABLE `e_book`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `e_bookmark`
--
ALTER TABLE `e_bookmark`
  MODIFY `bo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- AUTO_INCREMENT for table `e_category`
--
ALTER TABLE `e_category`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `e_comment`
--
ALTER TABLE `e_comment`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT for table `e_continue_read`
--
ALTER TABLE `e_continue_read`
  MODIFY `co_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `e_download`
--
ALTER TABLE `e_download`
  MODIFY `bd_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `e_general_setting`
--
ALTER TABLE `e_general_setting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `e_rating`
--
ALTER TABLE `e_rating`
  MODIFY `rating_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=548;

--
-- AUTO_INCREMENT for table `e_transacation`
--
ALTER TABLE `e_transacation`
  MODIFY `t_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `e_user`
--
ALTER TABLE `e_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=161;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
