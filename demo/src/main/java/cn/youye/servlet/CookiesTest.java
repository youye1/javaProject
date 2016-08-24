package cn.youye.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1.设置Cookies
 * Created by pc on 2016/8/24.
 */
public class CookiesTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //创建两个cookie
        Cookie userName = new Cookie("userName", req.getParameter("userName"));
        Cookie loginName = new Cookie("loginName", req.getParameter("loginName"));
        //设置cookie过期时间,以秒为单位
        userName.setMaxAge(1 * 30);
        loginName.setMaxAge(1 * 60);
        //在响应头中添加两个Cookie
        resp.addCookie(userName);
        resp.addCookie(loginName);

        // 设置响应内容类型
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        String title = "设置 Cookies 实例";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h3 align=\"center\">" + title + "</h3>\n" +
                "<ul>\n" +
                "  <li><b>用户名</b>："
                + req.getParameter("userName") + "\n</li>" +
                "  <li><b>登录名</b>："
                + req.getParameter("loginName") + "\n</li>" +
                "</ul>\n" +
                "</body></html>");
    }
}
