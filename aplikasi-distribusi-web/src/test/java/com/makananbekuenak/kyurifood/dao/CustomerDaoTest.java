package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Customer;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-customer.sql"})
public class CustomerDaoTest {
    @Autowired private CustomerDao customerDao;
    
    @Test
    public void testSave(){
        Customer k = new Customer();
        Assert.assertNull(k.getKode());
        Assert.assertNull(k.getNama());
        Assert.assertNull(k.getHp());
        Assert.assertNull(k.getAlamat());
        customerDao.save(k);
        Assert.assertNotNull(k.getKode());
    }
    
    @Test
    public void testCariById(){
        Assert.assertNotNull(customerDao.findOne("001"));
        Assert.assertNull(customerDao.findOne("xyz"));
    
    }
}
