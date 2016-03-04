/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.DistributorDao;

import com.makananbekuenak.kyurifood.entity.Distributor;
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
 * @author id-oz
 */
@RestController
@RequestMapping("/api/distributor")
@Transactional(readOnly = true)
public class DistributorController {
    
    @Autowired
    private DistributorDao distributorDao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Distributor> findAll(Pageable page) {
        return distributorDao.findAll(page);
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Distributor di, UriComponentsBuilder uriBuilder) {
        distributorDao.save(di);
        URI location = uriBuilder.path("/api/distributor/{id}").buildAndExpand(di.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Distributor findById(@PathVariable("id") Distributor di) {
        if (di == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return di;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Distributor di) {
        if (!distributorDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        di.setId(id);
        distributorDao.save(di);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) {
        if (!distributorDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        distributorDao.delete(id);
    }

    

  
    
}
