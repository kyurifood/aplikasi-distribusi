/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.entity.Regional;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author rinaldy
 */
public interface RegionalDao extends PagingAndSortingRepository<Regional, String> {
    
}
