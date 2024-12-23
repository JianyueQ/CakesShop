import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//@WebServlet(name = "AdminLoginServlet", value = "/AdminLoginServlet")
public class LoginServletAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (session.getAttribute("admin_username")!= null) {
            response.sendRedirect("admin_welcome.jsp");
            return;
        }
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("admin.properties");
        Properties properties = new Properties();
        properties.load(new FileInputStream(realPath));
        String storedUsername = properties.getProperty("admin.username");
        String storedPassword = properties.getProperty("admin.password");
        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            session.setAttribute("admin_username", username);
            response.sendRedirect("Welcome.jsp");
        }
        else {
            request.setAttribute("errorMsg", "用户名或密码错误，请重新输入！");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
