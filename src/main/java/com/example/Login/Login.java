package main.java.com.example.Login;

import com.example.daoman.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.example.utils.Utils.getDataSource;

/**
 * @author mucd
 * 2021年06月01日  上午 11:46
 */
@WebServlet(value = "/login", loadOnStartup = 1)
public class Login extends HttpServlet {
    private List<User> isNum() {
            try {
                QueryRunner queryRunner = new QueryRunner(getDataSource());
                return queryRunner.query(
                        "select * from mucd.login",
                        new BeanListHandler<>(User.class)
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> user = isNum();
            if (user != null) {
                QueryRunner queryRunner1 = new QueryRunner(getDataSource());
                List<User> query = queryRunner1.query(
                        "select * from mucd.login"
                        , new BeanListHandler<>(User.class)
                );
                String username1 = req.getParameter("username");
                String password1 = req.getParameter("password");
                for (User query1 : query) {
                    String username = query1.getUsername();
                    String password = query1.getPassword();
                    System.out.println("username = " + username);
                    System.out.println("password = " + password);
                    if (username.equals(username1) && password.equals(password1)) {
                        System.out.println("输入正确,正在登录");
                        req.getRequestDispatcher("successful.jsp").forward(req, resp);
                        break;
                    }
                }
                System.out.println("账号或密码错误");
                req.getRequestDispatcher("fail.jsp").forward(req, resp);
            } else {
                System.out.println("先注册账号");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
