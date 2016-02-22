/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author fidya
 */
public interface CustomerDao extends PagingAndSortingRepository<Customer, String> {
    
}
