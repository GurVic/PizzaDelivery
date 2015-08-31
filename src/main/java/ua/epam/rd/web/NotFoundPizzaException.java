/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NOT_FOUND, 
        reason = "Pizza not found")
public class NotFoundPizzaException extends RuntimeException {

    public NotFoundPizzaException(String string) {
        super(string);
    }
    
}
