package cn.edu.lingnan.dto;

public class ClothingDTO {
    private String clothingid;
    private String property;
    private String color;
    private String size;
    private float price;
    private float discount;
    private int state;

    /**
     * 默认函数
     */
    public ClothingDTO() {
        this(null, null, null, null,0, 1, 0);
    }

    /**
     * 构造初始函数
     *
     * @param clothingid
     * @param property
     * @param color
     * @param price
     * @param discount
     * @param state
     * @param size
     *
     */
    public ClothingDTO(String clothingid, String property, String color,String size,float price, float discount, int state) {
        this.clothingid = clothingid;
        this.property = property;
        this.color = color;
        this.price = price;
        this.discount = discount;
        this.state = state;
        this.size=size;
    }


    public String getClothingid() {
        return clothingid;
    }

    public void setClothingid(String clothingid) {
        this.clothingid = clothingid;
    }

    public String getProperty() { return property; }

    public void setProperty(String property) { this.property = property; }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public float getDiscount() { return discount; }

    public void setDiscount(float discount) { this.discount = discount; }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }
}
