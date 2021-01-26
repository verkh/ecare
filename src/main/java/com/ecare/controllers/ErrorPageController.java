package com.ecare.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * This controller handles error page
 */
@Controller
public class ErrorPageController {

    private static Logger logger = LogManager.getLogger(ErrorPageController.class);

    /**
     * Configures error JSP page to show the reason
     * @param httpRequest made request
     * @return the prepared model
     */
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("Error");
        HttpStatus httpErrorCode = HttpStatus.valueOf(getErrorCode(httpRequest));
        errorPage.getModelMap().addAttribute("errorMsg", httpErrorCode.getReasonPhrase());
        return errorPage;
    }

    /**
     * Extract status code from request
     * @param httpRequest original request
     * @return the value of status_code in attributes
     */
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
