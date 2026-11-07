-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 31, 2026 at 02:18 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ruteconnect`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `user_id` int NOT NULL,
  `tingkat_akses` varchar(50) NOT NULL,
  `bagian_divisi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`user_id`, `tingkat_akses`, `bagian_divisi`) VALUES
(14, 'Super Admin', 'Operasional & Sistem'),
(15, 'Staff Admin', 'Layanan Pelanggan');

-- --------------------------------------------------------

--
-- Table structure for table `dokumen`
--

CREATE TABLE `dokumen` (
  `pesanan_id` int NOT NULL,
  `nama_penerima` varchar(100) NOT NULL,
  `no_telepon_penerima` varchar(20) NOT NULL,
  `detail_dokumen` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `dokumen`
--

INSERT INTO `dokumen` (`pesanan_id`, `nama_penerima`, `no_telepon_penerima`, `detail_dokumen`) VALUES
(6, 'Bapak Rusdi Umum', '081298765432', 'Dokumen rahasia - Harap diserahkan langsung');

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE `driver` (
  `user_id` int NOT NULL,
  `merk_kendaraan` varchar(50) NOT NULL,
  `no_plat_kendaraan` varchar(15) NOT NULL,
  `jenis_kendaraan` varchar(20) NOT NULL,
  `status_aktif` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`user_id`, `merk_kendaraan`, `no_plat_kendaraan`, `jenis_kendaraan`, `status_aktif`) VALUES
(2, 'Honda Beat', 'KT 1234 AB', 'Motor', 'Non-Aktif'),
(4, 'Yamaha NMax', 'KT 5678 CD', 'Motor', 'Aktif'),
(6, 'Honda Vario', 'KT 4321 EF', 'Motor', 'Aktif'),
(8, 'Toyota Avanza', 'KT 9876 GH', 'Mobil', 'Aktif'),
(10, 'Daihatsu Sigra', 'KT 2468 IJ', 'Mobil', 'Aktif'),
(12, 'Honda Mio', 'KT 1357 KL', 'Motor', 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `layanan`
--

CREATE TABLE `layanan` (
  `layanan_id` int NOT NULL,
  `nama_layanan` varchar(50) NOT NULL,
  `jenis_kendaraan` varchar(20) NOT NULL,
  `kelas_layanan` varchar(20) NOT NULL,
  `tarif_per_km` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `layanan`
--

INSERT INTO `layanan` (`layanan_id`, `nama_layanan`, `jenis_kendaraan`, `kelas_layanan`, `tarif_per_km`) VALUES
(1, 'RuteBike Regular', 'Motor', 'Biasa', '6500.00'),
(2, 'RuteBike Comfort', 'Motor', 'Comfort', '8500.00'),
(3, 'RuteCar 4 Penumpang', 'Mobil', 'Biasa', '8000.00'),
(4, 'RuteCar 6 Penumpang', 'Mobil', 'Biasa', '10000.00'),
(5, 'RuteCar 8 Penumpang', 'Mobil', 'Biasa', '12000.00'),
(6, 'RuteCar Comfort 4 Penumpang', 'Mobil', 'Comfort', '11000.00'),
(7, 'RuteCar Comfort 6 Penumpang', 'Mobil', 'Comfort', '14000.00'),
(8, 'RuteExpress Dokumen', 'Motor', 'Reguler', '7000.00');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `user_id` int NOT NULL,
  `alamat_favorit` varchar(250) DEFAULT NULL,
  `saldo_wallet` decimal(10,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`user_id`, `alamat_favorit`, `saldo_wallet`) VALUES
(1, 'Jl. M. Yamin No. 12, Samarinda', '27000.00'),
(3, 'Jl. Pramuka No. 45, Samarinda', '60000.00'),
(5, 'Jl. Awang Long No. 8, Samarinda', '32000.00'),
(7, 'Jl. Juanda Blok B No. 3, Samarinda', '168000.00'),
(9, 'Jl. Antasari No. 19, Samarinda', '30000.00'),
(11, 'Jl. Lambung Mangkurat No. 22, Samarinda', '150000.00'),
(13, 'Jl. Cipto Mangunkusumo No. 7, Samarinda', '95000.00'),
(16, 'Jl. PM. Noor No. 10, Samarinda', '50000.00'),
(17, 'Jl. Cipto Mangunkusumo No. 88, Samarinda', '120000.00'),
(18, 'Jl. A. Yani No. 54, Samarinda', '75000.00');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `pesanan_id` int NOT NULL,
  `lokasi_jemput` varchar(255) NOT NULL,
  `lokasi_tujuan` varchar(255) NOT NULL,
  `jarak_km` decimal(10,2) NOT NULL,
  `waktu_penjemputan` datetime DEFAULT NULL,
  `catatan` varchar(255) DEFAULT NULL,
  `total_biaya` decimal(10,2) NOT NULL,
  `waktu_pesanan` date NOT NULL,
  `status_pesanan` varchar(30) NOT NULL,
  `alasan_batal` varchar(200) DEFAULT NULL,
  `pelanggan_id` int NOT NULL,
  `driver_id` int DEFAULT NULL,
  `layanan_id` int NOT NULL,
  `transaksi_id` int NOT NULL,
  `jenis_pesanan` varchar(50) DEFAULT NULL
) ;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`pesanan_id`, `lokasi_jemput`, `lokasi_tujuan`, `jarak_km`, `waktu_penjemputan`, `catatan`, `total_biaya`, `waktu_pesanan`, `status_pesanan`, `alasan_batal`, `pelanggan_id`, `driver_id`, `layanan_id`, `transaksi_id`, `jenis_pesanan`) VALUES
(1, 'Jl. M. Yamin No. 12', 'Universitas Mulawarman', '5.50', '2026-10-01 12:05:00', 'Hati-hati di jalan pak', '23000.00', '2026-10-01', 'Selesai', NULL, 1, 2, 1, 8, 'Transportasi'),
(2, 'Jl. Awang Long No. 45', 'BigMall Samarinda', '8.00', '2026-10-02 13:40:00', 'Titip di lobby utama', '40000.00', '2026-10-02', 'Selesai', NULL, 3, 6, 3, 9, 'Dokumen'),
(3, 'Jl. Pangeran Antasari', 'Pasar Pagi', '3.00', '2026-10-03 11:05:00', 'Bawa belanjaan agak banyak', '18000.00', '2026-10-03', 'Selesai', NULL, 5, 4, 1, 10, 'Transportasi'),
(4, 'Jl. Cipto Mangunkusumo', 'Mall Lembuswana', '4.50', '2026-10-04 15:10:00', 'Jangan ngebut-ngebut ya mas', '32000.00', '2026-10-04', 'Selesai', NULL, 7, 8, 2, 11, 'Dokumen'),
(5, 'Jl. Pramuka No. 15', 'Pelabuhan Samarinda', '7.00', '2026-10-05 15:40:00', 'Bawa koper sedang', '45000.00', '2026-10-05', 'Selesai', NULL, 9, 10, 3, 12, 'Transportasi'),
(6, 'Jl. Juanda No. 99', 'Kantor Gubernur Kaltim', '4.00', '2026-10-06 12:10:00', 'Dokumen penting tanda tangan', '20000.00', '2026-10-06', 'Selesai', NULL, 11, 2, 8, 13, 'Dokumen'),
(7, 'Jl. Ahmad Yani No. 77', 'RSUD AWS', '5.00', '2026-10-07 13:40:00', 'Pasien darurat ringan', '25000.00', '2026-10-07', 'Selesai', NULL, 13, 8, 1, 14, 'Transportasi'),
(8, 'Universitas Mulawarman', 'Jl. M. Yamin No. 12', '5.50', '2026-10-08 16:10:00', 'Pulang kuliah', '23000.00', '2026-10-08', 'Selesai', NULL, 1, 4, 1, 15, 'Transportasi');

-- --------------------------------------------------------

--
-- Table structure for table `top_up`
--

CREATE TABLE `top_up` (
  `transaksi_id` int NOT NULL,
  `saldo_masuk` decimal(10,2) NOT NULL,
  `saldo_keluar` decimal(10,2) NOT NULL,
  `status_top_up` varchar(30) NOT NULL,
  `pelanggan_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `top_up`
--

INSERT INTO `top_up` (`transaksi_id`, `saldo_masuk`, `saldo_keluar`, `status_top_up`, `pelanggan_id`) VALUES
(1, '50000.00', '0.00', 'Berhasil', 1),
(2, '100000.00', '0.00', 'Berhasil', 3),
(3, '50000.00', '0.00', 'Gagal', 5),
(4, '200000.00', '0.00', 'Berhasil', 7),
(5, '75000.00', '0.00', 'Berhasil', 9),
(6, '150000.00', '0.00', 'Berhasil', 11),
(7, '120000.00', '0.00', 'Berhasil', 13);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `transaksi_id` int NOT NULL,
  `jenis_transaksi` varchar(30) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `biaya_lainnya` decimal(10,2) DEFAULT NULL,
  `jumlah_transaksi` decimal(10,2) NOT NULL,
  `metode_pembayaran` varchar(30) NOT NULL,
  `status_transaksi` varchar(30) NOT NULL,
  `waktu_transaksi` date NOT NULL,
  `user_id` int NOT NULL,
  `pesanan_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`transaksi_id`, `jenis_transaksi`, `harga`, `biaya_lainnya`, `jumlah_transaksi`, `metode_pembayaran`, `status_transaksi`, `waktu_transaksi`, `user_id`, `pesanan_id`) VALUES
(1, 'Top Up', '50000.00', '0.00', '50000.00', 'E-Wallet', 'Berhasil', '2026-10-01', 1, NULL),
(2, 'Pembayaran Pesanan', '23000.00', '2000.00', '25000.00', 'Cash', 'Berhasil', '2026-10-01', 1, 1),
(3, 'Top Up', '100000.00', '0.00', '100000.00', 'E-Wallet', 'Berhasil', '2026-10-02', 3, NULL),
(4, 'Pembayaran Pesanan', '40000.00', '5000.00', '45000.00', 'Cash', 'Berhasil', '2026-10-02', 3, 2),
(5, 'Top Up', '50000.00', '0.00', '50000.00', 'E-Wallet', 'Gagal', '2026-10-03', 5, NULL),
(6, 'Pembayaran Pesanan', '18000.00', '0.00', '18000.00', 'Cash', 'Berhasil', '2026-10-03', 5, 3),
(7, 'Top Up', '200000.00', '0.00', '200000.00', 'E-Wallet', 'Berhasil', '2026-10-04', 7, NULL),
(8, 'Pembayaran Pesanan', '32000.00', '3000.00', '35000.00', 'Cash', 'Berhasil', '2026-10-04', 7, 4),
(9, 'Top Up', '75000.00', '0.00', '75000.00', 'E-Wallet', 'Berhasil', '2026-10-05', 9, NULL),
(10, 'Pembayaran Pesanan', '45000.00', '5000.00', '50000.00', 'Cash', 'Berhasil', '2026-10-05', 9, 5),
(11, 'Top Up', '150000.00', '0.00', '150000.00', 'E-Wallet', 'Berhasil', '2026-10-06', 11, NULL),
(12, 'Pembayaran Pesanan', '20000.00', '2000.00', '22000.00', 'E-Wallet', 'Berhasil', '2026-10-06', 11, 6),
(13, 'Top Up', '120000.00', '0.00', '120000.00', 'E-Wallet', 'Berhasil', '2026-10-07', 13, NULL),
(14, 'Pembayaran Pesanan', '25000.00', '3000.00', '28000.00', 'Cash', 'Berhasil', '2026-10-07', 13, 7),
(15, 'Pembayaran Pesanan', '50000.00', '10000.00', '60000.00', 'E-Wallet', 'Berhasil', '2026-10-08', 1, 8),
(901, 'Transportasi', '15000.00', '0.00', '15000.00', 'Saldo', 'Berhasil', '2026-10-31', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `transportasi`
--

CREATE TABLE `transportasi` (
  `pesanan_id` int NOT NULL,
  `jumlah_penumpang` int NOT NULL,
  `status_penjemputan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transportasi`
--

INSERT INTO `transportasi` (`pesanan_id`, `jumlah_penumpang`, `status_penjemputan`) VALUES
(1, 1, 'Selesai'),
(2, 3, 'Selesai'),
(3, 1, 'Selesai'),
(4, 1, 'Selesai'),
(5, 4, 'Selesai'),
(7, 1, 'Selesai'),
(8, 1, 'Selesai');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_telepon` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL
) ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `nama`, `no_telepon`, `email`, `password`, `role`) VALUES
(1, 'Juned Abdillah', '081234567890', 'junedwow2299@email.com', 'juned12399900', 'Pelanggan'),
(2, 'Abdul Qodir', '081987654321', 'ABDULDULDULDUL@gmail.com', 'GondalGandul2626', 'Driver'),
(3, 'Ahmad Mahmud', '081311223344', 'ahmad.muhmadgaming01@gmail.com', 'ahmadGANTENG99', 'Pelanggan'),
(4, 'Dewi Lestari Kejora', '081222334455', 'dewi.lestari.official@gmail.com', 'dewiLestari1_1', 'Driver'),
(5, 'Joko Sutisno', '081333445566', 'jokoanakteknik@gmail.com', 'jokosisfor23', 'Pelanggan'),
(6, 'Rina Marlina', '081444556677', 'rina.marlina88@gmail.com', 'rinaMarlina#88', 'Driver'),
(7, 'Eko Pratama', '081555667788', 'ekoOkeSip@gmail.com', 'haiAkuEko00997', 'Pelanggan'),
(8, 'Siti Rahma', '081666778899', 'siti.rahma55@gmail.com', 'sitiRahma55', 'Driver'),
(9, 'Doni Setiawan', '081777889900', 'donisetiakawan@gmail.com', 'doniSetia1', 'Pelanggan'),
(10, 'Ani Yudhono', '081888990011', 'aniyudho877@gmail.com', 'aniudontkn0w', 'Driver'),
(11, 'Rian Hidayat', '081999001122', 'rian.hidayat12@gmail.com', 'rianHidayat!23', 'Pelanggan'),
(12, 'Mega Puspita', '081112233445', 'megapuspitaKEREN@gmail.com', 'mega99wow', 'Driver'),
(13, 'Fajar Sidik', '081223344556', 'fajar.sidik226@gmail.com', 'fajarSidikjari000', 'Pelanggan'),
(14, 'Admin Sistem 1', '081100000001', 'admin.support1@gmail.com', 'AdminRuteConnect#1', 'Admin'),
(15, 'Admin Sistem 2', '081100000002', 'admin.support2@gmail.com', 'AdminRuteConnect#2', 'Admin'),
(16, 'Bowo Hasanudin', '081322334455', 'udingagahperkasa@email.com', 'duhaiUdin123', 'Pelanggan'),
(17, 'Budi Santoso', '081566778899', 'bsantoso@email.com', 'budiSANTOS999', 'Pelanggan'),
(18, 'Putri Lestari', '081911223344', 'ptri.lestari@email.com', 'putrip111', 'Pelanggan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `dokumen`
--
ALTER TABLE `dokumen`
  ADD PRIMARY KEY (`pesanan_id`);

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `layanan`
--
ALTER TABLE `layanan`
  ADD PRIMARY KEY (`layanan_id`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`pesanan_id`),
  ADD KEY `Pesanan_Pelanggan_FK` (`pelanggan_id`),
  ADD KEY `Pesanan_Driver_FK` (`driver_id`),
  ADD KEY `Pesanan_Layanan_FK` (`layanan_id`),
  ADD KEY `Pesanan_Transaksi_FK` (`transaksi_id`);

--
-- Indexes for table `top_up`
--
ALTER TABLE `top_up`
  ADD PRIMARY KEY (`transaksi_id`),
  ADD KEY `Top_Up_Pelanggan_FK` (`pelanggan_id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`transaksi_id`),
  ADD KEY `Transaksi_User_FK` (`user_id`),
  ADD KEY `fk_transaksi_pesanan` (`pesanan_id`);

--
-- Indexes for table `transportasi`
--
ALTER TABLE `transportasi`
  ADD PRIMARY KEY (`pesanan_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `User_Email_Unique` (`email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `Admin_User_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `dokumen`
--
ALTER TABLE `dokumen`
  ADD CONSTRAINT `Dokumen_Pesanan_FK` FOREIGN KEY (`pesanan_id`) REFERENCES `pesanan` (`pesanan_id`);

--
-- Constraints for table `driver`
--
ALTER TABLE `driver`
  ADD CONSTRAINT `Driver_User_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD CONSTRAINT `Pelanggan_User_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `Pesanan_Driver_FK` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`user_id`),
  ADD CONSTRAINT `Pesanan_Layanan_FK` FOREIGN KEY (`layanan_id`) REFERENCES `layanan` (`layanan_id`),
  ADD CONSTRAINT `Pesanan_Pelanggan_FK` FOREIGN KEY (`pelanggan_id`) REFERENCES `pelanggan` (`user_id`),
  ADD CONSTRAINT `Pesanan_Transaksi_FK` FOREIGN KEY (`transaksi_id`) REFERENCES `transaksi` (`transaksi_id`);

--
-- Constraints for table `top_up`
--
ALTER TABLE `top_up`
  ADD CONSTRAINT `Top_Up_Pelanggan_FK` FOREIGN KEY (`pelanggan_id`) REFERENCES `pelanggan` (`user_id`),
  ADD CONSTRAINT `Top_Up_Transaksi_FK` FOREIGN KEY (`transaksi_id`) REFERENCES `transaksi` (`transaksi_id`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_transaksi_pesanan` FOREIGN KEY (`pesanan_id`) REFERENCES `pesanan` (`pesanan_id`),
  ADD CONSTRAINT `Transaksi_User_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `transportasi`
--
ALTER TABLE `transportasi`
  ADD CONSTRAINT `Transportasi_Pesanan_FK` FOREIGN KEY (`pesanan_id`) REFERENCES `pesanan` (`pesanan_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
