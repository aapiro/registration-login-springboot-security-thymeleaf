package com.ilimitech.webapp.realstate.dashboard.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class DashboardController {

    @GetMapping("/dashboard")
    public ModelAndView getDashboard() {

        ModelAndView mav = new ModelAndView("realstate/dashboard/index");
        return mav;
    }
    @GetMapping("/properties/list")
    public ModelAndView getAllProperties() {

        ModelAndView mav = new ModelAndView("realstate/dashboard/property-list");
        return mav;
    }

}
