package cn.edu.lingnan.dto;


public class DepotDTO {
    private String clothingid;
    private int numbers;
    private String userid;
    private int state;
    /**
     * 默认函数
     */
    public DepotDTO() {
        this(null, 0,null,0);
    }

    /**
     * 构造初始函数
     *
     * @param clothingid
     * @param numbers
     * @param userid
     * @param state
     */
    public DepotDTO(String clothingid,int numbers,String userid,int state) {
        this.clothingid=clothingid;
        this.numbers=numbers;
        this.userid = userid;
        this.state=state;
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
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
