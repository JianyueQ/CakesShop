public class Cake {
    private String id;
    private String name;
    private String image1;
    private float price;
   private String intro; // 蛋糕描述

    public Cake(String id, String name, String image1, float price, String intro) {
        this.id = id;
        this.name = name;
        this.image1 = image1;
        this.price = price;
        this.intro = intro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image1='" + image1 + '\'' +
                ", price=" + price +
                ", intro='" + intro + '\'' +
                '}';
    }
}
