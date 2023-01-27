package mx.com.tutum.admin.infrastructure.config.cache;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CacheControlFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		filterChain.doFilter(request, response);
		if (request.getRequestURI().startsWith("/admin")) {
			((HttpServletResponse)response).setHeader("Cache-Control", "max-age=0, private, must-revalidate");
		}
	}

}
