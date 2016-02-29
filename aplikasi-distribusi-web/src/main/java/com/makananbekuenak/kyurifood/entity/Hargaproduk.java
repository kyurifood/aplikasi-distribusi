package com.makananbekuenak.kyurifood.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "hargaproduk")
public class Hargaproduk {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String produk;
    
    private String regional;
    @NotNull
    @Min(0)
    @Column(nullable = false)
    private BigDecimal harga;
    
    @NotNull @NotEmpty
    @Column(nullable = false)
    private String berlakumulai;
    
    private String berlakusampai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBerlakumulai() {
        return berlakumulai;
    }

    public void setBerlakumulai(String berlakumulai) {
        this.berlakumulai = berlakumulai;
    }

    public String getBerlakusampai() {
        return berlakusampai;
    }

    public void setBerlakusampai(String berlakusampai) {
        this.berlakusampai = berlakusampai;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }
}
