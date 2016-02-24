/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Marketer;
import com.makananbekuenak.kyurifood.entity.Permission;
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
 * @author satria
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AplikasiDistribusiWebApplication.class)
@Transactional
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-permission.sql"})
public class PermissionDaoTest {
    @Autowired private PermissionDao permissionDao;
    
    @Test
    public void testSave(){
        Permission p = new Permission();
        
        p.setId("123");
        p.setKode("Tes 123");
        p.setNama("Cibinong");
        
     //   Assert.assertNull(p.getId());
        permissionDao.save(p);
        Assert.assertNotNull(p.getId());

    }
    
    @Test
    public void testCariById(){
        Permission p = permissionDao.findOne("123");
        
        Assert.assertNotNull(p);
        Assert.assertEquals("1234", p.getKode());
        Assert.assertEquals("city mall", p.getNama());
        
        Assert.assertNull(permissionDao.findOne("abc"));
    }
}
