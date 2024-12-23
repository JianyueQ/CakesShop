import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "AdminLoginServlet", value = "/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // 验证管理员身份
            if (UserRegistration.validateAdmin(username, password)) {
                // 登录成功
                HttpSession session = request.getSession();
                session.setAttribute("adminUsername", username);
                session.setAttribute("adminRole", "管理员");
                response.sendRedirect("AdminUserServlet?action=list");
            } else {
                // 登录失败
                request.setAttribute("errorMsg", "用户名或密码错误，或者该账号没有管理员权限");
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "登录失败，请稍后重试");
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}



