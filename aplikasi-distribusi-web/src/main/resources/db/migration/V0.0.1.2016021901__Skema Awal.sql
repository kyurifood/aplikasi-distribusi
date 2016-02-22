-- Tabel Kabupaten --
create table kabupaten (
    id varchar(255) primary key,
    kode varchar(255) NOT NULL,
    nama varchar(255) NOT NULL
) Engine=InnoDB;

-- Tabel Kecamatan --

-- Tabel Kelurahan --

-- Tabel Kodepos --

-- Tabel User --
create table user (
username varchar(255) not null unique,
email varchar(255) not null unique,
fullname varchar(255) not null
) Engine=InnoDB;
-- Tabel Role --

-- Tabel Permission --

-- Tabel Produk --

-- Tabel Distributor --

-- Tabel Marketer --

-- Tabel Customer --