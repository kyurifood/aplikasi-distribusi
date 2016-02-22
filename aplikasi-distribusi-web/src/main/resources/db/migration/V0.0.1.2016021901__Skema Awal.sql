-- Tabel Kabupaten --
create table kabupaten (
id varchar(255) primary key unique,
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL
)Engine=InnoDB;

-- Tabel Kecamatan --

-- Tabel Kelurahan --

-- Tabel Kodepos --

-- Tabel User --
create table user (
username varchar(255)not null unique,
email varchar(255) not null unique,
fullname varchar(255) not null
)Engine=InnoDB;
-- Tabel Role --

-- Tabel Permission --

-- Tabel Produk --

-- Tabel Distributor --

-- Tabel Marketer --
create table marketer (
kode varchar(255) not null unique,
nama varchar(255) not null,
hp varchar(255) not null,
pinbb varchar(255),
email varchar(255) not null,
rekening varchar(255) not null
alamat varchar(255) not null
)Engine=InnoDB;
-- Tabel Customer --