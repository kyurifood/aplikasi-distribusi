/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Marketer;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author gilang
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Transactional
@Sql(scripts = {"/mysql/delete-data.sql","/mysql/sample-marketer.sql"})
public class MarketerDaoTest {
    
    @Autowired
    private MarketerDao marketerDao;
    
    @Test
    public void testSave(){
        Marketer m = new Marketer();
        
        m.setNama("Marketer 001");
        m.setAlamat("Cibinong");
        m.setEmail("m001@test.com");
        m.setHp("123");
        m.setPinbb("abc123");
        m.setRekening("123");
        
        Assert.assertNull(m.getKode());
        marketerDao.save(m);
        Assert.assertNotNull(m.getKode());
    }
    
    @Test
    public void testCariByKode(){
        Marketer m = marketerDao.findOne("123");
        
        Assert.assertNotNull(m);
        Assert.assertEquals("market", m.getNama());
        Assert.assertEquals("085397477853", m.getHp());
        Assert.assertEquals("market@gmail.com", m.getEmail());
        Assert.assertEquals("jln.Sapta Marga", m.getAlamat());
        Assert.assertEquals("abcdef", m.getPinbb());
        Assert.assertEquals("0123456789", m.getRekening());
        
        Assert.assertNull(marketerDao.findOne("abc"));
    }
    
}
