import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AvatarServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // 从session获取当前登录用户
        HttpSession session = request.getSession();
        RegisterUser user = (RegisterUser) session.getAttribute("user");

        if (user != null) {
            // 从数据库获取用户最新的头像信息
            String avatarPath = UserRegistration.getUserAvatar(user.getUsername());

            // 将头像路径存储到request属性中
            request.setAttribute("userAvatar", avatarPath);
        }

        // 转发到显示页面
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}