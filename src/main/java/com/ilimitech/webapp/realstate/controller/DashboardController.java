package com.ilimitech.webapp.realstate.controller;

import com.ilimitech.webapp.realstate.service.PropertyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final PropertyService propertyDashboardService;

    @GetMapping("")
    public ModelAndView getDashboard() {
        return new ModelAndView("realstate/dashboard/index");
    }

}
