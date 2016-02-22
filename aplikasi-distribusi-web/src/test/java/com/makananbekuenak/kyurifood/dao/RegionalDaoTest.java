/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Regional;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-regional.sql"})
public class RegionalDaoTest {
    
    @Autowired
    private RegionalDao regionalDao;
    
    @Test
    public void testSave(){
        Regional g = new Regional();
        Assert.assertNull(g.getKode());
        Assert.assertNull(g.getNama());
        regionalDao.save(g);
    }
    
    @Test
    public void testCariByRegional(){
        Assert.assertNotNull(regionalDao.findOne("235"));
        Assert.assertNull(regionalDao.findOne("asdi"));
} 
}
