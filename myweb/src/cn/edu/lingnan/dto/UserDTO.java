package cn.edu.lingnan.dto;


public class UserDTO {
    private String userid;
    private String username;
    private String password;
    private String authority;
    private int state;
    /**
     * 默认函数
     */
    public UserDTO() {
        this(null, null,null,null,0);
    }

    /**
     * 构造初始函数
     *
     * @param userid
     * @param username
     * @param password
     * @param authority
     * @param state
     */
    public UserDTO(String userid, String username,String password,String authority,int state) {
        this.userid = userid;
        this.username =username;
        this.password = password;
        this.authority=authority;
        this.state=state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
