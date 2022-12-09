package model;

public class User {
    private String Uid;
    private String UAcc;
    private String WPwd;
    private String UName;
    private String UPhone;
    //管理员等级 1：最高管理员 2：普通仓管
    private Integer WPower;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getUAcc() {
        return UAcc;
    }

    public void setUAcc(String UAcc) {
        this.UAcc = UAcc;
    }

    public String getWPwd() {
        return WPwd;
    }

    public void setWPwd(String WPwd) {
        this.WPwd = WPwd;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getUPhone() {
        return UPhone;
    }

    public void setUPhone(String UPhone) {
        this.UPhone = UPhone;
    }

    public Integer getWPower() {
        return WPower;
    }

    public void setWPower(Integer WPower) {
        this.WPower = WPower;
    }
}
