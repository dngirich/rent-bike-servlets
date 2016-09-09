package com.belhard.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class SetCharacterEncodingFilter implements Filter {

    private static final String UTF8 = "UTF-8";

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String reqEncoding = request.getCharacterEncoding();
        String newEncoding = UTF8;

        if (!newEncoding.equalsIgnoreCase(reqEncoding)) {
            request.setCharacterEncoding(newEncoding);
            response.setCharacterEncoding(newEncoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
