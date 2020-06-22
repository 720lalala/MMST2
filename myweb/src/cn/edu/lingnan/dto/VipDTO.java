package cn.edu.lingnan.dto;

/**
 * @author admin on 2020-02-26.
 * @version 1.0
 */
public class VipDTO {
    private String phone;
    private String name;
    private int score;
    private String date;
    private int state;
    /**
     * 默认函数
     */
    public VipDTO() {
        this(null, null,0,null,0);
    }

    /**
     * 构造初始函数
     *
     * @param phone
     * @param name
     * @param score
     * @param date
     * @param state
     */
    public VipDTO(String phone, String name,int score,String date,int state) {
        this.phone = phone;
        this.name =name;
        this.score = score;
        this.date=date;
        this.state=state;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
