package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.AplikasiDistribusiWebApplication;
import com.makananbekuenak.kyurifood.entity.Bank;
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
@Sql(scripts = {"/mysql/delete-data.sql", "/mysql/sample-bank.sql"})
public class BankDaoTest {
    @Autowired private BankDao bankDao;
    
    @Test
    public void testSave(){
        Bank b = new Bank();
        b.setKode("BCA");
        b.setNama("Bank Central Asia");
        b.setKodeBi("041");
        
        Assert.assertNull(b.getId());
        bankDao.save(b);
        Assert.assertNotNull(b.getId());
    }
    
    @Test
    public void testCariById(){
        Bank b = bankDao.findOne("bsm");
        Assert.assertNotNull(b);
        Assert.assertEquals("BSM", b.getKode());
        Assert.assertEquals("Bank Syariah Mandiri", b.getNama());
        Assert.assertEquals("000", b.getKodeBi());
        
        Assert.assertNull(bankDao.findOne("aaa"));
    }
    
    @Test
    public void testUpdate(){
        Bank b = new Bank();
        b.setId("bsm");
        b.setKode("BSM");
        b.setNama("Bank Syariah Mandiri");
        b.setKodeBi("451");
        
        bankDao.save(b);
        Bank bx = bankDao.findOne("bsm");
        Assert.assertNotNull(bx);
        Assert.assertEquals("451", b.getKodeBi());
        
    }
}
