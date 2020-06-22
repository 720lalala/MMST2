package cn.edu.lingnan.dto;

public class DepotDetailsDTO {
    private String clothingid;
    private String property;
    private String color;
    private String size;
    private float price;
    private int numbers;
    private String userid;
    private float discount;
    private String staffid;

    /**
     * 默认函数
     */
    public DepotDetailsDTO() {
        this(null, null,null,null,0,0,null,0,null);
    }

    /**
     * 构造初始函数
     *
     * @param clothingid
     * @param property
     * @param color
     * @param size
     * @param price
     * @param numbers
     * @param userid
     * @param discount
     * @param staffid
     *
     */
    public DepotDetailsDTO(String clothingid,String property,String color,String size,float price,int numbers,String userid,float discount,String staffid) {
        this.clothingid=clothingid;
        this.property=property;
        this.color=color;
        this.size=size;
        this.price=price;
        this.numbers=numbers;
        this.userid = userid;
        this.discount=discount;
        this.staffid=staffid;
    }


    public String getClothingid() {
        return clothingid;
    }

    public void setClothingid(String clothingid) {
        this.clothingid = clothingid;
    }

    public int getNumbers(){
        return numbers;
    }

    public void setNumbers(int numbers){
        this.numbers=numbers;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setColor(String color) { this.color = color; }

    public String getColor() { return color; }

    public void setProperty(String property) { this.property = property; }

    public String getProperty() { return property; }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setStaffid(String staffid) { this.staffid = staffid; }

    public String getStaffid() { return staffid; }
}
