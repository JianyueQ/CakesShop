import java.sql.*;
import java.util.*;

public class CakeDB {
//    存放所有的蛋糕对象
//   private static HashMap<String,Cake> box=new HashMap<String,Cake>();
//  static {
//      box.put("1",new Cake("1","草莓蛋糕","pic/1-1.jpg",19,"甜郁草莓配合冰淇淋的丝滑口感,"));
//      box.put("2",new Cake("2","玫瑰舒芙蕾","pic/2-1.jpg",18,"优选法国芝士,奶香浓郁,口感细腻."));
//      box.put("3",new Cake("3","慕斯蛋糕","pic/3-1.jpg",6,"清新淡雅的口味，宛如春日里的一缕微风"));
//      box.put("4",new Cake("4","水果蛋糕","pic/4-1.jpg",39,"色彩斑斓的水果口味蛋糕，是大自然的馈赠"));
//      box.put("5",new Cake("5","榴莲蛋糕","pic/5-1.jpg",99,"XXXXX"));
//      box.put("6",new Cake("6","栗子蛋糕","pic/6-1.jpg",59,"XXXXX"));
//      box.put("7",new Cake("7","鲜花蛋糕","pic/7-1.jpg",49,"清新脱俗的抹茶口味蛋糕，犹如一位优雅的女子"));
//      box.put("8",new Cake("8","卡通蛋糕","pic/8-1.jpg",89,"XXXXX"));
//
//   }
//
////   获取所有的蛋糕
//    public static Collection<Cake> getAllCake(){
//      return box.values();
//    }
////   获取指定的蛋糕
//    public static Cake getCake(String id){
//      return  box.get(id);
//    }
    public static Collection<Cake> getAllCake() {
        ArrayList<Cake> cakes = new ArrayList<>();
        String sql = "SELECT * FROM cakes";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cake cake = new Cake(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getString("intro")
                );
                cakes.add(cake);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cakes;

    }

    // 获取指定蛋糕
    public static Cake getCake(String id) {
        String sql = "SELECT * FROM cakes WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Cake(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("image"),
                            rs.getFloat("price"),
                            rs.getString("intro")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 添加新蛋糕
    public static boolean addCake(Cake cake) {
        String sql = "INSERT INTO cakes (id, name, image, price, intro) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cake.getId());
            pstmt.setString(2, cake.getName());
            pstmt.setString(3, cake.getImage1());
            pstmt.setFloat(4, cake.getPrice());
            pstmt.setString(5, cake.getIntro());

            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Collection<Cake> searchCakes(String keyword) {
        ArrayList<Cake> cakes = new ArrayList<>();
        String sql = "SELECT * FROM cakes WHERE name LIKE ? ";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 添加模糊查询的参数
//            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, "%" + keyword + "%");
//            pstmt.setString(1, searchPattern);  // 设置第一个参数用于匹配名称
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Cake cake = new Cake(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("image"),
                            rs.getFloat("price"),
                            rs.getString("intro")
                    );
                    cakes.add(cake);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cakes;
    }

//    public static List<RegisterUser> searchUsersByUsername(String username) {
//        List<RegisterUser> users = new ArrayList<>();
//        String sql = "SELECT * FROM users WHERE username LIKE ?";
//
//        try (Connection conn = getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, "%" + username + "%");
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                RegisterUser user = new RegisterUser();
//                user.setUsername(rs.getString("username"));
//                user.setEmail(rs.getString("email"));
//                user.setPhone(rs.getString("phone"));
//                user.setName(rs.getString("name"));
//                user.setAddress(rs.getString("address"));
//                user.setRole(rs.getString("role"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }

}
