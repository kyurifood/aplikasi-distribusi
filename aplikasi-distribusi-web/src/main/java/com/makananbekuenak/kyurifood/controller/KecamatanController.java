package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.KecamatanDao;
import com.makananbekuenak.kyurifood.entity.Kecamatan;
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
@RequestMapping("/api/kecamatan")
@Transactional(readOnly = true)
public class KecamatanController {

    @Autowired
    private KecamatanDao kecamatanDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Kecamatan> findAll(Pageable page) {
        return kecamatanDao.findAll(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Kecamatan k, UriComponentsBuilder uriBuilder) {
        kecamatanDao.save(k);
        URI location = uriBuilder.path("/api/kecamatan/{id}")
                .buildAndExpand(k.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Kecamatan findById(@PathVariable("id") Kecamatan k) {
        if (k == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return k;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Kecamatan k) {
        if (!kecamatanDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        k.setId(id);
        kecamatanDao.save(k);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) {
        if (!kecamatanDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        kecamatanDao.delete(id);
    }
}
