package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Kecamatan;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-kecamatan.sql"})
public class KecamatanDaoTest {
    @Autowired private KecamatanDao kecamatanDao;
    
    @Test
    public void testSave(){
        Kecamatan k = new Kecamatan();
      //  k.setId("kec");
        k.setKode("Kode 001");
        k.setNama("Cibinong");
        
     //   Assert.assertNull(k.getId());
        kecamatanDao.save(k);
        Assert.assertNotNull(k.getId());
    }
    
    @Test
    public void testCariById(){
        Kecamatan k = kecamatanDao.findOne("kec");
        
        Assert.assertNotNull(k);
        Assert.assertEquals("001", k.getKode());
        Assert.assertEquals("camat", k.getNama());
        
        Assert.assertNull(kecamatanDao.findOne("bcd"));
    }
    
}
