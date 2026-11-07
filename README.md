<!-- ===================================================== -->
<!--                    RUTECONNECT                        -->
<!-- ===================================================== -->

<div align="center">

  <!-- HEADER BANNER -->
  <img src="https://raw.githubusercontent.com/Sitinursinta04/PRAKTIKUM_PBO/main/Dokumentasi/second/RuteConnect_%20Journey%20and%20Delivery%20Network.png"
       width="100%"
       alt="RuteConnect Banner"/>

  <br/>

  <h1>RUTECONNECT</h1>

  <strong>INTEGRATED TRANSPORTATION & DOCUMENT DELIVERY SYSTEM</strong>

  <p><em>Connecting People, Delivering Possibilities.</em></p>

  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Java%20Swing-GUI-123B68?style=for-the-badge" />
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-Build%20Tool-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />

  <br/><br/>

  **Universitas Mulawarman | Program Studi Sistem Informasi | 2026**

</div>

---

## 📑 DAFTAR ISI

| No. | Bagian | Deskripsi |
|:---:|---|---|
| 01 | [Nama Program](#-1-nama-program) | Identitas aplikasi |
| 02 | [Deskripsi Singkat](#-2-deskripsi-singkat) | Gambaran umum sistem |
| 03 | [Fitur Utama Program](#-3-fitur-utama-program) | Fitur berdasarkan pengguna |
| 04 | [Tech Stack](#️-4-tech-stack) | Teknologi dan tools pendukung |
| 05 | [Perancangan Sistem](#️-5-perancangan-sistem) | Flowchart, Use Case, dan ERD |
| 06 | [Struktur Folder/Package](#-6-struktur-folderpackage) | Organisasi kode program |
| 07 | [Penerapan 4 Pilar OOP](#-7-penerapan-4-pilar-oop) | Konsep OOP dalam aplikasi |
| 08 | [Cara Penggunaan Program](#️-8-cara-penggunaan-program) | Panduan dan dokumentasi GUI |
| 09 | [Kontributor](#-9-kontributor) | Tim pengembang |
| 10 | [Dokumentasi Konsultasi](#-10-dokumentasi-konsultasi) | Dokumentasi proses konsultasi |

---

## 🚀 1. Nama Program

### RuteConnect

**RuteConnect: Sistem Informasi Terintegrasi Pemesanan Transportasi Daring dan Pengiriman Dokumen Berbasis Digital**

RuteConnect merupakan aplikasi desktop yang mengintegrasikan layanan pemesanan transportasi daring dan pengiriman dokumen dalam satu platform digital.

Nama RuteConnect berasal dari gabungan kata *Rute* dan *Connect*. Nama ini menggambarkan tujuan aplikasi untuk menghubungkan pengguna dengan layanan transportasi dan pengiriman melalui sistem yang praktis dan terintegrasi.

<div align="center">

  <img src="https://img.shields.io/badge/ROUTE-Connected%20Services-0B1F3A?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/DELIVERY-Smart%20Solution-164B78?style=for-the-badge"/>

</div>

---

## 📖 2. Deskripsi Singkat

RuteConnect adalah aplikasi sistem informasi berbasis desktop yang dikembangkan untuk memberikan kemudahan dalam melakukan pemesanan layanan transportasi daring dan pengiriman dokumen secara digital.

Aplikasi ini mengintegrasikan dua kebutuhan layanan dalam satu platform, sehingga pengguna dapat melakukan perjalanan maupun mengirimkan dokumen melalui sistem yang sama.

RuteConnect memiliki tiga jenis hak akses pengguna, yaitu **Pelanggan, Driver, dan Admin**. Setiap pengguna memiliki akses dan tanggung jawab yang berbeda sesuai dengan perannya di dalam sistem.

Sistem dikembangkan menggunakan bahasa pemrograman Java dengan antarmuka Java Swing serta database MySQL untuk menyimpan dan mengelola data aplikasi.

### 🎯 Tujuan Pengembangan

- Mengintegrasikan layanan transportasi dan pengiriman dokumen dalam satu aplikasi.
- Mempermudah proses pemesanan dan pengelolaan layanan secara digital.
- Menyediakan informasi status pesanan secara terstruktur.
- Membantu pengelolaan data pelanggan, driver, layanan, dan transaksi.
- Menerapkan konsep Object-Oriented Programming dalam pengembangan aplikasi.

### 🗺️ Gambaran Konsep RuteConnect

<div align="center">

  <img src="https://raw.githubusercontent.com/Sitinursinta04/PRAKTIKUM_PBO/main/Dokumentasi/second/RuteConnect_%20Digital%20Transport%20and%20Delivery.png"
       width="90%"
       alt="Ilustrasi Konsep RuteConnect"/>

  <br/>

  <em>Ilustrasi konsep perjalanan dan pengiriman pada RuteConnect.</em>

</div>

---

## ⚙️ 3. Fitur Utama Program

RuteConnect menyediakan fitur yang disesuaikan dengan tiga jenis pengguna. Setiap fitur dirancang untuk mendukung proses layanan dan pengelolaan data dalam aplikasi.

### A. Pelanggan

| Fitur | Deskripsi |
|---|---|
| Registrasi | Membuat akun untuk menggunakan layanan RuteConnect. |
| Login | Masuk ke sistem menggunakan akun terdaftar. |
| Pemesanan Transportasi | Membuat pesanan layanan transportasi. |
| Pengiriman Dokumen | Melakukan pemesanan layanan pengiriman dokumen. |
| Pembatalan Pesanan | Membatalkan pesanan sesuai ketentuan sistem. |
| Status Pesanan | Melihat perkembangan status pesanan. |
| Riwayat Pesanan | Melihat daftar pemesanan sebelumnya. |
| Dompet Digital | Melihat dan melakukan pengisian saldo. |
| Riwayat Transaksi | Melihat catatan transaksi dan top up. |

### B. Driver

| Fitur | Deskripsi |
|---|---|
| Login | Masuk ke sistem sebagai driver. |
| Pesanan Tersedia | Melihat daftar pesanan yang tersedia. |
| Detail Pesanan | Melihat informasi pelanggan dan tujuan pesanan. |
| Menerima Pesanan | Menerima pesanan yang tersedia. |
| Menolak Pesanan | Menolak pesanan sesuai mekanisme sistem. |
| Pembaruan Status | Memperbarui status pengerjaan pesanan. |
| Riwayat Pesanan | Melihat pesanan yang pernah ditangani. |

### C. Admin

| Fitur | Deskripsi |
|---|---|
| Login Admin | Masuk ke halaman pengelolaan sistem. |
| Manajemen Pengguna | Mengelola data pelanggan dan driver. |
| Manajemen Layanan | Mengelola informasi dan tarif layanan. |
| Data Pesanan | Melihat dan memantau seluruh pesanan. |
| Data Transaksi | Melihat informasi transaksi pengguna. |
| Laporan | Mengakses informasi laporan sistem. |

---

## 🛠️ 4. Tech Stack

RuteConnect dikembangkan dengan memanfaatkan berbagai teknologi dan tools pendukung untuk membangun aplikasi desktop, mengelola database, serta membantu proses pengembangan sistem.

### A. Core Technologies

Teknologi utama yang digunakan dalam pengembangan aplikasi RuteConnect.

<div align="center">

  <img src="https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Java_Swing-123B68?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/JDBC-0B1F3A?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>

</div>

| Teknologi | Kegunaan |
|---|---|
| Java 21 | Bahasa pemrograman utama aplikasi. |
| Java Swing | Membangun antarmuka aplikasi desktop. |
| MySQL | Menyimpan dan mengelola data aplikasi. |
| JDBC | Menghubungkan aplikasi Java dengan database. |
| Maven | Mengelola dependency dan proses build proyek. |
| NetBeans IDE | Lingkungan pengembangan aplikasi. |

### B. Development Environment & Supporting Tools

Berbagai teknologi dan tools digunakan untuk mendukung proses pengembangan aplikasi, pengelolaan database, pengujian program, serta penyusunan dokumentasi proyek RuteConnect.

<div align="center">

<table>
  <tr>
    <th>CORE TECHNOLOGIES</th>
    <th>DEVELOPMENT ENVIRONMENT</th>
    <th>SUPPORTING TOOLS</th>
  </tr>
  <tr>
    <td align="center" valign="middle" width="33%">
      <img src="https://skillicons.dev/icons?i=java,mysql,maven,html&theme=light" height="55" alt="Java, MySQL, Maven, HTML"/>
      <br/><br/>
      <strong>Java 21 • MySQL</strong>
      <br/>
      <strong>Maven • HTML</strong>
    </td>
    <td align="center" valign="middle" width="33%">
      <img src="https://cdn.simpleicons.org/laragon/0E8A70" height="42" alt="Laragon"/>
      &nbsp;
      <img src="https://cdn.simpleicons.org/apachenetbeanside/1B6AC6" height="42" alt="Apache NetBeans"/>
      <br/><br/>
      <strong>Laragon • Apache NetBeans</strong>
    </td>
    <td align="center" valign="middle" width="33%">
      <img src="https://cdn.simpleicons.org/github/181717" height="42" alt="GitHub"/>
      &nbsp;
      <img src="https://cdn.simpleicons.org/googlechrome/4285F4" height="42" alt="Google Chrome"/>
      &nbsp;
      <img src="https://cdn.simpleicons.org/microsoftword/2B579A" height="42" alt="Microsoft Word"/>
      <br/><br/>
      <strong>GitHub • Google Chrome</strong>
      <br/>
      <strong>Microsoft Word</strong>
    </td>
  </tr>
</table>

</div>

### C. README Design Resources

Beberapa layanan berbasis web dimanfaatkan untuk membantu membuat tampilan README GitHub lebih menarik, informatif, dan terstruktur.

<div align="center">

<table>
  <tr>
    <th>CAPSULE RENDER</th>
    <th>SKILL ICONS</th>
    <th>SHIELDS.IO</th>
    <th>SIMPLE ICONS</th>
  </tr>
  <tr>
    <td align="center" valign="middle">
      <img src="https://cdn.simpleicons.org/vercel/000000" height="42" alt="Capsule Render"/>
      <br/><br/>
      <strong>Banner README</strong>
    </td>
    <td align="center" valign="middle">
      <img src="https://skillicons.dev/icons?i=github&theme=light" height="42" alt="Skill Icons"/>
      <br/><br/>
      <strong>Technology Icons</strong>
    </td>
    <td align="center" valign="middle">
      <img src="https://cdn.simpleicons.org/shieldsdotio/000000" height="42" alt="Shields.io"/>
      <br/><br/>
      <strong>Badges</strong>
    </td>
    <td align="center" valign="middle">
      <img src="https://cdn.simpleicons.org/simpleicons/111111" height="42" alt="Simple Icons"/>
      <br/><br/>
      <strong>Brand Icons</strong>
    </td>
  </tr>
</table>

</div>

<sub>
Design Resources:
<a href="https://github.com/kyechan99/capsule-render">Capsule Render</a> •
<a href="https://skillicons.dev/">Skill Icons</a> •
<a href="https://shields.io/">Shields.io</a> •
<a href="https://simpleicons.org/">Simple Icons</a>
</sub>

### C. README Design Resources

Beberapa layanan berbasis web dimanfaatkan untuk membantu membuat tampilan README GitHub lebih menarik, informatif, dan terstruktur.

<div align="center">

<table>
  <tr>
    <th>CAPSULE RENDER</th>
    <th>SKILL ICONS</th>
    <th>SHIELDS.IO</th>
    <th>SIMPLE ICONS</th>
  </tr>
  <tr>
    <td align="center" valign="middle">
      <img src="https://cdn.simpleicons.org/vercel/000000" height="42" alt="Vercel"/>
      <br/><br/>
      <strong>Banner README</strong>
    </td>
    <td align="center" valign="middle">
      <img src="https://skillicons.dev/icons?i=github&theme=light" height="42" alt="Skill Icons"/>
      <br/><br/>
      <strong>Technology Icons</strong>
    </td>
    <td align="center" valign="middle">
      <img src="https://cdn.simpleicons.org/shieldsdotio/000000" height="42" alt="Shields.io"/>
      <br/><br/>
      <strong>Badges</strong>
    </td>
    <td align="center" valign="middle">
      <img src="https://cdn.simpleicons.org/simpleicons/111111" height="42" alt="Simple Icons"/>
      <br/><br/>
      <strong>Brand Icons</strong>
    </td>
  </tr>
</table>

</div>

<sub>Design resources: <a href="https://github.com/kyechan99/capsule-render">Capsule Render</a> • <a href="https://skillicons.dev/">Skill Icons</a> • <a href="https://shields.io/">Shields.io</a> • <a href="https://simpleicons.org/">Simple Icons</a></sub>

### D. Service Platform References

<div align="center">

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://cdn.simpleicons.org/gojek/00AA13" height="65" alt="Gojek"/>
      <br/><br/>
      <strong>GOJEK</strong>
    </td>
    <td align="center" width="50%">
      <img src="https://cdn.simpleicons.org/grab/00B14F" height="65" alt="Grab"/>
      <br/><br/>
      <strong>GRAB</strong>
    </td>
  </tr>
</table>

<sub>Transportation & Delivery Platform References</sub>

</div>

> **Catatan:** Gojek dan Grab merupakan referensi konsep layanan, bukan integrasi API. Claude, ChatGPT, dan Gemini AI merupakan tools pendukung pengembangan, bukan komponen yang dijalankan di dalam aplikasi.

### E. Referensi Pengembangan README

Beberapa sumber yang digunakan sebagai referensi untuk membantu penyusunan tampilan dan dokumentasi README:

- [Readme.so](https://readme.so/) – Membantu menyusun struktur README.
- [Shields.io](https://shields.io/) – Membuat badge teknologi dan informasi proyek.
- [Simple Icons](https://simpleicons.org/) – Menyediakan ikon berbagai brand dan teknologi.
- [Capsule Render](https://capsule-render.vercel.app/) – Membuat banner GitHub README.
- [Awesome README](https://github.com/matiassingers/awesome-readme) – Referensi README proyek yang menarik dan informatif.

---

## 🏗️ 5. Perancangan Sistem

Perancangan sistem dilakukan sebagai dasar dalam proses pengembangan RuteConnect. Tahapan ini bertujuan untuk menggambarkan alur kerja, interaksi pengguna, serta hubungan antar-data yang digunakan dalam aplikasi.

### A. Flowchart

Flowchart menggambarkan alur proses aplikasi RuteConnect, mulai dari pengguna membuka aplikasi, melakukan login atau registrasi, memilih layanan, hingga proses pemesanan, pembayaran, dan pengelolaan data oleh admin.

<div align="center">

<table>
  <tr>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Login.drawio.png?raw=true" width="100%" alt="Flowchart Login"/>
      <br/>
      <sub>Flowchart Login</sub>
    </td>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Registrasi.drawio.png?raw=true" width="100%" alt="Flowchart Registrasi"/>
      <br/>
      <sub>Flowchart Registrasi</sub>
    </td>
  </tr>
  <tr>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Menu%20Customer.drawio.png?raw=true" width="100%" alt="Flowchart Menu Customer"/>
      <br/>
      <sub>Flowchart Menu Customer</sub>
    </td>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Transportasi.drawio.png?raw=true" width="100%" alt="Flowchart Customer Transportasi"/>
      <br/>
      <sub>Flowchart Customer – Transportasi</sub>
    </td>
  </tr>
  <tr>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Pengiriman%20Dokumen.drawio.png?raw=true" width="100%" alt="Flowchart Pengiriman Dokumen"/>
      <br/>
      <sub>Flowchart Customer – Pengiriman Dokumen</sub>
    </td>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Pembayaran.drawio.png?raw=true" width="100%" alt="Flowchart Pembayaran"/>
      <br/>
      <sub>Flowchart Customer – Pembayaran</sub>
    </td>
  </tr>
  <tr>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Top%20Up.drawio.png?raw=true" width="100%" alt="Flowchart Top Up"/>
      <br/>
      <sub>Flowchart Customer – Top Up</sub>
    </td>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Customer%20-%20Status%20%26%20Riwayat.drawio.png?raw=true" width="100%" alt="Flowchart Status dan Riwayat"/>
      <br/>
      <sub>Flowchart Customer – Status & Riwayat</sub>
    </td>
  </tr>
  <tr>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Driver.drawio.png?raw=true" width="100%" alt="Flowchart Driver"/>
      <br/>
      <sub>Flowchart Driver</sub>
    </td>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi8-Admin%20-%20Menu%20Admin.drawio.png?raw=true" width="100%" alt="Flowchart Menu Admin"/>
      <br/>
      <sub>Flowchart Admin – Menu Admin</sub>
    </td>
  </tr>
  <tr>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi9-Admin%20-%20Kelola%20Data.drawio.png?raw=true" width="100%" alt="Flowchart Kelola Data"/>
      <br/>
      <sub>Flowchart Admin – Kelola Data</sub>
    </td>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi9-Admin%20-%20Data%20Pesanan.drawio.png?raw=true" width="100%" alt="Flowchart Data Pesanan"/>
      <br/>
      <sub>Flowchart Admin – Data Pesanan</sub>
    </td>
  </tr>
  <tr>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi9-Admin%20-%20Data%20Transaksi.drawio.png?raw=true" width="100%" alt="Flowchart Data Transaksi"/>
      <br/>
      <sub>Flowchart Admin – Data Transaksi</sub>
    </td>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Flowchart_RuteConnect_Revisi9-Admin%20-%20Laporan.drawio.png?raw=true" width="100%" alt="Flowchart Laporan"/>
      <br/>
      <sub>Flowchart Admin – Laporan</sub>
    </td>
  </tr>
</table>

</div>

### B. Use Case Diagram

Use Case Diagram menggambarkan hubungan antara aktor dengan fitur yang tersedia dalam sistem.

Aktor utama dalam aplikasi RuteConnect terdiri atas:

- **Pelanggan:** melakukan pemesanan transportasi dan pengiriman dokumen.
- **Driver:** menerima serta mengelola pesanan.
- **Admin:** mengelola data pengguna, layanan, pesanan, dan transaksi.

<div align="center">

  <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/diagram%20PA(1)-Page-5.drawio(1).png?raw=true"
       width="85%"
       alt="Use Case Diagram RuteConnect"/>

  <br/>

  <strong>Use Case Diagram RuteConnect</strong>

</div>

### C. Entity Relationship Diagram (ERD)

Entity Relationship Diagram (ERD) digunakan untuk menggambarkan struktur database serta hubungan antar-entitas yang mendukung proses pengelolaan data aplikasi.

Entitas utama dalam perancangan database RuteConnect meliputi:

1. User
2. Pelanggan
3. Driver
4. Admin
5. Layanan
6. Pesanan
7. Transportasi
8. Dokumen
9. Transaksi
10. Top_Up

<div align="center">

<table>
  <tr>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-02%20at%2019.38.02.jpeg?raw=true" width="100%" alt="ERD Logical"/>
      <br/>
      <strong>ERD Logical</strong>
      <br/>
      <sub>Gambaran entitas, atribut, dan hubungan antar-entitas dalam database.</sub>
    </td>
    <td width="50%" align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-02%20at%2019.38.02.%20(1).jpeg?raw=true" width="100%" alt="ERD Relational"/>
      <br/>
      <strong>ERD Relational</strong>
      <br/>
      <sub>Representasi struktur tabel database beserta primary key dan foreign key.</sub>
    </td>
  </tr>
</table>

</div>

---

## 📂 6. Struktur Folder/Package

Struktur proyek RuteConnect disusun menggunakan beberapa package yang memiliki tanggung jawab berbeda. Pemisahan ini membantu menjaga keteraturan kode serta memudahkan proses pengembangan dan pemeliharaan aplikasi.

### A. Struktur Proyek

Struktur proyek RuteConnect disusun ke dalam beberapa package berdasarkan fungsi dan tanggung jawab masing-masing untuk menjaga keteraturan kode serta memudahkan proses pengembangan aplikasi.

<div align="center">

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Screenshot%202026-11-07%20204304.png?raw=true" width="100%" alt="Struktur Proyek RuteConnect 1"/>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Screenshot%202026-11-07%20204359.png?raw=true" width="100%" alt="Struktur Proyek RuteConnect 2"/>
    </td>
  </tr>
</table>

<sub>Struktur package dan file proyek RuteConnect pada Apache NetBeans.</sub>

</div>

### B. Penjelasan Package

Setiap package memiliki fungsi tersendiri untuk memisahkan komponen aplikasi berdasarkan tanggung jawabnya.

| Package | Fungsi |
|---|---|
| `config` | Mengatur konfigurasi serta koneksi aplikasi dengan database MySQL. |
| `controller` | Mengelola proses pengendalian aplikasi, seperti autentikasi, sesi pengguna, pengaturan tarif, dan pengelolaan data pengguna. |
| `dao` | Menangani interaksi antara aplikasi dan database melalui operasi CRUD (*Create, Read, Update, Delete*). |
| `main` | Menyimpan class utama sebagai titik awal untuk menjalankan aplikasi serta aset pendukung seperti gambar dan halaman HTML. |
| `model` | Mendefinisikan class yang merepresentasikan objek dan struktur data dalam sistem, seperti User, Pelanggan, Driver, Pesanan, dan Transaksi. |
| `service` | Menangani logika bisnis aplikasi, seperti pengelolaan pesanan, saldo, transaksi, dan pembuatan laporan. |
| `util` | Menyediakan berbagai fungsi bantuan dan utilitas yang digunakan oleh komponen aplikasi. |
| `view` | Mengelola tampilan antarmuka aplikasi menggunakan Java Swing, termasuk form, dashboard, dan halaman layanan. |

---

## 🧩 7. Penerapan 4 Pilar OOP

RuteConnect dikembangkan menggunakan konsep *Object-Oriented Programming* (OOP) yang menerapkan empat pilar utama, yaitu Encapsulation, Inheritance, Polymorphism, dan Abstraction. Penerapan konsep tersebut dapat ditemukan pada berbagai class dalam package `model` dan `dao`.

### 1. Encapsulation (Enkapsulasi)

Encapsulation merupakan konsep membungkus data dan method dalam sebuah class serta membatasi akses langsung terhadap atribut tertentu.

Pada RuteConnect, konsep ini diterapkan melalui penggunaan access modifier `private` pada atribut class `User`. Akses dan perubahan data dilakukan melalui method getter dan setter yang disediakan.

**Contoh penerapan pada class User:**

```java
public abstract class User {
    private int userId;
    private String nama;
    private String noTelepon;
    private String email;
    private String password;
    private String role;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || !nama.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nama hanya boleh huruf dan spasi");
        }
        nama = nama.trim();
        if (nama.length() < 3) {
            throw new IllegalArgumentException("Nama minimal 3 karakter");
        }
        if (nama.length() > 100) {
            throw new IllegalArgumentException("Nama maksimal 100 karakter");
        }
        this.nama = nama;
    }
}
```

**Penjelasan:**

Atribut pada class `User` menggunakan access modifier `private` sehingga tidak dapat diakses secara langsung dari luar class. Pengambilan data dilakukan melalui getter, sedangkan perubahan data dilakukan melalui setter. Setter `setNama()` juga menerapkan validasi untuk memastikan nama hanya berisi huruf dan spasi serta memenuhi batas panjang yang ditentukan.

### 2. Inheritance (Pewarisan)

Inheritance merupakan konsep pewarisan atribut dan method dari class induk (*parent class*) kepada class turunannya (*child class*).

Pada RuteConnect, konsep ini diterapkan melalui class `User` yang diwariskan kepada `Pelanggan`, `Driver`, dan `Admin`. Selain itu, class `Pesanan` diwariskan kepada `Transportasi` dan `Dokumen`.

**Contoh penerapan pada class Pelanggan:**

```java
public class Pelanggan extends User {

    private String alamatFavorit;
    private double saldoWallet;

    @Override
    public String getJenisUser() {
        return "Pelanggan";
    }
}
```

**Penjelasan:**

Class `Pelanggan` menggunakan keyword `extends` untuk mewarisi atribut dan method dari class `User`. Dengan demikian, informasi umum pengguna dapat digunakan kembali tanpa harus didefinisikan ulang. Class `Pelanggan` juga memiliki atribut tambahan, yaitu `alamatFavorit` dan `saldoWallet`, sesuai dengan kebutuhan pengguna layanan.

### 3. Polymorphism (Polimorfisme)

Polymorphism merupakan konsep yang memungkinkan method dengan nama yang sama memiliki implementasi atau perilaku berbeda berdasarkan class yang menggunakannya.

Pada RuteConnect, konsep ini diterapkan melalui *method overriding* pada method `getJenisUser()` yang didefinisikan dalam class `User` dan diimplementasikan kembali oleh class `Pelanggan`, `Driver`, serta `Admin`.

**Contoh penerapan:**

```java
// Class Pelanggan
@Override
public String getJenisUser() {
    return "Pelanggan";
}
```

```java
// Class Driver
@Override
public String getJenisUser() {
    return "Driver";
}
```

```java
// Class Admin
@Override
public String getJenisUser() {
    return "Admin";
}
```

**Penjelasan:**

Ketiga class turunan memiliki method `getJenisUser()` dengan nama dan tipe pengembalian yang sama, tetapi menghasilkan nilai berbeda sesuai dengan jenis pengguna. Hal ini menunjukkan penerapan polymorphism melalui mekanisme *method overriding*.

### 4. Abstraction (Abstraksi)

Abstraction merupakan konsep menyederhanakan kompleksitas sistem dengan menampilkan fungsi utama yang perlu dimiliki suatu class tanpa mengharuskan seluruh detail implementasinya ditampilkan.

Pada RuteConnect, konsep ini diterapkan melalui penggunaan abstract class `User` dan `Pesanan`. Kedua class tersebut memiliki abstract method yang wajib diimplementasikan oleh class turunannya.

**Contoh penerapan pada class User:**

```java
public abstract class User {

    public abstract String getJenisUser();

}
```

Class `User` kemudian diwariskan oleh `Pelanggan`, `Driver`, dan `Admin` yang masing-masing memberikan implementasi terhadap method `getJenisUser()`.

**Contoh penerapan pada class Pesanan:**

```java
public abstract class Pesanan {

    public abstract String getJenisPesanan();

}
```

Class `Pesanan` memiliki dua class turunan, yaitu `Transportasi` dan `Dokumen`.

```java
// Class Transportasi
@Override
public String getJenisPesanan() {
    return JENIS_TRANSPORTASI;
}
```

```java
// Class Dokumen
@Override
public String getJenisPesanan() {
    return JENIS_DOKUMEN;
}
```

**Penjelasan:**

Class `User` dan `Pesanan` merupakan abstract class yang tidak digunakan untuk membuat objek secara langsung. Keduanya menyediakan struktur dasar dan method abstrak yang harus diimplementasikan oleh class turunannya. Dengan demikian, setiap jenis pengguna dan pesanan dapat memiliki implementasi sesuai dengan kebutuhan masing-masing.

### Penerapan Interface pada DAO

Selain menerapkan empat pilar OOP, RuteConnect juga menggunakan *interface* untuk mendefinisikan standar operasi pengelolaan data pada lapisan DAO (*Data Access Object*).

Salah satu penerapannya terdapat pada interface `CrudDao<T>` yang mendefinisikan operasi dasar CRUD (*Create, Read, Update, Delete*).

**Contoh penerapan pada interface CrudDao:**

```java
public interface CrudDao<T> {

    boolean insert(T obj);

    boolean update(T obj);

    boolean delete(int id);

    T findById(int id);

    List<T> findAll();

}
```

Interface tersebut kemudian diimplementasikan oleh abstract class `BaseDao<T>`.

```java
public abstract class BaseDao<T> implements CrudDao<T> {

    protected Connection conn() {
        return Koneksi.getConnection();
    }

    protected abstract T mapRow(ResultSet rs) throws SQLException;

}
```

**Penjelasan:**

Interface `CrudDao<T>` berfungsi sebagai kontrak yang menetapkan operasi pengelolaan data yang harus tersedia. Sementara itu, abstract class `BaseDao<T>` mengimplementasikan interface tersebut dan menyediakan fungsi dasar untuk berinteraksi dengan database. Class DAO lainnya kemudian dapat mengembangkan fungsi tersebut sesuai dengan kebutuhan masing-masing entitas.

---

## 🖥️ 8. Cara Penggunaan Program

Bagian ini menjelaskan tahapan penggunaan aplikasi RuteConnect berdasarkan tampilan antarmuka dan fitur yang tersedia.

### A. Tampilan Awal Aplikasi

Halaman awal merupakan tampilan yang muncul ketika aplikasi RuteConnect pertama kali dijalankan. Halaman ini menjadi tampilan pembuka sebelum pengguna masuk dan mengakses fitur yang tersedia.

<table border="1" bordercolor="black" align="center">
  <tr>
    <td align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/Screenshot%202026-11-07%20202959.png" width="100%" alt="Tampilan Awal RuteConnect" style="display: block; margin: auto;"/>
    </td>
  </tr>
</table>

### B. Login Pengguna

Pengguna memasukkan email dan password yang telah terdaftar untuk masuk ke dalam aplikasi dan mengakses fitur sesuai dengan hak akses masing-masing.

<table border="1" bordercolor="black" align="center">
  <tr>
    <td align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2020.57.23.jpeg" width="100%" alt="Halaman Login Pengguna" style="display: block; margin: auto;"/>
    </td>
  </tr>
</table>

### C. Dashboard Pelanggan

Dashboard Pelanggan merupakan halaman utama yang digunakan pelanggan untuk mengakses layanan transportasi dan pengiriman dokumen, melihat informasi akun, serta memantau aktivitas pemesanan.

<table border="1" bordercolor="black" align="center">
  <tr>
    <td align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2020.57.23.1.jpeg" width="100%" alt="Dashboard Pelanggan" style="display: block; margin: auto;"/>
    </td>
  </tr>
</table>

### D. Halaman Pemesanan

Halaman pemesanan digunakan pelanggan untuk memilih jenis layanan yang dibutuhkan, yaitu transportasi atau pengiriman dokumen. Pelanggan kemudian mengisi informasi yang diperlukan sesuai dengan layanan yang dipilih untuk membuat pesanan.

<table border="1" bordercolor="black" align="center">
  <tr>
    <td align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2020.57.23.2.jpeg" width="100%" alt="Halaman Pemesanan Transportasi" style="display: block; margin: auto;"/>
    </td>
  </tr>
</table>

<table border="1" bordercolor="black" align="center">
  <tr>
    <td align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2020.57.23.3.jpeg" width="100%" alt="Halaman Pengiriman Dokumen" style="display: block; margin: auto;"/>
    </td>
  </tr>
</table>

### E. Dashboard Driver

Dashboard Driver merupakan halaman yang digunakan oleh mitra pengemudi untuk melihat pesanan yang tersedia, memeriksa detail pesanan, menerima atau menolak pesanan, serta memperbarui status pengerjaan.

<table border="1" bordercolor="black" align="center">
  <tr>
    <td align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2020.57.23.5.jpeg" width="100%" alt="Dashboard Driver" style="display: block; margin: auto;"/>
    </td>
  </tr>
</table>

### F. Dashboard Admin

Dashboard Admin merupakan halaman pengelolaan sistem yang memberikan akses kepada admin untuk mengelola data pengguna, layanan, dan pesanan, serta memantau informasi transaksi dalam sistem.

<table border="1" bordercolor="black" align="center">
  <tr>
    <td align="center">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2020.57.24.6.jpeg" width="100%" alt="Dashboard Admin" style="display: block; margin: auto;"/>
    </td>
  </tr>
</table>

---

## 👥 9. Kontributor

Proyek RuteConnect dikembangkan secara berkelompok sebagai bagian dari penyelesaian tugas mata kuliah **Pemrograman Berorientasi Objek (PBO)**.

<div align="center">

| No. | Nama | NIM | Peran |
|:---:|---|:---:|---|
| 1 | Siti Nursinta | 2509116087 | Project Leader |
| 2 | Daffa Rizqi Fadhillah | 2509116068 | Database Design |
| 3 | Nabila Salma Putri | 2509116065 | Back-End Developer |
| 4 | Selvi Bella Dwi Anita | 2509116053 | Minor Contribution |

</div>

---

## 📸 10. Dokumentasi Konsultasi

Dokumentasi ini berisi foto kegiatan konsultasi proyek RuteConnect sebagai bukti pelaksanaan bimbingan, diskusi, dan evaluasi selama proses pengembangan aplikasi.

<div align="center">

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 1"/>
    <br/>
    <sub>Dokumentasi Konsultasi 1</sub>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.1.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 2"/>
      <br/>
      <sub>Dokumentasi Konsultasi 2 SBD</sub>
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.4.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 3"/>
      <br/>
      <sub>Dokumentasi Konsultasi 2 SBD</sub>
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.3.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 4"/>
      <br/>
      <sub>Dokumentasi Konsultasi 2 PBO</sub>
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/Sitinursinta04/PRAKTIKUM_PBO/blob/main/dokum_PA/WhatsApp%20Image%202026-11-07%20at%2016.42.39.2.jpeg?raw=true" width="100%" alt="Dokumentasi Konsultasi 5"/>
      <br/>
      <sub>Dokumentasi Konsultasi 2 PBO</sub>
    </td>
    <td width="50%"></td>
  </tr>
</table>

<br/>

<em>Dokumentasi kegiatan konsultasi proyek RuteConnect.</em>

</div>

---

<!-- ===================================================== -->
<!--                       FOOTER                          -->
<!-- ===================================================== -->

<div align="center">

  <img src="https://img.shields.io/badge/RUTECONNECT-Connecting%20People-0B1F3A?style=for-the-badge"/>

  <h2>RUTECONNECT</h2>

  <strong>Connecting People, Delivering Possibilities.</strong>

  <br/>

  <em>One Platform, Connected Routes, Better Services.</em>

  <br/><br/>

  <sub>Developed with Java | Sistem Informasi | Universitas Mulawarman © 2026</sub>

</div>
