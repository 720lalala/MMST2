package cn.edu.lingnan.dto.Count;

public class salesKingDTO {
    private String clothingid;
    private int munber;
    /**
     * 默认函数
     */
    public salesKingDTO() {
        this(null, 0);
    }
    /**
     * 构造初始函数
     *
     * @param clothingid
     * @param munber
     * */
    public salesKingDTO(String clothingid, int munber) {
        this.clothingid = clothingid;
        this.munber =munber;

    }

    public void setClothingid(String clothingid) {
        this.clothingid = clothingid;
    }

    public String getClothingid() {
        return clothingid;
    }

    public int getMunber() {
        return munber;
    }

    public void setMunber(int munber) {
        this.munber = munber;
    }
}
