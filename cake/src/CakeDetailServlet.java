import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CakeDetailServlet")
public class CakeDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取蛋糕ID
        String id = request.getParameter("id");

        if (id != null && !id.trim().isEmpty()) {
            // 从数据库获取蛋糕详情
            Cake cake = CakeDB.getCake(id);

            if (cake != null) {
                // 将蛋糕信息存入request
                request.setAttribute("cake", cake);
                // 转发到详情页面
                request.getRequestDispatcher("cakeDetail.jsp").forward(request, response);
            } else {
                // 蛋糕不存在时的处理
                response.sendRedirect("CakeListServlet?error=notfound");
            }
        } else {
            // ID参数无效时的处理
            response.sendRedirect("CakeListServlet?error=invalid");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}