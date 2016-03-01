/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makananbekuenak.kyurifood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author fidya
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {
    
    public DataNotFoundException(String massage) {
        super(massage);
    }
    
}
