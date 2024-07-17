package com.ilimitech.webapp.realstate.administration.repository;

import com.ilimitech.webapp.realstate.administration.entity.RequestLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {

@Query("""
        select r from RequestLog r
        where upper(r.username) like upper(concat('%', ?1, '%')) and r.timestamp <= ?2 and r.timestamp >= ?3""")
Page<RequestLog> findLogsByFilter(@Nullable String username, @Nullable LocalDateTime timestamp, @Nullable LocalDateTime timestamp1, Pageable pageable);
Page<RequestLog> findAllByTimestampIsBetween(LocalDateTime timestamp, LocalDateTime timestamp2, Pageable pageable);
}