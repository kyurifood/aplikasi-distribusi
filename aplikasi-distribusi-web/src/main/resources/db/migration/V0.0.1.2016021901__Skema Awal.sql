-- Tabel Bank --
create table bank (
id varchar(255) primary key unique,
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL,
kode_bi varchar(255) NOT NULL
)Engine=InnoDB;

-- Tabel Kabupaten --
create table kabupaten (
id varchar(255) primary key unique,
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL
)Engine=InnoDB;

-- Tabel Kecamatan --
create table kecamatan (
id varchar(255) primary key,
kode varchar(255) not null unique,
nama varchar(255) not null
) Engine=InnoDB ;

-- Tabel Kelurahan --
create table kelurahan (
id varchar(255) primary key unique,
kode varchar(255) not null unique,
nama varchar(255) not null
)Engine=InnoDB ;

-- Tabel Kodepos --
create table kodepos (
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL
)Engine=InnoDB ;

-- Tabel User --
create table user (
username varchar(255)not null unique,
email varchar(255) not null unique,
fullname varchar(255) not null
)Engine=InnoDB;

-- Tabel Role --
create table role (
id varchar(255) primary key not null,
kode varchar(255) not null unique,
nama varchar(255) not null
)Engine=InnoDB;

-- Tabel Permission --
create table permission (
id varchar(255) primary key unique,
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL
)Engine=InnoDB;

-- Tabel Produk --
create table produk (
id varchar(255) primary key not null,
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL
)Engine=InnoDB;

-- Tabel Distributor --
create table distributor (
kode varchar(255) not null unique,
nama varchar(255) not null,
hp varchar(255) not null,
pinBB varchar(255),
email varchar(255) not null,
rekening varchar(255) not null,
alamat varchar(255) not null
)Engine=InnoDB;

-- Tabel Marketer --
create table marketer (
kode varchar(255) not null unique,
nama varchar(255) not null,
hp varchar(255) not null,
pinbb varchar(255),
email varchar(255) not null,
rekening varchar(255) not null,
alamat varchar(255) not null
)Engine=InnoDB;

-- Tabel Customer --
create table customer (
id varchar(255) primary key unique,
kode varchar(255) not null unique,
nama varchar(255) not null,
hp varchar(255) not null,
alamat varchar(255) not null
)Engine=InnoDB;

-- Tabel Regional --
create table regional (
kode varchar(255) not null,
nama varchar(255) not null
) Engine=InnoDB;

--Tabel HargaProduk--
-- Tabel Harga Produk --
create table hargaproduk (
id varchar(255) primary key,
produk varchar(255) not null,
regional varchar(255) not null,
harga decimal(19,2) not null,
berlakumulai date not null,
berlakusampai date
)Engine=InnoDB;