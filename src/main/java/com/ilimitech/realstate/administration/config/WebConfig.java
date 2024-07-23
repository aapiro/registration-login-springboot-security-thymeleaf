//    package com.ilimitech.realstate.administration.config;
//
//    import com.ilimitech.realstate.administration.interceptors.RequestLoggingInterceptor;
//    import lombok.AllArgsConstructor;
//    import org.springframework.context.annotation.Configuration;
//    import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//    @AllArgsConstructor
//    @Configuration
//    public class WebConfig implements WebMvcConfigurer {
//
//        private RequestLoggingInterceptor requestLoggingInterceptor;
//
//        @Override
//        public void addInterceptors(InterceptorRegistry registry) {
//            registry.addInterceptor(requestLoggingInterceptor);
//        }
//    }
