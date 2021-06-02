package com.example.Login;

import com.example.utils.Utils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 * @author mucd
 * 2021年06月01日  上午 10:41
 */
@WebServlet(value = "/register")
public class Regist extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private int insert(String name,String password){
        try {
            QueryRunner queryRunner = new QueryRunner(Utils.getDataSource());
            int update = queryRunner.update(
                    "insert into mucd.login (username, password) VALUES (?,?)"
                    , name, password
            );
            System.out.println("update = " + update);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("rname");
        String password = req.getParameter("rpasswd");
        //账号密码长度不能小于 5
        int len = 5;
        if(username.length()>=len && password.length()>=len){
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<html><body>");
            writer.println("<h1> 账号密码长度不能小于"+len+"</h1>");
            writer.println("</body></html>");
        }
        if (insert(username,password) > 0){
            System.out.println("注册成功");
            //注册完成跳转到登录
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<html><body>");
            writer.println("<h1> 注册成功跳转登录 </h1>");
            writer.println("</body></html>");
            req.setAttribute("title","注册完成!!!开始登录");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }else {
            System.out.println("注册失败,重新注册");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
