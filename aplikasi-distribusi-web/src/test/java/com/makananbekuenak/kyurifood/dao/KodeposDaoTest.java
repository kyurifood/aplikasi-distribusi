package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Kodepos;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-kodepos.sql"})
public class KodeposDaoTest {
    
    @Autowired 
    private KodeposDao kodeposDao;
    
    @Test
    public void testSave(){
        Kodepos k = new Kodepos();
        k.setId("abc");
        k.setKode("1234");
        k.setNama("artivisi");

        //Assert.assertNull(k.getId());
        kodeposDao.save(k);
        Assert.assertNotNull(k.getId());

    }
    
    @Test
    public void testCariById() {
        Kodepos k = kodeposDao.findOne("abcd");
        Assert.assertNotNull(k);
        Assert.assertEquals("1234", k.getKode());
        Assert.assertEquals("artivisi", k.getNama());


        Assert.assertNull(kodeposDao.findOne("xyz"));
    }
}
