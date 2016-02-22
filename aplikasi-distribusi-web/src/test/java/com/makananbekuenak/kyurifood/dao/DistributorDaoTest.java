/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Distributor;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-distributor.sql"})
public class DistributorDaoTest {
    
    @Autowired
    private DistributorDao distributorDao;
    
    @Test
    public void testSave(){
        Distributor di = new Distributor();
        Assert.assertNull(di.getKode());
        Assert.assertNull(di.getNama());
        Assert.assertNull(di.getHp());
        Assert.assertNull(di.getPinBB());
        Assert.assertNull(di.getEmail());
        Assert.assertNull(di.getRekening());
        Assert.assertNull(di.getAlamat());
        distributorDao.save(di);
        Assert.assertNotNull(di.getKode());
    
    }
    
    @Test
    public void testCariBykode(){
        Assert.assertNotNull(distributorDao.findOne("dis001"));
        Assert.assertNull(distributorDao.findOne("xyz"));
    }
    
}
