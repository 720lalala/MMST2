package cn.edu.lingnan.dto;


    public class StaffDTO {
    private String staffid;
    private String staffname;
    private String userid;
    private int state;
    /**
     * 默认函数
     */
    public StaffDTO() {
        this(null, null,null,0);
    }

    /**
     * 构造初始函数
     *
     * @param staffid
     * @param staffname
     * @param userid
     * @param state
     */
    public StaffDTO(String staffid, String staffname,String userid,int state) {
        this.userid = userid;
        this.staffid =staffid;
        this.staffname = staffname;
        this.state=state;
    }


    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }
    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
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
