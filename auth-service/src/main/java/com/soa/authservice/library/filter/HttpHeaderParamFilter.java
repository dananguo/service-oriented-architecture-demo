package com.soa.authservice.library.filter;

import com.soa.authservice.library.support.RibbonFilterContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 19028
 * @date 2019/12/21 23:51
 */

public class HttpHeaderParamFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        String uid = httpRequest.getHeader("uid");
        //RibbonFilterContextHolder.clearCurrentContext();
        RibbonFilterContextHolder.getCurrentContext().add("uid", uid);
        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {

    }

}
