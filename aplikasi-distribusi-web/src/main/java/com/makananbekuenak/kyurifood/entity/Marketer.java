/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author gilang
 */
@Entity
public class Marketer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotNull @NotEmpty
    @Column(nullable = false, unique = true)
    private String kode;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String nama;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String hp;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String pinbb;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String email;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String rekening;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String alamat;

    
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

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

    public String getPinbb() {
        return pinbb;
    }

    public void setPinbb(String pinbb) {
        this.pinbb = pinbb;
    }

    public String getRekening() {
        return rekening;
    }

    public void setRekening(String rekening) {
        this.rekening = rekening;
    }
}
