import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRegistration {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cakes?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
        String username = "root";
        String password = "123456";
        return DriverManager.getConnection(url, username, password);
    }
    public static boolean registerUser(RegisterUser user) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); // 开启事务

            // 1. 插入用户基本信息
            String userSql = "INSERT INTO users (username, password, email, phone, name, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement userStmt = conn.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, user.getUsername());
            userStmt.setString(2, user.getPassword());
            userStmt.setString(3, user.getEmail());
            userStmt.setString(4, user.getPhone());
            userStmt.setString(5, user.getName());
            userStmt.setString(6, user.getAddress());
            userStmt.setString(7, user.getRole());

            int userResult = userStmt.executeUpdate();

            // 2. 如果是商家，还需要插入商家信息
            if (userResult > 0 && "商家".equals(user.getRole())) {
                ResultSet rs = userStmt.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    String sellerSql = "INSERT INTO sellers (user_id, shop_name, business_phone, business_address) VALUES (?, ?, ?, ?)";

                    PreparedStatement sellerStmt = conn.prepareStatement(sellerSql);
                    sellerStmt.setInt(1, userId);
                    sellerStmt.setString(2, user.getName());     // 店铺名称
                    sellerStmt.setString(3, user.getPhone());    // 商户电话
                    sellerStmt.setString(4, user.getAddress());  // 商户地址

                    int sellerResult = sellerStmt.executeUpdate();
                    if (sellerResult <= 0) {
                        throw new SQLException("Failed to insert seller information");
                    }
                }
            }

            conn.commit(); // 提交事务
            return true;

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // 发生错误时回滚事务
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // 恢复自动提交
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 添加检查用户名是否存在的方法
    public static boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 添加检查邮箱是否存在的方法
    public static boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<RegisterUser> getAllUsers() {
        List<RegisterUser> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                RegisterUser user = new RegisterUser();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 根据用户名搜索用户
    public static List<RegisterUser> searchUsersByUsername(String username) {
        List<RegisterUser> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE username LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + username + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RegisterUser user = new RegisterUser();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 更新用户信息
    public static boolean updateUser(RegisterUser user) {
        String sql = "UPDATE users SET email = ?, phone = ?, name = ?, address = ?, role = ? WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getRole());
            pstmt.setString(6, user.getUsername());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 删除用户
    public static boolean deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 根据用户名获取用户信息
    public static RegisterUser getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                RegisterUser user = new RegisterUser();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean validateAdmin(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // 如果有结果返回true，否则返回false

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public RegisterUser checkLogin(String username, String password) throws SQLException {
        RegisterUser user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new RegisterUser();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
            }
        }
        return user;
    }

    public static boolean updateUserAvatar(String username, String avatarUrl) {
        String sql = "UPDATE users SET avatar = ? WHERE username = ?";
        try (Connection conn =
                     getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, avatarUrl);
            pstmt.setString(2, username);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String getUserAvatar(String username) {
        String sql = "SELECT avatar FROM users WHERE username = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("avatar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
