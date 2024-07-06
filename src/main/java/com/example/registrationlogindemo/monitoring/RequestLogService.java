package com.example.registrationlogindemo.monitoring;

import com.example.registrationlogindemo.entity.RequestLog;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RequestLogService {

    private RequestLogRepository requestLogRepository;

    public Page<RequestLog> getLogs(String username, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return requestLogRepository.findAll(pageable);
    }
}