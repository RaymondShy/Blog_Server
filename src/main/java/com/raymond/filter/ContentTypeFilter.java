package com.raymond.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决JSON请求体读取问题
 */
public class ContentTypeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getContentType() == null && request.getMethod().equals("POST")) {
            request.setAttribute("org.springframework.web.util.WebUtils.ERROR_EXCEPTION",
                    new ServletException("Content type not set"));
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
