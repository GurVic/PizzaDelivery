/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@EnableWebMvc
public class GlobalErrorHandler {

    //@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundPizzaException.class)
    public ModelAndView exceptionHandler(
            Exception exception,
            HttpServletRequest req) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("ex", exception);
        model.addObject("url", req.getRequestURL());
        return model;
    }
}
