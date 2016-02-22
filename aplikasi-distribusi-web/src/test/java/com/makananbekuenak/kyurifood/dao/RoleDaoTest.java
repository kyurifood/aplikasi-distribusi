/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Role;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-role.sql"})
public class RoleDaoTest {
    
    @Autowired
    private RoleDao roleDao;
    
    @Test
    public void testSave(){
        Role r = new Role();
        Assert.assertNull(r.getId());
        Assert.assertNull(r.getKode());
        Assert.assertNull(r.getNama());
        roleDao.save(r);
        Assert.assertNotNull(r.getId());
    }
    
    @Test
    public void testCariByRole(){
        Assert.assertNotNull(roleDao.findOne("1"));
        Assert.assertNull(roleDao.findOne("abcd"));
} 

}
