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
    public void testSave() {
        Distributor di = new Distributor();
        
        di.setId("sss");
        di.setKode("1234");
        di.setNama("dis001");
        di.setHp("085397477853");
        di.setPinBB("abcdef");
        di.setEmail("market@gmail.com");
        di.setRekening("0123456789");
        di.setAlamat("jln.Sapta Marga");

        //Assert.assertNull(di.getId());
        distributorDao.save(di);
        Assert.assertNotNull(di.getId());

    }

    @Test
    public void testCariById() {
        Distributor di = distributorDao.findOne("sss");
        Assert.assertNotNull(di);
        Assert.assertEquals("1234", di.getKode());
        Assert.assertEquals("dis001", di.getNama());
        Assert.assertEquals("085397477853", di.getHp());
        Assert.assertEquals("abcdef", di.getPinBB());
        Assert.assertEquals("market@gmail.com", di.getEmail());
        Assert.assertEquals("0123456789", di.getRekening());
        Assert.assertEquals("jln.Sapta Marga", di.getAlamat());

        Assert.assertNull(distributorDao.findOne("anm"));
    }
}
