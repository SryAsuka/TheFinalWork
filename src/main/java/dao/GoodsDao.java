package dao;

import model.Goods;
import utils.ToolUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodsDao
{
    public GoodsDao() {}
    public int addGoods(Connection con,Goods goods) throws Exception {
        String sql1 = "select * from goods where  GName = ?";
        String sql3 = "select * from goods where  Gid = ?";
        PreparedStatement pstmt1 = con.prepareStatement(sql1);
        PreparedStatement pstmt3 = con.prepareStatement(sql3);
        pstmt1.setString(1, goods.getGName());
        pstmt3.setString(1, goods.getGid());
        ResultSet rs = pstmt1.executeQuery();
        ResultSet rs1 = pstmt3.executeQuery();
        if (rs.next())
        {
//            System.out.println("已经存在该货物");
            return 2;
        }else if(rs1.next()){
//            System.out.println("货物编号重复");
            return 3;
        }
        else {
            String  sql2 = "insert into goods (Gid,GName,GType) values(?,?,?)";
            PreparedStatement pstmt2 = con.prepareStatement(sql2);
            pstmt2.setString( 1,goods.getGid());
            pstmt2.setString(2, goods.getGName());
            pstmt2.setString(3, goods.getGType());
            //System.out.println("货物添加成功");
            return pstmt2.executeUpdate();
        }
    }
    public ResultSet findGoods(Connection con,Goods goods) throws Exception{
        StringBuilder sb= new StringBuilder("select * from goods ");
        if (!ToolUtil.isEmpty(goods.getGName())){
            sb.append("where GName like '%").append(goods.getGName()).append("%'");
        }
        if (!ToolUtil.isEmpty(goods.getGType())) {
            sb.append("where GType like '%").append(goods.getGType()).append("%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
//        System.out.println(sb); //测试函数
        return pstmt.executeQuery();
    }
    public int delGoods(Connection con,String GName) throws  Exception{
        String sql="delete from goods where GName=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,GName);
        return pstmt.executeUpdate();
    }
    public int updateGoods(Connection con,Goods goods)throws Exception{
        String sql="update goods set GType=? where GName=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,goods.getGType());
        pstmt.setString(2,goods.getGName());
        return pstmt.executeUpdate();
    }
}

