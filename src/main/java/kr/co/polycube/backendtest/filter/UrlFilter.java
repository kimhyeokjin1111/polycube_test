package kr.co.polycube.backendtest.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UrlFilter implements Filter {
    private static final String PASS_CHAR_EXPRESSION = "^[a-zA-Z0-9/?&=.:-]*$";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        StringBuilder requestUrl = new StringBuilder(req.getRequestURI().toString());

        if (req.getQueryString() != null && !req.getQueryString().isEmpty()) {
            requestUrl.append("?").append(req.getQueryString());
        }

        if (!requestUrl.toString().matches(PASS_CHAR_EXPRESSION)) {
            System.out.println("requestUrl = " + requestUrl);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
