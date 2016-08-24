package cn.youye.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pc on 2016/8/24.
 */
public class FileUploadServletTest extends HttpServlet {


    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 10 * 1024;
    private int maxMemSize = 4 * 10 * 1024;
    private File file;

    /**
     * 初始化，获取保存地址
     */
    public void init() {
        filePath = getServletContext().getInitParameter("file-upload");
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //检查一个文件上传请求
        isMultipart = ServletFileUpload.isMultipartContent(req);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        //不存在文件上传请求时
        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        //存在上传请求时
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //文件大小的最大存放在内存中
        factory.setSizeThreshold(maxFileSize);
        //设置暂存
        factory.setRepository(new File("E:\\JavaWorkSpace\\JavaProject\\temp\\"));

        //创建一个新的文件处理程序
        ServletFileUpload upload = new ServletFileUpload(factory);

        //允许上传文件的最大值
        upload.setSizeMax(maxFileSize);

        try {
            //解析请求，获取文件项
            List fileItems = upload.parseRequest(req);

            //处理上传的文件项(此时只有一个文件项)
            Iterator it = fileItems.iterator();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");

            while (it.hasNext()) {
                FileItem fileItem = (FileItem) it.next();

                if (!fileItem.isFormField()) {
                    // 获取上传文件的参数
                    String fieldName = fileItem.getFieldName();
                    String fileName = fileItem.getName();
                    String contentType = fileItem.getContentType();
                    boolean isInMemory = fileItem.isInMemory();
                    long sizeInBytes = fileItem.getSize();
                    // 写入文件
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fileItem.write(file);

                    out.println("Uploaded Filename: " + fileName + "<br>");
                    out.println(" <img src=\'" +
                            "http://localhost:8080/demo/fileUpload/" + fileName
                            + "\' alt=\'暂无图片\'/>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (FileUploadException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new ServletException("GET method used with " +
                getClass().getName() + ": POST method required.");
    }
}
