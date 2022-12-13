package JFrame;

import dao.UserDao;
import model.User;
import utils.DbUtil;
import utils.ToolUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class UserFrame {

    static UserDao userDao = new UserDao();
    static class CheckUser extends JPanel {
        private  DefaultTableModel model1;
        public CheckUser() {
            setLayout(null);
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"人员查询",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10,10,560,80);
            panel.setLayout(null);
            add(panel);

            JComboBox<String> comboBox;
            comboBox = new JComboBox<>();
            comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            comboBox.setBounds(75, 28, 90, 24);
            comboBox.addItem("人员姓名");
            comboBox.addItem("人员电话");
            panel.add(comboBox);

            JTextField textField = new JTextField();
            textField.setBounds(185, 28, 146, 24);
            panel.add(textField);

            JButton checkButton = new JButton("查询");
            checkButton.setBounds(390, 28, 81, 25);
            panel.add(checkButton);

            JPanel panel1 = new JPanel();
            panel1.setBorder(new TitledBorder(null,"查询结果",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel1.setBounds(10, 105, 560, 300);
            panel1.setLayout(null);
            add(panel1);

            String[] title={"人员编号","账号","密码","姓名","电话","等级"};
            String[][] data ={};
            DefaultTableModel model = new DefaultTableModel(data, title);
            JTable table = new JTable(model);
            model1 = (DefaultTableModel) table.getModel();
            show(new User());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(20, 22, 520, 260);
            jScrollPane.setViewportView(table);
            panel1.add(jScrollPane);

            /* 事件 */
            checkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int index = comboBox.getSelectedIndex();
                    if(index==0){
                        String Uname = textField.getText();
                        User user = new User();
                        user.setUName(Uname);
                        show(user);
                    }else{
                        String Uphone = textField.getText();
                        User user = new User();
                        user.setUPhone(Uphone);
                        show(user);
                    }
                }
            });
        }
        //放置数据
        private void show(User user){
            model1.setRowCount(0);
            Connection connection = null;
            try{
                connection = DbUtil.getConnection();
                ResultSet resultSet = userDao.findUser(connection,user);
                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("uid"));
                    Data.add(resultSet.getString("uacc"));
                    Data.add(resultSet.getString("upwd"));
                    Data.add(resultSet.getString("uname"));
                    Data.add(resultSet.getString("uphone"));
                    Data.add(resultSet.getInt("upower"));
                    model1.addRow(Data);
                }
                connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    static class EditUser extends JPanel{
        private  DefaultTableModel model1;
        public EditUser(){
            setLayout(null);

            /* 信息显示界面 */
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"信息显示",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10, 10, 560, 150);
            panel.setLayout(null);
            add(panel);

            String[] title={"人员编号","账号","密码","姓名","电话","等级"};
            String[][] data ={};
            DefaultTableModel model = new DefaultTableModel(data, title);
            JTable table = new JTable(model);
            model1 = (DefaultTableModel) table.getModel();
            show(new User());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(10, 15, 540, 125);
            jScrollPane.setViewportView(table);
            panel.add(jScrollPane);


            /* 修改界面 */
            JPanel panel1 = new JPanel();
            panel1.setBorder(new TitledBorder(null,"人员修改",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel1.setBounds(10, 170, 270, 200);
            panel1.setLayout(null);
            add(panel1);

            JLabel label = new JLabel("所要修改的人员名:");
            label.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label.setBounds(25,28,130,24);

            JTextField modUname = new JTextField();
            modUname.setBounds(150, 30, 90, 24);

            JLabel label1 = new JLabel("账号：");
            label1.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label1.setBounds(10,68,80,24);

            JTextField modUacc = new JTextField();
            modUacc.setBounds(45, 70, 85, 24);

            JLabel label6 = new JLabel("密码：");
            label6.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label6.setBounds(140,68,80,24);

            JTextField modUpwd = new JTextField();
            modUpwd.setBounds(175, 70, 85, 24);

            JLabel label7 = new JLabel("电话：");
            label7.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label7.setBounds(10,108,80,24);

            JTextField modUphone = new JTextField();
            modUphone.setBounds(45, 110, 85, 24);

            JButton mod = new JButton("修改");
            mod.setFont(new Font("微软雅黑", Font.BOLD, 14));
            mod.setBounds(150,140,90,30);

            panel1.add(modUname);
            panel1.add(modUacc);
            panel1.add(modUpwd);
            panel1.add(modUphone);
            panel1.add(modUphone);
            panel1.add(label);
            panel1.add(label1);
            panel1.add(label6);
            panel1.add(label7);
            panel1.add(mod);


            /* 添加界面 */
            JPanel panel2 = new JPanel();
            panel2.setBorder(new TitledBorder(null,"人员添加",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel2.setBounds(290, 170, 280, 200);
            panel2.setLayout(null);
            add(panel2);

            JLabel label2 = new JLabel("人员编号:");
            label2.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label2.setBounds(10,20,70,24);

            JTextField addUid = new JTextField();
            addUid.setBounds(70, 22, 70, 24);

            JLabel label8 = new JLabel("人员姓名:");
            label8.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label8.setBounds(140,20,70,24);

            JTextField addUname = new JTextField();
            addUname.setBounds(200, 22, 70, 24);

            JLabel label3 = new JLabel("人员账号:");
            label3.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label3.setBounds(10,60,70,24);

            JTextField addUacc = new JTextField();
            addUacc.setBounds(70, 62, 70, 24);

            JLabel label9 = new JLabel("账号密码:");
            label9.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label9.setBounds(140,60,70,24);

            JTextField addUpwd = new JTextField();
            addUpwd.setBounds(200, 62, 70, 24);

            JLabel label4 = new JLabel("人员电话：");
            label4.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label4.setBounds(10,100,70,24);

            JTextField addUphone = new JTextField();
            addUphone.setBounds(70, 102, 70, 24);

            JLabel label10 = new JLabel("人员等级：");
            label10.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label10.setBounds(140,100,70,24);

            JComboBox<String> addUpower = new JComboBox<>();
            addUpower.addItem("最高管理员");
            addUpower.addItem("普通管理员");
            addUpower.setBounds(200, 102, 70, 24);

            JButton add = new JButton("添加");
            add.setFont(new Font("微软雅黑", Font.BOLD, 14));
            add.setBounds(120,145,70,30);

            panel2.add(label2);
            panel2.add(label3);
            panel2.add(label4);
            panel2.add(label8);
            panel2.add(label9);
            panel2.add(label10);
            panel2.add(addUid);
            panel2.add(addUacc);
            panel2.add(addUpwd);
            panel2.add(addUname);
            panel2.add(addUpower);
            panel2.add(addUacc);
            panel2.add(addUphone);
            panel2.add(add);

            /* 删除界面 */
            JPanel panel3 = new JPanel();
            panel3.setBorder(new TitledBorder(null,"人员删除",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel3.setBounds(10, 370, 560, 60);
            panel3.setLayout(null);
            add(panel3);

            JLabel label5 = new JLabel("需要删除的人员名:");
            label5.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label5.setBounds(100,20,130,24);

            JTextField deleteUname = new JTextField();
            deleteUname.setBounds(220, 22, 70, 24);

            JButton delete = new JButton("删除");
            delete.setFont(new Font("微软雅黑", Font.BOLD, 14));
            delete.setBounds(370,20,70,25);

            panel3.add(deleteUname);
            panel3.add(label5);
            panel3.add(delete);

            /* 事件 */

            //点击表格获取数据
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int row = table.getSelectedRow();
                    String Uname = (String) table.getValueAt(row,3);
                    User user = new User();
                    user.setUName(Uname);
                    Connection connection = null;
                    try{
                        connection = DbUtil.getConnection();
                        ResultSet resultSet = userDao.findUser(connection,user);
                        if (resultSet.next()){
                            modUname.setText(resultSet.getString("Uname"));
                            deleteUname.setText(resultSet.getString("Uname"));
                            modUacc.setText(resultSet.getString("Uacc"));
                            modUpwd.setText(resultSet.getString("Upwd"));
                            modUphone.setText(resultSet.getString("Uphone"));
                        }
                        connection.close();
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
            });

            //点击修改
            mod.addActionListener(e -> {
                JOptionPane.showConfirmDialog(null, "确定修改？", "确定修改", JOptionPane.YES_NO_OPTION);
                String Uname = modUname.getText();
                String Uacc = modUacc.getText();
                String Upwd = modUpwd.getText();
                String Uphone = modUphone.getText();
                if (ToolUtil.isEmpty(Uname)){
                    JOptionPane.showMessageDialog(null, "请输入要修改的货物名");
                }
                if (ToolUtil.isEmpty(Uacc)){
                    JOptionPane.showMessageDialog(null, "请输入修改后的货物类型");
                }
                if (ToolUtil.isEmpty(Uacc)){
                    JOptionPane.showMessageDialog(null, "请输入修改后的货物类型");
                }
                User user = new User();
                user.setUName(Uname);
                user.setUAcc(Uacc);
                user.setUPwd(Upwd);
                user.setUPhone(Uphone);
                Connection connection = null;
                try {
                    connection = DbUtil.getConnection();
                    int i = userDao.updateUser(connection,user);
                    if (i == 1) {
                        JOptionPane.showMessageDialog(null, "修改成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败(人员姓名无法修改)");
                    }
                    connection.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                show(new User());
            });

            //点击添加
            add.addActionListener(e -> {
                String Uid = addUid.getText();
                String Uname = addUname.getText();
                String Uacc = addUacc.getText();
                String Upwd = addUpwd.getText();
                String Uphone = addUphone.getText();
                int index1 = addUpower.getSelectedIndex();

                if (ToolUtil.isEmpty(Uid) || ToolUtil.isEmpty(Uname) || ToolUtil.isEmpty(Uacc) || ToolUtil.isEmpty(Upwd) ||
                        ToolUtil.isEmpty(Uphone)){
                    JOptionPane.showMessageDialog(null, "请输入需要添加的完整内容");
                }
                User user = new User();
                user.setUid(Uid);
                user.setUAcc(Uacc);
                user.setUPwd(Upwd);
                user.setUName(Uname);
                user.setUPhone(Uphone);
                if (index1 == 0){
                    user.setUPower(1);
                }else
                    user.setUPower(2);
                Connection connection = null;
                try{
                    connection = DbUtil.getConnection();
                    int i = userDao.addUser(connection,user);
                    if (i==1){
                        JOptionPane.showMessageDialog(null,"成功添加"+Uname,"成功添加",JOptionPane.PLAIN_MESSAGE);
                    }else if (i == 2){
                        JOptionPane.showMessageDialog(null,"已经存在该人员","添加失败",JOptionPane.ERROR_MESSAGE);
                    }else if (i == 4) {
                        JOptionPane.showMessageDialog(null, "已存在该账号", "添加失败", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"人员编号重复","添加失败",JOptionPane.ERROR_MESSAGE);
                    }
                    connection.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                show(new User());
            });
            //点击删除
            delete.addActionListener(e -> {
                JOptionPane.showConfirmDialog(null, "确定删除？", "确定删除", JOptionPane.YES_NO_OPTION);
                String Uname = deleteUname.getText();
                if (ToolUtil.isEmpty(Uname)){
                    JOptionPane.showMessageDialog(null, "请输入需要删除的货物名");
                }
                Connection connection = null;
                try{
                    connection = DbUtil.getConnection();
                    int i = userDao.delUser(connection,Uname);
                    if (i == 1){
                        JOptionPane.showMessageDialog(null,"成功删除"+Uname,"成功删除",JOptionPane.PLAIN_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null,"删除失败"+Uname,"删除失败",JOptionPane.ERROR_MESSAGE);
                    }
                    connection.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                show(new User());
            });

        }

        private void show(User user){
            model1.setRowCount(0);
            Connection connection = null;
            try{
                connection = DbUtil.getConnection();
                ResultSet resultSet = userDao.findUser(connection,user);
                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("uid"));
                    Data.add(resultSet.getString("uacc"));
                    Data.add(resultSet.getString("upwd"));
                    Data.add(resultSet.getString("uname"));
                    Data.add(resultSet.getString("uphone"));
                    Data.add(resultSet.getInt("upower"));
                    model1.addRow(Data);
                }
                connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    //放置数据

}
