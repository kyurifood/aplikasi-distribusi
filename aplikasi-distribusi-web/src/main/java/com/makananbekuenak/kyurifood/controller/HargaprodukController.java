package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.HargaprodukDao;
import com.makananbekuenak.kyurifood.entity.Hargaproduk;
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
@RequestMapping("/api/hargaproduk")
@Transactional(readOnly = true)
public class HargaprodukController {
    
    @Autowired
    private HargaprodukDao hargaprodukDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Hargaproduk> findAll(Pageable page) {
        return hargaprodukDao.findAll(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Hargaproduk h, UriComponentsBuilder uriBuilder) {
        hargaprodukDao.save(h);
        URI location = uriBuilder.path("/api/hargaproduk/{id}")
                .buildAndExpand(h.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Hargaproduk findById(@PathVariable("id") Hargaproduk h) {
        if (h == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return h;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Hargaproduk h) {
        if (!hargaprodukDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        h.setId(id);
        hargaprodukDao.save(h);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) {
        if (!hargaprodukDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        hargaprodukDao.delete(id);
    }
    
}
