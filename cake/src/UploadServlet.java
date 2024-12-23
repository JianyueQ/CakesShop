import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "UploadServlet", value = "/UploadServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,    // 1  MB
        maxFileSize = 1024 * 1024 * 10,     // 10 MB
        maxRequestSize = 1024 * 1024 * 15   // 15 MB
)
public class UploadServlet extends HttpServlet {
    // 定义最大尺寸
    private static final int MAX_WIDTH = 800;
    private static final int MAX_HEIGHT = 800;
//     处理图片上传和压缩
    private String processAndSaveImage(Part filePart, String uploadPath, String fileName) throws IOException {
        // 读取原始图片
        BufferedImage originalImage = ImageIO.read(filePart.getInputStream());

        if (originalImage == null) {
            throw new IOException("Invalid image file");
        }
        // 计算新的尺寸
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        int newWidth = originalWidth;
        int newHeight = originalHeight;
        // 如果图片超过最大尺寸，计算新的尺寸
        if (originalWidth > MAX_WIDTH || originalHeight > MAX_HEIGHT) {
            double ratio = Math.min(
                    (double) MAX_WIDTH / originalWidth,
                    (double) MAX_HEIGHT / originalHeight
            );
            newWidth = (int) (originalWidth * ratio);
            newHeight = (int) (originalHeight * ratio);
        }
        // 创建新的图片
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();

        // 设置图片质量
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制图片
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        // 保存图片
        String filePath = uploadPath + File.separator + fileName;
        ImageIO.write(resizedImage, "jpg", new File(filePath));

        return fileName;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uploadType = request.getParameter("uploadType");
        // 获取当前会话和用户
        HttpSession session = request.getSession();
        RegisterUser user = (RegisterUser) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            // 获取web应用的根路径
            String applicationPath = request.getServletContext().getRealPath("");
            // 创建uploads目录
            String uploadPath = applicationPath + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            if ("avatar".equals(uploadType)) {
                Part filePart = request.getPart("avatar");
                if (filePart != null && filePart.getSize() > 0) {

                    String fileName = filePart.getSubmittedFileName();
                    String avatarPath = UserRegistration.getUserAvatar(user.getUsername());
                    // 处理并保存图片
                    processAndSaveImage(filePart, uploadPath, fileName);

                    // 数据库中存储的相对路径
                    String avatarUrl = "avatar/" + fileName;


                    if (UserRegistration.updateUserAvatar(avatarPath, avatarUrl)) {
                        request.setAttribute("useravatar", avatarPath);
                        user.setAvatar(avatarUrl);
                        session.setAttribute("user", user);
                        response.sendRedirect("profile.jsp?section=avatar&success=true");
                    } else {
                        response.sendRedirect("profile.jsp?section=avatar&error=database");
                    }
                }
            }
            else if ("product".equals(uploadType)) {
                Part filePart = request.getPart("productImage");
                String productName = request.getParameter("productName");
                String productDescription = request.getParameter("productDescription");
                float productPrice = Float.parseFloat(request.getParameter("productPrice"));

                if (filePart != null && filePart.getSize() > 0) {
                    // 获取原始文件名
                    String originalFileName = filePart.getSubmittedFileName();

                    String fileName = originalFileName;

                    // 处理并保存图片
                    processAndSaveImage(filePart, uploadPath, fileName);

                    // 数据库中存储的相对路径
                    String imageUrl = "pic/" + fileName;  //这里改为pic/目录

                    // 保存到数据库
                    Cake newCake = new Cake(
                            UUID.randomUUID().toString().substring(0, 8), // 生成8位随机ID
                            productName,
                            imageUrl,
                            productPrice,
                            productDescription
                    );

                    if (CakeDB.addCake(newCake)) {
                        response.sendRedirect("cakelist.jsp?success=true");
                    } else {
                        response.sendRedirect("profile.jsp?section=products&error=database");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("profile.jsp?error=" + e.getMessage());
        }
    }
    // 获取文件扩展名的方法
    private String getFileExtension(Part part) {
        String submittedFileName = part.getSubmittedFileName();
        if (submittedFileName == null || submittedFileName.isEmpty()) {
            return ".jpg";
        }
        int lastDot = submittedFileName.lastIndexOf('.');
        if (lastDot == -1) {
            return ".jpg";
        }
        return submittedFileName.substring(lastDot);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.sendRedirect("profile.jsp");
    }
}
