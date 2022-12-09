package model;

public class Goods {
    private String Gid;
    private String GName;
    private String GType;

    public String getGid() {
        return Gid;
    }

    public void setGid(String gid) {
        Gid = gid;
    }

    public String getGName() {
        return GName;
    }

    public void setGName(String GName) {
        this.GName = GName;
    }

    public String getGType() {
        return GType;
    }

    public void setGType(String GType) {
        this.GType = GType;
    }
}
