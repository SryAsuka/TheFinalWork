package dao;

import model.Stocks;
import utils.ToolUtil;

import java.sql.*;

public class StocksDao {//库存

    public StocksDao(){}

    //查看所有货物库存
    public ResultSet show(Connection con,Stocks stocks) throws Exception {
        StringBuilder stringBuffer =new StringBuilder( "select * from stocks ");
        if (!ToolUtil.isEmpty(stocks.getGName())){
            stringBuffer.append("and GName like '%").append(stocks.getGName()).append("%' ");
        }
        if (!ToolUtil.isEmpty(stocks.getWName())){
            stringBuffer.append("and WName like '%").append(stocks.getWName()).append("%' ");
        }
        if (stocks.getSStocks() != null){
            stringBuffer.append("and SStocks like '%").append(stocks.getSStocks()).append("%' ");
        }
        PreparedStatement preparedStatement = con.prepareStatement(stringBuffer.toString().replaceFirst("and","where"));
//        System.out.println(stringBuffer);//测试函数
        return preparedStatement.executeQuery();
    }

}

