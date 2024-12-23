import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "CakeListServlet", value = "/CakeListServlet")
public class CakeListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 获取搜索关键词
        String keyword = request.getParameter("keyword");
        Collection<Cake> cakes;

        if (keyword != null && !keyword.trim().isEmpty()) {
            // 执行搜索
            cakes = CakeDB.searchCakes(keyword.trim());
            // 将关键词存入request，用于页面显示
            request.setAttribute("keyword", keyword);
        } else {
            // 显示所有蛋糕
            cakes = CakeDB.getAllCake();
        }

        // 将结果存入request
        request.setAttribute("cakes", cakes);

        // 添加调试日志
        System.out.println("搜索关键词: " + keyword);
        System.out.println("结果数量: " + cakes.size());

        // 转发到JSP页面
        request.getRequestDispatcher("cakelist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
