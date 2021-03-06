package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Kelurahan;
import javax.transaction.Transactional;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-kelurahan.sql"})
public class KelurahanDaoTest {
    @Autowired private KelurahanDao kelurahanDao;
    
    @Test
    public void testSave(){
        Kelurahan k = new Kelurahan();
        
        k.setId("abc");
        k.setKode("001");
        k.setNama("Cibinong");
        kelurahanDao.save(k);
        Assert.assertNotNull(k.getId());
    }
    
    @Test
    public void testCariById(){
        Kelurahan k = kelurahanDao.findOne("abc");
        
        Assert.assertNotNull(k);
        Assert.assertEquals("001", k.getKode());
        Assert.assertEquals("Mey", k.getNama());
        
        Assert.assertNull(kelurahanDao.findOne("ac"));
    
    }
}
