package cn.youye.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 网站访问统计
 * Created by pc on 2016/8/23.
 */
public class BaseFilter implements Filter {

    //网站访问次数计数器
    private int hitCount;

    public void init(FilterConfig config) throws ServletException {
        System.out.println("baseFilter初始化...");
        hitCount = 0;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("baseFilter放行前...");
        hitCount++;
        System.out.println("网站访问次数统计：" + hitCount + "...");
        chain.doFilter(req, resp);
        System.out.println("baseFilter放行后...");
    }

    public void destroy() {
        System.out.println("baseFilter destroy...");
    }
}
