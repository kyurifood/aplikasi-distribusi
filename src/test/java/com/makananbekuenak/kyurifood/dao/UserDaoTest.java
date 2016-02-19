/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.User;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-kabupaten.sql"})
public class UserDaoTest {
    
    @Autowired
    private UserDao userDao;
    
    @Test
    public void testSave(){
        User u = new User();
        Assert.assertNull(u.getUsername());
        Assert.assertNull(u.getEmail());
        Assert.assertNull(u.getFullname());
        userDao.save(u);
        Assert.assertNotNull(u.getUsername());
    }
    
    @Test
    public void testCariByUsername(){
        Assert.assertNotNull(userDao.findOne("artivisi"));
        Assert.assertNull(userDao.findOne("abcd"));
    }
    
}
