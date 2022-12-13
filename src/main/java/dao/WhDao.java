package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Wh;
import utils.ToolUtil;

public class WhDao
{
    public  WhDao(){}
    public int addWh(Connection con,Wh wh)throws Exception
    {
        String sql = "select * from wh where WName=?";
        String sql2 = "select * from wh where wid=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        PreparedStatement pstmt2=con.prepareStatement(sql2);
        pstmt.setString(1,wh.getWName());
        pstmt2.setString(1,wh.getWid());
        ResultSet rs=pstmt.executeQuery();
        ResultSet rs1 = pstmt2.executeQuery();
        if (rs.next())
        {
//            System.out.println("已经存在该仓库");
            return 2;
        }else if(rs1.next()){
//            System.out.println("仓库编号重复");
            return 3;
        } else {
            String sql1="insert into wh(Wid,WName,WAdd) values(?,?,?)";
            PreparedStatement pstmt1 = con.prepareStatement(sql1);
            pstmt1.setString( 1,wh.getWid());
            pstmt1.setString(2,wh.getWName() );
            pstmt1.setString(3, wh.getWAdd());
            return pstmt1.executeUpdate();
        }

    }
    public ResultSet findWh(Connection con, Wh wh) throws Exception {
        StringBuilder sb = new StringBuilder("select * from wh ");
        if (!ToolUtil.isEmpty(wh.getWName())) {
            sb.append("where WName like '%").append(wh.getWName()).append("%'");
        }
        if (!ToolUtil.isEmpty(wh.getWAdd())) {
            sb.append("where WAdd like '%").append(wh.getWAdd()).append("%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }
    public int delWh(Connection con,String WName) throws  Exception{
        String sql="delete from wh where WName=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,WName);
        return pstmt.executeUpdate();
    }
    public int updateWh(Connection con,Wh wh)throws Exception{
        String sql="update wh set WAdd=? where WName=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, wh.getWAdd());
        pstmt.setString(2, wh.getWName());
        return pstmt.executeUpdate();
    }
}