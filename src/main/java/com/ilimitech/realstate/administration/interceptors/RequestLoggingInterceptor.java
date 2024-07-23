//package com.ilimitech.realstate.administration.interceptors;
//
//import com.ilimitech.realstate.administration.entity.RequestLog;
//import com.ilimitech.realstate.administration.repository.RequestLogRepository;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.time.LocalDateTime;
//
//@Component
//@AllArgsConstructor
//public class RequestLoggingInterceptor implements HandlerInterceptor {
//
//    private RequestLogRepository requestLogRepository;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String method = request.getMethod();
//        String uri = request.getRequestURI();
//        String remoteAddr = request.getRemoteAddr();
//        String username = getUsername(); // Obtener el nombre del usuario autenticado
//
//        // Crear y guardar el registro en la base de datos
//        RequestLog log = new RequestLog();
//        log.setMethod(method);
//        log.setUri(uri);
//        log.setRemoteAddr(remoteAddr);
//        log.setTimestamp(LocalDateTime.now());
//        log.setUsername(username); // Establecer el nombre del usuario
//
//        requestLogRepository.save(log);
//
//        return true;
//    }
//
//    private String getUsername() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
//            return authentication.getName();
//        }
//        return "anonimo";
//    }
//}
