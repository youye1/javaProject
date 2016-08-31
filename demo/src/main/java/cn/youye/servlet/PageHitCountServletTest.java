package cn.youye.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 页面访问统计
 * Created by pc on 2016/8/25.
 */
public class PageHitCountServletTest extends HttpServlet {
    //计数器
    private int hitCount;
    @Override
    public void init() throws ServletException {
        //重置计数器
        hitCount=0;
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应内容类型
        resp.setContentType("text/html;charset=utf-8");
        // 该方法在 Servlet 被点击时执行
        // 增加 hitCount
        hitCount++;
        PrintWriter out = resp.getWriter();
        String title = "总点击量";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<h2 align=\"center\">" + hitCount + "</h2>\n" +
                "</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        //可将hitCount值存入数据库
        super.destroy();
    }
}
