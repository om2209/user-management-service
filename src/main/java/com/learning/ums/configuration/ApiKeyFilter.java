package com.learning.ums.configuration;

import com.learning.ums.configuration.helper.ConfigurationHelper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterChain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {

    private static final String X_API_KEY_HEADER_NAME = "x-api-key";
    private final String X_API_KEY_HEADER_VALUE;

    private final ConfigurationHelper configurationHelper;

    public ApiKeyFilter(@Value("${security.x-api-key}") String xApiKey, ConfigurationHelper configurationHelper) {

        X_API_KEY_HEADER_VALUE = xApiKey;
        this.configurationHelper = configurationHelper;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        configurationHelper.addFilter(servletRequest, servletResponse, filterChain, X_API_KEY_HEADER_NAME, X_API_KEY_HEADER_VALUE);
    }

    @Override
    public void destroy() {
    }
}
