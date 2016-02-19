package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Kabupaten;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-kabupaten.sql"})
public class KabupatenDaoTest {
    @Autowired private KabupatenDao kabupatenDao;
    
    @Test
    public void testSave(){
        Kabupaten k = new Kabupaten();
        Assert.assertNull(k.getId());
        Assert.assertNull(k.getKode());
        Assert.assertNull(k.getNama());
        kabupatenDao.save(k);
        Assert.assertNotNull(k.getId());

    }
    
    @Test
    public void testCariById(){
        Assert.assertNotNull(kabupatenDao.findOne("abc"));
        Assert.assertNull(kabupatenDao.findOne("xyz"));
    }
}
