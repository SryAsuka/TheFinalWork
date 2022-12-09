package model;

public class IStocks {
    private String Iid;
    //外键 货物名
    private String GName;
    //外键 仓库名
    private String WName;
    private Integer IStocks;
    //外键 管理员名
    private String UName;
    private String IDate;

    public String getIid() {
        return Iid;
    }

    public void setIid(String iid) {
        Iid = iid;
    }

    public String getGName() {
        return GName;
    }

    public void setGName(String GName) {
        this.GName = GName;
    }

    public String getWName() {
        return WName;
    }

    public void setWName(String WName) {
        this.WName = WName;
    }

    public Integer getIStocks() {
        return IStocks;
    }

    public void setIStocks(Integer IStocks) {
        this.IStocks = IStocks;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getIDate() {
        return IDate;
    }

    public void setIDate(String IDate) {
        this.IDate = IDate;
    }
}
