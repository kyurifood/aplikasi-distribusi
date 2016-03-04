package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.CustomerDao;
import com.makananbekuenak.kyurifood.entity.Customer;
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
 * @author fidya
 */
@RestController
@RequestMapping("/api/customer")
@Transactional(readOnly = true)
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Customer> findAll(Pageable page) {
        return customerDao.findAll(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid Customer c, UriComponentsBuilder uriBuilder) {
        customerDao.save(c);
        URI location = uriBuilder.path("/api/customer/{id}").buildAndExpand(c.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer findById(@PathVariable("id") Customer c) {
        if (c == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return c;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid Customer c) {
        if (!customerDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        c.setId(id);
        customerDao.save(c);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) {
        if (!customerDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        customerDao.delete(id);
    }
}