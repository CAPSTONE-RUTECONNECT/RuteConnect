
<!-- ====================================================== -->
<!--                    HEADER / BANNER                     -->
<!-- ====================================================== -->

<div align="center">

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:0B1F3A,50:163A63,100:24527A&height=220&section=header&text=RUTECONNECT&fontSize=55&fontColor=FFFFFF&fontAlignY=40&desc=Connecting%20Your%20Journey,%20Simplifying%20Your%20Delivery&descSize=15&descAlignY=65&animation=fadeIn" width="100%"/>

<br/>

### SISTEM INFORMASI TERINTEGRASI PEMESANAN TRANSPORTASI DARING DAN PENGIRIMAN DOKUMEN BERBASIS DIGITAL

<br/>

<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Java%20Swing-GUI-163A63?style=for-the-badge"/>
<img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-Build%20Tool-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>

<br/><br/>

**KELOMPOK 27**

Program Studi Sistem Informasi  
Universitas Mulawarman

</div>

<br/>

---

<!-- ====================================================== -->
<!--                     TABLE OF CONTENTS                  -->
<!-- ====================================================== -->

<div align="center">

## ✦ EXPLORE PROJECT ✦

| [📖 Tentang](#-1-nama-program) | [⚡ Fitur](#-3-fitur-utama-program) | [🛠️ Teknologi](#-4-tech-stack) | [📐 Desain](#-5-perancangan-sistem) |
|:---:|:---:|:---:|:---:|
| [📂 Struktur](#-6-struktur-folderpackage) | [🧩 OOP](#-7-penerapan-4-pilar-oop) | [💻 Penggunaan](#-8-cara-penggunaan-program) | [👥 Tim](#-9-kontributor) |

</div>

<br/>

---

## 📖 1. NAMA PROGRAM

<div align="center">

# 🚗 RuteConnect

**Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital**

</div>

## 💡 2. DESKRIPSI SINGKAT

RuteConnect merupakan aplikasi berbasis desktop yang dikembangkan untuk memudahkan pengguna dalam melakukan pemesanan layanan transportasi daring dan pengiriman dokumen secara digital.

Aplikasi ini memiliki tiga jenis pengguna, yaitu **Pelanggan, Driver, dan Admin**, dengan hak akses serta fitur yang berbeda sesuai perannya. Melalui aplikasi ini, proses pemesanan, pengelolaan layanan, dan pemantauan transaksi dapat dilakukan dalam satu sistem yang terintegrasi.

---

## ⚡ 3. FITUR UTAMA PROGRAM

RuteConnect memiliki tiga jenis pengguna dengan fungsionalitas yang disesuaikan berdasarkan kebutuhan masing-masing.

<table>
<tr>
<td width="50%" valign="top">

### 👤 PELANGGAN

- Registrasi dan login akun.
- Pemesanan layanan transportasi.
- Pemesanan pengiriman dokumen.
- Melihat detail dan status pesanan.
- Membatalkan pesanan.
- Melihat riwayat pesanan.
- Top up saldo.
- Melihat riwayat transaksi.

</td>
<td width="50%" valign="top">

### 🛵 DRIVER

- Login ke sistem.
- Melihat pesanan tersedia.
- Melihat detail pesanan.
- Menerima atau menolak pesanan.
- Memperbarui status pesanan.
- Melihat riwayat pesanan.

</td>
</tr>
<tr>
<td colspan="2" valign="top">

### 🛠️ ADMIN

- Login ke sistem.
- Mengelola data pelanggan.
- Mengelola data driver.
- Mengelola tarif layanan.
- Melihat data pesanan.
- Melihat data transaksi.
- Melihat laporan.

</td>
</tr>
</table>

---

## 🛠️ 4. TECH STACK

Teknologi yang digunakan dalam proses pengembangan RuteConnect.

<div align="center">

| TECHNOLOGY | DESCRIPTION |
|:---:|:---|
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="32"/> **Java 21** | Bahasa pemrograman utama |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="32"/> **MySQL** | Sistem manajemen basis data |
| **Swing** | Pengembangan antarmuka aplikasi desktop |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="32"/> **Maven** | Build automation dan manajemen dependensi |
| **JDBC** | Penghubung aplikasi dengan database |

</div>

---

## 📐 5. PERANCANGAN SISTEM

Perancangan sistem merupakan gambaran mengenai alur proses, interaksi pengguna, serta hubungan data yang digunakan dalam pengembangan RuteConnect.

### 5.1 Flowchart

Flowchart menggambarkan tahapan dan alur kerja sistem RuteConnect.

<div align="center">

<details>
<summary><b>📊 LIHAT FLOWCHART SISTEM</b></summary>

<br/>

<img src="docs/flowchart.png" width="90%" alt="Flowchart RuteConnect"/>

</details>

</div>

### 5.2 Use Case Diagram

Use Case Diagram menggambarkan interaksi antara aktor dengan fungsi-fungsi yang tersedia dalam aplikasi.

<div align="center">

<details>
<summary><b>👥 LIHAT USE CASE DIAGRAM</b></summary>

<br/>

<img src="docs/use-case.png" width="90%" alt="Use Case RuteConnect"/>

</details>

</div>

### 5.3 Entity Relationship Diagram (ERD)

ERD menggambarkan struktur entitas, atribut, serta hubungan antarentitas dalam basis data RuteConnect.

<div align="center">

<details>
<summary><b>🗄️ LIHAT ENTITY RELATIONSHIP DIAGRAM</b></summary>

<br/>

<img src="docs/erd.png" width="90%" alt="ERD RuteConnect"/>

</details>

</div>

---

## 📂 6. STRUKTUR FOLDER/PACKAGE

Struktur folder dan package digunakan untuk mengorganisasi source code agar pengembangan aplikasi lebih terstruktur.

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

> Sesuaikan struktur di atas dengan package dan folder yang benar-benar digunakan dalam project.

---

## 🧩 7. PENERAPAN 4 PILAR OOP

Aplikasi RuteConnect dikembangkan menggunakan konsep Object-Oriented Programming (OOP) yang mencakup empat pilar utama.

<table>
<tr>
<td width="50%" valign="top">

### 01. Encapsulation

**Enkapsulasi**

[Penjelasan penerapan enkapsulasi berdasarkan class dan atribut dalam program.]

</td>
<td width="50%" valign="top">

### 02. Inheritance

**Pewarisan**

[Penjelasan penerapan inheritance berdasarkan hubungan antarclass dalam program.]

</td>
</tr>
<tr>
<td width="50%" valign="top">

### 03. Polymorphism

**Polimorfisme**

[Penjelasan penerapan polymorphism berdasarkan method dalam program.]

</td>
<td width="50%" valign="top">

### 04. Abstraction

**Abstraksi**

[Penjelasan penerapan abstraction berdasarkan abstract class atau interface dalam program.]

</td>
</tr>
</table>

---

## 💻 8. CARA PENGGUNAAN PROGRAM

Berikut merupakan langkah-langkah penggunaan aplikasi RuteConnect:

1. Jalankan aplikasi RuteConnect.
2. Tunggu hingga halaman login ditampilkan.
3. Masukkan akun sesuai jenis pengguna.
4. Setelah berhasil login, pengguna akan diarahkan ke halaman dashboard.
5. Pilih fitur yang tersedia sesuai hak akses pengguna.
6. Lakukan aktivitas sesuai kebutuhan.
7. Keluar dari aplikasi setelah selesai digunakan.

### 📸 DOKUMENTASI GUI

Berikut merupakan dokumentasi tampilan antarmuka aplikasi RuteConnect.

<details>
<summary><b>01 — HALAMAN LOGIN</b></summary>

<br/>

<div align="center">

<img src="docs/login.png" width="85%" alt="Halaman Login RuteConnect"/>

<sub>Halaman login aplikasi RuteConnect.</sub>

</div>

</details>

<details>
<summary><b>02 — DASHBOARD PELANGGAN</b></summary>

<br/>

<div align="center">

<img src="docs/dashboard-pelanggan.png" width="85%" alt="Dashboard Pelanggan"/>

<sub>Halaman utama untuk pelanggan.</sub>

</div>

</details>

<details>
<summary><b>03 — DASHBOARD DRIVER</b></summary>

<br/>

<div align="center">

<img src="docs/dashboard-driver.png" width="85%" alt="Dashboard Driver"/>

<sub>Halaman utama untuk driver.</sub>

</div>

</details>

<details>
<summary><b>04 — DASHBOARD ADMIN</b></summary>

<br/>

<div align="center">

<img src="docs/dashboard-admin.png" width="85%" alt="Dashboard Admin"/>

<sub>Halaman utama untuk admin.</sub>

</div>

</details>

<details>
<summary><b>05 — HALAMAN LAINNYA</b></summary>

<br/>

[Tambahkan screenshot halaman lainnya jika diperlukan.]

</details>

---

## 👥 9. KONTRIBUTOR

RuteConnect dikembangkan oleh Kelompok 27 dari Program Studi Sistem Informasi, Universitas Mulawarman.

<div align="center">

| NO. | NAMA ANGGOTA | NIM |
|:---:|:---|:---:|
| 01 | [Nama Anggota 1] | [NIM] |
| 02 | [Nama Anggota 2] | [NIM] |
| 03 | [Nama Anggota 3] | [NIM] |
| 04 | [Nama Anggota 4] | [NIM] |

</div>

---

<!-- ====================================================== -->
<!--                         FOOTER                         -->
<!-- ====================================================== -->

<div align="center">

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:0B1F3A,100:24527A&height=130&section=footer&text=RUTECONNECT&fontSize=30&fontColor=FFFFFF&fontAlignY=45&desc=Connecting%20Your%20Journey,%20Simplifying%20Your%20Delivery&descSize=12&descAlignY=70" width="100%"/>

**Developed with dedication by Kelompok 27**

<sub>Program Studi Sistem Informasi · Universitas Mulawarman</sub>

</div>
