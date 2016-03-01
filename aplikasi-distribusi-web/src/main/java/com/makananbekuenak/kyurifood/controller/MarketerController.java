package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.MarketerDao;
import com.makananbekuenak.kyurifood.entity.Marketer;
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
 * @author gilang
 */
@RestController
@RequestMapping("/api/marketer")
@Transactional
public class MarketerController {

    @Autowired
    private MarketerDao marketerDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Marketer> findAll(Pageable page) {
        return marketerDao.findAll(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Marketer m, UriComponentsBuilder uriBuilder) {
        marketerDao.save(m);
        URI location = uriBuilder.path("/api/marketer/{id}").buildAndExpand(m.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Marketer findById(@PathVariable("id") Marketer m) {
        if (m == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return m;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Marketer m) {
        if (!marketerDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        m.setId(id);
        marketerDao.save(m);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) {
        if (!marketerDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        marketerDao.delete(id);
    }
}
