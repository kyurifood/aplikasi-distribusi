/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.entity.Marketer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author gilang
 */
public interface MarketerDao extends PagingAndSortingRepository<Marketer, String> {
    
}
