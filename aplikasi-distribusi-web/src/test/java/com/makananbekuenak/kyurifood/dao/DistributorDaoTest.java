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
        Assert.assertNull(di.getKodeDis());
        Assert.assertNull(di.getNamaDis());
        Assert.assertNull(di.getHpDis());
        Assert.assertNull(di.getPinBBDis());
        Assert.assertNull(di.getEmailDis());
        Assert.assertNull(di.getRekeningDis());
        Assert.assertNull(di.getAlamatDis());
        distributorDao.save(di);
        Assert.assertNotNull(di.getKodeDis());
    
    }
    
    @Test
    public void testCariBykode(){
        Assert.assertNotNull(distributorDao.findOne("dis001"));
        Assert.assertNull(distributorDao.findOne("xyz"));
    }
    
}
