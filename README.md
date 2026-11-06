
<div align="center">

# 🚗 RUTECONNECT

### Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Java_Swing-GUI-1E3A8A?style=for-the-badge" alt="Java Swing">
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/Maven-Build_Tool-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
</p>

**Kelompok 27**  
Program Studi Sistem Informasi  
Universitas Mulawarman

</div>

---

## 📑 DAFTAR ISI

1. [Nama Program](#1--nama-program)
2. [Deskripsi Singkat](#2--deskripsi-singkat)
3. [Fitur Utama Program](#3--fitur-utama-program)
4. [Tech Stack](#4--tech-stack)
5. [Perancangan Sistem](#5--perancangan-sistem)
6. [Struktur Folder/Package](#6--struktur-folderpackage)
7. [Penerapan 4 Pilar OOP](#7--penerapan-4-pilar-oop)
8. [Cara Penggunaan Program](#8--cara-penggunaan-program)
9. [Kontributor](#9--kontributor)

---

## 1. 📖 NAMA PROGRAM

**RuteConnect**

*Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital.*

## 2. 💡 DESKRIPSI SINGKAT

RuteConnect merupakan aplikasi berbasis desktop yang dikembangkan untuk memudahkan pengguna dalam melakukan pemesanan layanan transportasi daring dan pengiriman dokumen secara digital.

Aplikasi ini memiliki tiga jenis pengguna, yaitu **Pelanggan, Driver, dan Admin**, dengan hak akses serta fitur yang berbeda sesuai perannya. Melalui aplikasi ini, proses pemesanan, pengelolaan layanan, dan pemantauan transaksi dapat dilakukan dalam satu sistem yang terintegrasi.

## 3. 🚀 FITUR UTAMA PROGRAM

RuteConnect menyediakan beberapa fitur berdasarkan jenis pengguna:

### 👤 A. Pelanggan

- Registrasi dan login akun.
- Melakukan pemesanan layanan transportasi.
- Melakukan pemesanan pengiriman dokumen.
- Melihat detail dan status pesanan.
- Membatalkan pesanan.
- Melihat riwayat pesanan.
- Melakukan top up saldo.
- Melihat riwayat transaksi.

### 🛵 B. Driver

- Login ke sistem.
- Melihat daftar pesanan yang tersedia.
- Melihat detail pesanan.
- Menerima atau menolak pesanan.
- Memperbarui status pesanan.
- Melihat riwayat pesanan.

### 🛠️ C. Admin

- Login ke sistem.
- Mengelola data pelanggan.
- Mengelola data driver.
- Mengelola tarif layanan.
- Melihat data pesanan.
- Melihat data transaksi.
- Melihat laporan.

## 4. 🧰 TECH STACK

Teknologi yang digunakan dalam pengembangan aplikasi RuteConnect:

| Teknologi | Keterangan |
|:---|:---|
| ☕ Java 21 | Bahasa pemrograman |
| 🗄️ MySQL | Sistem manajemen basis data |
| 🖥️ Java Swing | Pembuatan antarmuka aplikasi |
| 📦 Maven | Build automation dan manajemen dependensi |
| 🔌 JDBC | Koneksi aplikasi dengan database |

## 5. 📐 PERANCANGAN SISTEM

Perancangan sistem dilakukan sebagai dasar dalam proses pengembangan aplikasi RuteConnect.

### A. Flowchart

Flowchart menggambarkan alur proses dan tahapan kerja sistem RuteConnect.

<details>
<summary><b>📊 Klik untuk melihat Flowchart</b></summary>

![Flowchart RuteConnect](docs/flowchart.png)

</details>

### B. Use Case Diagram

Use Case Diagram menggambarkan interaksi antara aktor dengan fitur-fitur yang tersedia di dalam sistem.

<details>
<summary><b>👥 Klik untuk melihat Use Case Diagram</b></summary>

![Use Case Diagram RuteConnect](docs/use-case.png)

</details>

### C. Entity Relationship Diagram (ERD)

ERD menggambarkan hubungan antarentitas yang digunakan dalam basis data RuteConnect.

<details>
<summary><b>🗃️ Klik untuk melihat ERD</b></summary>

![ERD RuteConnect](docs/erd.png)

</details>

## 6. 📁 STRUKTUR FOLDER/PACKAGE

Berikut merupakan struktur folder/package pada project RuteConnect:

```text
RuteConnect/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── config/
│       │   ├── dao/
│       │   ├── model/
│       │   ├── service/
│       │   ├── util/
│       │   └── view/
│       │
│       └── resources/
│
├── pom.xml
└── README.md
```

> Catatan: Struktur di atas merupakan contoh susunan. Sesuaikan nama package dan folder dengan struktur project yang sebenarnya.

## 7. 🧩 PENJELASAN PENERAPAN 4 PILAR OOP DI DALAM PROGRAM

### A. Encapsulation (Enkapsulasi)

[Masukkan penjelasan penerapan enkapsulasi berdasarkan class dalam program.]

### B. Inheritance (Pewarisan)

[Masukkan penjelasan penerapan pewarisan berdasarkan class dalam program.]

### C. Polymorphism (Polimorfisme)

[Masukkan penjelasan penerapan polimorfisme berdasarkan class dalam program.]

### D. Abstraction (Abstraksi)

[Masukkan penjelasan penerapan abstraksi berdasarkan class dalam program.]

## 8. 💻 CARA PENGGUNAAN PROGRAM

Berikut merupakan langkah-langkah penggunaan aplikasi RuteConnect:

1. Jalankan aplikasi RuteConnect.
2. Tunggu hingga halaman login ditampilkan.
3. Masukkan akun sesuai dengan jenis pengguna.
4. Setelah berhasil login, pengguna akan diarahkan ke halaman dashboard.
5. Pilih fitur yang tersedia sesuai hak akses pengguna.
6. Lakukan aktivitas sesuai kebutuhan.
7. Keluar dari aplikasi setelah selesai digunakan.

### 📸 SCREENSHOT GUI

Berikut merupakan dokumentasi tampilan antarmuka aplikasi RuteConnect.

#### A. Halaman Login

![Halaman Login](docs/login.png)

#### B. Dashboard Pelanggan

![Dashboard Pelanggan](docs/dashboard-pelanggan.png)

#### C. Dashboard Driver

![Dashboard Driver](docs/dashboard-driver.png)

#### D. Dashboard Admin

![Dashboard Admin](docs/dashboard-admin.png)

#### E. Halaman Lainnya

[Tambahkan screenshot halaman lainnya jika diperlukan.]

## 9. 👥 KONTRIBUTOR

Project RuteConnect dikembangkan oleh Kelompok 27 dari Program Studi Sistem Informasi, Universitas Mulawarman.

| No. | Nama | NIM |
|:---:|:---|:---:|
| 1. | [Nama Anggota 1] | [NIM] |
| 2. | [Nama Anggota 2] | [NIM] |
| 3. | [Nama Anggota 3] | [NIM] |
| 4. | [Nama Anggota 4] | [NIM] |

---

<div align="center">

### 🚗 RUTECONNECT

**Connecting Your Journey, Simplifying Your Delivery.**

<sub>Developed by Kelompok 27 · Sistem Informasi · Universitas Mulawarman</sub>

</div>
