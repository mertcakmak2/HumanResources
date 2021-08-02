package com.company.hrms.Api.Web;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class WebController {

    @GetMapping("")
    public ModelAndView redirectWithUsingRedirectPrefix() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}
