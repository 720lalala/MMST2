package cn.edu.lingnan.dto.Count;

public class achievementUserDTO {
    private String userid;
    private float achievement;
    /**
     * 默认函数
     */
    public achievementUserDTO() {
        this(null, 0);
    }
    /**
     * 构造初始函数
     *
     * @param userid
     * @param achievement
     * */
    public achievementUserDTO(String userid, float achievement) {
        this.userid = userid;
        this.achievement =achievement;

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public float getAchievement() {
        return achievement;
    }

    public void setAchievement(float achievement) {
        this.achievement = achievement;
    }
}
