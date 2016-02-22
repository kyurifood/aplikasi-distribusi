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
        Kecamatan a = new Kecamatan();
        Assert.assertNull(a.getNama());
        Assert.assertNull(a.getKode());
        kecamatanDao.save(a);
        Assert.assertNotNull(a.getKode());
    }
    
    @Test
    public void testCariByKode(){
        Assert.assertNotNull(kecamatanDao.findOne("bbb"));
        Assert.assertNull(kecamatanDao.findOne("xyz"));
    }
}
