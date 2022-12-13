package JFrame;

import dao.*;
import model.*;
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

public class OSFrame {

    static OStocksDao oStocksDao = new OStocksDao();
    static StocksDao stocksDao = new StocksDao();
    static WhDao whDao = new WhDao();
    static GoodsDao goodsDao = new GoodsDao();
    static UserDao userDao =new UserDao();
    static class CheckOS extends JPanel {
        private  DefaultTableModel model1;
        public CheckOS(){
            setLayout(null);
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"出库查询",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10,10,560,80);
            panel.setLayout(null);
            add(panel);

            JComboBox<String> comboBox;
            comboBox = new JComboBox<>();
            comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            comboBox.setBounds(75, 28, 90, 24);
            comboBox.addItem("货物名称");
            comboBox.addItem("仓库名称");
            comboBox.addItem("出库人员");
            comboBox.addItem("出库时间");
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

            String[] title={"出库编号", "货物名称", "仓库名称","出库量","出库管理员","出库时间"};
            String[][] data ={};
            DefaultTableModel model = new DefaultTableModel(data, title);
            JTable table = new JTable(model);
            model1 = (DefaultTableModel) table.getModel();
            show(new OStocks());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(20, 22, 520, 260);
            jScrollPane.setViewportView(table);
            panel1.add(jScrollPane);

