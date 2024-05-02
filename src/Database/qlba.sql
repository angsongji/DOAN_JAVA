-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 28, 2024 at 10:29 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlba`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `SOHD` varchar(10) NOT NULL,
  `MASP` varchar(10) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `SOLUONG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MAPN` varchar(10) NOT NULL,
  `MASP` varchar(10) NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `GIANHAP` double NOT NULL,
  `THANHTIEN` double NOT NULL,
  `MASIZE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chitietquyen`
--

CREATE TABLE `chitietquyen` (
  `MAQUYEN` varchar(10) NOT NULL,
  `MACHUCNANG` varchar(10) NOT NULL,
  `HANHDONG` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chitietsanpham`
--

CREATE TABLE `chitietsanpham` (
  `MASP` varchar(10) NOT NULL,
  `MASIZE` varchar(10) NOT NULL,
  `SOLUONG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `MACHUCNANG` varchar(10) NOT NULL,
  `TENCHUCNANG` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hinh`
--

CREATE TABLE `hinh` (
  `TENHINH` varchar(50) NOT NULL,
  `MASP` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `SOHD` varchar(10) NOT NULL,
  `NGAYHD` date NOT NULL,
  `MAKH` int(11) NOT NULL,
  `MANV` varchar(10) NOT NULL,
  `TONGTIEN` double NOT NULL,
  `TIENGIAMGIA` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MAKH` int(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `TENKH` varchar(50) NOT NULL,
  `SDT` varchar(11) NOT NULL,
  `DIEMTICHLUY` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loai`
--

CREATE TABLE `loai` (
  `MALOAI` varchar(10) NOT NULL,
  `TENLOAI` varchar(50) NOT NULL,
  `TINHTRANG` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MANCC` varchar(10) NOT NULL,
  `TENNCC` varchar(30) NOT NULL,
  `SDT` int(10) NOT NULL,
  `TRANGTHAI` tinyint default 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MANV` varchar(10) NOT NULL,
  `TENNV` varchar(50) NOT NULL,
  `CHUCVU` varchar(50) NOT NULL,
  `SDT` int NOT NULL,
  `DIACHI` varchar(255) NOT NULL,
  `EMAIL`  varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MAPN` varchar(10) NOT NULL,
  `MANV` varchar(10) NOT NULL,
  `NGAYNHAP` date NOT NULL,
  `TONGTIEN` double NOT NULL,
  `MANCC` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `MAQUYEN` varchar(10) NOT NULL,
  `TENQUYEN` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MASP` varchar(10) NOT NULL,
  `MALOAI` varchar(10) NOT NULL,
  `PRICE` double NOT NULL,
  `TENSP` varchar(50) NOT NULL,
  `TRANGTHAI` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `size`
--

CREATE TABLE `size` (
  `MASIZE` varchar(10) NOT NULL,
  `TENSIZE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MANV` varchar(10) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `NGAYTAOTK` date NOT NULL,
  `TINHTRANG` int(11) NOT NULL,
  `MAQUYEN` varchar(10) NOT NULL,
   PRIMARY KEY (USERNAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `chitiethoadon` VALUES ('HD001','AH001','SIZE5',4),('HD002','AL002','SIZE5',1);
INSERT INTO `chitietphieunhap` VALUES ('PN001','ASM002',1,4000000,4000000,'SIZE2'),('PN002','AF001',6,8500000,51000000,'SIZE5'),('PN003','AF002',2,6400000,12800000,'SIZE5'),('PN004','AF002',2,6400000,12800000,'SIZE5'),('PN005','AF003',3,2500000,7500000,'SIZE5'),('PN006','AH001',3,200000,600000,'SIZE5'),('PN006','AK001',3,2000000,6000000,'SIZE1'),('PN006','AK001',3,2000000,6000000,'SIZE2'),('PN006','AK001',3,2000000,6000000,'SIZE3'),('PN006','AL001',5,1500000,7500000,'SIZE5'),('PN006','AL002',9,170000,1530000,'SIZE5');
INSERT INTO `quyen` VALUES ('QNV','Quyền nhân viên'),('QQLBH','Quyền quản lí bán hàng'),('QQLHT','Quyền quản lí hệ thống'),('QQLK','Quyền quản lí kho');

INSERT INTO `chucnang` VALUES 
('SP','Sản phẩm'),
('NV','Nhân viên'),
('KH','Khách hàng'),
('PN','Phiếu nhập'),
('TK','Tài khoản'),
('PQ','Phân quyền'),
('NCC','Nhà cung cấp'),
('LOAI','Loại'),
('SIZE','Size'),
('HD','Hoá đơn'),
('NULLThK','Thống kê');


INSERT INTO `chitietquyen` VALUES
('QNV','KH','Sửa'),
('QNV','KH','Xem'),
('QNV','KH','Thêm'),
('QNV','SP','Xem'),
('QNV','HD','Xem'),
('QNV','HD','Thêm'),
('QQLBH','SP','Xem'),
('QQLBH','NV','Xem'),
('QQLBH','NV','Thêm'),
('QQLBH','NV','Xóa'),
('QQLBH','NV','Sửa'),
('QQLBH','KH','Xóa'),
('QQLBH','KH','Sửa'),
('QQLBH','KH','Xem'),
('QQLBH','KH','Thêm'),
('QQLBH','HD','Xem'),
('QQLBH','HD','Xóa'),
('QQLBH','HD','Thêm'),
('QQLBH','HD','Sửa'),
('QQLK','SP','Xem'),
('QQLK','SP','Sửa'),
('QQLK','SP','Xóa'),
('QQLK','NV','Xem'),
('QQLK','KH','Xem'),
('QQLK','PN','Export Excel'),
('QQLK','PN','Sửa'),
('QQLK','PN','Thêm'),
('QQLK','PN','Xem'),
('QQLK','PN','Xóa'),
('QQLK','HD','Xem'),
('QQLHT','NULLThK','Xem'),
('QQLHT','KH','Sửa'),
('QQLHT','KH','Xem'),
('QQLHT','KH','Xóa'),
('QQLHT','KH','Thêm'),
('QQLHT','SP','Xem'),
('QQLHT','SP','Sửa'),
('QQLHT','SP','Xóa'),
('QQLHT','NCC','Xem'),
('QQLHT','NCC','Sửa'),
('QQLHT','NCC','Xóa'),
('QQLHT','NCC','Thêm'),
('QQLHT','PN','Export Excel'),
('QQLHT','PN','Sửa'),
('QQLHT','PN','Thêm'),
('QQLHT','PN','Xem'),
('QQLHT','PN','Xóa'),
('QQLHT','NV','Xem'),
('QQLHT','NV','Thêm'),
('QQLHT','NV','Xóa'),
('QQLHT','NV','Sửa'),
('QQLHT','HD','Xem'),
('QQLHT','HD','Xóa'),
('QQLHT','HD','Thêm'),
('QQLHT','HD','Sửa'),
('QQLHT','TK','Xem'),
('QQLHT','TK','Sửa'),
('QQLHT','TK','Xóa'),
('QQLHT','TK','Thêm'),
('QQLHT','PQ','Xem'),
('QQLHT','PQ','Thêm'),
('QQLHT','PQ','Xóa'),
('QQLHT','PQ','Sửa'),
('QQLHT','LOAI','Xem'),
('QQLHT','LOAI','Sửa'),
('QQLHT','LOAI','Xóa'),
('QQLHT','LOAI','Thêm'),
('QQLHT','SIZE','Xem'),
('QQLHT','SIZE','Sửa'),
('QQLHT','SIZE','Xóa'),
('QQLHT','SIZE','Thêm');

INSERT INTO `chitietsanpham` VALUES ('AF001','SIZE5',6),('AF002','SIZE5',4),('AF003','SIZE5',3),('AH001','SIZE5',10),('AK001','SIZE1',5),('AK001','SIZE2',7),('AK001','SIZE3',3),('AL001','SIZE5',5),('AL002','SIZE5',9),('ASM001','SIZE1',4),('ASM001','SIZE2',7),('ASM001','SIZE3',6),('ASM001','SIZE4',3),('ASM002','SIZE1',2),('ASM002','SIZE2',7),('ASM002','SIZE3',4),('ASM003','SIZE2',3),('ASM003','SIZE3',4);

INSERT INTO `hinh` VALUES ('AF001_1.jpg','AF001'),('AF001_2.jpg','AF001'),('AF002_1.jpg','AF002'),('AF002_2.jpg','AF002'),('AF003_1.jpg','AF003'),('AF003_2.jpg','AF003'),('AH001.jpg','AH001'),('AK001.jpg','AK001'),('AL001.jpg','AL001'),('AL002.jpg','AL002'),('ASM001_1.jpg','ASM001'),('ASM001_2.jpg','ASM001'),('ASM002_1.jpg','ASM002'),('ASM002_2.jpg','ASM002'),('ASM003_1.jpg','ASM003'),('ASM003_2.jpg','ASM003');

INSERT INTO `hoadon` VALUES ('HD001','2023-08-13','3','NV003',1260000,100000),('HD002','2023-07-22','6','NV003',270000,0);

INSERT INTO `khachhang` VALUES (1,'Tuấn Khùng','908112345',230),(2,'Quỳnh Quỳnh','909112443',200),(3,'Trịnh Trần Phương Tuấn','907545661',150),(4,'Hà Lê','909887123',220),(5,'Hương Võ','909700813',280),(6,'Yến Hoàng','907661234',180);


INSERT INTO `loai` VALUES ('LOAI1','Áo flannel',1),('LOAI2','Áo Hoodie',1),('LOAI3','Áo Khoác',1),('LOAI4','Áo Len',0),('LOAI5','Áo Nỉ',1),('LOAI6','Áo sơmi',1);
INSERT INTO `nhacungcap`(MANCC,TENNCC,SDT) VALUES ('NCC1','MLB',901123444),('NCC2','Supreme',907345556),('NCC3','OFF-WHITE ',909557689),('NCC4','LEVIS',907112580),('NCC5','Sakura',901558739);
INSERT INTO `nhanvien` VALUES ('AD001','Thanh Sang','Quản lí ứng dụng',0907665456,'273 An Dương Vương Quận 5 TPHCM','ngthanhsangsgu@gmail.com'),('NV001','Thanh Phương','Nhân viên bán hàng',0909332432,'18 Nguyễn Tri Phương Quận 8 TPHCM','thanhphuong22@gmail.com'),('NV002','Nhật Long','Nhân viên bán hàng',0908332112,'23/1 Lê Hồng Phong TPHCM','ntLong@gmail.com'),('NV003','Oanh Le','Nhân viên bán hàng',0907665512,'Quận 2 TPHCM','Oanhle204@gmail.com'),('QL001','Phương Uyên','Quản lí kho',0338653321,'Quận 3 TPHCM','phuongUyen11@gmail.com'),('QL002','Trí Anh','Quản lí bán hàng',0901132445,'Quận 5 TPHCM','triANH@gmail.com');
INSERT INTO `phieunhap` VALUES ('PN001','NV001','2024-06-05',4000000,'NCC1'),('PN002','NV002','2024-06-05',51000000,'NCC2'),('PN003','NV003','2024-04-03',12800000,'NCC3'),('PN004','NV003','2024-01-25',12800000,'NCC3'),('PN005','QL001','2024-02-11',7500000,'NCC4'),('PN006','QL001','2024-05-12',27630000,'NCC5');
INSERT INTO `sanpham` VALUES ('AF001','AF',10860500,'Supreme Áo Flannel lót bông',1),('AF002','AF',7400000,'OFF-WHITE Áo Flannel ',0),('AF003','AF',2500000,'LEVIS Áo Flannel tươi mát',1),('AH001','AH',340000,'Áo hoodie nữ gấu trúc Panda',1),('AK001','AK',2500000,'Áo khoác nữ',1),('AL001','AL',1800000,'Áo len nữ mỏng dáng ôm',1),('AL002','AL',270000,'Áo len dệt kim nhung cao cấp nữ',0),('ASM001','ASM',4790000,'MLB - Áo sơ mi denim unisex cổ bẻ tay dài Basic Multi Mega Logo',1),('ASM002','ASM',4490000,'MLB - Áo sơ mi denim unisex cổ bẻ tay dài phom suông thời trang',1),('ASM003','ASM',5490000,'MLB - Áo sơ mi denim unisex cổ bẻ tay dài Cube Monogram',0);
INSERT INTO `size` VALUES ('SIZE1','S'),('SIZE2','M'),('SIZE3','L'),('SIZE4','XL'),('SIZE5','onesize');
INSERT INTO `taikhoan` VALUES ('NV001','NV001','Phuong@','2023-09-14',1,'QNV'),('AD001','AD001','SangHard!','2023-02-13',1,'QQLHT'),('NV002','NV002','anhLong1111','2023-11-20',0,'QNV'),('NV003','NV003','Oanh2004!','2023-10-22',1,'QNV'),('QL001','QL001','PhuongUyen!','2023-09-01',1,'QQLK'),('QL002','QL002','TriAnhhh','2023-06-22',1,'QQLBH');
--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`SOHD`,`MASP`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`MAPN`,`MASP`,`MASIZE`);

--
-- Indexes for table `chitietquyen`
--
ALTER TABLE `chitietquyen`
  ADD PRIMARY KEY (`MAQUYEN`,`MACHUCNANG`,`HANHDONG`);

--
-- Indexes for table `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  ADD PRIMARY KEY (`MASP`,`MASIZE`);

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`MACHUCNANG`);

--
-- Indexes for table `hinh`
--
ALTER TABLE `hinh`
  ADD PRIMARY KEY (`TENHINH`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`SOHD`);

--
-- Indexes for table `khachhang`
--


--
-- Indexes for table `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`MALOAI`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MANCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MANV`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MAPN`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`MAQUYEN`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MASP`);

--
-- Indexes for table `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`MASIZE`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
