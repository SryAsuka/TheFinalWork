package dao;

import model.OStocks;
import utils.ToolUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OStocksDao {//出库

    public OStocksDao(){}


    public int addOS(Connection con, OStocks  oStocks)throws Exception {//出库后修改信息
        String sql2 = "select * from ostocks where warehouse.ostocks.oid = ? ";
        PreparedStatement pstmt1 = con.prepareStatement(sql2);
        pstmt1.setString(1, oStocks.getOid());
        ResultSet rs1 = pstmt1.executeQuery();
        if (rs1.next()) {
            return 2;
        } else {
            String sql = "insert into ostocks(oid, gname, wname, ostocks, uname, odate)  values (?,?,?,?,?,now())";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, oStocks.getOid());
            preparedStatement.setString(2, oStocks.getGName());
            preparedStatement.setString(3, oStocks.getWName());
            preparedStatement.setInt(4, oStocks.getOStocks());
            preparedStatement.setString(5, oStocks.getUName());
            return preparedStatement.executeUpdate();
        }
    }
    public ResultSet show(Connection con,OStocks oStocks) throws Exception {
        StringBuilder stringBuffer =new StringBuilder( "select * from OStocks ");
        if (!ToolUtil.isEmpty(oStocks.getGName())){
            stringBuffer.append("and GName like '%").append(oStocks.getGName()).append("%'");
        }
        if (!ToolUtil.isEmpty(oStocks.getWName())){
            stringBuffer.append("and WName like '%").append(oStocks.getWName()).append("%'");
        }
        if (!ToolUtil.isEmpty(oStocks.getUName())){
            stringBuffer.append("and UName like '%").append(oStocks.getUName()).append("%'");
        }
        if (!ToolUtil.isEmpty(oStocks.getODate())){
            stringBuffer.append("and ODate like '%").append(oStocks.getODate()).append("%'");
        }
        PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString().replaceFirst("and","where"));
//        System.out.println(stringBuffer.toString());//测试函数
        return preparedStatement.executeQuery();
    }

    public int delOStocks(Connection con, OStocks oStocks) throws  Exception{
        StringBuilder stringBuffer =new StringBuilder( "delete from OStocks ");
        if (!ToolUtil.isEmpty(oStocks.getOid())){
            stringBuffer.append("and Oid like '%").append(oStocks.getOid()).append("%'");
        }
        PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString().replaceFirst("and","where"));
//        System.out.println(stringBuffer.toString());//测试函数
        return preparedStatement.executeUpdate();
    }
}