            /* 事件 */
            checkButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int index = comboBox.getSelectedIndex();
                    if(index==0) {
                        String GName = textField.getText();
                        OStocks oStocks = new OStocks();
                        oStocks.setGName(GName);
                        show(oStocks);
                    }if(index==1){
                        String WName = textField.getText();
                        OStocks oStocks = new OStocks();
                        oStocks.setWName(WName);
                        show(oStocks);
                    }if(index==2){
                        String UName = textField.getText();
                        OStocks oStocks = new OStocks();
                        oStocks.setUName(UName);
                        show(oStocks);
                    }if (index == 3){
                        String ODate = textField.getText();
                        OStocks oStocks = new OStocks();
                        oStocks.setODate(ODate);
                        show(oStocks);
                    }
                }
            });
        }
        private void show(OStocks oStocks){
            model1.setRowCount(0);
            Connection connection = null;
            try{
                connection = DbUtil.getConnection();
                ResultSet resultSet = oStocksDao.show(connection,oStocks);
                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("oid"));
                    Data.add(resultSet.getString("gname"));
                    Data.add(resultSet.getString("wname"));
                    Data.add(resultSet.getInt("ostocks"));
                    Data.add(resultSet.getString("uname"));
                    Data.add(resultSet.getString("odate"));
                    model1.addRow(Data);
                }
                connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class EditOS extends JPanel {
        private  DefaultTableModel model1;
        public EditOS(){
            setLayout(null);
            /* 信息显示界面 */
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"信息显示",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10, 10, 560, 150);
            panel.setLayout(null);
            add(panel);

            String[] title={"出库编号", "货物名称", "仓库名称","出库量","出库管理员","出库时间"};
            String[][] data={};
            DefaultTableModel model = new DefaultTableModel(data, title);
            JTable table = new JTable(model);
            model1 = (DefaultTableModel) table.getModel();
            show(new OStocks());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(10, 15, 540, 125);
            jScrollPane.setViewportView(table);
            panel.add(jScrollPane);



            /* 添加界面 */
            JPanel panel2 = new JPanel();
            panel2.setBorder(new TitledBorder(null,"出库添加",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel2.setBounds(10, 170, 280, 250);
            panel2.setLayout(null);
            add(panel2);

            JLabel label2 = new JLabel("出库编号:");
            label2.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label2.setBounds(10,20,70,24);

            JTextField addOid = new JTextField();
            addOid.setBounds(80, 22, 70, 24);

            JLabel label3 = new JLabel("货物名称:");
            label3.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label3.setBounds(10,60,70,24);

            JComboBox addGname = new JComboBox();
            addGname.setBounds(80, 62, 70, 24);

            JLabel label4 = new JLabel("仓库名称：");
            label4.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label4.setBounds(10,100,70,24);

            JComboBox addWname = new JComboBox();
            addWname.setBounds(80, 102, 70, 24);

            JLabel label6 = new JLabel("   出库量：");
            label6.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label6.setBounds(10,140,70,24);

            JTextField addOS = new JTextField();
            addOS.setBounds(80, 142, 70, 24);

            JLabel label1 = new JLabel("出库人员：");
            label1.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label1.setBounds(10,180,70,24);

            JComboBox addUname = new JComboBox();
            addUname.setBounds(80, 182, 70, 24);

            JButton add = new JButton("添加");
            add.setFont(new Font("微软雅黑", Font.BOLD, 14));
            add.setBounds(180,200,70,30);

            JButton refresh = new JButton("刷新");
            refresh.setFont(new Font("微软雅黑", Font.BOLD, 14));
            refresh.setBounds(180,150,70,30);


            panel2.add(label1);
            panel2.add(label2);
            panel2.add(label3);
            panel2.add(label6);
            panel2.add(label4);
            panel2.add(addGname);
            panel2.add(addOid);
            panel2.add(addWname);
            panel2.add(addOS);
            panel2.add(addUname);
            panel2.add(add);
            panel2.add(refresh);

            /* 删除界面 */
            JPanel panel3 = new JPanel();
            panel3.setBorder(new TitledBorder(null,"出库删除",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel3.setBounds(290, 170, 280, 250);
            panel3.setLayout(null);
            add(panel3);

            JLabel label5 = new JLabel("需要删除的出库id:");
            label5.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label5.setBounds(20,50,130,24);

            JTextField deleteOid = new JTextField();
            deleteOid.setBounds(145, 52, 70, 24);




            JButton delete = new JButton("删除");
            delete.setFont(new Font("微软雅黑", Font.BOLD, 14));
            delete.setBounds(170,180,70,25);


            panel3.add(deleteOid);

            panel3.add(label5);
            panel3.add(delete);

            //点击获取数据
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int row = table.getSelectedRow();
                    String Oid = (String) table.getValueAt(row,0);
                    deleteOid.setText(Oid);
                }
            });

            //获取出库信息
            getData(new Stocks(),new User(),new Goods(),new Wh(),addGname,addWname,addUname);

            //刷新信息
            refresh.addActionListener(e -> {
                addGname.removeAllItems();
                addWname.removeAllItems();
                addUname.removeAllItems();
                getData(new Stocks(),new User(),new Goods(),new Wh(),addGname,addWname,addUname);
            });

            //添加出库
            add.addActionListener(e -> {
                String Oid = addOid.getText();
                String Gname = addGname.getSelectedItem().toString();
                String Wname = addWname.getSelectedItem().toString();
                String Uname = addUname.getSelectedItem().toString();
                String OS = addOS.getText();
                int os1 = 0;
                if (ToolUtil.isEmpty(Oid) || ToolUtil.isEmpty(OS)){
                    JOptionPane.showMessageDialog(null, "请输入出库编号/出库量");
                    return;
                }else
                     os1 = Integer.valueOf(OS);
                OStocks oStocks = new OStocks();
                oStocks.setOid(Oid);
                oStocks.setGName(Gname);
                oStocks.setWName(Wname);
                oStocks.setUName(Uname);
                oStocks.setOStocks(os1);

                //检查是否符合出库要求
                Stocks stocks = new Stocks();
                stocks.setGName(Gname);
                stocks.setWName(Wname);

                Connection connection = null;
                try {
                    connection = DbUtil.getConnection();
                    ResultSet resultSet = stocksDao.show(connection, stocks);
                    int SStocks = 0;
                    int i = 0;
                    while (resultSet.next()) {
                        SStocks = resultSet.getInt("sstocks");
                        i++;
                    }
                    if (i == 0) {
                        JOptionPane.showMessageDialog(null, "未查到" + Wname + "的" + Gname, "出库失败", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (SStocks < os1){
                        JOptionPane.showMessageDialog(null,  Wname + "的" + Gname+"库存不足", "出库失败", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int t = oStocksDao.addOS(connection,oStocks);
                    if (t == 2){
                        JOptionPane.showMessageDialog(null,"出库编号重复","添加失败",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                show(new OStocks());
            });

            //点击删除
            delete.addActionListener(e -> {
                JOptionPane.showConfirmDialog(null, "确定删除？", "确定删除", JOptionPane.YES_NO_OPTION);
                String Oid = deleteOid.getText();
                OStocks oStocks = new OStocks();
                oStocks.setOid(Oid);
                if (ToolUtil.isEmpty(Oid)){
                    JOptionPane.showMessageDialog(null, "请输入需要删除的货物名/仓库名");
                }
                Connection connection = null;
                try{
                    connection = DbUtil.getConnection();
                    int i = oStocksDao.delOStocks(connection,oStocks);
                    if (i == 1){
                        JOptionPane.showMessageDialog(null,"成功删除","成功删除",JOptionPane.PLAIN_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null,"删除失败","删除失败",JOptionPane.ERROR_MESSAGE);
                    }
                    connection.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                show(new OStocks());
            });
        }

        //放置数据
        private void show(OStocks oStocks){
            model1.setRowCount(0);
            Connection connection = null;
            try{
                connection = DbUtil.getConnection();
                ResultSet resultSet = oStocksDao.show(connection,oStocks);
                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("oid"));
                    Data.add(resultSet.getString("gname"));
                    Data.add(resultSet.getString("wname"));
                    Data.add(resultSet.getInt("ostocks"));
                    Data.add(resultSet.getString("uname"));
                    Data.add(resultSet.getString("odate"));
                    model1.addRow(Data);
                }
                connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private  void getData(Stocks stocks, User user, Goods goods, Wh wh, JComboBox addGname, JComboBox addWname, JComboBox addUname){
            Connection connection =null;
            try{
               connection = DbUtil.getConnection();
               ResultSet resultSet =  goodsDao.findGoods(connection,goods);
               ResultSet resultSet1 = userDao.findUser(connection,user);
               ResultSet resultSet2 = whDao.findWh(connection,wh);
               while (resultSet.next())
                   addGname.addItem(resultSet.getString("Gname"));
               while (resultSet2.next())
                   addWname.addItem(resultSet2.getString("Wname"));
               while (resultSet1.next())
                   addUname.addItem(resultSet1.getString("uname"));
               connection.close();
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }





    }


}
