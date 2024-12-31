package com.learning.ums.configuration.helper;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import static com.learning.ums.UserManagementServiceApplication.APP_ROOT;

@Component
public class ConfigurationHelper {

    public void addFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                          FilterChain filterChain, String headerFieldName, String headerFieldValue)
            throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();

        if (requestURI.equals(APP_ROOT + "/health")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String headerFieldValueInRequest = httpServletRequest.getHeader(headerFieldName);
        if (headerFieldValueInRequest == null) {
            throw new ServletException("Missing " + headerFieldName + " field in request headers");
        } else if (headerFieldValueInRequest.isEmpty()) {
            throw new ServletException("Missing " + headerFieldName + " value in request headers");
        } else if (!headerFieldValueInRequest.equals(headerFieldValue)) {
            throw new ServletException("Invalid " + headerFieldName + " in request headers");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
