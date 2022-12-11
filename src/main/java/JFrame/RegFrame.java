package JFrame;

import dao.UserDao;
import model.User;
import utils.DbUtil;
import utils.ToolUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class RegFrame {
    public static User users;
    private JFrame jFrame;
    private JTextField userName;
    private JTextField userPwd;
    private JTextField userPhone;

    UserDao userDao = new UserDao();

    DbUtil dbUtil = new DbUtil();

    public RegFrame(){
        Font font = new Font("微软雅黑", Font.PLAIN, 16);
        jFrame = new JFrame("用户注册");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(435,370);
        jFrame.setLocationRelativeTo(null);                 //窗口设置中央，对有ImagePanelPanel面板的窗口无效
        jFrame.setLayout(null);                             //布局不受限制
        jFrame.setResizable(false);                         //禁用最大化窗口,无法更改
        jFrame.setVisible(true);                            //可视化

        /* 按钮与对话框的初始化 */
        JLabel label1 = new JLabel("用户名:");         // 用户名
        label1.setFont(font);

        JLabel label2 = new JLabel(" 密码:");         //密码
        label2.setFont(font);

        JLabel label3 = new JLabel("手机号:");        //密码
        label3.setFont(font);

        userName = new JTextField();                        //账号文本框
        userPwd = new JPasswordField();                     //密码文本框
        userPhone = new JTextField();                       //手机号文本框

        JButton register = new JButton("注册");        //注册
        JButton returnLog = new JButton("返回");        //登录

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
        register.addActionListener (this::checkReg);     //lambda替换成方法引用
        returnLog.addActionListener(this::Log);

        /* 聚焦事件 */
        JLabel usernameMes = new JLabel("");
        usernameMes.setFont(new Font("黑体",Font.PLAIN,12));

        JLabel passwordMes = new JLabel("");
        passwordMes.setFont(new Font("黑体",Font.PLAIN,12));

        JLabel phoneMes = new JLabel();
        phoneMes.setFont(new Font("黑体",Font.PLAIN,12));

        userName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = userName.getText();
                if(ToolUtil.isEmpty(text)){
                    usernameMes.setText("用户名不能为空");
                    usernameMes.setForeground(Color.RED);
                }else{
                    usernameMes.setText("√");
                    usernameMes.setForeground(Color.GREEN);
                }
            }
        });

        userPwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String pwd=userPwd.getText();
                if(ToolUtil.isEmpty(pwd)){
                    passwordMes.setText("密码不能为空");
                    passwordMes.setForeground(Color.RED);
                }else{
                    boolean flag=pwd.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
                    if(flag){
                        passwordMes.setText("√");
                        passwordMes.setForeground(Color.GREEN);
                    }else{
                        JOptionPane.showMessageDialog(null, "密码需为6-16位数字和字母的组合");
                    }

                }
            }
        });

        userPhone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                String phone=userPhone.getText();
                if(ToolUtil.isEmpty(phone)){
                    phoneMes.setText("手机号不能为空");
                    phoneMes.setForeground(Color.RED);
                }else{
                    boolean flag=phone.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");
                    if(flag){
                        phoneMes.setText("√");
                        phoneMes.setForeground(Color.GREEN);
                    }else{
                        //JOptionPane.showMessageDialog(null, "请输入正确的手机号格式");
                        phoneMes.setText("手机号格式错误");
                        phoneMes.setForeground(Color.RED);
                    }

                }
            }
        });


        /* 登录界面的布局 */
        label1.setBounds(90,120,75,20);
        label2.setBounds(90,160,75,20);
        label3.setBounds(90,200,75,20);
        usernameMes.setBounds(200,140,150,20);
        passwordMes.setBounds(200,180,150,20);
        phoneMes.setBounds(200,220,150,20);
        userName.setBounds(180,122,150,20);
        userPwd.setBounds(180,162,150,20);
        userPhone.setBounds(180,202,150,20);
        returnLog.setBounds(270,245,60,30);
        register.setBounds(90,245,150,30);
        jFrame.add(label1);
        jFrame.add(userName);
        jFrame.add(label2);
        jFrame.add(userPwd);
        jFrame.add(label3);
        jFrame.add(userPhone);
        jFrame.add(returnLog);
        jFrame.add(register);
        jFrame.add(usernameMes);
        jFrame.add(passwordMes);
        jFrame.add(phoneMes);

    }

    private void Log(ActionEvent e){
        jFrame.setVisible(false);
        new LoginFrame();
    }
    private void checkReg(ActionEvent e){

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater( ()-> new RegFrame());
    }
}
