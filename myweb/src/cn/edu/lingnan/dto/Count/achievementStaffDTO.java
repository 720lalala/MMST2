package cn.edu.lingnan.dto.Count;

public class achievementStaffDTO {
    private String staffid;
    private float achievement;
    /**
     * 默认函数
     */
    public achievementStaffDTO() {
        this(null, 0);
    }
    /**
     * 构造初始函数
     *
     * @param staffid
     * @param achievement
     * */
    public achievementStaffDTO(String staffid, float achievement) {
        this.staffid = staffid;
        this.achievement =achievement;

    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getStaffid() {
        return staffid;
    }

    public float getAchievement() {
        return achievement;
    }

    public void setAchievement(float achievement) {
        this.achievement = achievement;
    }
}
