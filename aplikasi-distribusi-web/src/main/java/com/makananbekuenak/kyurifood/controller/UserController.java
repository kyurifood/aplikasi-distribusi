package com.makananbekuenak.kyurifood.controller;

import com.makananbekuenak.kyurifood.dao.UserDao;
import com.makananbekuenak.kyurifood.entity.User;
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
@RequestMapping("/api/user")
@Transactional(readOnly = true)
public class UserController {

    @Autowired
    private UserDao userDao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<User> findAll(Pageable page) {
        return userDao.findAll(page);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ResponseEntity<Void> create(@RequestBody @Valid User u, UriComponentsBuilder uriBuilder) {
        userDao.save(u);
        URI location = uriBuilder.path("/api/user/{id}").buildAndExpand(u.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") User u) {
        if (u == null) {
            throw new DataNotFoundException("No data with the specified id");
        }

        return u;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void update(@PathVariable("id") String id, @RequestBody @Valid User u) {
        if (!userDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        u.setId(id);
        userDao.save(u);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = false)
    public void delete(@PathVariable("id") String id) {
        if (!userDao.exists(id)) {
            throw new DataNotFoundException("No data with the specified id");
        }
        userDao.delete(id);
    }
}
