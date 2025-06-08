-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 08, 2025 at 01:56 AM
-- Server version: 8.4.3
-- PHP Version: 8.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kresika_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `id_pemesanan` int NOT NULL,
  `kode_pemesanan` varchar(20) NOT NULL COMMENT 'Kode unik untuk setiap transaksi pemesanan',
  `id_jadwal` int NOT NULL,
  `nama_pemesan` varchar(100) NOT NULL COMMENT 'Nama yang melakukan pemesanan/pembayaran',
  `email_pemesan` varchar(100) NOT NULL COMMENT 'Email yang melakukan pemesanan/pembayaran',
  `jumlah_tiket` int NOT NULL,
  `total_harga` decimal(14,2) NOT NULL,
  `barcode` varchar(255) DEFAULT NULL COMMENT 'Menyimpan data barcode atau path ke gambar barcode',
  `tanggal_pemesanan` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`id_pemesanan`, `kode_pemesanan`, `id_jadwal`, `nama_pemesan`, `email_pemesan`, `jumlah_tiket`, `total_harga`, `barcode`, `tanggal_pemesanan`) VALUES
(1, 'KRSK-1749229564395', 6, 'saripudin', 'saripudin@gmail.com', 2, 900000.00, 'KRSK-1749229564395-BSI-SBY-E01', '2025-06-06 17:06:04'),
(2, 'KRSK-1749230398673', 2, 'udin petot', 'udinpetot@gmail.com', 1, 120000.00, 'KRSK-1749230398673-BSI-BDG-B01', '2025-06-06 17:19:58'),
(3, 'KRSK-1749230560927', 2, 'abi', 'andikocak@gmail.com', 10, 1200000.00, 'KRSK-1749230560927-BSI-BDG-B01', '2025-06-06 17:22:40'),
(4, 'KRSK-1749231777584', 13, 'anjing', 'anjing123@gmail.com', 20, 10000000.00, 'KRSK-1749231777584-BSI-BLT-E01', '2025-06-06 17:42:57'),
(5, 'KRSK-1749231838496', 1, 'anto', 'anto@gmail.com', 10, 1500000.00, 'KRSK-1749231838496-BSI-BDG-E01', '2025-06-06 17:43:58'),
(6, 'KRSK-1749231968533', 13, 'anto saputro', 'anto@gmail.com', 5, 2500000.00, 'KRSK-1749231968533-BSI-BLT-E01', '2025-06-06 17:46:08'),
(7, 'KRSK-1749246267366', 11, 'admin', 'admin@gmail.com', 1, 250000.00, 'KRSK-1749246267366-KAI-JOG-B01', '2025-06-06 21:44:27'),
(8, 'KRSK-1749261169906', 4, 'nabila', 'nabila@gmail.com', 2, 160000.00, 'KRSK-1749261169906-BSI-JNG-E01', '2025-06-07 01:52:49'),
(9, 'KRSK-1749261982078', 1, 'najwasihap', 'najwa@gmail.com', 1, 150000.00, 'KRSK-1749261982078-BSI-BDG-E01', '2025-06-07 02:06:22'),
(10, 'KRSK-1749267406482', 2, 'Ahmad Habibi', '15240133@bsi.ac.id', 1, 120000.00, 'KRSK-1749267406482-BSI-BDG-B01', '2025-06-07 03:36:46'),
(11, 'KRSK-1749267545795', 2, 'udin', '15240133@bsi.ac.id', 1, 120000.00, 'KRSK-1749267545795-BSI-BDG-B01', '2025-06-07 03:39:05'),
(12, 'KRSK-1749267763822', 8, 'habibi', '15240133@bsi.ac.id', 1, 420000.00, 'KRSK-1749267763822-KAI-SBY-E01', '2025-06-07 03:42:43'),
(13, 'KRSK-1749268200056', 13, 'udin jainudin', '15240133@bsi.ac.id', 1, 500000.00, 'KRSK-1749268200056-BSI-BLT-E01', '2025-06-07 03:50:00'),
(14, 'KRSK-1749268591641', 2, 'anto', '15240133@bsi.ac.id', 1, 120000.00, 'KRSK-1749268591641-BSI-BDG-B01', '2025-06-07 03:56:31'),
(15, 'KRSK-1749268842935', 2, 'Anti', 'ahmadhabibi.fadilah@gmail.com', 1, 120000.00, 'KRSK-1749268842935-BSI-BDG-B01', '2025-06-07 04:00:42'),
(16, 'KRSK-1749269076460', 2, 'admin Suki', 'ahmadhabibi.fadilah@gmail.com', 1, 120000.00, 'KRSK-1749269076460-BSI-BDG-B01', '2025-06-07 04:04:36'),
(17, 'KRSK-1749269678748', 8, 'udinsaripudin', 'hbi2zz.contact@gmail.com', 1, 420000.00, 'KRSK-1749269678748-KAI-SBY-E01', '2025-06-07 04:14:38'),
(18, 'KRSK-1749270309524', 3, 'UdinSaripudin Stress', 'ahmadhabibi.fadilah@gmail.com', 1, 155000.00, 'KRSK-1749270309524-BSI-BDG-E02', '2025-06-07 04:25:09'),
(19, 'KRSK-1749275146432', 2, 'Udin Saprudin', '15240133@bsi.ac.id', 2, 240000.00, 'KRSK-1749275146432-BSI-BDG-B01', '2025-06-07 05:45:46'),
(20, 'KRSK-1749275419901', 3, 'Goyut', 'ahmadhabibi.fadilah@gmail.com', 1, 155000.00, 'KRSK-1749275419901-BSI-BDG-E02', '2025-06-07 05:50:19'),
(21, 'KRSK-1749275498398', 1, 'Goyut Rahmawati', '15240133@bsi.ac.id', 1, 150000.00, 'KRSK-1749275498398-BSI-BDG-E01', '2025-06-07 05:51:38'),
(22, 'KRSK-1749275600522', 1, 'ahmad habibi', '15240133@bsi.ac.id', 2, 300000.00, 'KRSK-1749275600522-BSI-BDG-E01', '2025-06-07 05:53:20'),
(23, 'KRSK-1749275718225', 2, 'opal', 'ahmadhabibi.fadilah@gmail.com', 1, 120000.00, 'KRSK-1749275718225-BSI-BDG-B01', '2025-06-07 05:55:18'),
(24, 'KRSK-1749305612514', 1, 'Asep', 'asep@gmail.com', 1, 150000.00, 'KRSK-1749305612514-BSI-BDG-E01', '2025-06-07 14:13:32'),
(25, 'KRSK-1749308021110', 13, 'Anto Kewer', 'antokewer@gmail.com', 4, 2000000.00, 'KRSK-1749308021110-BSI-BLT-E01', '2025-06-07 14:53:41'),
(26, 'KRSK-1749308386746', 1, 'ali', 'ali@gmail.com', 1, 150000.00, 'KRSK-1749308386746-BSI-BDG-E01', '2025-06-07 14:59:46');

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE `routes` (
  `id_rute` int NOT NULL,
  `stasiun_tujuan_nama` varchar(100) NOT NULL COMMENT 'Nama Stasiun Tujuan, misal: Bandung, Jatinegara'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`id_rute`, `stasiun_tujuan_nama`) VALUES
