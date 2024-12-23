import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddCartServlet", value = "/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        // 修改这里的类型声明
        List<CartItem> cart = (List<CartItem>) session.getAttribute("gou");
        if (id == null || id.trim().isEmpty()) {
            response.sendRedirect("CakeListServlet");
            return;
        }
        if(cart == null){
            cart = new ArrayList<CartItem>();
            session.setAttribute("gou", cart);
        }


        if (action == null || action.trim().isEmpty() || "add".equals(action)) {
            addToCart(cart, id);
        } else if ("decrease".equals(action)) {
            decreaseQuantity(cart, id);
        } else if ("remove".equals(action)) {
            removeFromCart(cart, id);
        }

        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(30*60);
        response.addCookie(cookie);
        response.sendRedirect("showcart.jsp");
    }

    private void addToCart(List<CartItem> cart, String id) {
        Cake cake = CakeDB.getCake(id);
        if (cake == null) return;  // 如果找不到蛋糕，直接返回

        boolean found = false;
        for (CartItem item : cart) {
            if (item.getCake().getId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(cake, 1));
        }
    }

    private void decreaseQuantity(List<CartItem> cart, String id) {
        CartItem itemToRemove = null;
        for (CartItem item : cart) {
            if (item.getCake().getId().equals(id)) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    itemToRemove = item;
                }
                break;
            }
        }
        if (itemToRemove != null) {
            cart.remove(itemToRemove);
        }
    }


    private void removeFromCart(List<CartItem> cart, String id) {
        cart.removeIf(item -> item.getCake().getId().equals(id));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}