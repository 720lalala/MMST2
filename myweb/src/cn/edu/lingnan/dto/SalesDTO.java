package cn.edu.lingnan.dto;


public class SalesDTO {
    private String staffid;
    private String clothingid;
    private int numbers;
    private float disprice;
    private  String flowid;
    private String userid;
    private int state;
    /**
     * 默认函数
     */
    public SalesDTO() {
        this(null,null, 0,0,null,null,0);
    }

    /**
     * 构造初始函数
     *
     * @param staffid
     * @param clothingid
     * @param numbers
     * @param disprice
     * @param flowid
     * @param userid
     * @param state
     */

    public SalesDTO(String staffid,String clothingid,int numbers,float disprice,String flowid,String userid,int state) {
        this.staffid=staffid;
        this.clothingid=clothingid;
        this.numbers=numbers;
        this.disprice=disprice;
        this.flowid=flowid;
        this.userid = userid;
        this.state=state;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getClothingid() {
        return clothingid;
    }

    public void setClothingid(String clothingid) {
        this.clothingid = clothingid;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public float getDisprice() {
        return disprice;
    }

    public void setDisprice(float disprice) {
        this.disprice = disprice;
    }

    public String getFlowid() {
        return flowid;
    }

    public void setFlowid(String flowid) {
        this.flowid = flowid;
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
