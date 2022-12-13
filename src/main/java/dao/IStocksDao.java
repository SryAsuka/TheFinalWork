package dao;
import model.IStocks;
import utils.ToolUtil;

import java.sql.*;




public class IStocksDao {//入库

    public IStocksDao(){}

    public int addIS(Connection con, IStocks iStocks)throws Exception {//出库后修改信息
        String sql2 = "select * from istocks where iid = ? ";
        PreparedStatement pstmt1 = con.prepareStatement(sql2);
        pstmt1.setString(1, iStocks.getIid());
        ResultSet rs1 = pstmt1.executeQuery();
        if (rs1.next()) {
            return 2;
        } else {
            String sql = "insert into istocks(iid, gname, wname, istocks, uname, idate)  values (?,?,?,?,?,now())";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, iStocks.getIid());
            preparedStatement.setString(2, iStocks.getGName());
            preparedStatement.setString(3, iStocks.getWName());
            preparedStatement.setInt(4, iStocks.getIStocks());
            preparedStatement.setString(5, iStocks.getUName());
            return preparedStatement.executeUpdate();
        }
    }
    public ResultSet show(Connection con,IStocks iStocks) throws Exception {
        StringBuilder stringBuffer =new StringBuilder( "select * from IStocks ");
        if (!ToolUtil.isEmpty(iStocks.getGName())){
            stringBuffer.append("and GName like '%").append(iStocks.getGName()).append("%'");
        }
        if (!ToolUtil.isEmpty(iStocks.getWName())){
            stringBuffer.append("and WName like '%").append(iStocks.getWName()).append("%'");
        }
        if (!ToolUtil.isEmpty(iStocks.getUName())){
            stringBuffer.append("and UName like '%").append(iStocks.getUName()).append("%'");
        }
        if (!ToolUtil.isEmpty(iStocks.getIDate())){
            stringBuffer.append("and IDate like '%").append(iStocks.getIDate()).append("%'");
        }
//        System.out.println(stringBuffer.toString());//测试函数
        PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString().replaceFirst("and","where"));
        return preparedStatement.executeQuery();
    }

    public int delIStocks(Connection con, IStocks iStocks) throws  Exception{
        StringBuilder stringBuffer =new StringBuilder( "delete from IStocks ");
        if (!ToolUtil.isEmpty(iStocks.getIid())){
            stringBuffer.append("and iid like '%").append(iStocks.getIid()).append("%'");
        }
//        System.out.println(stringBuffer.toString());//测试函数
        PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString().replaceFirst("and","where"));
        return preparedStatement.executeUpdate();
    }
}

