package dao;

import model.Goods;
import utils.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;

public class Test_Dao {

     static GoodsDao goodsDao = new GoodsDao();

    public static void main(String[] args) throws Exception {
        //getGoods();

    }


//    public static void getGoods() throws Exception {
//        Connection connection = null;
//        connection = DbUtil.getConnection();
//
//        ResultSet resultSet = goodsDao.showGoods(connection);
//        while (resultSet.next()){
//            Goods goods = new Goods();
//            goods.setGid(resultSet.getString("gid"));
//            goods.setGName(resultSet.getString("gname"));
//            goods.setGType(resultSet.getString("gtype"));
//
//            //System.out.println(goods.getGid()+" "+goods.getGName()+" "+goods.getGType());
//        }
//    }
}