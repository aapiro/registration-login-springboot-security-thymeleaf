package com.example.registrationlogindemo.monitoring;

import com.example.registrationlogindemo.entity.RequestLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {

//    @Query("""
//            select distinct r from RequestLog r
//            where upper(r.username) like upper(concat('%', ?1, '%')) or r.timestamp >= ?2 and r.timestamp <= ?3""")
//    Page<RequestLog> findAllLogByFilter(@Nullable String username, @Nullable LocalDateTime startDate, @Nullable LocalDateTime endDate, Pageable pageable);

//    @Query("SELECT r FROM RequestLog r WHERE (:username IS NULL OR r.username = :username) " +
//            "AND (:startDate IS NULL OR r.timestamp >= :startDate) " +
//            "AND (:endDate IS NULL OR r.timestamp <= :endDate)")
//    Page<RequestLog> findAllByFilters(@Param("username") String username,
//                                      @Param("startDate") LocalDateTime startDate,
//                                      @Param("endDate") LocalDateTime endDate,
//                                      Pageable pageable);
@Query("""
        select r from RequestLog r
        where upper(r.username) like upper(concat('%', ?1, '%')) and r.timestamp <= ?2 and r.timestamp >= ?3""")
Page<RequestLog> findLogsByFilter(@Nullable String username, @Nullable LocalDateTime timestamp, @Nullable LocalDateTime timestamp1, Pageable pageable);
Page<RequestLog> findAllByTimestampIsBetween(LocalDateTime timestamp, LocalDateTime timestamp2, Pageable pageable);
}