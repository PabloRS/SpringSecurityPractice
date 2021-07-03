package com.sec.practice.securingweb;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MetricFilter implements Filter {

    private MetricService metricService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        metricService = (MetricService) WebApplicationContextUtils
                .getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean("metricService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) servletRequest);
        String req = httpRequest.getMethod() + " " + httpRequest.getRequestURI();

        filterChain.doFilter(servletRequest, servletResponse);

        int status = ((HttpServletResponse) servletResponse).getStatus();
        metricService.increaseCount(req, status);
    }
}
