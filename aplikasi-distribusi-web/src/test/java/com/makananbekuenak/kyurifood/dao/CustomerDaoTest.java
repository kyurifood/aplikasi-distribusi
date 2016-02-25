package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import javax.transaction.Transactional;
import com.makananbekuenak.kyurifood.entity.Customer;
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
@Sql(scripts = {"/mysql/delete-data.sql","/mysql/sample-customer.sql"})
public class CustomerDaoTest {
    
    @Autowired private CustomerDao customerDao;
    
    @Test
    public void testSave(){
        Customer c = new Customer();
        c.setId("1");
        c.setKode("001");
        c.setNama(("Mey"));
        c.setHp("085240");
        c.setAlamat("Cibinong");
        customerDao.save(c);
        Assert.assertNotNull(c.getId());
    }


    @Test
    public void testFindById(){
        Customer c = customerDao.findOne("ac");
        Assert.assertNotNull(c);
        Assert.assertEquals("001", c.getKode());
        Assert.assertEquals("Mey", c.getNama());
        Assert.assertEquals("085240", c.getHp());
        Assert.assertEquals("Cibinong", c.getAlamat());
        
        Assert.assertNull(customerDao.findOne("aa"));
        
    }
}
