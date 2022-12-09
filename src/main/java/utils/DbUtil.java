package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {

    private static DruidDataSource dataSource = null;

    public static void init() throws Exception{
        Properties properties = new Properties();

        InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(in);
        dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        assert in != null;
        in.close();
    }

    public static Connection getConnection() throws Exception{
        if(null == dataSource)
        {
            init();
        }
        return dataSource.getConnection();
    }

//    public static void main(String[] args) throws Exception {
//        Connection connection = null;
//
//        connection = DbUtil.getConnection();
//
//        if (connection != null){
//            System.out.println("Database connect successfully");
//        }
//
//        assert connection != null;
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from goods");
//
//        while (resultSet.next()){
//            System.out.println("1"+resultSet.getString("gid"));
//            System.out.println("1"+resultSet.getString("gname"));
//            System.out.println("1"+resultSet.getString("gtype"));
//        }
//
//
//        statement.close();
//        resultSet.close();
//        connection.close();
//    }
}


