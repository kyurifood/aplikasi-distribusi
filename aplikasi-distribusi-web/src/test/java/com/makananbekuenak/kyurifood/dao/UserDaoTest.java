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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-user.sql"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSave() {
        User u = new User();
        u.setId("001");
        u.setUsername("artivisi");
        u.setEmail("artivisi@gmail.com");
        u.setFullname("Artivisi Intermedia");
        userDao.save(u);
        Assert.assertNotNull(u.getId());
    }

    @Test
    public void testCariById() {
        User u = userDao.findOne("001");

        Assert.assertNotNull(u);
        Assert.assertEquals("artivisi", u.getUsername());
        Assert.assertEquals("artivisi@gmail.com", u.getEmail());
        Assert.assertEquals("artivisi intermedia", u.getFullname());

        Assert.assertNull(userDao.findOne("123"));
    }
}
