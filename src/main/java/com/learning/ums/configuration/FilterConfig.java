package com.learning.ums.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final ApiKeyFilter apiKeyFilter;
    private final ContentTypeFilter contentTypeFilter;

    public FilterConfig(ApiKeyFilter apiKeyFilter,
                        ContentTypeFilter contentTypeFilter) {

        this.apiKeyFilter = apiKeyFilter;
        this.contentTypeFilter = contentTypeFilter;
    }

    @Bean
    public FilterRegistrationBean<ApiKeyFilter> apiKeyFilterBean() {

        FilterRegistrationBean<ApiKeyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setName("apiKeyFilterBean");
        filterRegistrationBean.setFilter(apiKeyFilter);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<ContentTypeFilter> contentTypeFilterBean() {

        FilterRegistrationBean<ContentTypeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setName("contentTypeFilterBean");
        filterRegistrationBean.setFilter(contentTypeFilter);
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
}
