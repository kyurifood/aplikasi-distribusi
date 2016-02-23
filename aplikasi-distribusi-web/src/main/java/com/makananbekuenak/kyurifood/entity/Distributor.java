
package com.makananbekuenak.kyurifood.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Distributor {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String kodeDis;
    private String namaDis;
    private String hpDis;
    private String pinBBDis;
    private String emailDis;
    private String rekeningDis;
    private String alamatDis;

    public String getAlamatDis() {
        return alamatDis;
    }

    public void setAlamatDis(String alamatDis) {
        this.alamatDis = alamatDis;
    }

    public String getEmailDis() {
        return emailDis;
    }

    public void setEmailDis(String emailDis) {
        this.emailDis = emailDis;
    }

    public String getHpDis() {
        return hpDis;
    }

    public void setHpDis(String hpDis) {
        this.hpDis = hpDis;
    }

    public String getKodeDis() {
        return kodeDis;
    }

    public void setKodeDis(String kodeDis) {
        this.kodeDis = kodeDis;
    }

    public String getNamaDis() {
        return namaDis;
    }

    public void setNamaDis(String namaDis) {
        this.namaDis = namaDis;
    }

    public String getPinBBDis() {
        return pinBBDis;
    }

    public void setPinBBDis(String pinBBDis) {
        this.pinBBDis = pinBBDis;
    }

    public String getRekeningDis() {
        return rekeningDis;
    }

    public void setRekeningDis(String rekeningDis) {
        this.rekeningDis = rekeningDis;
    }

   
    
    
    
}
