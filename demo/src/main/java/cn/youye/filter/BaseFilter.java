package cn.youye.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by pc on 2016/8/23.
 */
public class BaseFilter implements Filter {
    public void destroy() {
        System.out.println("baseFilter destroy...");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("baseFilter放行前...");
        chain.doFilter(req, resp);
        System.out.println("baseFilter放行后...");
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("baseFilter初始化...");
    }

}