(1, 'Bandung'),
(4, 'Blitar'),
(2, 'Jatinegara'),
(5, 'Jogjakarta'),
(6, 'Malang'),
(3, 'Surabaya');

-- --------------------------------------------------------

--
-- Table structure for table `schedules`
--

CREATE TABLE `schedules` (
  `id_jadwal` int NOT NULL,
  `id_kereta` int NOT NULL,
  `id_rute` int NOT NULL COMMENT 'Merujuk ke rute yang tujuannya dipilih',
  `id_kelas` int NOT NULL,
  `train_code` varchar(20) DEFAULT NULL COMMENT 'Train Code yang ditampilkan di UI',
  `waktu_keberangkatan` datetime NOT NULL COMMENT 'Tanggal dan Waktu Keberangkatan Lengkap dari Tangerang',
  `waktu_kedatangan` datetime NOT NULL COMMENT 'Tanggal dan Waktu Kedatangan Lengkap di Tujuan',
  `harga` decimal(12,2) NOT NULL COMMENT 'Harga tiket untuk jadwal ini',
  `total_kursi` int NOT NULL COMMENT 'Jumlah total kursi untuk kelas ini pada jadwal ini',
  `sisa_kursi` int NOT NULL,
  `is_free_cancellation` tinyint(1) DEFAULT '0' COMMENT 'Informasi Free Cancellation'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `schedules`
--

INSERT INTO `schedules` (`id_jadwal`, `id_kereta`, `id_rute`, `id_kelas`, `train_code`, `waktu_keberangkatan`, `waktu_kedatangan`, `harga`, `total_kursi`, `sisa_kursi`, `is_free_cancellation`) VALUES
(1, 1, 1, 1, 'BSI-BDG-E01', '2025-06-07 08:00:00', '2025-06-07 11:00:00', 150000.00, 50, 34, 1),
(2, 1, 1, 2, 'BSI-BDG-B01', '2025-06-07 08:00:00', '2025-06-07 11:00:00', 120000.00, 60, 41, 1),
(3, 1, 1, 1, 'BSI-BDG-E02', '2025-06-07 14:00:00', '2025-06-07 17:00:00', 155000.00, 50, 48, 0),
(4, 1, 2, 1, 'BSI-JNG-E01', '2025-06-07 09:00:00', '2025-06-07 09:45:00', 80000.00, 50, 48, 1),
(5, 1, 2, 2, 'BSI-JNG-B01', '2025-06-07 09:00:00', '2025-06-07 09:45:00', 60000.00, 60, 60, 1),
(6, 1, 3, 1, 'BSI-SBY-E01', '2025-06-08 07:00:00', '2025-06-08 17:00:00', 450000.00, 40, 38, 1),
(7, 1, 3, 2, 'BSI-SBY-B01', '2025-06-08 07:00:00', '2025-06-08 17:00:00', 380000.00, 50, 50, 0),
(8, 2, 3, 1, 'KAI-SBY-E01', '2025-06-07 19:00:00', '2025-06-08 05:00:00', 420000.00, 55, 53, 1),
(9, 2, 3, 2, 'KAI-SBY-B01', '2025-06-07 19:00:00', '2025-06-08 05:00:00', 350000.00, 65, 65, 1),
(10, 2, 5, 1, 'KAI-JOG-E01', '2025-06-08 08:30:00', '2025-06-08 16:30:00', 300000.00, 50, 50, 0),
(11, 2, 5, 2, 'KAI-JOG-B01', '2025-06-08 08:30:00', '2025-06-08 16:30:00', 250000.00, 60, 59, 1),
(12, 2, 6, 1, 'KAI-MLG-E01', '2025-06-09 06:00:00', '2025-06-09 19:00:00', 480000.00, 45, 45, 1),
(13, 1, 4, 1, 'BSI-BLT-E01', '2025-06-09 10:00:00', '2025-06-09 22:00:00', 500000.00, 30, 0, 1),
(14, 2, 1, 2, 'KAI-BDG-B02', '2025-06-08 10:00:00', '2025-06-08 13:15:00', 110000.00, 70, 70, 0);

-- --------------------------------------------------------

--
-- Table structure for table `trains`
--

CREATE TABLE `trains` (
  `id_kereta` int NOT NULL,
  `nama_kereta` varchar(100) NOT NULL COMMENT 'Contoh: BSI EXP, KAI EXP',
  `tipe_kereta` varchar(50) DEFAULT NULL COMMENT 'Contoh: Ekspres, Ekonomi, Luxury'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `trains`
--

INSERT INTO `trains` (`id_kereta`, `nama_kereta`, `tipe_kereta`) VALUES
(1, 'BSI EXP', 'Eksekutif'),
(2, 'KAI EXP', 'Ekspres Campuran');

-- --------------------------------------------------------

--
-- Table structure for table `train_classes`
--

CREATE TABLE `train_classes` (
  `id_kelas` int NOT NULL,
  `kode_kelas` varchar(10) NOT NULL COMMENT 'Contoh: 1E, 2B - Sesuai UI',
  `nama_kelas` varchar(50) NOT NULL COMMENT 'Contoh: Eksekutif Subclass A, Bisnis Subclass B'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `train_classes`
--

INSERT INTO `train_classes` (`id_kelas`, `kode_kelas`, `nama_kelas`) VALUES
(1, '1E', 'Eksekutif A'),
(2, '2B', 'Bisnis B');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fullname`, `email`) VALUES
(1, 'BudiSantoso', 'budi123', 'Budi Santoso', 'budi.santoso@kresika.com'),
(2, 'CitraLestari', 'citra123', 'Citra Lestari', 'citra.lestari@kresika.com'),
(3, 'admin', 'admin1234', 'Admin Utama', 'admin.utama@kresika.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id_pemesanan`),
  ADD UNIQUE KEY `kode_pemesanan` (`kode_pemesanan`),
  ADD UNIQUE KEY `barcode` (`barcode`),
  ADD KEY `id_jadwal` (`id_jadwal`);

--
-- Indexes for table `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`id_rute`),
  ADD UNIQUE KEY `stasiun_tujuan_nama` (`stasiun_tujuan_nama`);

--
-- Indexes for table `schedules`
--
ALTER TABLE `schedules`
  ADD PRIMARY KEY (`id_jadwal`),
  ADD UNIQUE KEY `uk_schedule_offering` (`id_kereta`,`id_rute`,`id_kelas`,`waktu_keberangkatan`),
  ADD UNIQUE KEY `train_code` (`train_code`),
  ADD KEY `id_rute` (`id_rute`),
  ADD KEY `id_kelas` (`id_kelas`);

--
-- Indexes for table `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`id_kereta`);

--
-- Indexes for table `train_classes`
--
ALTER TABLE `train_classes`
  ADD PRIMARY KEY (`id_kelas`),
  ADD UNIQUE KEY `kode_kelas` (`kode_kelas`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `id_pemesanan` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `routes`
--
ALTER TABLE `routes`
  MODIFY `id_rute` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `schedules`
--
ALTER TABLE `schedules`
  MODIFY `id_jadwal` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `trains`
--
ALTER TABLE `trains`
  MODIFY `id_kereta` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `train_classes`
--
ALTER TABLE `train_classes`
  MODIFY `id_kelas` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`id_jadwal`) REFERENCES `schedules` (`id_jadwal`);

--
-- Constraints for table `schedules`
--
ALTER TABLE `schedules`
  ADD CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`id_kereta`) REFERENCES `trains` (`id_kereta`),
  ADD CONSTRAINT `schedules_ibfk_2` FOREIGN KEY (`id_rute`) REFERENCES `routes` (`id_rute`),
  ADD CONSTRAINT `schedules_ibfk_3` FOREIGN KEY (`id_kelas`) REFERENCES `train_classes` (`id_kelas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
