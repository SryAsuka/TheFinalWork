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

public class ISFrame {

    static IStocksDao iStocksDao = new IStocksDao();
    static StocksDao stocksDao = new StocksDao();
    static WhDao whDao = new WhDao();
    static GoodsDao goodsDao = new GoodsDao();
    static UserDao userDao =new UserDao();
    static class CheckIS extends JPanel {
        private  DefaultTableModel model1;
        public CheckIS(){
            setLayout(null);
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"入库查询",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10,10,560,80);
            panel.setLayout(null);
            add(panel);

            JComboBox<String> comboBox;
            comboBox = new JComboBox<>();
            comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            comboBox.setBounds(75, 28, 90, 24);
            comboBox.addItem("货物名称");
            comboBox.addItem("仓库名称");
            comboBox.addItem("入库人员");
            comboBox.addItem("入库时间");
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

            String[] title={"入库编号", "货物名称", "仓库名称","入库量","入库管理员","入库时间"};
            String[][] data ={};
            DefaultTableModel model = new DefaultTableModel(data, title);
            JTable table = new JTable(model);
            model1 = (DefaultTableModel) table.getModel();
            show(new IStocks());
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
                        IStocks iStocks = new IStocks();
                        iStocks.setGName(GName);
                        show(iStocks);
                    }if(index == 1){
                        String WName = textField.getText();
                        IStocks iStocks = new IStocks();
                        iStocks.setWName(WName);
                        show(iStocks);
                    }if(index == 2){
                        String UName = textField.getText();
                        IStocks iStocks = new IStocks();
                        iStocks.setUName(UName);
                        show(iStocks);
                    }if(index == 3){
                        String IDate = textField.getText();
                        IStocks iStocks = new IStocks();
                        iStocks.setIDate(IDate);
                        show(iStocks);
                    }
                }
            });
        }
        private void show(IStocks iStocks){
            model1.setRowCount(0);
            Connection connection = null;
            try{
                connection = DbUtil.getConnection();
                ResultSet resultSet = iStocksDao.show(connection,iStocks);
                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("iid"));
                    Data.add(resultSet.getString("gname"));
                    Data.add(resultSet.getString("wname"));
                    Data.add(resultSet.getInt("istocks"));
                    Data.add(resultSet.getString("uname"));
                    Data.add(resultSet.getString("idate"));
                    model1.addRow(Data);
                }
                connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class EditIS extends JPanel {
        private  DefaultTableModel model1;
        public EditIS(){
            setLayout(null);

            /* 信息显示界面 */
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null,"信息显示",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel.setBounds(10, 10, 560, 150);
            panel.setLayout(null);
            add(panel);

            String[] title={"入库编号", "货物名称", "仓库名称","入库量","入库管理员","入库时间"};
            String[][] dates={};
            DefaultTableModel model = new DefaultTableModel(dates, title);
            JTable table = new JTable(model);
            model1 = (DefaultTableModel) table.getModel();
            show(new IStocks());
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(10, 15, 540, 125);
            jScrollPane.setViewportView(table);
            panel.add(jScrollPane);




            /* 添加界面 */
            JPanel panel2 = new JPanel();
            panel2.setBorder(new TitledBorder(null,"入库添加",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel2.setBounds(10, 170, 280, 250);
            panel2.setLayout(null);
            add(panel2);

            JLabel label2 = new JLabel("入库编号:");
            label2.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label2.setBounds(10,20,70,24);

            JTextField addIid = new JTextField();
            addIid.setBounds(80, 22, 70, 24);

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

            JLabel label6 = new JLabel("   入库量：");
            label6.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label6.setBounds(10,140,70,24);

            JTextField addIS = new JTextField();
            addIS.setBounds(80, 142, 70, 24);

            JLabel label1 = new JLabel("入库人员：");
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
            panel2.add(addIid);
            panel2.add(addWname);
            panel2.add(addIS);
            panel2.add(addUname);
            panel2.add(add);
            panel2.add(refresh);

            /* 删除界面 */
            JPanel panel3 = new JPanel();
            panel3.setBorder(new TitledBorder(null,"入库删除",TitledBorder.LEADING,TitledBorder.TOP,null,Color.BLACK));
            panel3.setBounds(290, 170, 280, 250);
            panel3.setLayout(null);
            add(panel3);

            JLabel label5 = new JLabel("需要删除的入库id:");
            label5.setFont(new Font("微软雅黑", Font.BOLD, 14));
            label5.setBounds(20,50,130,24);

            JTextField deleteIid = new JTextField();
            deleteIid.setBounds(145, 52, 70, 24);

            JButton delete = new JButton("删除");
            delete.setFont(new Font("微软雅黑", Font.BOLD, 14));
            delete.setBounds(170,180,70,25);

            panel3.add(deleteIid);
            panel3.add(label5);
            panel3.add(delete);

            //点击获取数据
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int row = table.getSelectedRow();
                    String Iid = (String) table.getValueAt(row,0);
                    deleteIid.setText(Iid);
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

            //添加入库
            add.addActionListener(e -> {
                String Iid = addIid.getText();
                String Gname = addGname.getSelectedItem().toString();
                String Wname = addWname.getSelectedItem().toString();
                String Uname = addUname.getSelectedItem().toString();
                String IS = addIS.getText();
                int is1 = Integer.valueOf(IS);
                if (ToolUtil.isEmpty(Iid) || ToolUtil.isEmpty(IS)){
                    JOptionPane.showMessageDialog(null, "请输入入库编号/出库量");
                    return;
                }

                IStocks iStocks = new IStocks();
                iStocks.setIid(Iid);
                iStocks.setGName(Gname);
                iStocks.setWName(Wname);
                iStocks.setUName(Uname);
                iStocks.setIStocks(is1);


                Connection connection = null;
                try {
                    connection = DbUtil.getConnection();
                    int t = iStocksDao.addIS(connection, iStocks);
                    if (t == 2){
                        JOptionPane.showMessageDialog(null,"入库编号重复","添加失败",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                show(new IStocks());
            });

            //点击删除
            delete.addActionListener(e -> {
                JOptionPane.showConfirmDialog(null, "确定删除？", "确定删除", JOptionPane.YES_NO_OPTION);
                String Iid = deleteIid.getText();
                IStocks iStocks = new IStocks();
                iStocks.setIid(Iid);
                if (ToolUtil.isEmpty(Iid)){
                    JOptionPane.showMessageDialog(null, "请输入需要删除的入库id");
                }
                Connection connection = null;
                try{
                    connection = DbUtil.getConnection();
                    int i = iStocksDao.delIStocks(connection, iStocks);
                    if (i == 1){
                        JOptionPane.showMessageDialog(null,"成功删除","成功删除",JOptionPane.PLAIN_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null,"删除失败","删除失败",JOptionPane.ERROR_MESSAGE);
                    }
                    connection.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                show(new IStocks());
            });
        }

        private void show(IStocks iStocks){
            model1.setRowCount(0);
            Connection connection = null;
            try{
                connection = DbUtil.getConnection();
                ResultSet resultSet = iStocksDao.show(connection,iStocks);
                while (resultSet.next()){
                    Vector<Serializable> Data = new Vector<>();
                    Data.add(resultSet.getString("iid"));
                    Data.add(resultSet.getString("gname"));
                    Data.add(resultSet.getString("wname"));
                    Data.add(resultSet.getInt("istocks"));
                    Data.add(resultSet.getString("uname"));
                    Data.add(resultSet.getString("idate"));
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
