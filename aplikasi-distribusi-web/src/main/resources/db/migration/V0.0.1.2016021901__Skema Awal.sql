-- Tabel Kabupaten --
create table kabupaten (
id varchar(255) primary key unique,
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL
)Engine=InnoDB;

-- Tabel Kecamatan --
create table kecamatan (
kode varchar(255) not null unique,
nama varchar(255) not null
) Engine=InnoDB ;

-- Tabel Kelurahan --
create table kelurahan (
id varchar(255) primary key unique,
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL
) Engine=InnoDB ;

-- Tabel Kodepos --
create table kodepos (
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL
) Engine=InnoDB ;

-- Tabel User --
create table user (
username varchar(255)not null unique,
email varchar(255) not null unique,
fullname varchar(255) not null
)Engine=InnoDB;

-- Tabel Role --
create table role (
id varchar(255) not null,
kode varchar(255) not null unique,
nama varchar(255) not null
)Engine=InnoDB;

-- Tabel Permission --
create table permission (
kode varchar(255) NOT NULL unique,
nama varchar(255) NOT NULL
)Engine=InnoDB;

-- Tabel Produk --

-- Tabel Distributor --

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