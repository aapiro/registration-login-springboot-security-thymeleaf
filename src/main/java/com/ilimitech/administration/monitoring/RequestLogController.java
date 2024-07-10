package com.ilimitech.administration.monitoring;

import com.ilimitech.administration.entity.RequestLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class RequestLogController {

    @Autowired
    private RequestLogService requestLogService;

    @GetMapping("/logs")
    public String getLogs(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(defaultValue = "0") int pageRq,
            @RequestParam(defaultValue = "100") int size,
            Model model) {
        Pageable pageable = PageRequest.of(pageRq, size);

        LocalDateTime start = (startDate != null) ? LocalDateTime.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        LocalDateTime end = (endDate != null) ? LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;

        Page<RequestLog> page = requestLogService.getLogs(username, start, end, pageable);
        model.addAttribute("logs", page.getContent());
//        model.addAttribute("page", page);
        model.addAttribute("username", username);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "logs";
    }
}
