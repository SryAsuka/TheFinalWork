package JFrame;

import dao.GoodsDao;
import model.Goods;
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

public class GoodsFrame {

    static GoodsDao goodsDao = new GoodsDao();

    static class CheckGoods extends JPanel {
        private  DefaultTableModel model1;
        public CheckGoods(){
            setLayout(null);
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"货物查询",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10,10,560,80);
            panel.setLayout(null);
            add(panel);

            JComboBox<String> comboBox;
            comboBox = new JComboBox<>();
            comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            comboBox.setBounds(75, 28, 90, 24);
            comboBox.addItem("货物名称");
            comboBox.addItem("货物类型");
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

            String[] title={"货物编号", "货物名称", "货物类别" };
            String[][] dates={};
            DefaultTableModel model = new DefaultTableModel(dates, title);
            JTable table = new JTable(model);
            model1 = (DefaultTableModel) table.getModel();
            show(new Goods());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(20, 22, 520, 260);
            jScrollPane.setViewportView(table);
            panel1.add(jScrollPane);

            /* 事件 */
            checkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int index = comboBox.getSelectedIndex();
                    if(index==0){
                        String GName = textField.getText();
                        Goods goods = new Goods();
                        goods.setGName(GName);
                        show(goods);
                    }else{
                        String Gtype = textField.getText();
                        Goods goods = new Goods();
                        goods.setGType(Gtype);
                        show(goods);
                    }
                }
            });
        }
        //放置数据
        private void show(Goods goods){
            model1.setRowCount(0);
            Connection con = null;
            try {
                con = DbUtil.getConnection();
                ResultSet resultSet = goodsDao.findGoods(con,goods);

                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("gid"));
                    Data.add(resultSet.getString("gname"));
                    Data.add(resultSet.getString("gtype"));
                    model1.addRow(Data);
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    static class EditGoods extends JPanel{
        private  DefaultTableModel model1;

        public EditGoods() {
            setLayout(null);

            /* 信息显示界面 */
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"信息显示",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10, 10, 560, 150);
            panel.setLayout(null);
            add(panel);

            String[] title={"货物编号", "货物名称", "货物类别" };
            String[][] dates={};
            DefaultTableModel model = new DefaultTableModel(dates, title);
            JTable table1 = new JTable(model);
            model1 = (DefaultTableModel) table1.getModel();
            show(new Goods());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(10, 15, 540, 125);
            jScrollPane.setViewportView(table1);
            panel.add(jScrollPane);


            /* 修改界面 */
            JPanel panel1 = new JPanel();
            panel1.setBorder(new TitledBorder(null,"货物修改",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel1.setBounds(10, 170, 270, 250);
            panel1.setLayout(null);
            add(panel1);

            JLabel label = new JLabel("所要修改的货物名:");
            label.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label.setBounds(25,48,130,24);

            JTextField modGname = new JTextField();
            modGname.setBounds(150, 50, 90, 24);

            JLabel label1 = new JLabel("货物类型：");
            label1.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label1.setBounds(25,108,80,24);

            JTextField modGtype = new JTextField();
            modGtype.setBounds(100, 110, 90, 24);

            JButton mod = new JButton("修改");
            mod.setFont(new Font("微软雅黑", Font.BOLD, 14));
            mod.setBounds(150,180,90,30);

            panel1.add(modGname);
            panel1.add(modGtype);
            panel1.add(label);
            panel1.add(label1);
            panel1.add(mod);


            /* 添加界面 */
            JPanel panel2 = new JPanel();
            panel2.setBorder(new TitledBorder(null,"货物添加",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel2.setBounds(290, 170, 280, 150);
            panel2.setLayout(null);
            add(panel2);

            JLabel label2 = new JLabel("货物编号:");
            label2.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label2.setBounds(10,20,70,24);

            JTextField addGid = new JTextField();
            addGid.setBounds(80, 22, 70, 24);

            JLabel label3 = new JLabel("货物名称:");
            label3.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label3.setBounds(10,60,70,24);

            JTextField addGname = new JTextField();
            addGname.setBounds(80, 62, 70, 24);

            JLabel label4 = new JLabel("货物类型：");
            label4.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label4.setBounds(10,100,70,24);

            JTextField addGtype = new JTextField();
            addGtype.setBounds(80, 102, 70, 24);

            JButton add = new JButton("添加");
            add.setFont(new Font("微软雅黑", Font.BOLD, 14));
            add.setBounds(180,60,70,30);

            panel2.add(label2);
            panel2.add(addGid);
            panel2.add(label3);
            panel2.add(addGname);
            panel2.add(label4);
            panel2.add(addGtype);
            panel2.add(add);

            /* 删除界面 */
            JPanel panel3 = new JPanel();
            panel3.setBorder(new TitledBorder(null,"货物删除",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel3.setBounds(290, 320, 280, 100);
            panel3.setLayout(null);
            add(panel3);

            JLabel label5 = new JLabel("需要删除的货物名:");
            label5.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label5.setBounds(20,20,130,24);

            JTextField deleteGname = new JTextField();
            deleteGname.setBounds(145, 22, 70, 24);

            JButton delete = new JButton("删除");
            delete.setFont(new Font("微软雅黑", Font.BOLD, 14));
            delete.setBounds(170,60,70,25);

            panel3.add(deleteGname);
            panel3.add(label5);
            panel3.add(delete);

            /* 事件 */

            //点击表格获取数据
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int row = table1.getSelectedRow();
                    String Gname = (String) table1.getValueAt(row,1);
                    Goods goods = new Goods();
                    goods.setGName(Gname);
                    Connection connection = null;
                    try{
                        connection = DbUtil.getConnection();
                        ResultSet resultSet = goodsDao.findGoods(connection,goods);
                        if (resultSet.next()){
                            modGname.setText(resultSet.getString("Gname"));
                            deleteGname.setText(resultSet.getString("Gname"));
                            modGtype.setText(resultSet.getString("GType"));
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
                String Gtype = modGtype.getText();
                String Gname = modGname.getText();
                if (ToolUtil.isEmpty(Gname)){
                    JOptionPane.showMessageDialog(null, "请输入要修改的货物名");
                }
                if (ToolUtil.isEmpty(Gtype)){
                    JOptionPane.showMessageDialog(null, "请输入修改后的货物类型");
                }
                Goods goods = new Goods();
                goods.setGType(Gtype);
                goods.setGName(Gname);
                Connection connection = null;
                try {
                    connection = DbUtil.getConnection();
                    int i = goodsDao.updateGoods(connection,goods);
                    if (i == 1) {
                        JOptionPane.showMessageDialog(null, "修改成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败(货物名无法修改)");
                    }
                    connection.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                show(new Goods());
            });

            //点击添加
            add.addActionListener(e -> {
                String Gid = addGid.getText();
                String Gname = addGname.getText();
                String Gtype = addGtype.getText();
                if (ToolUtil.isEmpty(Gtype) || ToolUtil.isEmpty(Gid) || ToolUtil.isEmpty(Gname)){
                    JOptionPane.showMessageDialog(null, "请输入需要添加的完整内容");
                }
                Goods goods = new Goods();
                goods.setGid(Gid);
                goods.setGName(Gname);
                goods.setGType(Gtype);
                Connection connection = null;
                try{
                    connection = DbUtil.getConnection();
                    int i = goodsDao.addGoods(connection,goods);
                    if (i==1){
                        JOptionPane.showMessageDialog(null,"成功添加"+Gname,"成功添加",JOptionPane.PLAIN_MESSAGE);
                    }else if (i == 2){
                        JOptionPane.showMessageDialog(null,"已经存在该货物","添加失败",JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null,"货物编号重复","添加失败",JOptionPane.ERROR_MESSAGE);
                    }
                    connection.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                show(new Goods());
            });

            //点击删除
            delete.addActionListener(e -> {
                JOptionPane.showConfirmDialog(null, "确定删除？", "确定删除", JOptionPane.YES_NO_OPTION);
                String Gname = deleteGname.getText();
                if (ToolUtil.isEmpty(Gname)){
                    JOptionPane.showMessageDialog(null, "请输入需要删除的货物名");
                }
                Connection connection = null;
                try{
                    connection = DbUtil.getConnection();
                    int i = goodsDao.delGoods(connection,Gname);
                    if (i == 1){
                        JOptionPane.showMessageDialog(null,"成功删除"+Gname,"成功删除",JOptionPane.PLAIN_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null,"删除失败"+Gname,"删除失败",JOptionPane.ERROR_MESSAGE);
                    }
                    connection.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                show(new Goods());
            });
        }


        private void show(Goods goods){
            model1.setRowCount(0);
            Connection con = null;
            try {
                con = DbUtil.getConnection();
                ResultSet resultSet = goodsDao.findGoods(con,goods);

                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("gid"));
                    Data.add(resultSet.getString("gname"));
                    Data.add(resultSet.getString("gtype"));
                    model1.addRow(Data);
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
