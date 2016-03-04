/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.KabupatenDao;
import com.makananbekuenak.kyurifood.entity.Kabupaten;
//import java.awt.print.Pageable;
import com.makananbekuenak.kyurifood.exception.DataNotFoundException;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
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
@RequestMapping("/api/kabupaten")
@Transactional(readOnly = true)
public class KabupatenController {

    @Autowired
    private KabupatenDao kabupatenDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Kabupaten> findAll(Pageable page) {
        return kabupatenDao.findAll(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Kabupaten k, UriComponentsBuilder uriBuilder) {
        kabupatenDao.save(k);
        URI location = uriBuilder.path("/api/kabupaten/{id}").buildAndExpand(k.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Kabupaten findById(@PathVariable("id") Kabupaten k) {
        if (k == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return k;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Kabupaten k) {
        if (!kabupatenDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        k.setId(id);
        kabupatenDao.save(k);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) {
        if (!kabupatenDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        kabupatenDao.delete(id);
    }
}
