package dao;

import model.User;
import utils.ToolUtil;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class UserDao {
    public UserDao(){}

    public User login(Connection con, User user) throws Exception {
        User resultUser = null;
        String sql = "select * from user where warehouse.user.uacc = ? and warehouse.user.upwd = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUAcc());
        pstmt.setString(2, user.getUPwd());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            resultUser = new User();
            resultUser.setUid(rs.getString("Uid"));
            resultUser.setUAcc(rs.getString("UAcc"));
            resultUser.setUName(rs.getString("Uname"));
            resultUser.setUPhone(rs.getString("UPhone"));
        }
        return resultUser;
    }

    public Integer checkPower(Connection connection, User user) throws Exception {
        String sql = "select warehouse.user.upower from user where warehouse.user.uacc = ? and warehouse.user.upwd = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, user.getUAcc());
        pstmt.setString(2, user.getUPwd());
        ResultSet resultSet = pstmt.executeQuery();
        Integer power = null;
        if (resultSet.next()) {
            power = resultSet.getInt("UPower");
        }
        return power;
    }


    public int addUser(Connection con, User user) throws Exception {
        String sql = "select * from user where warehouse.user.uname = ? ";
        String sql2 = "select * from user where warehouse.user.uid = ? ";
        String sql3 = "select * from user where warehouse.user.uacc = ? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        PreparedStatement pstmt1 = con.prepareStatement(sql2);
        PreparedStatement pstmt3 = con.prepareStatement(sql3);
        pstmt.setString(1, user.getUName());
        pstmt1.setString(1, user.getUid());
        pstmt3.setString(1, user.getUAcc());
        ResultSet rs = pstmt.executeQuery();
        ResultSet rs1 = pstmt1.executeQuery();
        ResultSet rs2 = pstmt3.executeQuery();
        if (rs.next()) {
            return 2;
        } else if(rs1.next()){
            return 3;
        }else if(rs2.next()){
            return 4;
        } else {
            sql = "insert into user (uid,uacc,uname,upwd,uphone,upower) values (?,?,?,?,?,?)";
            try (PreparedStatement pstmt2 = con.prepareStatement(sql)) {
                pstmt2.setString(1, user.getUid());
                pstmt2.setString(2, user.getUAcc());
                pstmt2.setString(3, user.getUName());
                pstmt2.setString(4, user.getUPwd());
                pstmt2.setString(5, user.getUPhone());
                pstmt2.setInt(6, user.getUPower());
                return pstmt2.executeUpdate();
            }
        }
    }

    public int updateUser(Connection con, User user)throws Exception{
        String sql="update user set UAcc=?,Upwd = ?,UPhone=? where UName=? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,user.getUAcc());
        pstmt.setString(2,user.getUPwd());
        pstmt.setString(3,user.getUPhone());
        pstmt.setString(4,user.getUName());
        return pstmt.executeUpdate();
    }


    public ResultSet findUser(Connection con,User user) throws Exception {
        StringBuilder sb = new StringBuilder("select * from user ");
        if (!ToolUtil.isEmpty(user.getUName())){
            sb.append("where UName like '%").append(user.getUName()).append("%'");
        }
        if (!ToolUtil.isEmpty(user.getUPhone())){
            sb.append("where Uphone like '%").append(user.getUPhone()).append("%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int delUser(Connection con,String UName) throws  Exception{
        String sql="delete from user where UName=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,UName);
        return pstmt.executeUpdate();
    }
}