/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.ProdukDao;
import com.makananbekuenak.kyurifood.entity.Produk;
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
@RequestMapping("/api/produk")
@Transactional(readOnly = true)
public class ProdukController {

    @Autowired
    private ProdukDao produkDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Produk> findAll(Pageable page) {
        return produkDao.findAll(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Produk u, UriComponentsBuilder uriBuilder) {
        produkDao.save(u);
        URI location = uriBuilder.path("/api/produk/{id}").buildAndExpand(u.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Produk findById(@PathVariable("id") Produk u) {
        if (u == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return u;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Produk u) {
        if (!produkDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        u.setId(id);
        produkDao.save(u);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id){
        if (!produkDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        produkDao.delete(id);
    }
}