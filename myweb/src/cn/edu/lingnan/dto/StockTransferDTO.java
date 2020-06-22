package cn.edu.lingnan.dto;

import java.util.Date;

/**
 * @author admin on 2020-04-08.
 * @version 1.0
 */
public class StockTransferDTO {
    private String id;

    private int numbers;

    private String outTime;

    private String inTime;

    private String inStaffid;

    private String outStaffid;

    private String outUserid;

    private String inUserid;

    private int status;
    /**
     * 默认函数
     */
    public StockTransferDTO() {
        this(null,0,null,null,null, null,null,null,0);
    }

    /**
     * 构造初始函数
     *
     * @param id
     * @param numbers
     * @param outTime
     * @param inTime
     * @param inStaffid
     * @param outStaffid
     * @param outUserid
     * @param inUserid
     * @param status
     */
    public StockTransferDTO(String id,int numbers,String outTime,String inTime,String outStaffid,
                            String inStaffid,String outUserid,String inUserid,int status) {
        this.id = id;
        this.numbers =numbers;
        this.outTime = outTime;
        this.inTime = inTime;
        this.outStaffid = outStaffid;
        this.inStaffid = inStaffid;
        this.outUserid = outUserid;
        this.inUserid = inUserid;
        this.status=status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutUserid() {
        return outUserid;
    }

    public void setOutUserid(String outUserid) {
        this.outUserid = outUserid;
    }

    public String getInUserid() {
        return inUserid;
    }

    public void setInUserid(String inUserid) {
        this.inUserid = inUserid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInStaffid() {
        return inStaffid;
    }

    public void setInStaffid(String inStaffid) {
        this.inStaffid = inStaffid;
    }

    public String getOutStaffid() {
        return outStaffid;
    }

    public void setOutStaffid(String outStaffid) {
        this.outStaffid = outStaffid;
    }
}
