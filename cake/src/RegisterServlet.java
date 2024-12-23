import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();

        RegisterUser user = new RegisterUser();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setName(request.getParameter("name"));
        user.setAddress(request.getParameter("address"));
        user.setRole(request.getParameter("role"));

        Map<String, String> errorMessages = new HashMap<>();

//        if ("商家".equals(user.getRole())) {
//            // 商家必填项验证
//            if (user.getName() == null || user.getName().trim().isEmpty()) {
//                errorMessages.put("nameError", "店铺名称不能为空");
//            }
//            if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
//                errorMessages.put("phoneError", "商户电话不能为空");
//            }
//            if (user.getAddress() == null || user.getAddress().trim().isEmpty()) {
//                errorMessages.put("addressError", "商户地址不能为空");
//            }
//        }

        String usernameRegex = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$";
        if (!user.getUsername().matches(usernameRegex)) {
            errorMessages.put("usernameError", "用户名要求英文字母和数字组成");
        }else if (UserRegistration.isUsernameExists(user.getUsername())) {
            errorMessages.put("usernameError", "该用户名已被注册");
        }


        // 验证密码
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\W)\\S{8,}$";
        if (!user.getPassword().matches(passwordRegex)) {
            errorMessages.put("passwordError", "密码至少有一个大写字母和一个符号组成，8个字符以上");
        }

        // 验证邮箱
        String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        if (!user.getEmail().matches(emailRegex)) {
            errorMessages.put("emailError", "邮箱格式不正确");
        }else if (UserRegistration.isEmailExists(user.getEmail())) {
            errorMessages.put("emailError", "该邮箱已被注册");
        }



//        // 验证收货电话
//        String phoneRegex = "^\\d{11}$";
//        if (!user.getPhone().matches(phoneRegex)) {
//            errorMessages.put("phoneError", "收货电话手机号码要求11位数字");
//        }
        if ("商家".equals(user.getRole())) {
            // 商家必填项验证
            if (user.getName() == null || user.getName().trim().isEmpty()) {
                errorMessages.put("nameError", "店铺名称不能为空");
            }

            // 验证商户电话
            String phoneRegex = "^\\d{11}$";
            if (!user.getPhone().matches(phoneRegex)) {
                errorMessages.put("phoneError", "商户电话要求11位数字");
            }

            if (user.getAddress() == null || user.getAddress().trim().isEmpty()) {
                errorMessages.put("addressError", "商户地址不能为空");
            }
        } else if ("消费者".equals(user.getRole()) && user.getPhone() != null && !user.getPhone().isEmpty()) {
            // 如果消费者填写了电话，验证格式
            String phoneRegex = "^\\d{11}$";
            if (!user.getPhone().matches(phoneRegex)) {
                errorMessages.put("phoneError", "收货电话要求11位数字");
            }
        }

        if (!errorMessages.isEmpty()) {
            request.setAttribute("errorMessages", errorMessages);
            request.setAttribute("user", user);
            request.getRequestDispatcher("user_register.jsp").forward(request, response);
        }else {
            boolean registrationSuccess = UserRegistration.registerUser(user);
            if (registrationSuccess) {
                // 注册成功，跳转到登录页面
                request.setAttribute("msg", "注册成功，请登录！");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                // 注册失败
                errorMessages.put("registerError", "注册失败，请稍后重试！");
                request.setAttribute("errorMessages", errorMessages);
                request.setAttribute("user", user);
                request.getRequestDispatcher("user_register.jsp").forward(request, response);
            }
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
