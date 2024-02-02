-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 02 Feb 2024 pada 05.09
-- Versi server: 10.4.25-MariaDB
-- Versi PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `book`
--

INSERT INTO `book` (`id`, `title`, `category`, `quantity`) VALUES
(1, 'Introduction to Algorithms', 'Programming', 4),
(2, 'Learning Web Design: A Beginner\'s Guide to HTML, CSS, JavaScript, and Web Graphics', 'Programming', 7),
(3, 'The Pragmatic Programmer: Your Journey to Mastery', 'Software Engineering', 5),
(4, 'Clean Code: A Handbook of Agile Software Craftsmanship', 'Software Engineering', 4),
(5, 'The Hobbit', 'Novel: Fantasy', 6),
(6, 'Harry Potter', 'Novel: Fantasy', 7),
(7, 'Python', 'Programming', 2),
(8, 'Sapiens: Sejarah Singkat Manusia', 'History', 4),
(9, 'Project Management', 'Information Systems', 5),
(10, 'Data and Information Mangement', 'Information Systems', 3),
(11, 'Eloquent JavaScript: A Modern Introduction to Programming', 'Programming', 4),
(12, 'Head First Java', 'Programming', 4),
(13, 'Programming Pearls', 'Programming', 2),
(14, 'Code Complete: A Practical Handbook of Software Construction', 'Programming', 1);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
