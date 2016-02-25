/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;


import com.makananbekuenak.kyurifood.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface RoleDao extends PagingAndSortingRepository<Role, String> {
    
}
