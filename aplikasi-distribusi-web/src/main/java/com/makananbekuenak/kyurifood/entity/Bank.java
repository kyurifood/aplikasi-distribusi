package com.makananbekuenak.kyurifood.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity @Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "kode", nullable = false, unique = true)
    private String kode;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(name = "kode_bi", nullable = false)
    private String kodeBi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKodeBi() {
        return kodeBi;
    }

    public void setKodeBi(String kodeBi) {
        this.kodeBi = kodeBi;
    }
    
    
}
