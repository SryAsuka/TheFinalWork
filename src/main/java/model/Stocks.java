package model;

public class Stocks {
    private String Sid;
    private String GName;
    private String WName;
    private Integer SStocks;

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
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

    public Integer getSStocks() {
        return SStocks;
    }

    public void setSStocks(Integer SStocks) {
        this.SStocks = SStocks;
    }
}
