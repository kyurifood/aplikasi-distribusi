/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.dao;
import com.makananbekuenak.kyurifood.entity.Permission;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author satria
 */
public interface PermissionDao extends PagingAndSortingRepository<Permission, String>{
    
}
