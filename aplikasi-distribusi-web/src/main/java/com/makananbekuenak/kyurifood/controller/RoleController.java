/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.RoleDao;
import com.makananbekuenak.kyurifood.entity.Role;
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

@RestController
@RequestMapping("/api/role")
@Transactional(readOnly = true)
public class RoleController {

    @Autowired
    private RoleDao roleDao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Role> findAll(Pageable page) {
        return roleDao.findAll(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Role r, UriComponentsBuilder uriBuilder) {
        roleDao.save(r);
        URI location = uriBuilder.path("/api/role/{id}").buildAndExpand(r.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Role findById(@PathVariable("id") Role r){
        if (r == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return r;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Role r){
        if (!roleDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        r.setId(id);
        roleDao.save(r);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) {
        if (!roleDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        roleDao.delete(id);
    }
}
