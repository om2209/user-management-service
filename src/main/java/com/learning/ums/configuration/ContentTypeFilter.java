package com.learning.ums.configuration;

import com.learning.ums.configuration.helper.ConfigurationHelper;
import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ContentTypeFilter implements Filter {

    private static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";

    private final String CONTENT_TYPE_HEADER_VALUE;

    private final ConfigurationHelper configurationHelper;

    public ContentTypeFilter(@Value("${security.content-type}") String contentType, ConfigurationHelper configurationHelper) {

        CONTENT_TYPE_HEADER_VALUE = contentType;
        this.configurationHelper = configurationHelper;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        configurationHelper.addFilter(servletRequest, servletResponse, filterChain, CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_VALUE);
    }



    @Override
    public void destroy() {
    }
}
