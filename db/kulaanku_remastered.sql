-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 21 Bulan Mei 2024 pada 12.18
-- Versi server: 8.0.30
-- Versi PHP: 8.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kulaanku_remastered`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id` int NOT NULL,
  `kode` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nama` varchar(50) NOT NULL,
  `id_unit` int NOT NULL,
  `harga` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id`, `kode`, `nama`, `id_unit`, `harga`) VALUES
(1, 'IDMIEGR', 'Indomie Goreng', 2, 3000),
(2, 'SDPSOTO', 'Mie Sedap Soto', 2, 3000),
(3, 'SDPGRG', 'Mie Sedap Goreng', 2, 3000),
(4, 'TRGBIRU', 'Tepung Terigu Segitiga Biru', 1, 6000),
(5, 'BML1L', 'MInyal Bimoli 1 Liter', 4, 15000),
(6, 'LHNBRS', 'Beras Lohan', 1, 10000),
(7, 'GLKIJO', 'Gulaku Gula Tebu', 1, 10000),
(8, 'AIRALQDR', 'Air Al Qodiri', 6, 11000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_kulaan`
--

CREATE TABLE `detail_kulaan` (
  `id` int NOT NULL,
  `id_barang` int NOT NULL,
  `hargaKulaan` int NOT NULL,
  `stokDidapat` int NOT NULL,
  `stok_tersisa` int NOT NULL,
  `tgl_exp` datetime DEFAULT NULL,
  `id_kulaan` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Trigger `detail_kulaan`
--
DELIMITER $$
CREATE TRIGGER `update_tambah_stok` AFTER INSERT ON `detail_kulaan` FOR EACH ROW update barang set stok = stok + NEW.stokDidapat where barang.id = NEW.id_barang
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_retur_kulaan`
--

CREATE TABLE `detail_retur_kulaan` (
  `id` int NOT NULL,
  `id_retur_kulaan` int DEFAULT NULL,
  `id_barang` int DEFAULT NULL,
  `harga_kulaan` int DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `subtotal` int DEFAULT NULL,
  `alasan` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id` int NOT NULL,
  `id_transaksi` int NOT NULL,
  `id_barang` int NOT NULL,
  `harga` int NOT NULL,
  `qty` int NOT NULL,
  `subtotal` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Trigger `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `update_kurang_stok` AFTER INSERT ON `detail_transaksi` FOR EACH ROW update barang set stok = stok - NEW.qty WHERE barang.id = NEW.id_barang
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kulaan`
--

CREATE TABLE `kulaan` (
  `id` int NOT NULL,
  `judul_kulakan` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `total` int NOT NULL,
  `catatan` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `retur_kulaan`
--

CREATE TABLE `retur_kulaan` (
  `id` int NOT NULL,
  `id_kulaan` int DEFAULT NULL,
  `tanggal` datetime DEFAULT NULL,
  `total_harga` int DEFAULT NULL,
  `catatan` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int NOT NULL,
  `noTransaksi` varchar(50) NOT NULL,
  `id_user` int NOT NULL,
  `total_item` int NOT NULL,
  `total_harga` int NOT NULL,
  `total_dibayar` int NOT NULL,
  `total_kembalian` int NOT NULL,
  `tanggal` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `unit`
--

CREATE TABLE `unit` (
  `id` int NOT NULL,
  `unit` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `unit`
--

INSERT INTO `unit` (`id`, `unit`) VALUES
(1, 'Kg'),
(2, 'Pcs'),
(3, 'Buah'),
(4, 'Liter'),
(5, 'Lusin'),
(6, 'Dus'),
(7, 'Gram');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `rfid_code` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `nama`, `username`, `rfid_code`, `password`, `role`) VALUES
(1, 'admin', 'admin', '2931225634', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', '1'),
(3, 'kasir', 'kasir', '3959761977', 'f02b7c1e519e4fa436147f7e1399974f9510aa9c8e0cb8be29151eb540f9d214', '2');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kode` (`kode`),
  ADD KEY `id_unit` (`id_unit`);

--
-- Indeks untuk tabel `detail_kulaan`
--
ALTER TABLE `detail_kulaan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_kulaan` (`id_kulaan`);

--
-- Indeks untuk tabel `detail_retur_kulaan`
--
ALTER TABLE `detail_retur_kulaan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_retur_kulaan` (`id_retur_kulaan`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indeks untuk tabel `kulaan`
--
ALTER TABLE `kulaan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `retur_kulaan`
--
ALTER TABLE `retur_kulaan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kulaan` (`id_kulaan`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Indeks untuk tabel `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `barang`
--
ALTER TABLE `barang`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `detail_kulaan`
--
ALTER TABLE `detail_kulaan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `kulaan`
--
ALTER TABLE `kulaan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `unit`
--
ALTER TABLE `unit`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`id_unit`) REFERENCES `unit` (`id`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_kulaan`
--
ALTER TABLE `detail_kulaan`
  ADD CONSTRAINT `detail_kulaan_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_kulaan_ibfk_2` FOREIGN KEY (`id_kulaan`) REFERENCES `kulaan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_retur_kulaan`
--
ALTER TABLE `detail_retur_kulaan`
  ADD CONSTRAINT `detail_retur_kulaan_ibfk_1` FOREIGN KEY (`id_retur_kulaan`) REFERENCES `retur_kulaan` (`id`),
  ADD CONSTRAINT `detail_retur_kulaan_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id`);

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `retur_kulaan`
--
ALTER TABLE `retur_kulaan`
  ADD CONSTRAINT `retur_kulaan_ibfk_1` FOREIGN KEY (`id_kulaan`) REFERENCES `kulaan` (`id`);

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
