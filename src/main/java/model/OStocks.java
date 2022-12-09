package model;

public class OStocks {
    private String Oid;
    //外键 货物名
    private String GName;
    //外键 仓库名
    private String WName;
    private Integer OStocks;
    //外键 管理员名
    private String UName;
    private String ODate;

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
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

    public Integer getOStocks() {
        return OStocks;
    }

    public void setOStocks(Integer OStocks) {
        this.OStocks = OStocks;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getODate() {
        return ODate;
    }

    public void setODate(String ODate) {
        this.ODate = ODate;
    }
}
