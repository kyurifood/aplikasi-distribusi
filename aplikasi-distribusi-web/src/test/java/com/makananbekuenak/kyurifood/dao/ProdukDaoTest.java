package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Permission;
import javax.transaction.Transactional;
import com.makananbekuenak.kyurifood.entity.Produk;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author satria
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Transactional
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-produk.sql"})
public class ProdukDaoTest {

    @Autowired
    private ProdukDao produkDao;

    @Test
    public void testSave() {
        Produk p = new Produk();
        p.setId("123");
        p.setKode("Tes 123");
        p.setNama("Cibinong");
        
      //  Assert.assertNull(p.getId());
        produkDao.save(p);
        Assert.assertNotNull(p.getId());
    }
    
    @Test
    public void testCariBykode(){
        Produk p = produkDao.findOne("123");
        
        Assert.assertNotNull(p);
        Assert.assertEquals("123", p.getKode());
        Assert.assertEquals("pabuaran", p.getNama());
        
        Assert.assertNull(produkDao.findOne("abc"));
    }
}
