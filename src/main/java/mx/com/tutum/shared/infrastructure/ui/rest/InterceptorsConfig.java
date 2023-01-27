package mx.com.tutum.shared.infrastructure.ui.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer {

    private final Set<HandlerInterceptor> interceptors;

    public InterceptorsConfig(Set<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (interceptors != null) {
            for (HandlerInterceptor interceptor : interceptors) {
                registry.addInterceptor(interceptor);
            }
        }
    }
}
