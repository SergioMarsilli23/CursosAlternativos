package mx.com.tutum.shared.infrastructure.ui.rest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import mx.com.tutum.shared.infrastructure.UuidGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MDCRequestHandler implements HandlerInterceptor {

    private UuidGenerator uuidGenerator;

    public MDCRequestHandler(final UuidGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        String requestId = request.getHeader("X-Request-ID");
        MDC.put("requestId", StringUtils.isNotEmpty(requestId) ? requestId : uuidGenerator.next());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        MDC.clear();
    }

}
