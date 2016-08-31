package cn.youye.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * 测试数据库连接操作
 * Created by pc on 2016/8/24.
 */
public class DataBaseAccessTest extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
    static final String JDBC_USER = "root";
    static final String JDBC_PASSWORD = "123456";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Connection conn = null;
        Statement statement = null;

        // 设置响应内容类型
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String title = "数据库结果";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n");


        try {
            //注册jdbc驱动器
            Class.forName(JDBC_DRIVER);

            //打开一个连接
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            //执行SQL查询
            statement = conn.createStatement();
            String sql = "SELECT * FROM USER WHERE NAME='youye';";
            //结果集
            ResultSet resultSet = statement.executeQuery(sql);

            //从结果集中提取数据
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");

                out.print("id: " + id + "<br/>");
                out.print("name: " + name + "<br/>");
                out.print("password: " + password + "<br/><hr/>");
            }
            out.print("</body></html>");

            //清理环境
            resultSet.close();
            statement.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //处理Class.forName错误
            e.printStackTrace();
        } finally {
            //关闭所有资源块
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
