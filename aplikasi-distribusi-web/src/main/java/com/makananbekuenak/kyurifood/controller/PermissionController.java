/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.PermissionDao;
import com.makananbekuenak.kyurifood.entity.Permission;
import com.makananbekuenak.kyurifood.exception.DataNotFoundException;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author satria
 */
@RestController
@RequestMapping("/api/permission")
@Transactional(readOnly = true)
public class PermissionController {

    @Autowired
    private PermissionDao permissionDao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Permission> findAll(Pageable page) {
    return permissionDao.findAll(page);
    }
    

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Permission p, UriComponentsBuilder uriBuilder) {
        permissionDao.save(p);
        URI location = uriBuilder.path("/api/permission/{id}").buildAndExpand(p.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Permission findById(@PathVariable("id") Permission p) {
    if (p == null) {
    throw new DataNotFoundException("No data with the specified id");
    }
    
    return p;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Permission p) {
    if (!permissionDao.exists(id)) {
    throw new DataNotFoundException("No data with the specified id");
    }
    p.setId(id);
    permissionDao.save(p);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id){
    if (!permissionDao.exists(id)) {
    throw new DataNotFoundException("No data with the specified id");
    }
    permissionDao.delete(id);
    }
    
}