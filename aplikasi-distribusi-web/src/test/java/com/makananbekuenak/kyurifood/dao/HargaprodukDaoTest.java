package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import javax.transaction.Transactional;
import com.makananbekuenak.kyurifood.entity.Hargaproduk;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Transactional
@Sql(scripts = {"/mysql/delete-data.sql","/mysql/sample-hargaproduk.sql"})
public class HargaprodukDaoTest {
    
    @Autowired private HargaprodukDao hargaprodukDao;
    
    @Test
    public void testSave(){
        Hargaproduk h = new Hargaproduk();
        
        h.setId("abc");
        h.setProduk("test 001");
        h.setRegional("test abc");
        h.setHarga(new BigDecimal("100000.01"));
        h.setBerlakumulai("tes 2015-01-01");
        h.setBerlakusampai("tes 2015-01-02");
        
     //   Assert.assertNull(h.getId());
        hargaprodukDao.save(h);
        Assert.assertNotNull(h.getId());
    }


    @Test
    public void testFindById(){
        Hargaproduk h = hargaprodukDao.findOne("abc123");
        Assert.assertNotNull(h);
        Assert.assertEquals("001", h.getProduk());
        Assert.assertEquals("abc", h.getRegional());
        Assert.assertEquals(BigDecimal.valueOf(101000.01), h.getHarga());
        Assert.assertEquals("2015-01-01", h.getBerlakumulai());
        Assert.assertEquals("2015-01-02", h.getBerlakusampai());
        
        Assert.assertNull(hargaprodukDao.findOne("notexist"));
        
    }
}
