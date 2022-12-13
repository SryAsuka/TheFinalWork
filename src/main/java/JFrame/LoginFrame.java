package JFrame;

import dao.UserDao;
import model.User;
import utils.DbUtil;
import utils.ToolUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.Objects;

public class LoginFrame extends JFrame {

    private final JFrame jFrame ;
    private final JTextField userName;
    private final JTextField userPwd;


    UserDao userDao = new UserDao();

    DbUtil dbUtil = new DbUtil();

    public LoginFrame(){

        Font font = new Font("微软雅黑", Font.PLAIN, 16);
        jFrame = new JFrame("用户登录");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(435,320);
        jFrame.setLocationRelativeTo(null);                 //窗口设置中央，对有ImagePanelPanel面板的窗口无效
        jFrame.setLayout(null);                             //布局不受限制
        jFrame.setResizable(false);                         //禁用最大化窗口,无法更改
        jFrame.setVisible(true);                            //可视化


        /* 按钮与对话框的初始化 */
        JLabel label1 = new JLabel("用户名:");          // 用户名
        label1.setFont(font);

        JLabel label2 = new JLabel(" 密码:");          //密码
        label2.setFont(font);

        userName = new JTextField();                        //账号文本框
        userPwd = new JPasswordField();                     //密码文本框

        JButton login = new JButton("登录");           //登录
        JButton register = new JButton("注册");        //注册

//        ButtonGroup buttonGroup = new ButtonGroup();
//        JRadioButton admin = new JRadioButton("最高管理员");
//        JRadioButton user = new JRadioButton("普通管理员");

        /* 登录界面的布局 */
        label1.setBounds(90,120,75,20);
        label2.setBounds(90,160,75,20);
        userName.setBounds(180,122,150,20);
        userPwd.setBounds(180,162,150,20);
        register.setBounds(270,210,60,30);
        login.setBounds(90,210,150,30);
        jFrame.add(label1);
        jFrame.add(userName);
        jFrame.add(label2);
        jFrame.add(userPwd);
        jFrame.add(login);
        jFrame.add(register);

        /* 修改Windows图标 */
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/picture/icon.png")));
        jFrame.setIconImage(imageIcon.getImage());

        /* 添加表头 */
        JLabel WarehouseIcon = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/picture/Warehouse.png"))));
        WarehouseIcon.setBounds(35,30,72,72);
        jFrame.add(WarehouseIcon);

        JLabel WarehouseTitle = new JLabel("仓库管理系统");
        WarehouseTitle.setFont(new Font("隶书",Font.BOLD,40));
        WarehouseTitle.setBounds(120,40,250,45);
        jFrame.add(WarehouseTitle);

        /* 点击事件 */
        register.addActionListener (this::Reg);     //lambda替换成方法引用
        login.addActionListener(this::check);

    }

    private void Reg(ActionEvent e){
        jFrame.setVisible(false);
        new RegFrame();
    }

    private void check(ActionEvent e){
        String UAcc = userName.getText();
        String UPwd = userPwd.getText();
        if (ToolUtil.isEmpty(UAcc) || ToolUtil.isEmpty(UPwd)) {
            JOptionPane.showMessageDialog(null, "用户名和密码不能为空");
            return;
        }
        User user = new User();
        user.setUAcc(UAcc);
        user.setUPwd(UPwd);
        Connection connection;
        try{
            connection = DbUtil.getConnection();
            User loginUser = userDao.login(connection,user);
            if (loginUser == null){
                JOptionPane.showMessageDialog(null,"账号或密码错误","登录失败",JOptionPane.ERROR_MESSAGE);
            }
            assert loginUser != null;
            if (userDao.checkPower(connection,user) == 1){
                jFrame.dispose();
                new AdminFrame();
            }else if (userDao.checkPower(connection,user) == 2){
                jFrame.dispose();
                new UserAdminFrame();
            }

            connection.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater( ()-> new LoginFrame());
    }

}
