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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-marketer.sql"})
public class MarketerDaoTest {
    
    @Autowired
    private MarketerDao marketerDao;
    
    @Test
    public void testSave(){
        Marketer m = new Marketer();
        Assert.assertNull(m.getKode());
        Assert.assertNull(m.getNama());
        Assert.assertNull(m.getHp());
        Assert.assertNull(m.getPinbb());
        Assert.assertNull(m.getEmail());
        Assert.assertNull(m.getRekening());
        Assert.assertNull(m.getAlamat());
        marketerDao.save(m);
        Assert.assertNotNull(m.getKode());
    }
    
    public void testCariByKode(){
        Assert.assertNotNull(marketerDao.findOne("123"));
        Assert.assertNull(marketerDao.findOne("abcd"));
    }
    
}
